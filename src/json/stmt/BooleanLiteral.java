package json.stmt;

public class BooleanLiteral extends Stmt {

    public String Type;
    public boolean Value;
    public BooleanLiteral(String Type, boolean Value){
        this.Type = Type;
        this.Value = Value;
    }
    public String GetType() {
        return this.Type;
    }

}
