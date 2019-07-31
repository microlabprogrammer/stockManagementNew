/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import stockmanagementsystem.util.DBConnection;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_00_homePageController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    @FXML
    private Label lblBikeAmount;
    @FXML
    private Label lblIncome;
    @FXML
    private Label lblTotalHPCustomers;
    @FXML
    private Label lblTotalOCustomers;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis x;
    @FXML
    private CategoryAxis y;
    
        int countCustomer1 = 0, countCustomer2 = 0, countCustomer3 = 0;
        int countCustomer4 = 0, countCustomer5 = 0, countCustomer6 = 0;
        int countCustomer7 = 0, countCustomer8 = 0, countCustomer9 = 0;
        int countCustomer10 = 0, countCustomer11 = 0, countCustomer12 = 0;
        int countCus1 = 0, countCus2 = 0, countCus3 = 0;
        int countCus4 = 0, countCus5 = 0, countCus6 = 0;
        int countCus7 = 0, countCus8 = 0, countCus9 = 0;
        int countCus10 = 0, countCus11 = 0, countCus12 = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int bikeStock = 0;
        int income = 0;
        int countHPC = 0;
        int countOC = 0;
        try {
            //Get Total from Bike Stock
            String bike = " SELECT * FROM showroom_stock ";
            rs=db.getData(bike);
            while(rs.next()) {
                bikeStock = bikeStock + Integer.parseInt(rs.getString("SS_stockQuantity"));
            }
            lblBikeAmount.setText(Integer.toString(bikeStock));
            
            //Get Net Income
            String netIncome = " SELECT * FROM invoice ";
            rs=db.getData(netIncome);
            while(rs.next()) {
                income = income + Integer.parseInt(rs.getString("Invoice_Amount"));
            }
            lblIncome.setText("Rs. " + Integer.toString(income));
            
            //Get Total from Hire Purchesed Customer
            String hpCustomer = " SELECT * FROM hire_purchesed_customer ";
            rs=db.getData(hpCustomer);
            while(rs.next()) {
                countHPC = countHPC + 1;
            }
            lblTotalHPCustomers.setText(Integer.toString(countHPC));
            
            //Get Total from Hire Purchesed Customer
            String oCustomer = " SELECT * FROM full_payment_customer ";
            rs=db.getData(oCustomer);
            while(rs.next()) {
                countOC = countOC + 1;
            }
            lblTotalOCustomers.setText(Integer.toString(countOC));
            
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error", "System error", JOptionPane.ERROR);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXML_00_homePageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            setDateTochart();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXML_00_homePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXML_00_homePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FXML_00_homePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setDateTochart() throws ClassNotFoundException, SQLException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        int nextDateMonth;
        barChart.setTitle("Customer Summary");
        x.setLabel("Amount of Customers");
        y.setLabel("Month");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Hire Purchase Customer");
        String query2 = " SELECT * FROM customer_account ";
        rs=db.getData(query2);
        while(rs.next()){
            Date date = df.parse(rs.getString("Account_createDate"));
            nextDateMonth = date.getMonth()+1;
            switch(nextDateMonth){
                case 1:
                countCustomer1 = countCustomer1 + 1;
                break;
                case 2:
                countCustomer2 = countCustomer2 + 1;
                break;
                case 3:
                countCustomer3 = countCustomer3 + 1;
                break;
                case 4:
                countCustomer4 = countCustomer4 + 1;
                break;
                case 5:
                countCustomer5 = countCustomer5 + 1;
                break;
                case 6:
                countCustomer6 = countCustomer6 + 1;
                break;
                case 7:
                countCustomer7 = countCustomer7 + 1;
                break;
                case 8:
                countCustomer8 = countCustomer8 + 1;
                break;
                case 9:
                countCustomer9 = countCustomer9 + 1;
                break;
                case 10:
                countCustomer10 = countCustomer10 + 1;
                break;
                case 11:
                countCustomer11 = countCustomer11 + 1;
                break;
                case 12:
                countCustomer12 = countCustomer12 + 1;
                break;
            }
            series1.getData().add(new XYChart.Data("January", countCustomer1));
            series1.getData().add(new XYChart.Data("February", countCustomer2));
            series1.getData().add(new XYChart.Data("March", countCustomer3));
            series1.getData().add(new XYChart.Data("April", countCustomer4));
            series1.getData().add(new XYChart.Data("May", countCustomer5));
            series1.getData().add(new XYChart.Data("June", countCustomer6));
            series1.getData().add(new XYChart.Data("July", countCustomer7));
            series1.getData().add(new XYChart.Data("August", countCustomer8));
            series1.getData().add(new XYChart.Data("September", countCustomer9));
            series1.getData().add(new XYChart.Data("October", countCustomer10));
            series1.getData().add(new XYChart.Data("November", countCustomer11));
            series1.getData().add(new XYChart.Data("December", countCustomer12));
        }
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Ordinary Customer"); 
        String query3 = " SELECT * FROM full_payment_customer ";
        rs=db.getData(query3);
        while(rs.next()){
            Date date = df.parse(rs.getString("FPC_date"));
            nextDateMonth = date.getMonth()+1;
            switch(nextDateMonth){
                case 1:
                countCus1 = countCus1 + 1;
                break;
                case 2:
                countCus2 = countCus2 + 1;
                break;
                case 3:
                countCus3 = countCus3 + 1;
                break;
                case 4:
                countCus4 = countCus4 + 1;
                break;
                case 5:
                countCus5 = countCus5 + 1;
                break;
                case 6:
                countCus6 = countCus6 + 1;
                break;
                case 7:
                countCus7 = countCus7 + 1;
                break;
                case 8:
                countCus8 = countCus8 + 1;
                break;
                case 9:
                countCus9 = countCus9 + 1;
                break;
                case 10:
                countCus10 = countCus10 + 1;
                break;
                case 11:
                countCus11 = countCus11 + 1;
                break;
                case 12:
                countCus12 = countCus12 + 1;
                break;
            }
            series2.getData().add(new XYChart.Data("January", countCus1));
            series2.getData().add(new XYChart.Data("February", countCus2));
            series2.getData().add(new XYChart.Data("March", countCus3));
            series2.getData().add(new XYChart.Data("April", countCus4));
            series2.getData().add(new XYChart.Data("May", countCus5));
            series2.getData().add(new XYChart.Data("June", countCus6));
            series2.getData().add(new XYChart.Data("July", countCus7));
            series2.getData().add(new XYChart.Data("August", countCus8));
            series2.getData().add(new XYChart.Data("September", countCus9));
            series2.getData().add(new XYChart.Data("October", countCus10));
            series2.getData().add(new XYChart.Data("November", countCus11));
            series2.getData().add(new XYChart.Data("December", countCus12));
        }
        
        barChart.getData().addAll(series1, series2);  
    } 
    
}
