package lexer; 
import java.io.*;
import java.util.*;

public class Lexer {
    public int line = 1;
    private char peek = ' ';
    private InputStream input;
    private Map<String, Word> words = new HashMap<>();

    void reserve(Word t) { words.put(t.lexeme, t); }

    public Lexer(){}

    public void inputData(InputStream input) {
        this.input = input;
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
    }

    void readch() throws IOException { peek = (char) input.read(); }

    boolean readch(char c) throws IOException {
        readch();
        if (peek != c) return false;
        peek = ' ';
        return true;
    }

    public Token scan() throws IOException {
        for (;; readch()) {
            if (peek == ' ' || peek == '\t') continue;
            else if (peek == '\n') line = line + 1;
            else break;
        }

        // switch (peek) {
        //     case '&':
        //         if (readch('&')) return Word.and; else return new Token('&');
        //     case '|':
        //         if (readch('|')) return Word.or; else return new Token('|');
        //     case '=':
        //         if (readch('=')) return Word.eq; else return new Token('=');
        //     case '!':
        //         if (readch('=')) return Word.ne; else return new Token('!');
        //     case '<':
        //         if (readch('=')) return Word.le; else return new Token('<');
        //     case '>':
        //         if (readch('=')) return Word.ge; else return new Token('>');
        // }

        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                readch();
            } while (Character.isDigit(peek));
            return new Num(v);
        }

        if (Character.isLetter(peek)) {
            StringBuilder b = new StringBuilder();
            do {
                b.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek));
            String s = b.toString();
            Word w = words.get(s);
            if (w != null) return w;
            w = new Word(Tag.ID, s);
            words.put(s, w);
            return w;
        }

        Token tok = new Token(peek);
        peek = ' ';
        return tok;
    }
}
