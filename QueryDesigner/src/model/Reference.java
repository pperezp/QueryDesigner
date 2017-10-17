package model;

/**
 *
 * @author prez
 */
public class Reference {
    private String nameField;
    private String tableReference;
    private String pkName;

    public Reference(String nameField, String tableReference, String pkName) {
        this.nameField = nameField;
        this.tableReference = tableReference;
        this.pkName = pkName;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getTableReference() {
        return tableReference;
    }

    public void setTableReference(String tableReference) {
        this.tableReference = tableReference;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    @Override
    public String toString() {
        return "Reference{" + "nameField=" + nameField + ", tableReference=" + tableReference + ", pkName=" + pkName + '}';
    }
    
    
}
