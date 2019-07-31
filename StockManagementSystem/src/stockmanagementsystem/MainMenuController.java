/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stockmanagementsystem.util.DBConnection;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class MainMenuController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement ps = null;

    @FXML
    private Label lblNotificationIndex;
    @FXML
    private Pane activePanel;
    @FXML
    private Label lblHome;
    @FXML
    private Label lblReportSale;
    @FXML
    private Label lblViewCustomers;
    @FXML
    private Label lblInvoice;
    @FXML
    private Label lblOrderStock;
    @FXML
    private Label lblSetting;
    @FXML
    private Label lblEPFNumber;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblLogout;
    
    public String fullName = null;
    @FXML
    private Label lblAdminSetting;
    @FXML
    private Label lblSearchBikes;
    @FXML
    private Label lblMonthlyPayments;
    @FXML
    private Label lblPrintReports;
    @FXML
    private Button btnNotification;
    @FXML
    private FontAwesomeIconView stockIcon;
    @FXML
    private FontAwesomeIconView settingIcon;
    @FXML
    private FontAwesomeIconView reportIcon;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkNotification();
    }
    
    public void setText(String EPF , String firstName , String lastName, int access) throws FileNotFoundException, UnsupportedEncodingException
    {
       this.lblEPFNumber.setText(EPF);
       fullName = firstName + " " + lastName;
       this.lblFullName.setText(fullName);
       
       if(access != 1)
       {
         this.lblOrderStock.setDisable(true);
         this.lblAdminSetting.setDisable(true);
         this.lblPrintReports.setDisable(true);
         this.stockIcon.setDisable(true);
         this.settingIcon.setDisable(true);
         this.reportIcon.setDisable(true);
       }
       PrintWriter writer = new PrintWriter("tmp\\temUserData.txt", "UTF-8");
       writer.println(EPF);
       writer.println(firstName);
       writer.println(lastName);
       writer.println(access);
       writer.close();
    }
    
    //Reset Navigation Bar Icon
    public void resetButton()
    {
        lblReportSale.getStyleClass().clear();
        lblReportSale.getStyleClass().add("navigationBarIcons");
        lblHome.getStyleClass().clear();
        lblHome.getStyleClass().add("navigationBarIcons");
        lblViewCustomers.getStyleClass().clear();
        lblViewCustomers.getStyleClass().add("navigationBarIcons");
        lblInvoice.getStyleClass().clear();
        lblInvoice.getStyleClass().add("navigationBarIcons");
        lblOrderStock.getStyleClass().clear();
        lblOrderStock.getStyleClass().add("navigationBarIcons");
        lblAdminSetting.getStyleClass().clear();
        lblAdminSetting.getStyleClass().add("navigationBarIcons");
        lblSearchBikes.getStyleClass().clear();
        lblSearchBikes.getStyleClass().add("navigationBarIcons");
        lblMonthlyPayments.getStyleClass().clear();
        lblMonthlyPayments.getStyleClass().add("navigationBarIcons");
        lblPrintReports.getStyleClass().clear();
        lblPrintReports.getStyleClass().add("navigationBarIcons");
    }

    @FXML
    private void go_to_notifications(ActionEvent event) throws IOException {
        checkNotification();
        resetButton();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_06_notificationCenter.fxml"));
        activePanel.getChildren().setAll(pane);
    }  
    
    @FXML
    private void go_to_homePage(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblHome.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_00_homePage.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void go_to_reportSale(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblReportSale.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_01_reportSale.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void go_to_viewCustomers(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblViewCustomers.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_02_viewCustomers.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void go_to_invoicesPage(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblInvoice.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_08_invoiceAndCoverNote.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void go_to_orderPage(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblOrderStock.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_07_stockDetails.fxml"));
        activePanel.getChildren().setAll(pane);
    }
    
    @FXML
    private void go_to_settingPage(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("settingMenuEmployee.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void logging_outFrmSystem(MouseEvent event) throws IOException {
        Parent Admin_parent =FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene Admin_Scene = new Scene(Admin_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(Admin_Scene);
        app_stage.show();
    }

    @FXML
    private void go_to_adminSetting(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblAdminSetting.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("settingMenu.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void go_to_searchBikes(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblSearchBikes.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_03_viewBike.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void go_to_updateMonthlyReport(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblMonthlyPayments.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_04_monthlyInstallment.fxml"));
        activePanel.getChildren().setAll(pane);
    }

    @FXML
    private void go_to_printReports(MouseEvent event) throws IOException {
        checkNotification();
        resetButton();
        lblPrintReports.getStyleClass().add("navigationBarIconsSelected");
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML_05_generatingReports.fxml"));
        activePanel.getChildren().setAll(pane);
    }
    
    private void checkNotification() {
        int count = 0;
        String getNextDate = null;
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        
        try {
            String val = null;
            int bikeQty = 0;
            String stockID = null;
            
            PrintWriter writer = new PrintWriter("tmp\\temData.txt", "UTF-8");
            PrintWriter writer2 = new PrintWriter("tmp\\temDataBike.txt", "UTF-8");
            
            String getAccountNumber = " SELECT DISTINCT MI_accountNumber FROM monthly_installment ";
            rs=db.getData(getAccountNumber);
            while(rs.next()) {
                String getLastPaymentDate = " SELECT * FROM monthly_installment WHERE MI_accountNumber = '" +rs.getString("MI_accountNumber")+ "' ";
                rs2=db.getData(getLastPaymentDate);
                while(rs2.next()) {
                   getNextDate = rs2.getString("MI_nextPaymentDate");
                   val = rs2.getString("MI_accountNumber");
                }
                final Date date = df.parse(getNextDate);
                int nextDateDay = date.getDate();
                int nextDateMonth = date.getMonth()+1;
                int todayDay = localDate.getDayOfMonth();
                int todayMonth = localDate.getMonthValue();
                if(nextDateMonth < todayMonth)
                {
                    //Count arrears customers
                    count = count + 1;
                    writer.println(val);
                }
                else if(nextDateMonth == todayMonth && nextDateDay < todayDay)
                {
                    //Count arrears customers
                    count = count + 1;
                    writer.println(val);
                }
                else
                {
                    //No arrears customers
                }
            }
            
            String getBikeQty = " SELECT * FROM showroom_stock ";
            rs=db.getData(getBikeQty);
            while(rs.next()) {
                   bikeQty = Integer.parseInt(rs.getString("SS_stockQuantity"));
                   stockID = rs.getString("SS_ID");
                   
                   if(bikeQty < 2)
                   {
                    count = count + 1;
                    writer2.println(stockID);
                   }    
            }
            
            //Set arrears customer amount & Low Stock Amount
            lblNotificationIndex.setText(Integer.toString(count));
            writer.close();
            writer2.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXML_00_homePageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
