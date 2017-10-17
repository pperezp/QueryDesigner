package model;

import java.sql.*; 
 
public class Connection {
    private final java.sql.Connection con; 
    private Statement sen;  
    private ResultSet rs;   
   
    public Connection(String bd, String pass) throws SQLException, ClassNotFoundException{
        String protocolo = "jdbc:mysql://";
        String lineaUser = "user=root";
        String lineaPass = "password="+pass;
       
        String url = protocolo +
                "localhost/" +
                bd + "?" +
                lineaUser + "&" +
                lineaPass;
       
        System.out.println(url);
       
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url);
    } 
   
    public void execute(String query) throws SQLException{
        sen = con.createStatement();
        sen.executeUpdate(query);
        close();
    }
   
    public ResultSet executeSelect(String query) throws SQLException{
        sen = con.createStatement();
        rs = sen.executeQuery(query);
        return rs;
    }
   
    public void close() throws SQLException{
        sen.close();
    }
}