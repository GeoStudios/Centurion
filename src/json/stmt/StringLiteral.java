package json.stmt;

public class StringLiteral extends Stmt {

    public String Type;
    public String Value;
    public StringLiteral(String Type, String Value){
        this.Type = Type;
        this.Value = Value;
    }
    public String GetType() {
        return this.Type;
    }

}
