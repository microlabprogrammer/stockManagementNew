/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import stockmanagementsystem.util.DBConnection;
import stockmanagementsystem.util.setDataToTable;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_02_viewCustomersController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement ps = null;
    
    @FXML
    private TextField txtSearchNIC;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<setDataToTable> tblPayment;
    @FXML
    private Label lblCustomerName;
    @FXML
    private Label lblCustomerAddress;
    @FXML
    private Label lblCustomerNIC;
    @FXML
    private Label lblDownpayment;
    @FXML
    private Label lblPaymentDate;
    @FXML
    private TableColumn<setDataToTable, String> col_paymentDate;
    @FXML
    private TableColumn<setDataToTable, String> col_amount;
    @FXML
    private TableColumn<setDataToTable, String> col_invoiceNumber;
    @FXML
    private TableColumn<setDataToTable, String> col_paymentDueDate;
    @FXML
    private Label lblCustomerAccountNumber;
    @FXML
    private Label lblCustomerTelPhoneNumber;
    @FXML
    private Label lblActive;
    @FXML
    private Label lblClosed;
    @FXML
    private Label lblAccountBalance;
    @FXML
    private Label lblHirePuSale;
    @FXML
    private Button lblPrintCustomerDetails;
    @FXML
    private Label lblBikeModel;
    @FXML
    private Label lblChassicNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    ObservableList<setDataToTable> oblist = FXCollections.observableArrayList();

    @FXML
    private void searchCustomer(ActionEvent event) throws SQLException {
        clear();
        oblist.clear();
        lblActive.setVisible(false);
        lblClosed.setVisible(false);
        lblHirePuSale.setVisible(false);
        String nic = txtSearchNIC.getText();
        String firstNameFrmDB = null;
        String lastNameFrmDB = null;
        String addressFrmDB = null;
        String nicFrmDB = null;
        String telePhoneNumberFrmDB = null;
        String accountNumberFrmDB = null;
        String bikeDownPaymentValue;
        String accountCreateDate;
        String accountBalance;
        String accountStatus;
        String monthlyPaymentAmount;
        String monthlyPaymentDueDate;
        String monthlyPaymentPayDate;
        String monthlyPaymentInNum = null;
       
        try{
            //retrive customer data
           String query = " SELECT * FROM hire_purchesed_customer where HC_nic = '"+txtSearchNIC.getText()+"' ";
           rs=db.getData(query);
           
           if(!rs.next())
           {
               String query5 = " SELECT * FROM full_payment_customer where FPC_nic = '"+txtSearchNIC.getText()+"' ";
               rs2=db.getData(query5);
               if(!rs2.next())
               {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Stock Management System");
                alert.setHeaderText("Incorrect NIC Number !!!!");
                alert.setContentText("Please check you entered NIC number is correct.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                alert.showAndWait();
               }
           }
           else
           {
            String QuerySub = " SELECT * FROM hire_purchesed_customer where HC_nic = '"+txtSearchNIC.getText()+"' ";
            rs=db.getData(QuerySub);
            while(rs.next()) {
                firstNameFrmDB = rs.getString("HC_fName");
                lastNameFrmDB = rs.getString("HC_lName");
                addressFrmDB = rs.getString("HC_address");
                nicFrmDB = rs.getString("HC_nic");
                telePhoneNumberFrmDB = rs.getString("HC_telNo");
                accountNumberFrmDB = rs.getString("HC_accountNumber");
                }
            System.out.println("dd"+accountNumberFrmDB);
                if(accountNumberFrmDB != null)
                {
                    lblCustomerName.setText(firstNameFrmDB+" "+lastNameFrmDB);
                    lblCustomerAddress.setText(addressFrmDB);
                    lblCustomerNIC.setText(nicFrmDB);
                    lblCustomerTelPhoneNumber.setText(telePhoneNumberFrmDB);
                    lblCustomerAccountNumber.setText(accountNumberFrmDB);

                    //retrive customer account data
                    String query2 = " SELECT * FROM customer_account where Account_number = '"+accountNumberFrmDB+"' ";
                     rs=db.getData(query2);
                     while(rs.next()) {
                      bikeDownPaymentValue = rs.getString("Account_downPayment");
                      accountCreateDate = rs.getString("Account_createDate");
                      accountBalance = rs.getString("Account_balance");
                      accountStatus = rs.getString("Account_status");
                      lblDownpayment.setText("Rs. "+bikeDownPaymentValue);
                      lblPaymentDate.setText(accountCreateDate);
                      lblAccountBalance.setText("Rs. "+accountBalance);

                      int status = Integer.parseInt(accountStatus);
                       if(status == 1)
                       {
                         lblActive.setVisible(true);
                       }
                       else if(status == 0)
                       {
                         lblClosed.setVisible(true);
                      }
                     }
                    String hirePurchesedCustomerBike = " SELECT * FROM bike where B_customerID = '"+txtSearchNIC.getText()+"' ";
                    rs=db.getData(hirePurchesedCustomerBike);
                    while(rs.next()) {
                        lblBikeModel.setText(rs.getString("B_bikeModel"));
                        lblChassicNumber.setText(rs.getString("B_bikeChassicNumber"));
                    }

                    //retrive payment data to table
                    String query3 = " SELECT * FROM monthly_installment where MI_accountNumber = '"+accountNumberFrmDB+"' ";
                    rs=db.getData(query3);
                     while(rs.next()) {
                       monthlyPaymentAmount = rs.getString("MI_amount");
                       monthlyPaymentDueDate = rs.getString("MI_paymentDueDate");
                       monthlyPaymentPayDate  = rs.getString("MI_paymentDate");
                       monthlyPaymentInNum  = rs.getString("MI_invoiceNumber");
                       oblist.add(new setDataToTable(monthlyPaymentDueDate, monthlyPaymentPayDate, "Rs. "+monthlyPaymentAmount, monthlyPaymentInNum));
                     }
                }
                else
                {

                }
           }
           if(!rs.isBeforeFirst())
           {
                  String ordinaryCustomerQuary = " SELECT * FROM full_payment_customer where FPC_NIC = '"+txtSearchNIC.getText()+"' ";
                  rs=db.getData(ordinaryCustomerQuary);
                  while(rs.next()) {
                     firstNameFrmDB = rs.getString("FPC_fName");
                     lastNameFrmDB = rs.getString("FPC_lName");
                     addressFrmDB = rs.getString("FPC_address");
                     nicFrmDB = rs.getString("FPC_NIC");
                     telePhoneNumberFrmDB = rs.getString("FPC_telNumber");
                     accountCreateDate = rs.getString("FPC_date");

                     lblCustomerName.setText(firstNameFrmDB+" "+lastNameFrmDB);
                     lblCustomerAddress.setText(addressFrmDB);
                     lblCustomerNIC.setText(nicFrmDB);
                     lblCustomerTelPhoneNumber.setText(telePhoneNumberFrmDB);
                     lblCustomerAccountNumber.setText("N/A");
                     lblDownpayment.setText("N/A");
                     lblPaymentDate.setText(accountCreateDate);
                     lblAccountBalance.setText("N/A");
                     lblHirePuSale.setVisible(true);
                     String ordinaryCustomerBike = " SELECT * FROM bike where B_customerID = '"+txtSearchNIC.getText()+"' ";
                     rs=db.getData(ordinaryCustomerBike);
                      while(rs.next()) {
                        lblBikeModel.setText(rs.getString("B_bikeModel"));
                        lblChassicNumber.setText(rs.getString("B_bikeChassicNumber"));
                      }
                  }
           }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "System error", JOptionPane.ERROR);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        col_paymentDueDate.setCellValueFactory(new PropertyValueFactory<>("paymentDueDate"));
        col_paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_invoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        tblPayment.setItems(oblist);
    }

    @FXML
    private void printDetails(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        if(lblCustomerName.getText() == "" || lblCustomerNIC.getText() == "")
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Stock Management System");
                alert.setHeaderText("Print Error !!!!!");
                alert.setContentText("Please select The Customer before print customer report.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                alert.showAndWait();
        }
        else
        {
            //Get file path & File name from user
            FileChooser chooser = new FileChooser();
            Window ownerWindow = null;
            File fullPath = chooser.showSaveDialog(ownerWindow);
            
            //Create Excel File
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Customer");  

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(2).setCellValue("****Customer Report****");
            
            HSSFRow rowPersonalInfo = sheet.createRow((short)2);
            rowPersonalInfo.createCell(0).setCellValue("Personal Information - ");
            
            HSSFRow row4 = sheet.createRow((short)4);
            row4.createCell(0).setCellValue("Customer Name:");
            row4.createCell(1).setCellValue(lblCustomerName.getText());
            row4.createCell(3).setCellValue("NIC Number:");
            row4.createCell(4).setCellValue(lblCustomerNIC.getText());
            
            HSSFRow row5 = sheet.createRow((short)5);
            row5.createCell(0).setCellValue("Address:");
            row5.createCell(1).setCellValue(lblCustomerAddress.getText());
            row5.createCell(3).setCellValue("Tel. Number:");
            row5.createCell(4).setCellValue(lblCustomerTelPhoneNumber.getText());
            
            HSSFRow row6 = sheet.createRow((short)6);
            row6.createCell(0).setCellValue("Account Number:");
            row6.createCell(1).setCellValue(lblCustomerAccountNumber.getText());
            row6.createCell(3).setCellValue("Account Status:");
            if (lblCustomerAccountNumber.getText() == "")
            {
                row6.createCell(4).setCellValue("");
            }
            else if(lblActive.isVisible() == true)
            {
                row6.createCell(4).setCellValue("Active");
            }
            else if(lblClosed.isVisible() == true)
            {
                row6.createCell(4).setCellValue("Closed");
            }
            
            HSSFRow row9 = sheet.createRow((short)9);
            row9.createCell(0).setCellValue("Payment Details - ");
            
            HSSFRow row11 = sheet.createRow((short)11);
            row11.createCell(0).setCellValue("Downpayment:");
            row11.createCell(1).setCellValue(lblDownpayment.getText());
            row11.createCell(3).setCellValue("Chassic Number:");
            row11.createCell(4).setCellValue(lblChassicNumber.getText());
            
            HSSFRow row12 = sheet.createRow((short)12);
            row12.createCell(0).setCellValue("Account Create Date:");
            row12.createCell(1).setCellValue(lblPaymentDate.getText());
            row12.createCell(3).setCellValue("Account Balance:");
            row12.createCell(4).setCellValue(lblAccountBalance.getText());
            HSSFRow row13 = sheet.createRow((short)13);
            row13.createCell(3).setCellValue("Bike Model:");
            row13.createCell(4).setCellValue(lblBikeModel.getText());
            
        if (lblCustomerAccountNumber.getText() == " - ")
        {
        }
        else
        {
          HSSFRow row15 = sheet.createRow((short)15);
            row15.createCell(0).setCellValue("Monthly Payment Details -");
            row15.createCell(5).setCellValue("Guarantors's Details -");
          HSSFRow row17 = sheet.createRow((short)17);
            row17.createCell(0).setCellValue("Payment Due Date");
            row17.createCell(1).setCellValue("Amount");
            row17.createCell(2).setCellValue("Payment Date");
            row17.createCell(3).setCellValue("Invoice Number");
            String getGuarantors = " SELECT * FROM hire_purchesed_customer where HC_nic = '"+txtSearchNIC.getText()+"' AND HC_accountNumber = '"+lblCustomerAccountNumber.getText()+"' ";
            rs=db.getData(getGuarantors);
             while(rs.next()) {
                row17.createCell(5).setCellValue("1st Guarantor's Name:");
                row17.createCell(6).setCellValue(rs.getString("GName1"));

                HSSFRow row18 = sheet.createRow((short)18);
                row18.createCell(5).setCellValue("1st Guarantor's Address:");
                row18.createCell(6).setCellValue(rs.getString("GAddress1"));

                HSSFRow row19 = sheet.createRow((short)19);
                row19.createCell(5).setCellValue("1st Guarantor's NIC");
                row19.createCell(6).setCellValue(rs.getString("GNIC1"));

                HSSFRow row21 = sheet.createRow((short)21);
                row21.createCell(5).setCellValue("2nd Guarantor's Name:");
                row21.createCell(6).setCellValue(rs.getString("GName2"));

                HSSFRow row22 = sheet.createRow((short)22);
                row22.createCell(5).setCellValue("2nd Guarantor's Address:");
                row22.createCell(6).setCellValue(rs.getString("GAddress2"));

                HSSFRow row23 = sheet.createRow((short)23);
                row23.createCell(5).setCellValue("2nd Guarantor's NIC");
                row23.createCell(6).setCellValue(rs.getString("GNIC2"));

                HSSFRow row25 = sheet.createRow((short)25);
                row25.createCell(5).setCellValue("3rd Guarantor's Name:");
                row25.createCell(6).setCellValue(rs.getString("GName3"));

                HSSFRow row26 = sheet.createRow((short)26);
                row26.createCell(5).setCellValue("3rd Guarantor's Address:");
                row26.createCell(6).setCellValue(rs.getString("GAddress3"));

                HSSFRow row27 = sheet.createRow((short)27);
                row27.createCell(5).setCellValue("3rd Guarantor's NIC");
                row27.createCell(6).setCellValue(rs.getString("GNIC3"));
             }

            //select Quaery to run details Guarantors's Details
        try{
            int n = 18;
            String query3 = " SELECT * FROM monthly_installment where MI_accountNumber = '"+lblCustomerAccountNumber.getText()+"' ";
            rs=db.getData(query3);
             while(rs.next()) {
               HSSFRow row = sheet.createRow((short)n);
                row.createCell(0).setCellValue(rs.getString("MI_paymentDueDate"));
                row.createCell(1).setCellValue(rs.getString("MI_amount"));
                row.createCell(2).setCellValue(rs.getString("MI_paymentDate"));
                row.createCell(3).setCellValue(rs.getString("MI_invoiceNumber")); 
               n = n + 1;
             }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "System error", JOptionPane.ERROR);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        }
            
        //-------------------------------------------------------------------------
            FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
            workbook.write(fileOut);
            fileOut.close();
//            workbook.close();
            System.out.println("Excel file has been generated!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Stock Management System");
                alert.setHeaderText("Successfully !!!!!");
                alert.setContentText("Excel file has been generated!");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                alert.showAndWait();
        }
    }
    
    private void clear()
    {
        lblBikeModel.setText("");
        lblChassicNumber.setText("");
        lblCustomerAccountNumber.setText("");
        lblCustomerTelPhoneNumber.setText("");
        lblAccountBalance.setText("");
        lblCustomerName.setText("");
        lblCustomerAddress.setText("");
        lblCustomerNIC.setText("");
        lblDownpayment.setText("");
        lblPaymentDate .setText("");
        lblActive.setVisible(false);
        lblClosed.setVisible(false);
        lblHirePuSale.setVisible(false);
    }
    
}
