package json;

import stmt.Stmt;
import stmt.Program;
import stmt.Property;
import stmt.ObjectLiteral;
import stmt.ArrayLiteral;
import stmt.BooleanLiteral;
import stmt.IntLiteral;
import stmt.FloatLiteral;
import stmt.NullLiteral;
import stmt.StringLiteral;

public class parser {

    private int pointer;
    private Token[] tokens;

    private Token at() {
        if (this.pointer >= this.tokens.length) {
            return new Token("Unexpected End Of File", Types.EOF, 0, 0);
        }
        return this.tokens[this.pointer];
    }

    private Token next() {
        if (this.pointer+1 >= this.tokens.length) {
            return this.tokens[this.tokens.length-1];
        }
        this.pointer++;
        return this.tokens[this.pointer-1];
    }

    private Token expect(Types Type, String err) {
        if (this.pointer+1 >= this.tokens.length) {
            return this.tokens[this.tokens.length-1];
        }
        this.pointer++;
        Token last = this.tokens[this.pointer-1];
        if (last.Type != Type) {
            System.out.println(err);
            System.out.println("Unexpected Token:");
            System.out.println(last.Value);
            System.out.println("Expecting Token Type:");
            System.out.println(Type);
            System.out.println("Line: " + Integer.toString(last.Line)+" Pos: " + Integer.toString(last.Column));
        }
        return last;
    }

    parser(Token[] tokens) {
        this.tokens = tokens;
        this.pointer = 0;
    }

    public stmt.Program Parse() {
        return new stmt.Program("Program", this.ParseStmt());
    }

    private stmt.Stmt ParseStmt() {
        
        switch (this.at().Type) {
            case Int:
                return new stmt.IntLiteral("Int", Integer.parseInt(this.next().Value));
            case Float:
                return new stmt.FloatLiteral("Float", Float.parseFloat(this.next().Value));
            case String:
                return new stmt.StringLiteral("String", this.next().Value);
            case Boolean:
                boolean q = false;
                if (this.next().Value.contains("true")) {
                    q = true;
                }
                return new stmt.BooleanLiteral("Boolean", q);
            case Null:
                this.next();
                return new stmt.NullLiteral("Null");
            case OpenBracket:
                this.next();
                stmt.Stmt[] elements = new stmt.Stmt[0];
                while (this.at().Type != Types.ClosedBracket) {
                    stmt.Stmt[] ne = new stmt.Stmt[elements.length+1];
                    for (int i = 0; i < elements.length; i++) {
                        ne[i] = elements[i];
                    }
                    ne[elements.length] = this.ParseStmt();
                    elements = ne;
                    if (this.at().Type == Types.Comma) {
                        this.next();
                    }
                }
                this.expect(
                    Types.ClosedBracket,
                    "Expected Closing For Array");
                return new stmt.ArrayLiteral("Array", elements);
            case OpenBrace:
                this.next();
                stmt.Property[] properties = new stmt.Property[0];
                while (this.at().Type != Types.ClosedBrace) {
                    String key = this.expect(
                    Types.String,
                    "Expected Key for Key:Value Pair").Value;
                    this.expect(
                    Types.Colon,
                    "Expected Colon for Key:Value Pair");
                    stmt.Property[] ne = new stmt.Property[properties.length+1];
                    for (int i = 0; i < properties.length; i++) {
                        ne[i] = properties[i];
                    }
                    ne[properties.length] = new stmt.Property("Property", key, this.ParseStmt());
                    properties = ne;
                    if (this.at().Type == Types.Comma) {
                        this.next();
                    } else if (this.at().Type == Types.ClosedBrace) {
                        this.next();
                        break;
                    }
                }
                return new stmt.ObjectLiteral("Object", properties);
            default:
                System.out.println("Invalid Token Found During Parsing");
                System.out.println(this.at().Value);
                System.out.println("Line: " + Integer.toString(this.at().Line)+" Pos: " + Integer.toString(this.at().Column));
                System.exit(1);
                break;
        }
        return new stmt.NullLiteral("Null");
    }

}