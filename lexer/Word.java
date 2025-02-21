package lexer;

public class Word extends Token {
    public String lexeme;
    // public static final Word
        // and = new Word(Tag.AND, "&&"), or = new Word(Tag.OR, "||"),
        // eq = new Word(Tag.EQ, "=="), ne = new Word(Tag.NE, "!="),
        // le = new Word(Tag.LE, "<="), ge = new Word(Tag.GE, ">=");
    
    public Word(int tag, String s) {
        super(tag);
        lexeme = s;
    }

    @Override
    public String toString() {
        return "<Id, " + '\'' + lexeme + '\'' + '>';
    }
}
