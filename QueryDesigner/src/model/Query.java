package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prez
 */
public class Query {
    private static List<String> fields = new ArrayList<>();
    private static List<String> tables = new ArrayList<>();
    private static List<String> where = new ArrayList<>();
    
    public static void addField(String field){
        fields.add(field);
    }
    
    public static void removeField(String field){
        fields.remove(field);
    }
    
    public static void addTable(String table){
        boolean exist = false;
        
        for (String tab : tables) {
            if(tab.equals(table)){
                exist = true;
                break;
            }
        }
        
        if(!exist){
            tables.add(table);
        }
        
        
    }
    
    public static void removeTable(String table){
        tables.remove(table);
    }
    
    public static void addWhere(String inner){
        where.add(inner);
    }
    
    public static void removeWhere(String inner){
        where.remove(inner);
    }
    
    public static StringBuilder getQuery(){
        StringBuilder query = new StringBuilder("SELECT\n");
        
        for (int i = 0; i < fields.size(); i++) {
            query.append("    ").append(fields.get(i));
            if(i != fields.size() - 1){
                query.append(",");
            }
            query.append("\n");
        }
        
        query.append("FROM\n");
        
        for (int i = 0; i < tables.size(); i++) {
            query.append("    ").append(tables.get(i));
            if(i != tables.size() - 1){
                query.append(",");
            }
            query.append("\n");
        }
        
        query.append("WHERE\n");
        
        for (int i = 0; i < where.size(); i++) {
            query.append("    ").append(where.get(i));
            if(i != where.size() - 1){
                query.append(" AND");
            }else{
                query.append(";");
            }
            query.append("\n");
        }
        
        
        
        return query;
    }
}
