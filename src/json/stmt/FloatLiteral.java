package stmt;

public class FloatLiteral extends Stmt {

    public String Type;
    public float Value;
    public FloatLiteral(String Type, float Value){
        this.Type = Type;
        this.Value = Value;
    }
    public String GetType() {
        return this.Type;
    }

}
