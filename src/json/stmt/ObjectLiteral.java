package json.stmt;

public class ObjectLiteral extends Stmt {

    public String Type;
    public Property[] Properties;
    public ObjectLiteral(String Type, Property[] Properties){
        this.Type = Type;
        this.Properties = Properties;
    }
    public String GetType() {
        return this.Type;
    }

}

