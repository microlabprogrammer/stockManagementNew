/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import stockmanagementsystem.util.DBConnection;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_01_reportSaleController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    @FXML
    private ComboBox<String> cmbInsuranceCompany;
    @FXML
    private TextField txtDownPayment;
    @FXML
    private Button btnSubmit;
    @FXML
    private CheckBox chbHirePurchase;
    @FXML
    private ComboBox<String> cmbScheme;
    @FXML
    private TextField txtSearchChassicNumber;
    @FXML
    private Button btnSearch;
    @FXML
    private Label lblModel;
    @FXML
    private Label lblMake;
    @FXML
    private Label lblBikeValue;
    @FXML
    private Label lblEngineNumber;
    @FXML
    private Label lblColor;
    @FXML
    private TextField txtGName1;
    @FXML
    private TextField txtGAddress1;
    @FXML
    private TextField txtGNIC1;
    @FXML
    private TextField txtGName2;
    @FXML
    private TextField txtGAddress2;
    @FXML
    private TextField txtGNIC2;
    @FXML
    private TextField txtGName3;
    @FXML
    private TextField txtGAddress3;
    @FXML
    private TextField txtGNIC3;
    @FXML
    private Button btnClear;
    @FXML
    private TextField txtCusFName;
    @FXML
    private TextField txtCusLName;
    @FXML
    private TextField txtCusNIC;
    @FXML
    private TextField txtCusAddress;
    @FXML
    private TextField txtCusTelePhoneNumber;
    @FXML
    private ComboBox<String> cmbCusCity;
    
        String bikeModel = null;
        String bikeMake = null; 
        String bikeValue = null; 
        String bikeColor = null;
        String engineNumber = null;
        String customerId = null;
        String insuranceCompany = null;
    @FXML
    private Label lblBikeAmount;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbCusCity.setEditable(false);
        cmbCusCity.getItems().addAll("Gampaha", "Ja-Ela", "Veyangoda", "Minuwangoda", "Nittambuwa", "Diulapitiya");
        try{
         String query = " SELECT DISTINCT IC_companyName FROM insurance_company";
         rs=db.getData(query);
         while(rs.next()) {
           insuranceCompany = rs.getString("IC_companyName");
           cmbInsuranceCompany.setEditable(false);
           cmbInsuranceCompany.getItems().addAll(insuranceCompany);
         }
        } catch (Exception e) {
         e.printStackTrace();
        } finally {
         if (con != null) {
             try {
                 con.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        }
        //Retrieve chassis number
        InputStream ins = null;
        Reader r = null;
        BufferedReader br = null;
        
        try {
            ins = new FileInputStream("tmp\\temDataChassisNo.txt");
            r = new InputStreamReader(ins, "UTF-8");
            br = new BufferedReader(r);
            
            String s = br.readLine();
            txtSearchChassicNumber.setText(s);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        finally {
            if (br != null) { try { br.close(); } catch(IOException t) { /* ensure close happens */ } }
            if (r != null) { try { r.close(); } catch(IOException t) { /* ensure close happens */ } }
            if (ins != null) { try { ins.close(); } catch(IOException t) { /* ensure close happens */ } }
        }
        //Reset
        PrintWriter writer;
        try {
            writer = new PrintWriter("tmp\\temDataChassisNo.txt", "UTF-8");
            writer.println("");
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void submitValues(ActionEvent event) throws SQLException, ClassNotFoundException, ParseException {
        try {
        String customerFName = txtCusFName.getText();
        String customerLName = txtCusLName.getText();
        String customerNIC = txtCusNIC.getText();
        String customerAddress = txtCusAddress.getText();
        String customerTPNumbers = txtCusTelePhoneNumber.getText();
        int customerTPNumber = Integer.parseInt(customerTPNumbers);
        String customerDownPayment = txtDownPayment.getText();
        String customerGName1 = txtGName1.getText();
        String customerGName2 = txtGName2.getText();
        String customerGName3 = txtGName3.getText();
        String customerGAddress1 = txtGAddress1.getText();
        String customerGAddress2 = txtGAddress2.getText();
        String customerGAddress3 = txtGAddress3.getText();
        String customerGNIC1 = txtGNIC1.getText();
        String customerGNIC2 = txtGNIC2.getText();
        String customerGNIC3 = txtGNIC3.getText();
        String invoice = null;
        if(!txtCusTelePhoneNumber.getText().matches("[0-9]+"))
        {
          Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Invalid Telephone Number !!!!!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();  
        }
        else {
        //Get today date
        Calendar calendar = Calendar.getInstance();
        java.sql.Date customerDate = new java.sql.Date(calendar.getTime().getTime());
        
        if(chbHirePurchase.isSelected())
        {
            if(txtGName1.getText().isEmpty() || txtGName2.getText().isEmpty() || txtGName3.getText().isEmpty())
            {
                  Alert alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Stock Management System");
                  alert.setHeaderText("Please fill all fileds in Guarantor details !!!!!");
                  Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                  stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                  alert.showAndWait(); 
            }
             else
            {
                 //Update bike table
                String update = " UPDATE bike SET B_customerID = '"+customerNIC+"' WHERE B_bikeChassicNumber = '"+txtSearchChassicNumber.getText()+"'"; 
                db.putData(update);

             //Update Stock details (Stock table)
                String queryStock = " SELECT * FROM showroom_stock where SS_bikeModel = '"+bikeModel+"' AND SS_bikeColor = '"+bikeColor+"' ";
                String oldStockQtys;
                int newStockQty,oldStockQty = 0;
                    rs=db.getData(queryStock);
                    while(rs.next()) {
                        oldStockQtys = rs.getString("SS_stockQuantity");
                        oldStockQty = Integer.parseInt(oldStockQtys);
                    }
                    newStockQty = oldStockQty-1; //Decrease quntity by 1
                String updateStock = " UPDATE showroom_stock SET SS_stockQuantity = '"+newStockQty+"' WHERE  SS_bikeModel = '"+bikeModel+"' AND SS_bikeColor = '"+bikeColor+"' "; 
                db.putData(updateStock);

             //Create New Account
                LocalDate localDate = LocalDate.now();
                int accountnumber = 0;
                int accountBalance = Integer.parseInt(lblBikeAmount.getText())-Integer.parseInt(txtDownPayment.getText());
                String newAccount = " INSERT INTO customer_account (Account_cusNIC, Account_bikeValue, Account_downPayment, Account_balance, Account_createDate, Account_status) VALUES('" +txtCusNIC.getText()+ "', '" +lblBikeAmount.getText()+ "', '" +txtDownPayment.getText()+ "', '" +accountBalance+ "', '" +localDate+ "', '1') "; 
                db.putData(newAccount);
                //------------------------ Get Account Number
                String getAccountNumber = " SELECT * FROM customer_account where Account_cusNIC = '"+txtCusNIC.getText()+"' AND Account_createDate = '"+localDate+"' ";
                rs=db.getData(getAccountNumber);
                while(rs.next()) {
                    accountnumber = Integer.parseInt(rs.getString("Account_number"));

                    //Insert data to Hire purchesed Customer Table
                    String hirePurchesedCustomer = " INSERT INTO hire_purchesed_customer (HC_fName, HC_lName, HC_address, HC_city, HC_nic, HC_telNo, HC_accountNumber, GName1, GAddress1, GNIC1, GName2, GAddress2, GNIC2, GName3, GAddress3, GNIC3) VALUES('" +txtCusFName.getText()+ "', '" +txtCusLName.getText()+ "', '" +txtCusAddress.getText()+ "', '" +cmbCusCity.getSelectionModel().getSelectedItem()+ "', '" +txtCusNIC.getText()+ "', '" +txtCusTelePhoneNumber.getText()+ "', '" +accountnumber+ "', '" +txtGName1.getText()+ "', '" +txtGAddress1.getText()+ "', '" +txtGNIC1.getText()+ "', '" +txtGName2.getText()+ "', '" +txtGAddress2.getText()+ "', '" +txtGNIC2.getText()+ "', '" +txtGName3.getText()+ "', '" +txtGAddress3.getText()+ "', '" +txtGNIC3.getText()+ "') "; 
                    db.putData(hirePurchesedCustomer);

                    //Generate Cover Note
                    String generateCoverNote = " INSERT INTO cover_note (CN_issuedDate, CN_companyName, CN_schemeAmount, CN_accountNumber) VALUES('" +localDate+ "', '" +cmbInsuranceCompany.getSelectionModel().getSelectedItem()+ "', '" +cmbScheme.getSelectionModel().getSelectedItem()+ "', '" +accountnumber+ "') "; 
                    db.putData(generateCoverNote);

                    //Generate Invoice
                    int x = 0;
                    String y = null;
                    String inc = " SELECT * FROM invoice";
                    rs=db.getData(inc);
                      while(rs.next()) {
                          y = rs.getString("Invoice_ID");
                          x = Integer.parseInt(y)+1;
                      }
                      if (y == null || y == "")
                      {
                          invoice = "Inv0";
                      }
                      else
                      {
                          invoice = "Inv"+String.valueOf(x);
                      }
                    String generateInvoice = " INSERT INTO invoice (Invoice_Number, Invoice_issuedDate, Invoice_Amount, Invoice_accountNumber) VALUES('" +invoice+ "', '" +localDate+ "', '" +txtDownPayment.getText()+ "', '" +accountnumber+ "') "; 
                    db.putData(generateInvoice);

                    //Initialize Monthly Payment
                       //Calculate next payment date
                       SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
                       final Date date = df.parse(localDate.toString()); // conversion from String
                       final java.util.Calendar cal = GregorianCalendar.getInstance();
                       cal.setTime( date );
                       cal.add( GregorianCalendar.MONTH, 1 ); // date manipulation
                       String localdueDate = df.format( cal.getTime() ) ;
                    String initializeMonthlyPayment = " INSERT INTO monthly_installment (MI_amount, MI_paymentDate, MI_nextPaymentDate, MI_accountNumber, MI_invoiceNumber) VALUES('" +txtDownPayment.getText()+ "', '" +localDate+ "', '" +localdueDate+ "', '" +accountnumber+ "', '" +invoice+ "') "; 
                    db.putData(initializeMonthlyPayment);
                }

            Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Stock Management System");
                    alert.setHeaderText("Successfully Hire Purches Sale !!!!!");
                    alert.setContentText("");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                    alert.showAndWait();

            //Print Receipt        
            printInvoice(txtCusFName.getText()+" "+txtCusLName.getText(), txtCusAddress.getText()+","+cmbCusCity.getSelectionModel().getSelectedItem(), txtCusNIC.getText(), invoice, String.valueOf(accountnumber), lblMake.getText(), lblModel.getText(), lblColor.getText(), txtSearchChassicNumber.getText(), lblBikeValue.getText(), "1", txtDownPayment.getText(), txtDownPayment.getText(), txtDownPayment.getText());

             //Reset 
             chbHirePurchase.setSelected(false);
             txtDownPayment.setDisable(true);
             cmbCusCity.getSelectionModel().clearSelection();
             cmbInsuranceCompany.setDisable(true);
             cmbScheme.setDisable(true);
             cmbInsuranceCompany.getSelectionModel().clearSelection();
             cmbScheme.getItems().clear();
             txtGName1.setDisable(true);
             txtGAddress1.setDisable(true);
             txtGNIC1.setDisable(true);
             txtGName2.setDisable(true);
             txtGAddress2.setDisable(true);
             txtGNIC2.setDisable(true);
             txtGName3.setDisable(true);
             txtGAddress3.setDisable(true);
             txtGNIC3.setDisable(true);

             txtCusFName.setText("");
             txtCusLName.setText("");
             txtCusNIC.setText("");
             txtCusAddress.setText("");
             txtCusTelePhoneNumber.setText("");
             txtDownPayment.setText("");
             txtGName1.setText("");
             txtGName2.setText("");
             txtGName3.setText("");
             txtGAddress1.setText("");
             txtGAddress2.setText("");
             txtGAddress3.setText("");
             txtGNIC1.setText("");
             txtGNIC2.setText("");
             txtGNIC3.setText("");
             lblModel.setText("");
             lblMake.setText("");
             lblBikeValue.setText("");
             lblEngineNumber.setText("");
             lblColor.setText("");   
            }
        }
        
        else {
              try
               {
                //Insert Data to Ordinary Customer Database
                String insert = " INSERT INTO full_payment_customer (FPC_NIC, FPC_fName, FPC_lName, FPC_address, FPC_city, FPC_telNumber, FPC_date) VALUES ('"+customerNIC+"', '"+customerFName+"', '"+customerLName+"', '"+customerAddress+"', '" +cmbCusCity.getSelectionModel().getSelectedItem()+ "', '"+customerTPNumber+"', '"+customerDate+"')"; 
                db.putData(insert);
                System.out.println("--------Customer details insert successfully");

                //Update bike table
                String update = " UPDATE bike SET B_customerID = '"+customerNIC+"' WHERE B_bikeChassicNumber = '"+txtSearchChassicNumber.getText()+"'"; 
                db.putData(update);

                //Update Stock details (Stock table)
                String queryStock = " SELECT * FROM showroom_stock where SS_bikeModel = '"+bikeModel+"' AND SS_bikeColor = '"+bikeColor+"' ";
                String oldStockQtys;
                int newStockQty,oldStockQty = 0;
                  rs=db.getData(queryStock);
                  while(rs.next()) {
                      oldStockQtys = rs.getString("SS_stockQuantity");
                      oldStockQty = Integer.parseInt(oldStockQtys);
                  }
                  newStockQty = oldStockQty-1; //Decrease quntity by 1
                String updateStock = " UPDATE showroom_stock SET SS_stockQuantity = '"+newStockQty+"' WHERE  SS_bikeModel = '"+bikeModel+"' AND SS_bikeColor = '"+bikeColor+"' "; 
                db.putData(updateStock);

                //Generate Invoice
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.now();
                int x = 0;
                String y = null;
                String inc = " SELECT * FROM invoice";
                rs=db.getData(inc);
                  while(rs.next()) {
                      y = rs.getString("Invoice_ID");
                      x = Integer.parseInt(y)+1;
                  }
                  if (y == null || y == "")
                  {
                      invoice = "Inv0";
                  }
                  else
                  {
                      invoice = "Inv"+String.valueOf(x);
                  }
                String generateInvoice = " INSERT INTO invoice (Invoice_Number, Invoice_issuedDate, Invoice_Amount, Invoice_accountNumber) VALUES('" +invoice+ "', '" +localDate+ "', '" +lblBikeAmount.getText()+ "', '" +txtCusNIC.getText()+ "') "; 
                db.putData(generateInvoice);
                
                //Open dialog box
                Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Stock Management System");
                 alert.setHeaderText("Successfully Sale !!!!");
                 alert.setContentText(" You have reporting sale successfully. \nNow you can print invoice to this sale.\n\nThank You..");
                 Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                 stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                 alert.showAndWait();
                 
                //Print Receipt        
               printInvoice(txtCusFName.getText()+" "+txtCusLName.getText(), txtCusAddress.getText()+","+cmbCusCity.getSelectionModel().getSelectedItem(), txtCusNIC.getText(), invoice, "N/A", lblMake.getText(), lblModel.getText(), lblColor.getText(), txtSearchChassicNumber.getText(), lblBikeValue.getText(), "1", "N/A", lblBikeValue.getText(), lblBikeValue.getText());
         
                 
                //Reset 
                txtCusFName.setText("");
                txtCusLName.setText("");
                txtCusNIC.setText("");
                txtCusAddress.setText("");
                txtCusTelePhoneNumber.setText("");
                cmbCusCity.getSelectionModel().clearSelection();
                txtDownPayment.setText("");
                txtGName1.setText("");
                txtGName2.setText("");
                txtGName3.setText("");
                txtGAddress1.setText("");
                txtGAddress2.setText("");
                txtGAddress3.setText("");
                txtGNIC1.setText("");
                txtGNIC2.setText("");
                txtGNIC3.setText("");
                lblModel.setText("");
                lblMake.setText("");
                lblBikeValue.setText("");
                lblEngineNumber.setText("");
                lblColor.setText("");
               }
              catch (Exception e) {
                 System.err.println(e.getMessage());
               } finally {
                 if (con != null) {
                    con.close();
                 }
               }
            }//End Else
        }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Input Error");
            alert.setContentText("Please fill all filed or check you given values are correctly");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait(); 
        }
    }
    
    private void printInvoice(String cusName, String cusAddress, String cusNIC, String invoiceNo, String accNo, String brand, String model, String color, String chsNo, String value, String qty, String dp, String total, String totalAmount)
    {
        try {
            String reportName = "stockmanagementsystem/reports/Invoice.jasper";
            String img = "stockmanagementsystem/images/logoAbans.png";
            
            HashMap map = new HashMap();
            map.put("cusName", cusName);
            map.put("cusAddress", cusAddress);
            map.put("cusNIC", cusNIC);
            map.put("invoiceNo", invoiceNo);
            map.put("accountNo", accNo);
            map.put("brand", brand);
            map.put("model", model);
            map.put("color", color);
            map.put("chassicNo", chsNo);
            map.put("value", value);
            map.put("qty", qty);
            map.put("downPayment", dp);
            map.put("total", total);
            map.put("totalInvoiceAmount", totalAmount);
            map.put("logoImage", img);

            //Get a stream to read the file
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportName);
            JasperPrint jp = JasperFillManager.fillReport(is, map, new JREmptyDataSource());
            
            //Viewer for JasperReport
            JRViewer jv = new JRViewer(jp);
            
            JDialog jf = new JDialog();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setAlwaysOnTop(true);
            jf.setSize(new Dimension(1200, 600));
            jf.setLocationRelativeTo(null);
            jf.setModal(true);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void checkHirePurchase(MouseEvent event) {
        if(chbHirePurchase.isSelected())
        {
          if(txtDownPayment.isDisable() == false)
          {
            txtDownPayment.setDisable(true);
            cmbInsuranceCompany.setDisable(true);
            cmbScheme.setDisable(true);
            txtGName1.setDisable(true);
            txtGAddress1.setDisable(true);
            txtGNIC1.setDisable(true);
            txtGName2.setDisable(true);
            txtGAddress2.setDisable(true);
            txtGNIC2.setDisable(true);
            txtGName3.setDisable(true);
            txtGAddress3.setDisable(true);
            txtGNIC3.setDisable(true);
          }
        }
        else
        {
         if(txtDownPayment.isDisable() == true)
          {
            txtDownPayment.setDisable(false);
            cmbInsuranceCompany.setDisable(false);
            cmbScheme.setDisable(false);
            txtGName1.setDisable(false);
            txtGAddress1.setDisable(false);
            txtGNIC1.setDisable(false);
            txtGName2.setDisable(false);
            txtGAddress2.setDisable(false);
            txtGNIC2.setDisable(false);
            txtGName3.setDisable(false);
            txtGAddress3.setDisable(false);
            txtGNIC3.setDisable(false);
          }
        }
        
    }

    @FXML
    private void searchChassicNumber(ActionEvent event) throws SQLException {
        String userEnterChassicNumber = txtSearchChassicNumber.getText();
        
     try{
         String query1 = " SELECT * FROM bike where B_bikeChassicNumber = '"+userEnterChassicNumber+"' ";
         rs=db.getData(query1);
        if (userEnterChassicNumber.isEmpty())
        {
            Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Stock Management System");
                        alert.setHeaderText("Please Enter Chassic Number first !!!!");
                        alert.setContentText("");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                        alert.showAndWait();
        }
        else
        {
             if (!rs.next())
             {
                 Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Stock Management System");
                                alert.setHeaderText("Invalid Chassic Number !!!!");
                                alert.setContentText("");
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                                alert.showAndWait();
             }
             else
             {
                 String query0 = " SELECT * FROM bike where B_bikeChassicNumber = '"+userEnterChassicNumber+"' ";
                 rs=db.getData(query0); 
                 while(rs.next()) {
                    bikeModel = rs.getString("B_bikeModel");
                    bikeMake = rs.getString("B_bikeMake");
                    bikeValue = rs.getString("B_bikeValue"); 
                    bikeColor = rs.getString("B_bikeColor");
                    engineNumber = rs.getString("B_bikeEngineNumber");
                    customerId = rs.getString("B_customerID");
                    lblBikeAmount.setText(bikeValue);
                    if(customerId == null)
                    {
                        lblModel.setText(bikeModel);
                        lblMake.setText(bikeMake);
                        lblBikeValue.setText("Rs. "+bikeValue);
                        lblEngineNumber.setText(engineNumber);
                        lblColor.setText(bikeColor);
                    }
                    else
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Stock Management System");
                        alert.setHeaderText("Already reported !!!!!");
                        alert.setContentText("This bike is already sold out throught the system. Please check you entered chassic number is correct or not.");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                        alert.showAndWait();

                        txtSearchChassicNumber.setText("");
                    }
                 }
             }
        }
        }
        catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, "Login Error", "System error", JOptionPane.ERROR);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @FXML
    private void clear_allTextField(ActionEvent event) {
         txtSearchChassicNumber.setText("");
         chbHirePurchase.setSelected(false);
         txtDownPayment.setDisable(true);
         cmbCusCity.getSelectionModel().clearSelection();
         cmbInsuranceCompany.getSelectionModel().clearSelection();
         cmbInsuranceCompany.setDisable(true);
         cmbScheme.setDisable(true);
         txtGName1.setDisable(true);
         txtGAddress1.setDisable(true);
         txtGNIC1.setDisable(true);
         txtGName2.setDisable(true);
         txtGAddress2.setDisable(true);
         txtGNIC2.setDisable(true);
         txtGName3.setDisable(true);
         txtGAddress3.setDisable(true);
         txtGNIC3.setDisable(true);
         
         txtCusFName.setText("");
         txtCusLName.setText("");
         txtCusNIC.setText("");
         txtCusAddress.setText("");
         txtCusTelePhoneNumber.setText("");
         txtDownPayment.setText("");
         txtGName1.setText("");
         txtGName2.setText("");
         txtGName3.setText("");
         txtGAddress1.setText("");
         txtGAddress2.setText("");
         txtGAddress3.setText("");
         txtGNIC1.setText("");
         txtGNIC2.setText("");
         txtGNIC3.setText("");
         lblModel.setText("");
         lblMake.setText("");
         lblBikeValue.setText("");
         lblEngineNumber.setText("");
         lblColor.setText("");
         cmbInsuranceCompany.getSelectionModel().clearSelection();
         cmbScheme.getItems().clear();
    }

    @FXML
    private void selectInsuranceCompany(ActionEvent event) {
        
        if(txtSearchChassicNumber.getText() == "")
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Input Error !!!!!");
            alert.setContentText("Please enter chassic number first");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
        }
        else
        {
         try{
             String query = " SELECT * FROM insurance_company WHERE IC_companyName = '"+cmbInsuranceCompany.getSelectionModel().getSelectedItem()+"' AND IC_bikeValue = '" +lblBikeAmount.getText()+ "' ";
             rs=db.getData(query);
             while(rs.next()) {
               cmbScheme.setEditable(false);
               cmbScheme.getItems().addAll(rs.getString("IC_schemeM3")); 
               cmbScheme.setEditable(false);
               cmbScheme.getItems().addAll(rs.getString("IC_schemeM6")); 
               cmbScheme.setEditable(false);
               cmbScheme.getItems().addAll(rs.getString("IC_schemeM9")); 
               cmbScheme.setEditable(false);
               cmbScheme.getItems().addAll(rs.getString("IC_schemeM12")); 
               cmbScheme.setEditable(false);
               cmbScheme.getItems().addAll(rs.getString("IC_schemeM24")); 
               cmbScheme.setEditable(false);
               cmbScheme.getItems().addAll(rs.getString("IC_schemeM36")); 
             }
            } catch (Exception e) {
             e.printStackTrace();
            } finally {
             if (con != null) {
                 try {
                     con.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(FXML_01_reportSaleController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
            }
        }
    }
}
