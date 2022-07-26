package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDatabase {
    final String dbName= "BKC";
    //final String conString = "jdbc:sqlserver://BEARHOMIE\\SQL_N01:1433;integratedSecurity=True;databaseName="+dbName;
    final String conString = "jdbc:sqlserver://BEARHOMIE\\SQL_N01:1433;user=student;password=123456;databaseName="+dbName;
    public Connection getConnection()
    {
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(conString);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE,null,ex);
        }
        return conn;
    }
    public static void main(String[] args) {
        ConnectDatabase kn = new ConnectDatabase();
        kn.getConnection();
    }
}
