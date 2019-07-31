/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import stockmanagementsystem.util.DBConnection;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_05_generatingReportsController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement ps = null;

    @FXML
    private CheckBox chkStock;
    @FXML
    private ComboBox<String> cmbStockType;
    @FXML
    private CheckBox chkInvoice;
    @FXML
    private DatePicker dpInvoiceFrom;
    @FXML
    private DatePicker dpInvoiceTo;
    @FXML
    private CheckBox chkCustomer;
    @FXML
    private ComboBox<String> cmbCustomerType;
    @FXML
    private CheckBox chkPayment;
    @FXML
    private DatePicker dpPaymentFrom;
    @FXML
    private DatePicker dpPaymentTo;
    @FXML
    private Button btnGenerate;
    @FXML
    private DatePicker dpCustomerFrom;
    @FXML
    private DatePicker dpCustomerTo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbStockType.setEditable(false);
        cmbStockType.getItems().addAll("Stock in showroom","Sale bikes");
        
        cmbCustomerType.setEditable(false);
        cmbCustomerType.getItems().addAll("Ordinary Customer","Hire purchased customer");
    }    
    
    @FXML
    private void select_customerType(ActionEvent event) {
    }
    
    @FXML
    private void generate_reports(ActionEvent event) {
        if(chkStock.isSelected() == false && chkInvoice.isSelected() == false && chkCustomer.isSelected() == false && chkPayment.isSelected() == false)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stock Management System");
                alert.setHeaderText("Select the category !!!!!");
                alert.setContentText("Please select the category first.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                alert.showAndWait();
        }
        else if(chkStock.isSelected() == true)
        {
         try{
             if(cmbStockType.getSelectionModel().getSelectedItem() == "Stock in showroom")
             {
                FileChooser chooser = new FileChooser();
                Window ownerWindow = null;
                File fullPath = chooser.showSaveDialog(ownerWindow);
                
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Available Stock");  

                HSSFRow rowhead = sheet.createRow((short)0);
                rowhead.createCell(2).setCellValue("Showroom Stock Details");
                int n = 3;
                String query = " SELECT * FROM bike WHERE B_customerID IS NULL ";
                rs=db.getData(query);
                HSSFRow rowColumn = sheet.createRow((short)2);
                    rowColumn.createCell(0).setCellValue("Brand");
                    rowColumn.createCell(1).setCellValue("Model");
                    rowColumn.createCell(2).setCellValue("Value");
                    rowColumn.createCell(3).setCellValue("Color");
                    rowColumn.createCell(4).setCellValue("Chassic Number");
                    rowColumn.createCell(5).setCellValue("Engine Number");
                while(rs.next()) {
                     //Set Data
                     HSSFRow row = sheet.createRow((short)n);
                      row.createCell(0).setCellValue(rs.getString("B_bikeMake"));
                      row.createCell(1).setCellValue(rs.getString("B_bikeModel"));
                      row.createCell(2).setCellValue(rs.getString("B_bikeValue"));
                      row.createCell(3).setCellValue(rs.getString("B_bikeColor")); 
                      row.createCell(4).setCellValue(rs.getString("B_bikeChassicNumber")); 
                      row.createCell(5).setCellValue(rs.getString("B_bikeEngineNumber")); 
                     n = n + 1;
                }
                //----Save Excel File
                FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
                workbook.write(fileOut);
                fileOut.close();
//                workbook.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Successfully !!!!!");
                    alert.setContentText("Excel file has been generated!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
             }
             else if(cmbStockType.getSelectionModel().getSelectedItem() == "Sale bikes")
             {
                FileChooser chooser = new FileChooser();
                Window ownerWindow = null;
                File fullPath = chooser.showSaveDialog(ownerWindow);
                
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Sold Bikes");  

                HSSFRow rowhead = sheet.createRow((short)0);
                rowhead.createCell(2).setCellValue("Sold Bike Stock Details");
                int n = 3;
                String query = " SELECT * FROM bike WHERE B_customerID != 'null' ";
                rs=db.getData(query);
                HSSFRow rowColumn = sheet.createRow((short)2);
                    rowColumn.createCell(0).setCellValue("Brand");
                    rowColumn.createCell(1).setCellValue("Model");
                    rowColumn.createCell(2).setCellValue("Value");
                    rowColumn.createCell(3).setCellValue("Color");
                    rowColumn.createCell(4).setCellValue("Chassic Number");
                    rowColumn.createCell(5).setCellValue("Engine Number");
                    rowColumn.createCell(6).setCellValue("Customer NIC");
                while(rs.next()) {
                     //Set Data
                     HSSFRow row = sheet.createRow((short)n);
                      row.createCell(0).setCellValue(rs.getString("B_bikeMake"));
                      row.createCell(1).setCellValue(rs.getString("B_bikeModel"));
                      row.createCell(2).setCellValue(rs.getString("B_bikeValue"));
                      row.createCell(3).setCellValue(rs.getString("B_bikeColor")); 
                      row.createCell(4).setCellValue(rs.getString("B_bikeChassicNumber")); 
                      row.createCell(5).setCellValue(rs.getString("B_bikeEngineNumber"));
                      row.createCell(6).setCellValue(rs.getString("B_customerID")); 
                     n = n + 1;
                }
                //----Save Excel File
                FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
                workbook.write(fileOut);
                fileOut.close();
//                workbook.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Successfully !!!!!");
                    alert.setContentText("Excel file has been generated!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
             }
         }catch (Exception e) {
            System.out.println(e.getMessage());
         }
         finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         }
        }
        else if(chkInvoice.isSelected() == true)
        {
            try{
             LocalDate dateInvoiceFrom = dpInvoiceFrom.getValue();
             LocalDate dateInvoiceTo = dpInvoiceTo.getValue();
                String issuedDate;
                String query = " SELECT * FROM invoice";
                rs=db.getData(query);
                //Get file path & File name from user
                FileChooser chooser = new FileChooser();
                Window ownerWindow = null;
                File fullPath = chooser.showSaveDialog(ownerWindow);

                //Create Excel File
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Invoice");  

                HSSFRow rowhead = sheet.createRow((short)0);
                rowhead.createCell(2).setCellValue("Invoice Report From "+dateInvoiceFrom+" To "+dateInvoiceTo);
                int n = 3;
                //Table Column Head
                HSSFRow rowColumn = sheet.createRow((short)2);
                    rowColumn.createCell(0).setCellValue("Invoice Data");
                    rowColumn.createCell(1).setCellValue("Invoice Number");
                    rowColumn.createCell(2).setCellValue("Amount");
                    rowColumn.createCell(3).setCellValue("Account Number");
                while(rs.next()) {
                    issuedDate = rs.getString("Invoice_issuedDate");
                    LocalDate issuedDateNew = LocalDate.parse(issuedDate);
                    if(dateInvoiceFrom.compareTo(issuedDateNew) <= 0 && dateInvoiceTo.compareTo(issuedDateNew) >= 0)
                    {
                     //Set Data
                     HSSFRow row = sheet.createRow((short)n);
                      row.createCell(0).setCellValue(rs.getString("Invoice_Number"));
                      row.createCell(1).setCellValue(rs.getString("Invoice_issuedDate"));
                      row.createCell(2).setCellValue(rs.getString("Invoice_Amount"));
                      row.createCell(3).setCellValue(rs.getString("Invoice_accountNumber")); 
                     n = n + 1;
                    }
                }
                //----Save Excel File
                FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
                workbook.write(fileOut);
                fileOut.close();
//                workbook.close();
                System.out.println("Excel file has been generated!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Successfully !!!!!");
                    alert.setContentText("Excel file has been generated!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
                
            }
            catch (Exception e) {
                 //System.out.println(e.getMessage());
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Fields cannot be empty !!!!!");
                    alert.setContentText("Please fill all fields first.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
            }
            finally {
                 if (con != null) {
                     try {
                         con.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
            }
            //Finish invoice generate
        }
        else if(chkCustomer.isSelected() == true)
        {
         try{
             if(cmbCustomerType.getSelectionModel().getSelectedItem() == "Ordinary Customer")
             {
                LocalDate dateInvoiceFrom = dpCustomerFrom.getValue();
                LocalDate dateInvoiceTo = dpCustomerTo.getValue();
                String issuedDate;
                String query = " SELECT * FROM full_payment_customer";
                rs=db.getData(query);
                
                FileChooser chooser = new FileChooser();
                Window ownerWindow = null;
                File fullPath = chooser.showSaveDialog(ownerWindow);
                
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Ordinary Customers");  
                
                HSSFRow rowhead = sheet.createRow((short)0);
                rowhead.createCell(2).setCellValue("Ordinary Customers Report From "+dateInvoiceFrom+" To "+dateInvoiceTo);
                int n = 3;
                //Table Column Head
                HSSFRow rowColumn = sheet.createRow((short)2);
                    rowColumn.createCell(0).setCellValue("Date");
                    rowColumn.createCell(1).setCellValue("Customer First Name");
                    rowColumn.createCell(2).setCellValue("Customer Last Name");
                    rowColumn.createCell(3).setCellValue("Address");
                    rowColumn.createCell(4).setCellValue("City");
                    rowColumn.createCell(5).setCellValue("Tel. No.");
                    rowColumn.createCell(6).setCellValue("NIC No.");
                while(rs.next()) {
                    issuedDate = rs.getString("FPC_date");
                    LocalDate issuedDateNew = LocalDate.parse(issuedDate);
                    if(dateInvoiceFrom.compareTo(issuedDateNew) <= 0 && dateInvoiceTo.compareTo(issuedDateNew) >= 0)
                    {
                     //Set Data
                     HSSFRow row = sheet.createRow((short)n);
                      row.createCell(0).setCellValue(rs.getString("FPC_date"));
                      row.createCell(1).setCellValue(rs.getString("FPC_fName"));
                      row.createCell(2).setCellValue(rs.getString("FPC_lName"));
                      row.createCell(3).setCellValue(rs.getString("FPC_address"));
                      row.createCell(4).setCellValue(rs.getString("FPC_city"));
                      row.createCell(5).setCellValue(rs.getString("FPC_telNumber"));
                      row.createCell(6).setCellValue(rs.getString("FPC_NIC"));
                     n = n + 1;
                    }
                }
                //----Save Excel File
                FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
                workbook.write(fileOut);
                fileOut.close();
//                workbook.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Successfully !!!!!");
                    alert.setContentText("Excel file has been generated!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
             }
             else if(cmbCustomerType.getSelectionModel().getSelectedItem() == "Hire purchased customer")
             {
                LocalDate dateInvoiceFrom = dpCustomerFrom.getValue();
                LocalDate dateInvoiceTo = dpCustomerTo.getValue();
                String issuedDate;
                String query = " SELECT * FROM hire_purchesed_customer";
                rs=db.getData(query);
                
                FileChooser chooser = new FileChooser();
                Window ownerWindow = null;
                File fullPath = chooser.showSaveDialog(ownerWindow);
                
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Hire Purchesed Customers");  
                
                HSSFRow rowhead = sheet.createRow((short)0);
                rowhead.createCell(4).setCellValue("Hire Purchesed Customers Report From "+dateInvoiceFrom+" To "+dateInvoiceTo);
                int n = 3;
                //Table Column Head
                HSSFRow rowColumn = sheet.createRow((short)2);
                    rowColumn.createCell(0).setCellValue("Date");
                    rowColumn.createCell(1).setCellValue("Account Number");
                    rowColumn.createCell(2).setCellValue("Customer First Name");
                    rowColumn.createCell(3).setCellValue("Customer Last Name");
                    rowColumn.createCell(4).setCellValue("Address");
                    rowColumn.createCell(5).setCellValue("City");
                    rowColumn.createCell(6).setCellValue("NIC No.");
                    rowColumn.createCell(7).setCellValue("Tel. No.");
                    rowColumn.createCell(8).setCellValue("Bike Value");
                    rowColumn.createCell(9).setCellValue("Balance of Today"); 
                    rowColumn.createCell(10).setCellValue("1st Guarantor Name");
                    rowColumn.createCell(11).setCellValue("1st Guarantor Address");
                    rowColumn.createCell(12).setCellValue("1st Guarantor NIC");
                    rowColumn.createCell(13).setCellValue("2nd Guarantor Name");
                    rowColumn.createCell(14).setCellValue("2nd Guarantor Address");
                    rowColumn.createCell(15).setCellValue("2nd Guarantor NIC");
                    rowColumn.createCell(16).setCellValue("3rd Guarantor Name");
                    rowColumn.createCell(17).setCellValue("3rd Guarantor Address");
                    rowColumn.createCell(18).setCellValue("3rd Guarantor NIC");
                while(rs.next()) {
                String query2 = " SELECT * FROM customer_account WHERE Account_number = '"+ rs.getString("HC_accountNumber") +"' AND Account_cusNIC = '"+ rs.getString("HC_nic") +"' ";
                rs2=db.getData(query2);
                  while(rs2.next()) {
                    issuedDate = rs2.getString("Account_createDate");
                    LocalDate issuedDateNew = LocalDate.parse(issuedDate);
                    if(dateInvoiceFrom.compareTo(issuedDateNew) <= 0 && dateInvoiceTo.compareTo(issuedDateNew) >= 0)
                    {
                     //Set Data
                     HSSFRow row = sheet.createRow((short)n);
                      row.createCell(0).setCellValue(rs2.getString("Account_createDate"));
                      row.createCell(1).setCellValue(rs.getString("HC_accountNumber"));
                      row.createCell(2).setCellValue(rs.getString("HC_fName"));
                      row.createCell(3).setCellValue(rs.getString("HC_lName"));
                      row.createCell(4).setCellValue(rs.getString("HC_address"));
                      row.createCell(5).setCellValue(rs.getString("HC_city"));
                      row.createCell(6).setCellValue(rs.getString("HC_nic"));
                      row.createCell(7).setCellValue(rs.getString("HC_telNo"));
                      row.createCell(8).setCellValue(rs2.getString("Account_bikeValue"));
                      row.createCell(9).setCellValue(rs2.getString("Account_balance"));
                      row.createCell(10).setCellValue(rs.getString("GName1"));
                      row.createCell(11).setCellValue(rs.getString("GAddress1"));
                      row.createCell(12).setCellValue(rs.getString("GNIC1"));
                      row.createCell(13).setCellValue(rs.getString("GName2"));
                      row.createCell(14).setCellValue(rs.getString("GAddress2"));
                      row.createCell(15).setCellValue(rs.getString("GNIC2"));
                      row.createCell(16).setCellValue(rs.getString("GName3"));
                      row.createCell(17).setCellValue(rs.getString("GAddress3"));
                      row.createCell(18).setCellValue(rs.getString("GNIC3"));
                     n = n + 1;
                    }
                }}
                //----Save Excel File
                FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
                workbook.write(fileOut);
                fileOut.close();
//                workbook.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Successfully !!!!!");
                    alert.setContentText("Excel file has been generated!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
             }
         }catch (Exception e) {
            System.out.println(e.getMessage());
         }
         finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         }
        }
        else if(chkPayment.isSelected() == true)
        {
            try{
             LocalDate dateInvoiceFrom = dpPaymentFrom.getValue();
             LocalDate dateInvoiceTo = dpPaymentTo.getValue();
                String issuedDate;
                String query = " SELECT * FROM monthly_installment";
                rs=db.getData(query);
                //Get file path & File name from user
                FileChooser chooser = new FileChooser();
                Window ownerWindow = null;
                File fullPath = chooser.showSaveDialog(ownerWindow);

                //Create Excel File
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Monthly Installment");  

                HSSFRow rowhead = sheet.createRow((short)0);
                rowhead.createCell(2).setCellValue("Monthly Installment Report From "+dateInvoiceFrom+" To "+dateInvoiceTo);
                int n = 3;
                //Table Column Head
                HSSFRow rowColumn = sheet.createRow((short)2);
                    rowColumn.createCell(0).setCellValue("Invoice Number");
                    rowColumn.createCell(1).setCellValue("Payment Date");
                    rowColumn.createCell(2).setCellValue("Account Number");
                    rowColumn.createCell(3).setCellValue("Amount");
                while(rs.next()) {
                    issuedDate = rs.getString("MI_paymentDate");
                    LocalDate issuedDateNew = LocalDate.parse(issuedDate);
                    if(dateInvoiceFrom.compareTo(issuedDateNew) <= 0 && dateInvoiceTo.compareTo(issuedDateNew) >= 0)
                    {
                     //Set Data
                     HSSFRow row = sheet.createRow((short)n);
                      row.createCell(0).setCellValue(rs.getString("MI_invoiceNumber"));
                      row.createCell(1).setCellValue(rs.getString("MI_accountNumber"));
                      row.createCell(2).setCellValue(rs.getString("MI_paymentDate"));
                      row.createCell(3).setCellValue(rs.getString("MI_amount")); 
                     n = n + 1;
                    }
                }
                //----Save Excel File
                FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
                workbook.write(fileOut);
                fileOut.close();
//                workbook.close();
                System.out.println("Excel file has been generated!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Successfully !!!!!");
                    alert.setContentText("Excel file has been generated!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
                
            }
            catch (Exception e) {
                 //System.out.println(e.getMessage());
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Fields cannot be empty !!!!!");
                    alert.setContentText("Please fill all fields first.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();
            }
            finally {
                 if (con != null) {
                     try {
                         con.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
            }
            //Finish Payment generate
        }
    }

    @FXML
    private void select_stockReport(MouseEvent event) {
        if(chkStock.isSelected() == false)
        {
            cmbStockType.setDisable(false);
            chkInvoice.setSelected(false);
            chkCustomer.setSelected(false);
            chkPayment.setSelected(false);
            dpInvoiceFrom.setDisable(true);
            dpInvoiceTo.setDisable(true);
            cmbCustomerType.setDisable(true);
            dpCustomerFrom.setDisable(true);
            dpCustomerTo.setDisable(true);
            dpPaymentFrom.setDisable(true);
            dpPaymentTo.setDisable(true);
        }
        else
        {
            cmbStockType.setDisable(true);
            dpInvoiceFrom.setDisable(true);
            dpInvoiceTo.setDisable(true);
            cmbCustomerType.setDisable(true);
            dpCustomerFrom.setDisable(true);
            dpCustomerTo.setDisable(true);
            dpPaymentFrom.setDisable(true);
            dpPaymentTo.setDisable(true);
        }
    }

    @FXML
    private void select_invoiceReport(MouseEvent event) {
        if(chkInvoice.isSelected() == false)
        {
            cmbStockType.setDisable(true);
            chkStock.setSelected(false);
            chkCustomer.setSelected(false);
            chkPayment.setSelected(false);
            dpInvoiceFrom.setDisable(false);
            dpInvoiceTo.setDisable(false);
            cmbCustomerType.setDisable(true);
            dpCustomerFrom.setDisable(true);
            dpCustomerTo.setDisable(true);
            dpPaymentFrom.setDisable(true);
            dpPaymentTo.setDisable(true);
        }
        else
        {
            cmbStockType.setDisable(true);
            dpInvoiceFrom.setDisable(true);
            dpInvoiceTo.setDisable(true);
            cmbCustomerType.setDisable(true);
            dpCustomerFrom.setDisable(true);
            dpCustomerTo.setDisable(true);
            dpPaymentFrom.setDisable(true);
            dpPaymentTo.setDisable(true);
        }
    }

    @FXML
    private void select_customerReport(MouseEvent event) {
        if(chkCustomer.isSelected() == false)
        {
            chkStock.setSelected(false);
            chkInvoice.setSelected(false);
            chkPayment.setSelected(false);
            cmbStockType.setDisable(true);
            dpInvoiceFrom.setDisable(true);
            dpInvoiceTo.setDisable(true);
            cmbCustomerType.setDisable(false);
            dpCustomerFrom.setDisable(false);
            dpCustomerTo.setDisable(false);
            dpPaymentFrom.setDisable(true);
            dpPaymentTo.setDisable(true);
        }
        else
        {
            cmbStockType.setDisable(true);
            dpInvoiceFrom.setDisable(true);
            dpInvoiceTo.setDisable(true);
            cmbCustomerType.setDisable(true);
            dpCustomerFrom.setDisable(true);
            dpCustomerTo.setDisable(true);
            dpPaymentFrom.setDisable(true);
            dpPaymentTo.setDisable(true);
        }
    }

    @FXML
    private void select_mpaymentReport(MouseEvent event) {
        if(chkPayment.isSelected() == false)
        {
            chkStock.setSelected(false);
            chkInvoice.setSelected(false);
            chkCustomer.setSelected(false);
            cmbStockType.setDisable(true);
            dpInvoiceFrom.setDisable(true);
            dpInvoiceTo.setDisable(true);
            cmbCustomerType.setDisable(true);
            dpCustomerFrom.setDisable(true);
            dpCustomerTo.setDisable(true);
            dpPaymentFrom.setDisable(false);
            dpPaymentTo.setDisable(false);
        }
        else
        {
            cmbStockType.setDisable(true);
            dpInvoiceFrom.setDisable(true);
            dpInvoiceTo.setDisable(true);
            cmbCustomerType.setDisable(true);
            dpCustomerFrom.setDisable(true);
            dpCustomerTo.setDisable(true);
            dpPaymentFrom.setDisable(true);
            dpPaymentTo.setDisable(true);
        }
    }
    
}
