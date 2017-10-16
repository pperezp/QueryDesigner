package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prez
 */
public class Table {
    private final String name;
    private final List<Field> fields;
    private List<Reference> references;

    public Table(String name) {
        fields = new ArrayList<>();
        this.name = name;
        this.references = new ArrayList<>();
    }
    
    public void addReference(Reference r){
        references.add(r);
        
        // Busco el campo y lo seteo como FK
        fields.stream()
                .filter(
                    f -> f.getName().equalsIgnoreCase(r.getNameField())
                )
                .findFirst()
                .get().setKey(Key.FK);
    }

    public Reference getReference(Field f){
        return references.stream()
                .filter(
                    r -> r.getNameField().equalsIgnoreCase(f.getName())
                )
                .findFirst()
                .get();
    }

    public String getName() {
        return name;
    }
    
    public void addField(Field f){
        fields.add(f);
    }

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Table{" + "name=" + name + "}'";
    }
    
    
}
