package LexicalAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

enum Token {
    // Keywords
    IF, FOR, WHILE, PROC, RETURN, INT, ELSE, DO, BREAK, END, ASSIGN, ADD_OP, SUB_OP, MUL_OP, DIV_OP, MOD_OP, GT, LT, GE, LE, INC, LP, RP, LB, RB, OR, AND, EE, NEG, COMMA, SEMI, INT_CONST, IDENT
}

public class Lexer {

    private static String line;
    private static int index;
    private static boolean isComment = false;

    public static void tokenize(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for(index = 0; index < line.length(); index++) {
                skipWhiteSpace();


            }
        }
    }

    public static String getNextSymbol() {
        return "" + line.charAt(index);
    }

    public static void error(){
        System.err.println("Error");
    }

    public static Token getNextToken(String symbol) {

        switch (symbol) {
            case "if":
                return Token.IF;
            case "for":
                return Token.FOR;
            case "while":
                return Token.WHILE;
            case "proc":
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
            case "=":{
                if(line.charAt(index+1) == '='){
                    index++;
                    return Token.EE;
                }
                return Token.ASSIGN;
            }
            case "+":{
                if(line.charAt(index+1) == '+'){
                    index++;
                    return Token.INC;
                }
                return Token.ADD_OP;
            }
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
            case "[":
                return Token.LB;
            case "]":
                return Token.RB;
            case "||":
                return Token.OR;
            case "&&":
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
            }
            case "//":
            {

            }
            default: {
                if (Character.isDigit(symbol.charAt(0))) {
                    while (Character.isDigit(line.charAt(index + 1))) {
                        index++;
                        symbol += line.charAt(index);
                    }
                    return Token.INT_CONST;
                } else if (Character.isLetter(symbol.charAt(0))) {
                    while (Character.isLetterOrDigit(line.charAt(index + 1))) {
                        index++;
                        symbol += line.charAt(index);
                    }
                    return Token.IDENT;
                } else {
                    error();
                    return null;
                }
            }
        }
    }


    public static void skipWhiteSpace() {
        while (index < line.length() && Character.isWhitespace(line.charAt(index))) {
            index++;
        }
    }

    public void skipComment(String symbol) {
        if (symbol.equals("/")) {
            isComment = true;
        }
        if (isComment) {
            if (symbol.equals("*")) {
                isComment = false;
            }
        }
    }

    public static void printToken(Token token) {
        System.out.println(token);
    }
}
