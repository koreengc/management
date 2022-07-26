package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProcessLogin {
    Connection cn = null;
    public ProcessLogin() {
        ConnectDatabase con = new ConnectDatabase();
        cn = con.getConnection();
    }
    public ResultSet getData() throws SQLException {
        ResultSet rs = null;     
        Statement stm = this.cn.createStatement(); 
        String sql = "select * from account.USERS"; 
        try {
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
