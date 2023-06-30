package stmt;

public class ArrayLiteral extends Stmt {

    public String Type;
    public Stmt[] Elements;
    public ArrayLiteral(String Type, Stmt[] Elements){
        this.Type = Type;
        this.Elements = Elements;
    }
    public String GetType() {
        return this.Type;
    }

}
