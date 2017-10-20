package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prez
 */
public class MySQL {

    private String rootPass;
    private String dbName;
    private Connection con;

    public MySQL(String bdName, String rootPass) throws SQLException, ClassNotFoundException {
        this.rootPass = rootPass;
        this.dbName = bdName;

        con = new Connection(bdName, rootPass);
    }
    
    public MySQL(String rootPass) throws SQLException, ClassNotFoundException{
        this("mysql", rootPass);
    }

    public List<String> getDatabasesName() throws SQLException{
        List<String> dbNames = new ArrayList<>();
        
        ResultSet rs = con.executeSelect("SHOW DATABASES");
        
        while(rs.next()){
            dbNames.add(rs.getString(1));
        }
        
        return dbNames;
    }
    
    /**
     * Obtiene una lista de todos los nombres de tabla de la base de datos
     * @return
     * @throws SQLException 
     */
    public List<String> getTablesName() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        ResultSet rs = con.executeSelect("SHOW TABLES");

        while (rs.next()) {
            tableNames.add(rs.getString(1));
        }
        
        con.close();

        return tableNames;
    }

    
    public List<Table> getTables(List<String> tableNames) throws SQLException {
        List<Table> tables = new ArrayList<>();
       
        Table t;
        for (String tableName : tableNames) {
            t = new Table(tableName);
            
            for (Field field : getFields(tableName)) {
                t.addField(field);
            }
            
            tables.add(t);
        }
        
        for (Table table : tables) {
            for (Reference reference : getReferences(table.getName())) {
                table.addReference(reference);
            }
        }
       
        return tables;
    }
    
    /**
     * Obtiene los campos de una tabla en específico.
     * @param tableName
     * @return
     * @throws SQLException 
     */
    private List<Field> getFields(String tableName) throws SQLException{
        List<Field> fields = new ArrayList<>();
        
        ResultSet rs = con.executeSelect("DESC "+tableName);
        
        Field f;
        while(rs.next()){
            f = new Field(rs.getString(1), rs.getString(2), tableName);
            
            // is Primary Key?
            if(rs.getString(4).equalsIgnoreCase("PRI")){
                f.setKey(Key.PK);
            }else{
                f.setKey(Key.NOT);
            }
            
            fields.add(f);
        }
        
        con.close();
        
        return fields;
    }

    /**
     * Obtiene una lista con las FK de una tabla en específico
     * @param tableName
     * @return
     * @throws SQLException 
     */
    private List<Reference> getReferences(String tableName) throws SQLException {
        List<Reference> references = new ArrayList<>();
        
        ResultSet rs = con.executeSelect("SELECT \n" +
                "    COLUMN_NAME AS 'Nombre FK',\n" +
                "    REFERENCED_TABLE_NAME AS 'Hace referencia a tabla...',\n" +
                "    REFERENCED_COLUMN_NAME AS 'Nombre PK'\n" +
                "FROM \n" +
                "    INFORMATION_SCHEMA.KEY_COLUMN_USAGE\n" +
                "WHERE \n" +
                "    TABLE_SCHEMA = '"+dbName+"' AND \n" +
                "    TABLE_NAME = '"+tableName+"' AND\n" +
                "    REFERENCED_COLUMN_NAME IS NOT NULL;");
        
        
        Reference r;
        while(rs.next()){
            r = new Reference(rs.getString(1), rs.getString(2), rs.getString(3));
            
            references.add(r);
        }
        
        con.close();
        
        
        return references;
    }

    public String getDbName() {
        return dbName;
    }

}
