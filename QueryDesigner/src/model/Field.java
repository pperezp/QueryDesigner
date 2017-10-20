package model;

/**
 *
 * @author prez
 */
public class Field {
    
    private String name;
    private String type;
    private Key key;
    
    /*Esto es el nombre del campo, pero con la tabla
    EJ: tabla.campo
    */
    private String selectName;
    

    public Field(String name, String type, String tableName) {
        this.name = name;
        this.type = type;
        this.key = Key.NOT;
        this.selectName = tableName+"."+name;
    }

    public String getSelectName() {
        return selectName;
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
    
    public boolean isPK(){
        return this.key == Key.PK;
    }
    
    public boolean isFK(){
        return this.key == Key.FK;
    }

    @Override
    public String toString() {
        return "Field{" + "name=" + name + ", type=" + type + ", key=" + key + '}';
    }
    
    
    
}
