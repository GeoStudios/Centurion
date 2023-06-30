package stmt;

public class Property extends Stmt {

    public String Type;
    public String Key;
    public Stmt Value;
    public Property(String Type, String Key, Stmt Value){
        this.Type = Type;
        this.Key = Key;
        this.Value = Value;
    }
    public String GetType() {
        return this.Type;
    }

}
