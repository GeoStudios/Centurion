package json.stmt;

public class IntLiteral extends Stmt {

    public String Type;
    public int Value;
    public IntLiteral(String Type, int Value){
        this.Type = Type;
        this.Value = Value;
    }
    public String GetType() {
        return this.Type;
    }

}
