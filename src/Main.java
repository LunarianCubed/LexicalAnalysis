import LexicalAnalysis.Lexer;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = sc.nextLine();
        try {
            Lexer.tokenize(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
