/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.awt.Dimension;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import stockmanagementsystem.util.DBConnection;
import stockmanagementsystem.util.setDataToTable;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_04_monthlyInstallmentController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @FXML
    private ComboBox<String> cmbAccountNumber;
    @FXML
    private Label lblInsFee;
    @FXML
    private Label lblBalance;
    @FXML
    private Label lblNIC;
    @FXML
    private Label lblName;
    
    int cusAcNumber;
    String cusAccountNumber = null;
    String cusFname = null;
    String cusLname = null;
    String cusNIC = null;
    String cusClosingBalance = null;
    String cusInsFee = null;
    String monthlyPaymentAmount = null;
    String monthlyPaymentPayDate = null;
    String monthlyPaymentInNum = null;
    
    @FXML
    private TableView<setDataToTable> tblPayment;
    @FXML
    private TableColumn<setDataToTable, String> col_paymentDueDate;
    @FXML
    private TableColumn<setDataToTable, String> col_amount;
    @FXML
    private TableColumn<setDataToTable, String> col_paymentDate;
    @FXML
    private TableColumn<setDataToTable, String> col_invoiceNumber;
    @FXML
    private TextField txtAmount;
    @FXML
    private Button btnSubmit;
    @FXML
    private Label lblID;
    @FXML
    private Label lblDueDate;
    @FXML
    private Label lblInvoiceNo;
    String monthlyPaymentID = null;
    String monthlyPaymentDueDate = null;
    String monthlyPaymentInvoice = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
         String query = " SELECT Account_number FROM customer_account";
         rs=db.getData(query);
         while(rs.next()) {
           cusAcNumber  = rs.getInt("Account_number");
           cusAccountNumber = Integer.toString(cusAcNumber);
           
           cmbAccountNumber.setEditable(false);
           cmbAccountNumber.getItems().addAll(cusAccountNumber);
         }
        } catch (Exception e) {
         e.printStackTrace();
        } finally {
         if (con != null) {
             try {
                 con.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FXML_03_viewBikeController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        }
    }

    ObservableList<setDataToTable> oblist = FXCollections.observableArrayList();    

    @FXML
    private void select_account(ActionEvent event) {
        lblName.setText("");
        lblNIC.setText("");
        lblBalance.setText("");
        lblInsFee.setText("");
        lblID.setText("");
        lblDueDate.setText("");
        lblInvoiceNo.setText("");
        txtAmount.setText("");
        oblist.clear();
        try{
         String query = " SELECT Account_balance, Account_number FROM customer_account WHERE Account_number = '"+cmbAccountNumber.getSelectionModel().getSelectedItem()+"'";
         rs=db.getData(query);
         while(rs.next()) {
           cusClosingBalance = rs.getString("Account_balance");
           String query2 = " SELECT * FROM hire_purchesed_customer WHERE HC_accountNumber = '"+cmbAccountNumber.getSelectionModel().getSelectedItem()+"'";
           rs=db.getData(query2);
           while(rs.next())
           {
             cusFname = rs.getString("HC_fName");
             cusLname = rs.getString("HC_lName");
             cusNIC = rs.getString("HC_nic");
             String accountNumber = rs.getString("HC_accountNumber");
             
             lblBalance.setText(cusClosingBalance);
             lblNIC.setText(cusNIC);
             lblName.setText(cusFname+" "+cusLname);
             String getInsFee = " SELECT * FROM cover_note WHERE CN_accountNumber = '"+accountNumber+"'";
             rs=db.getData(getInsFee);
             while(rs.next())
             {
                cusInsFee = rs.getString("CN_schemeAmount");
                lblInsFee.setText(cusInsFee);
             }
           }
         }
         
         //retrive payment data to table
             String query3 = " SELECT * FROM monthly_installment where MI_accountNumber = '"+cmbAccountNumber.getSelectionModel().getSelectedItem()+"' ";
             rs=db.getData(query3);
                while(rs.next()) {
                   monthlyPaymentDueDate = rs.getString("MI_nextPaymentDate");
                   lblDueDate.setText(monthlyPaymentDueDate);
                   monthlyPaymentAmount = rs.getString("MI_amount");
                   monthlyPaymentDueDate = rs.getString("MI_paymentDueDate");
                   monthlyPaymentPayDate  = rs.getString("MI_paymentDate");
                   monthlyPaymentInNum  = rs.getString("MI_invoiceNumber");
                   oblist.add(new setDataToTable(monthlyPaymentDueDate, monthlyPaymentPayDate, "Rs. "+monthlyPaymentAmount, monthlyPaymentInNum));
                 }
             col_paymentDueDate.setCellValueFactory(new PropertyValueFactory<>("paymentDueDate"));
             col_paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
             col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
             col_invoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
             tblPayment.setItems(oblist);
        } catch (Exception e) {
         e.printStackTrace();
        } finally {
         if (con != null) {
             try {
                 con.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FXML_03_viewBikeController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        }
    }

    @FXML
    private void insert_monthlyInstalment(ActionEvent event) throws SQLException, ParseException, ClassNotFoundException {
       if(lblName.getText()=="" && lblNIC.getText()=="" && lblBalance.getText()=="" && lblInsFee.getText()=="")
       {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Please select Account Number First !!!!!");
            alert.setContentText("");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
       }
       else if(txtAmount.getText() == "" || txtAmount.getText().isEmpty())
       {
       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Please enter payment amount !!!!!");
            alert.setContentText("");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
       }
       else
       {
        int x = 0;
        String y = null;
        String invoiceNumber = null;
        String inc = " SELECT * FROM invoice";
        rs=db.getData(inc);
        while(rs.next()) {
            y = rs.getString("Invoice_ID");
            x = Integer.parseInt(y)+1;
        }
          if (y == null || y == "")
          {
            invoiceNumber = "Inv0";
          }
          else
          {
            invoiceNumber = "Inv"+String.valueOf(x);
          }
       
       //Calculate next payment date
       SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
       final Date date = df.parse( lblDueDate.getText() ); // conversion from String
       final java.util.Calendar cal = GregorianCalendar.getInstance();
       cal.setTime( date );
       cal.add( GregorianCalendar.MONTH, 1 ); // date manipulation
       String localdueDate = df.format( cal.getTime() ) ;
       
       int accountNumber = Integer.parseInt(cmbAccountNumber.getValue());
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
       LocalDate localDate = LocalDate.now();
       
       try {
               String insert = " INSERT INTO monthly_installment (MI_amount, MI_paymentDate, MI_paymentDueDate, MI_nextPaymentDate, MI_accountNumber, MI_invoiceNumber) VALUES('" +Float.parseFloat(txtAmount.getText())+ "', '" +dtf.format(localDate)+ "', '" +lblDueDate.getText()+ "', '" +localdueDate+ "', " +accountNumber+ ", '" +invoiceNumber+ "') "; 
               db.putData(insert);
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stock Management System");
                alert.setHeaderText("Installment is paid successfully !!!!!");
                alert.setContentText("");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                alert.showAndWait();
                
             oblist.removeAll(oblist);
             String query3 = " SELECT * FROM monthly_installment where MI_accountNumber = '"+cmbAccountNumber.getSelectionModel().getSelectedItem()+"' ";
             rs=db.getData(query3);
                while(rs.next()) {
                   monthlyPaymentID = rs.getString("MI_ID");
                   monthlyPaymentDueDate = rs.getString("MI_nextPaymentDate");
                   lblID.setText(monthlyPaymentID);
                   lblDueDate.setText(monthlyPaymentDueDate);
                   monthlyPaymentAmount = rs.getString("MI_amount");
                   monthlyPaymentDueDate = rs.getString("MI_paymentDueDate");
                   monthlyPaymentPayDate  = rs.getString("MI_paymentDate");
                   monthlyPaymentInNum  = rs.getString("MI_invoiceNumber");
                   oblist.add(new setDataToTable(monthlyPaymentDueDate, monthlyPaymentPayDate, "Rs. "+monthlyPaymentAmount, monthlyPaymentInNum));
                 }
             col_paymentDueDate.setCellValueFactory(new PropertyValueFactory<>("paymentDueDate"));
             col_paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
             col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
             col_invoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
             tblPayment.setItems(oblist);
             
             //Update Balance
             int newBalance, oldBalance;
             String selectAccount = " SELECT * FROM customer_account where Account_number = '"+accountNumber+"' AND Account_cusNIC = '"+ lblNIC.getText() +"' ";
             rs=db.getData(selectAccount);
             while(rs.next()) {
                 oldBalance = Integer.parseInt(rs.getString("Account_balance"));
                 newBalance = oldBalance - Integer.parseInt(txtAmount.getText());
                 String update = " UPDATE customer_account SET Account_balance = '"+newBalance+"' WHERE Account_number = '"+accountNumber+"' AND Account_cusNIC = '"+ lblNIC.getText() +"' "; 
                 db.putData(update);
                 lblBalance.setText(String.valueOf(newBalance));
                 if(newBalance <= 0)
                 {
                     String status = " UPDATE customer_account SET Account_status = '0' WHERE Account_number = '"+accountNumber+"' AND Account_cusNIC = '"+ lblNIC.getText() +"' "; 
                     db.putData(status);
                 }
             }
             
             //Generate Invoice
             float amountInvoice = Float.parseFloat(txtAmount.getText())+Float.parseFloat(lblInsFee.getText());
             String amountInvoiceToString = String.valueOf(amountInvoice);
             String generateInvoice = " INSERT INTO invoice (Invoice_Number, Invoice_issuedDate, Invoice_Amount, Invoice_accountNumber) VALUES('" +invoiceNumber+ "', '" +localDate+ "', '" +amountInvoice+ "', '" +accountNumber+ "') "; 
             db.putData(generateInvoice);
             
             //Print Invoice
             String customerName = null;
             String customerAddress = null;
             String customerNIC = null;
             String selectCustomer = " SELECT * FROM hire_purchesed_customer where HC_accountNumber = '"+accountNumber+"' AND HC_nic = '"+ lblNIC.getText() +"' ";
             rs=db.getData(selectCustomer);
             while(rs.next()) {
                 customerName = rs.getString("HC_fName")+ " " + rs.getString("HC_lName");
                 customerAddress = rs.getString("HC_address");
                 customerNIC = rs.getString("HC_nic");
             }
             printInvoice(customerName, customerAddress, customerNIC, invoiceNumber, cmbAccountNumber.getSelectionModel().getSelectedItem(), txtAmount.getText(), lblInsFee.getText(), amountInvoiceToString, amountInvoiceToString);
       }
       catch (Exception e) {
        System.err.println(e.getMessage());
       } finally {
         if (con != null) {
            con.close();
        }
       }
       }
    }
    
    private void printInvoice(String cusName, String cusAddress, String cusNIC, String invoiceNo, String accNo, String payment, String insFee, String total, String totalAmount) {
        try {
            String reportName = "stockmanagementsystem/reports/monthlyInvoice.jasper";
            String img = "stockmanagementsystem/images/logoAbans.png";
            
            HashMap map = new HashMap();
            map.put("cusName", cusName);
            map.put("cusAddress", cusAddress);
            map.put("cusNIC", cusNIC);
            map.put("invoiceNo", invoiceNo);
            map.put("accountNo", accNo);
            map.put("payment", payment);
            map.put("insFee", insFee);
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
            //System.out.println(e);
        }
    }

}
