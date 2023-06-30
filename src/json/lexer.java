package json;

enum Types {

    OpenBrace,
    ClosedBrace,
    OpenBracket,
    ClosedBracket,
    Comma,
    Colon,
    Int,
    Float,
    Boolean,
    String,
    Null,
    EOF

}

class Token {

    public String Value;
    public Types Type;
    public int Line;
    public int Column;
    Token(String value, Types type, int line, int column){
        this.Value = value;
        this.Type = type;
        this.Line = line;
        this.Column = column;
    }
}

/*
 * 
 * The json.lexer Class takes a String input formatted like a json file and it outputs a series of tokens.
 * lexer L = new lexer("""
 *         {
 *             \"Hello\":\"World\",
 *             \"a\":{
 *                 \"b\":\"e\"
 *             }
 *         }
 *         """);
 * 
*/

public class lexer {

    private int pointer;
    private String[] chars;
    private int line;
    private int column;
    private Token[] tokens;
    private Boolean eof;

    lexer(String text) {
        this.pointer = 0;
        this.chars = text.split("");
        this.line = 1;
        this.column = 1;
        this.tokens = new Token[0];
        this.eof = false;
    }

    private void addToken(Token token) {
        Token[] q = new Token[this.tokens.length+1];
        for (int i = 0; i < this.tokens.length; i++) {
            q[i] = this.tokens[i];
        }
        q[this.tokens.length] = token;
        this.tokens = q;
    }

    private String at() {
        if (this.pointer >= this.chars.length) {
            this.eof = true;
            return " ";
        } else {
            return this.chars[this.pointer];
        }
    }

    private void next() {
        this.pointer++;
    }

    private void skippable() {
        switch (this.at()) {
            case "\n":
                this.line++;
                this.column = 0;
                this.next();
                break;
            case "\r":
            case "\b":
            case "\f":
            case "\t":
            case "\n\r":
            case " ":
            case "":
                this.next();
                break;
        }
    }

    private void oneCharToken() {
        switch (this.at()) {
            case "{":
                this.addToken(new Token(this.at(), Types.OpenBrace, this.line, this.column));
                this.next();
                break;
            case "}":
                this.addToken(new Token(this.at(), Types.ClosedBrace, this.line, this.column));
                this.next();
                break;
            case "[":
                this.addToken(new Token(this.at(), Types.OpenBracket, this.line, this.column));
                this.next();
                break;
            case "]":
                this.addToken(new Token(this.at(), Types.ClosedBracket, this.line, this.column));
                this.next();
                break;
            case ":":
                this.addToken(new Token(this.at(), Types.Colon, this.line, this.column));
                this.next();
                break;
            case ",":
                this.addToken(new Token(this.at(), Types.Comma, this.line, this.column));
                this.next();
                break;
        }
    }

    private void strings() {
        String Value = "";
        if (this.at().contains("\"")) {
            this.next();
            while (!this.at().contains("\"")) {
                Value += this.at();
                this.next();
            }
            this.next();
            this.addToken(new Token(Value, Types.String, this.line, this.column));
        }
    }

    private void check() {
        switch (this.at()) {
            case "[":
            case"]":
            case"{":
            case"}":
            case"\"":
            case":":
            case",":
            case"\n":
            case"\r":
            case"\b":
            case"\f":
            case"\t":
            case" ":
                break;
            default:
                System.out.println("Invalid Character: "+this.at()+" Line: "+Integer.toString(this.line)+" Pos: "+Integer.toString(this.column));
                System.exit(1);
            }
    }

    public Token[] Tokenize() {
        for (int i = 0; i < this.chars.length; i++) {
            if (!this.eof) {
                this.column++;
                this.strings();
                this.skippable();
                this.oneCharToken();

                this.check();
            }
        }

        return this.tokens;
    }

}