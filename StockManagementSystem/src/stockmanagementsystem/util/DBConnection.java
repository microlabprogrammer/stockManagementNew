/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MR.KASUN PRABATH
 */
public class DBConnection {
   
    static String url = "jdbc:mysql://localhost:3306/smsdb";
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public Connection con() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
//        System.out.println("Right");
        Connection con1 = DriverManager.getConnection(url, "root", "");
//        System.out.println("con = "+con1);
        return con1;

    }

    public void putData(String sql) throws ClassNotFoundException, SQLException {
        Statement st =  con().createStatement();
        st.executeUpdate(sql);
    }

    public ResultSet getData(String sql) throws ClassNotFoundException, SQLException {
       
        Statement st = con().createStatement();
        ResultSet rs = (ResultSet) st.executeQuery(sql);
        return rs;

    }
}


