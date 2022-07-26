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
import model.Product;

public class ProcessProduct {

    public ProcessProduct() {
    }
    
    public static List<Product> findAllPro() 
 {
      List<Product> listPro = new ArrayList<>();
      Connection conn=null;
      Statement st=null;
      ConnectDatabase kn = new ConnectDatabase();
      conn=kn.getConnection(); 
      String sql= "select * from product.PRODUCTS"; 
        try {
            st=conn.createStatement();
            ResultSet rs = st.executeQuery(sql); 
            while (rs.next()){
                Product pro = new Product(rs.getInt("IDProduct"),rs.getInt("IDSupplier"), rs.getInt("IDCategory"), rs.getString("ProductName"), rs.getString("Unit"), rs.getInt("Price"), rs.getString("Status"));
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcessProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPro; 
 }
    
    public static void insert(Product pro)
 {
     Connection conn =null;
     PreparedStatement st = null;
     ConnectDatabase kn = new ConnectDatabase();
     conn=kn.getConnection();
     String sql = "insert BKC.product.PRODUCTS(IDProduct, IDSupplier, IDCategory, ProductName, Unit, Price, Status) values (?,?,?,?,?,?,?)";
        try {
            st=conn.prepareCall(sql);
            st.setInt(1, pro.getIdproduct());
            st.setInt(2, pro.getIdsupplier());
            st.setInt(3, pro.getIdcategory());
            st.setString(4, pro.getName());
            st.setString(5, pro.getUnit());
            st.setInt(6, pro.getPrice());
            st.setString(7, pro.getStatus());
            st.execute();
         } catch (SQLException ex) {
            Logger.getLogger(ProcessProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    
    public static void delete(int idProduct)
{
    Connection conn = null;
    PreparedStatement st = null;
    ConnectDatabase kn = new ConnectDatabase();
    conn = kn.getConnection();
    String sql = "delete * from product.PRODUCTS where IDProduct=?";
        try {
            conn.prepareCall(sql);
            st.setInt(1, idProduct);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProcessProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public static List<Product> findByName(String name)
{
    List<Product> listPro= new ArrayList<>();
    Connection conn = null;
    PreparedStatement st = null;
    ConnectDatabase kn = new ConnectDatabase();
    conn = kn.getConnection();
    String sql = "select * from product.PRODUCTS where name like ?";
    try {
            conn.prepareCall(sql);
            st.setString(1, "%" + name+ "%");
            ResultSet rs =  st.executeQuery();
            while (rs.next())
            {
                Product pro = new Product(rs.getInt("IDProduct"),rs.getInt("IDSupplier"), rs.getInt("IDCategory"), rs.getString("ProductName"), rs.getString("Unit"), rs.getInt("Price"), rs.getString("Status"));
                listPro.add(pro);               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcessProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listPro;
}}


