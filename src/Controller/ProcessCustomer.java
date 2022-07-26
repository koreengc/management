








package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

public class ProcessCustomer {
    public ProcessCustomer() {
            }

 public static List<Customer> findAllCus() 
 {
      List<Customer> listCus = new ArrayList<>();
      Connection conn=null;
      Statement st=null;
      ConnectDatabase kn = new ConnectDatabase();
      conn=kn.getConnection(); //kết nối đến CSDL và gắn vào conn
      String sql= "select * from account.CUSTOMER"; //câu truy vấn
        try {
            st=conn.createStatement();
            ResultSet rs = st.executeQuery(sql); //đọc toàn bộ bản ghi từ database ra............
            while (rs.next()){
                Customer cus = new Customer(rs.getInt("IDCus"),rs.getString("Name"), rs.getString("Address"), rs.getInt("Tel"));
                listCus.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcessCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCus; //lấy được danh sách KH trong database và đưa vào..............
}
 public static void insert(Customer cus)
 {
     Connection conn =null;
     PreparedStatement st = null;
     ConnectDatabase kn = new ConnectDatabase();
     conn=kn.getConnection();
     String sql = "insert BKC.account.CUSTOMER(IDCus, Name, Address, Tel) values(?,?,?,?)";
        try {
            st=conn.prepareCall(sql);
            st.setInt(1, cus.getIdcus());
            st.setString(2, cus.getName());
            st.setString(3, cus.getAddress());
            st.setInt(4, cus.getTel());
            st.execute();
         } catch (SQLException ex) {
            Logger.getLogger(ProcessCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
public static void delete(int idCus)
{
    Connection conn = null;
    PreparedStatement st = null;
    ConnectDatabase kn = new ConnectDatabase();
    conn = kn.getConnection();
    String sql = "delete from account.CUSTOMER where IDCus=?";
        try {
            conn.prepareCall(sql);
            st.setInt(1, idCus);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProcessCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public static List<Customer> findByName(String name)
{
    List<Customer> listCus = new ArrayList<>();
    Connection conn = null;
    PreparedStatement st = null;
    ConnectDatabase kn = new ConnectDatabase();
    conn = kn.getConnection();
    String sql = "select * from account.CUSTOMER where name like ?";
    try {
            st = conn.prepareCall(sql);
            st.setString(1, "%" + name+ "%");
            ResultSet rs =  st.executeQuery();
            while (rs.next())
            {
                Customer cus = new Customer(rs.getInt("IDCus"),rs.getString("Name"), rs.getString("Address"), rs.getInt("Tel"));
                listCus.add(cus);               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcessCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listCus;
}
}