package json;

import map.map;
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

public class mapper {

    public Object Eval(Stmt node) {
        switch (node.GetType()) {
            case "Program":
                map body = new map();
                body.set("body", this.Eval(((Program) node).Body));
                return body;
            case "Object":
                map mapp = new map();

                for (int i=0;i<((ObjectLiteral) node).Properties.length;i++){
                    // System.out.println(((ObjectLiteral) node).Properties[i].Key.trim());
                    mapp.set(((ObjectLiteral) node).Properties[i].Key.trim(), this.Eval(((ObjectLiteral) node).Properties[i].Value));
                }

                return mapp;
            case "Array":
                Object[] arr = new Object[0];

                for (int i=0;i<((ArrayLiteral) node).Elements.length;i++){
                    Object[] q = new Object[arr.length+1];
                    for (int i2 = 0; i2 < arr.length; i2++) {
                        q[i2] = arr[i2];
                    }
                    q[arr.length] = this.Eval(((ArrayLiteral) node).Elements[i]);
                    arr = q; 
                }

                return arr;
            case "String":
                return ((StringLiteral) node).Value;
            case "Int":
                return ((IntLiteral) node).Value;
            case "Float":
                return ((stmt.FloatLiteral) node).Value;
            case "Boolean":
                return ((BooleanLiteral) node).Value;
            case "Null":
                return ((NullLiteral) node).Value;
        }
        return "";
    }

}
