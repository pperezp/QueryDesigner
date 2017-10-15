package model;

/**
 *
 * @author prez
 */
public class Field {
    
    private String name;
    private String type;
    private Key key;
    

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
        this.key = Key.NOT;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Field{" + "name=" + name + ", type=" + type + ", key=" + key + '}';
    }
    
    
    
}
