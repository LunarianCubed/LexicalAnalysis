import LexicalAnalysis.Lexer;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Lexer.tokenize("test.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
