/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import stockmanagementsystem.util.DBConnection;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_06_notificationCenterController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement ps = null;

    @FXML
    private ListView<String> lstNotification;
    @FXML
    private Label lblEmptyMsg;
    @FXML
    private Label lblarrearsCustomerLabel;
    @FXML
    private ImageView picarrearsCustomerLabel;
    @FXML
    private Label lblStockWarning;
    @FXML
    private ImageView picStockWarning;
    @FXML
    private ListView<String> lstNotificationStock;
    @FXML
    private Label lblEmptyMsg1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        InputStream ins = null; // raw byte-stream
        Reader r = null; // cooked reader
        BufferedReader br = null; // buffered for readLine()
        try {
            String s;
            
            //Set arrears cutomer details to Listview
            ins = new FileInputStream("tmp\\temData.txt");
            r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
            br = new BufferedReader(r);
            if(br.readLine() == null)
            {
             lstNotification.setVisible(false);
             
             lblEmptyMsg.setVisible(true);
            }
            else
            {
                ins = new FileInputStream("tmp\\temData.txt");
                r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
                br = new BufferedReader(r);
                String accountNumber = null;
                String customerName = null;
                String NIC = null;
                String arrearsAmount = null;
                while ((s = br.readLine()) != null) {
                    String getCustomerDetails = " SELECT * FROM hire_purchesed_customer WHERE HC_accountNumber = '"+ s +"'";
                    rs=db.getData(getCustomerDetails);
                    while(rs.next()) {
                       accountNumber = rs.getString("HC_accountNumber");
                       customerName = rs.getString("HC_fName")+" "+rs.getString("HC_lName");
                       NIC = rs.getString("HC_nic");
                        String getBalance = " SELECT * FROM customer_account WHERE Account_number = '"+ accountNumber +"'";
                        rs=db.getData(getBalance);
                        while(rs.next()) {
                          arrearsAmount = "Rs."+rs.getString("Account_balance");
                          lstNotification.getItems().add("Account Number "+accountNumber+" - "+customerName+" ( "+NIC+" ) was missing him/her Payment. Balance - "+arrearsAmount+"");
                        }
                    }
                }
            }
            
            //Set low bike stock details to Listview
            ins = new FileInputStream("tmp\\temDataBike.txt");
            r = new InputStreamReader(ins, "UTF-8");
            br = new BufferedReader(r);
            if(br.readLine() == null)
            {
             lstNotificationStock.setVisible(false);
             
             lblEmptyMsg1.setVisible(true);
            }
            else
            {
                ins = new FileInputStream("tmp\\temDataBike.txt");
                r = new InputStreamReader(ins, "UTF-8");
                br = new BufferedReader(r);
                int bikeQty = 0;
                String stockID, bikeModel, bikeColor;
                while ((s = br.readLine()) != null) {
                    String getBikeDetails = " SELECT * FROM showroom_stock WHERE SS_ID = '"+ s +"'";
                    rs=db.getData(getBikeDetails);
                    while(rs.next()) {
                       bikeQty = Integer.parseInt(rs.getString("SS_stockQuantity"));
                       stockID = rs.getString("SS_ID");
                       bikeModel = rs.getString("SS_bikeModel");
                       bikeColor = rs.getString("SS_bikeColor");
                       
                       lstNotificationStock.getItems().add("Stock ID "+stockID+"    "+bikeModel+" "+bikeColor+" Color bike limit is very Low at "+bikeQty+" !!!! ");
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage()); // handle exception
        }
        finally {
            if (br != null) { try { br.close(); } catch(Throwable t) { /* ensure close happens */ } }
            if (r != null) { try { r.close(); } catch(Throwable t) { /* ensure close happens */ } }
            if (ins != null) { try { ins.close(); } catch(Throwable t) { /* ensure close happens */ } }
        }
    }    
    
}
