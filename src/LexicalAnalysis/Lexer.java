package LexicalAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

enum Token {
    IF, FOR, WHILE, PROC, RETURN, INT, ELSE, DO, BREAK, END, ASSIGN, ADD_OP, SUB_OP, MUL_OP, DIV_OP, MOD_OP, GT, LT, GE, LE, INC, LP, RP, LB, RB, OR, AND, EE, NEG, COMMA, SEMI, INT_CONST, STR_CONST, IDENT
}
//Mingyang Li
//898137443


public class Lexer {



    private static String line;
    private static int index;
//    private static int lineNum = 0;
    private static boolean isComment = false;
//    private static boolean isString = false;

    public static void tokenize(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
//            lineNum++;
//                                                                                                                System.out.println(line);
            if(line.length() == 0) continue;
            deleteEndSpace();
            for(index = 0; index < line.length(); index++) {
                skipWhiteSpace();
                printToken(getNextToken(getNextSymbol()));
            }
        }
    }

    public static String getNextSymbol() {
        String sym =  "" + line.charAt(index);
        if (Character.isLetter(sym.charAt(0))){
            while ((index<line.length()-1)&&Character.isLetterOrDigit(line.charAt(index + 1))) {
                index++;
                sym += line.charAt(index);
            }
        }else if(sym.equals(">") || sym.equals("<") || sym.equals("!") || sym.equals("=")) {
            if (line.charAt(index + 1) == '=') {
                index++;
                sym += line.charAt(index);
            }
        } else if (sym.equals("+")) {
            if(line.charAt(index + 1) == '+') {
                index++;
                sym += line.charAt(index);
            }
//        } else if (sym.equals("|")) {
//            if(line.charAt(index + 1) == '|') {
//                index++;
//                sym += line.charAt(index);
//            }
//        } else if (sym.equals("&")) {
//            if(line.charAt(index + 1) == '&') {
//                index++;
//                sym += line.charAt(index);
//            }
        } else if (sym.equals("/")) {
            if(line.charAt(index + 1) == '/') {
                index++;
                sym += line.charAt(index);
            } else if(line.charAt(index + 1) == '*') {
                index++;
                sym += line.charAt(index);
            }
        } else if (sym.equals("\"")) {
            while (line.charAt(index + 1) != '"') {
                index++;
                sym += line.charAt(index);
                if(index == line.length() - 2) error("STRING NOT CLOSED");
            }
            sym += line.charAt(index + 1);
            index++;
        } else if (sym.equals("*")) {
            if(line.charAt(index + 1) == '/') {
                index++;
                sym += line.charAt(index);
            }
        }
//                                                                                                            System.out.println("index = " + index);
//                                                                                                            System.out.println(sym);
        return sym;
    }

    public static Token getNextToken(String symbol) {
        //Can't deal with /* */ in multiple lines for now
        if(isComment) {
            if(symbol.equals("*/")){
                isComment = false;
            }
            return null;
        }
        switch (symbol) {
            case "if":
                return Token.IF;
            case "for":
                return Token.FOR;
            case "while":
                return Token.WHILE;
            case "procedure":
                return Token.PROC;
            case "return":
                return Token.RETURN;
            case "int":
                return Token.INT;
            case "else":
                return Token.ELSE;
            case "do":
                return Token.DO;
            case "break":
                return Token.BREAK;
            case "end":
                return Token.END;
            case "=":
                return Token.ASSIGN;
            case "+":
                return Token.ADD_OP;
            case "-":
                return Token.SUB_OP;
            case "*":
                return Token.MUL_OP;
            case "/":
                return Token.DIV_OP;
            case "%":
                return Token.MOD_OP;
            case ">":
                return Token.GT;
            case "<":
                return Token.LT;
            case ">=":
                return Token.GE;
            case "<=":
                return Token.LE;
            case "++":
                return Token.INC;
            case "(":
                return Token.LP;
            case ")":
                return Token.RP;
            case "{":
                return Token.LB;
            case "}":
                return Token.RB;
//            case "||":
//                return Token.OR;
//            case "&&":
//                return Token.AND;
            case "|":
                return Token.OR;
            case "&":
                return Token.AND;
            case "==":
                return Token.EE;
            case "!":
                return Token.NEG;
            case ",":
                return Token.COMMA;
            case ";":
                return Token.SEMI;
            case "/*":
            {
                isComment = true;
                return null;
            }
            case "//":
            {
                line = line.substring(0, index-1);
                return null;
            }
            case "\"":
            {
//                isString = true;
                return Token.STR_CONST;
            }
            default: {
                if(symbol.charAt(0) == '"') return Token.STR_CONST;
                else if (Character.isDigit(symbol.charAt(0))) {
                    if(Character.isLetter(line.charAt(index + 1))) error("INVALID IDENTIFIER NAME");
                    while ((index<line.length()-1)&&Character.isDigit(line.charAt(index + 1))) {
                        index++;
                    }
                    return Token.INT_CONST;
                } else if (Character.isLetter(symbol.charAt(0))) {
                    while ((index<line.length()-1)&&Character.isLetterOrDigit(line.charAt(index + 1))) {
                        index++;
                    }
                    return Token.IDENT;
                } else {
                    error("INVALID SYMBOL");
                    return null;
                }
            }
        }
    }

    public static void error(String msg){
        System.err.print("SYNTAX ERROR: " + msg);
        System.exit(1);
    }

    public static void skipWhiteSpace() {
        while (index < line.length() && Character.isWhitespace(line.charAt(index))) {
            index++;
        }
    }

    public static void deleteEndSpace(){
        while(line.charAt(line.length()-1) == ' '){
            line = line.substring(0, line.length()-1);
        }
    }

    public static void printToken(Token token) {
        if(token != null) System.out.println(token);
    }
}
