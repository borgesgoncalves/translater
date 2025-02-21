import java.io.*;
import java.util.Scanner;
import lexer.*;

class Parser {
    static Token lookahead;
    static Lexer lexer = new Lexer();

    public Parser() throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            lexer.inputData(new ByteArrayInputStream(input.getBytes()));
        }
        lookahead = lexer.scan();
    }
    
void expr() throws IOException {
    term(); expr1();
}

    void expr1() throws IOException { 
         while(true) {
             if((char) lookahead.tag == '+' ) {
                 match('+'); 
                 term(); 
                 System.out.write('+'); 
                 expr1();
                }
                else if((char) lookahead.tag == '-' ) {
                    match('-'); 
                    term(); 
                    System.out.write('-'); 
                    expr1();
                }
                else {
                    return;
                } 
            }
        }
    
    void term() throws IOException{
        factor(); term1();
    }

    void term1() throws IOException{
        while(true){
            if((char) lookahead.tag == '*') {
                match('*');
                factor();
                System.out.write('*');
                term1();
            } 
            else if ((char) lookahead.tag == '/') {
                match('/');
                factor();
                System.out.write('/');
                term1();
            }
            else {
                return;
            }
        }
    }

    void factor() throws IOException {
        if (lookahead.tag == 256) {
            System.out.print(Num.value);
            match(lookahead.tag); 
        } else if (lookahead.tag == 257) {
            System.out.print(Tag.ID); 
            match(lookahead.tag);                
        } else { 
            System.out.print("Erro!");
            throw new Error("syntax error");
        }
    }

    void match(int t) throws IOException {
        if( lookahead.tag == t ) 
            lookahead = lexer.scan();
        else {
            System.out.print("Erro!");
            throw new Error("syntax error");
        }
    }
}

public class Postfix {
    public static void main(String[] args) throws IOException {
        Parser parse = new Parser();
        parse.expr();
        System.out.write('\n'); 
    }
}