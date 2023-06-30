package json.map;

public class map {
    
    String[] Keys;
    Object[] Values;

    public map(){

        this.Keys = new String[0];
        this.Values = new Object[0];

    }

    public Object find(String key) {
        int idex = -1;
        for(int i = 0; i < this.Keys.length; i++) {
            System.out.print(key.trim());
            System.out.print("! ");
            System.out.print(this.Keys[i].trim());
            System.out.print("! ");
            System.out.print(this.Keys[i].trim() == key.trim());
            System.out.print("\n");
            if(this.Keys[i].trim() == key.trim()) {
                idex = i;
                break;
            }
        }

        if (idex == -1) {
            System.out.println("\n"+key+" Invlaid");
            return null;
        }

        return this.Values[idex];
    }

    public void set(String key, Object value) {
        if (key != null) {
            int index = -1;
            for(int i = 0; i < this.Keys.length; i++) {
                if(this.Keys[i] == key.trim()) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                String[] q = new String[this.Keys.length+1];
                for (int i = 0; i < this.Keys.length; i++) {
                    q[i] = this.Keys[i];
                }
                q[this.Keys.length] = key.trim();
                this.Keys = q; 
                Object[] q2 = new Object[this.Values.length+1];
                for (int i = 0; i < this.Values.length; i++) {
                    q2[i] = this.Values[i];
                }
                q2[this.Values.length] = value;
                this.Values = q2; 
            }else {
                this.Values[index] = value;
            }
        }

    }

    public void dump() {
        for (int i=0;i<this.Keys.length;i++) {
            System.out.print(this.Keys[i]);
            System.out.print(", ");
            System.out.print(this.Values[i]);
            System.out.print("\n");
            // System.out.println("\n");
        }
    }

}
