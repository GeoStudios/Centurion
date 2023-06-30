package stmt;

public class Program extends Stmt {

    public String Type;
    public Stmt Body;
    public Program(String Type, Stmt Body){
        this.Type = Type;
        this.Body = Body;
    }
    public String GetType() {
        return this.Type;
    }

}