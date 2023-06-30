package json.stmt;

public class NullLiteral extends Stmt {

    public String Type;
    public String Value;
    public NullLiteral(String Type){
        this.Type = Type;
        this.Value = null;
    }
    public String GetType() {
        return this.Type;
    }

}
