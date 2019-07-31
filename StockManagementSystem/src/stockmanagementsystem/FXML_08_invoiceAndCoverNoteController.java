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
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
public class FXML_08_invoiceAndCoverNoteController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    public String insuranceAmount = null;
    public String bikeMake = null;
    public String bikeModel = null;
    public String bikeChassisNo = null;
    public String bikeEngineNo = null;
    public String insuranceCompany = null;

    @FXML
    private TextField txtInvoiceNumber;
    @FXML
    private Button btnSearchInvoice;
    @FXML
    private Label lblIssuedDate;
    @FXML
    private Label lblAmount;
    @FXML
    private Label lblAccountNumber;
    @FXML
    private Button btnInvoiceReprint;
    @FXML
    private TextField txtSearchAccountNo;
    @FXML
    private Button btnSearchAccount;
    @FXML
    private Button btnPrintCoverNote;
    @FXML
    private Label lblCoverNoteNo;
    @FXML
    private Label lblCoverNoteIssuedDate;
    @FXML
    private Label lblInsuranceCName;
    @FXML
    private Label lblCustomerName;
    @FXML
    private Label lblCustomerAddress;
    @FXML
    private Label lblNIC;
    @FXML
    private Label lblError;
    @FXML
    private Label lblErrorAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchInvoice(ActionEvent event) throws ClassNotFoundException, SQLException {
        lblIssuedDate.setText("");
        lblAmount.setText("");
        lblAccountNumber.setText("");
        lblError.setVisible(false);
        btnInvoiceReprint.setDisable(true);
        if(txtInvoiceNumber.getText() != "")
        {
            String selectInvoice = " SELECT * FROM invoice where Invoice_Number = '"+txtInvoiceNumber.getText()+"' ";
            rs=db.getData(selectInvoice);
            while(rs.next()) {
                lblIssuedDate.setText(rs.getString("Invoice_issuedDate"));
                lblAmount.setText(rs.getString("Invoice_Amount"));
                lblAccountNumber.setText(rs.getString("Invoice_accountNumber"));
            }
            if(lblIssuedDate.getText() != "" && lblAccountNumber.getText() != "")
            {
                btnInvoiceReprint.setDisable(false);
            }
            else
            {
                lblError.setVisible(true); 
            }
        }
        else
        {
            lblError.setVisible(true);
        }

    }
    
    @FXML
    private void reprint_invoices(ActionEvent event) throws ClassNotFoundException, SQLException {
        //Get Customer Details
        String customerName = null;
        String customerAddress = null;
        String customerNIC = null;
        String selectCustomer = " SELECT * FROM hire_purchesed_customer where HC_accountNumber = '"+lblAccountNumber.getText()+"' ";
        rs=db.getData(selectCustomer);
        while(rs.next()) {
            customerName = rs.getString("HC_fName")+ " " + rs.getString("HC_lName");
            customerAddress = rs.getString("HC_address");
            customerNIC = rs.getString("HC_nic");
        }
        //Get Insurance Details
        String cusInsFee = null;
        String amount = null;
        String getInsFee = " SELECT * FROM cover_note WHERE CN_accountNumber = '"+lblAccountNumber.getText()+"'";
        rs=db.getData(getInsFee);
        while(rs.next())
        {
            cusInsFee = rs.getString("CN_schemeAmount");
            amount = String.valueOf(Integer.parseInt(lblAmount.getText()) - Integer.parseInt(cusInsFee));
        }
        printInvoice(customerName, customerAddress, customerNIC, txtInvoiceNumber.getText(), lblAccountNumber.getText(), amount, cusInsFee, lblAmount.getText(), lblAmount.getText());
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
        
    @FXML
    private void print_coverNote(ActionEvent event) {
        try {
            String reportName = "stockmanagementsystem/reports/coverNoteNew.jasper";
            String img = null;
            if(insuranceCompany.matches("Janashakthi Insurance"))
            {
               img = "stockmanagementsystem/images/janashakthi.jpg";
            }
            else
            {
               img = "stockmanagementsystem/images/conti.jpg"; 
            }
            
            HashMap map = new HashMap();
            map.put("coverNoteNumber", lblCoverNoteNo.getText());
            map.put("cusName", lblCustomerName.getText());
            map.put("cusAddress", lblCustomerAddress.getText());
            map.put("sumTotal", insuranceAmount);
            map.put("bikeMake", bikeMake);
            map.put("bikeModel", bikeModel);
            map.put("chassisNumber", bikeChassisNo);
            map.put("EngineNumber", bikeEngineNo);
            map.put("logo", img);

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

    @FXML
    private void searchAccount(ActionEvent event) throws ClassNotFoundException, SQLException {
        lblCoverNoteNo.setText("");
        lblCoverNoteIssuedDate.setText("");
        lblInsuranceCName.setText("");
        lblCustomerName.setText("");
        lblCustomerAddress.setText("");
        lblNIC.setText("");
        btnPrintCoverNote.setDisable(true);
        String q1 = " SELECT * FROM cover_note where CN_accountNumber = '"+txtSearchAccountNo.getText()+"' ";
        rs=db.getData(q1);
        while(rs.next()) {
            lblCoverNoteNo.setText(rs.getString("CN_number"));
            lblCoverNoteIssuedDate.setText(rs.getString("CN_issuedDate"));
            lblInsuranceCName.setText(rs.getString("CN_companyName"));
            insuranceCompany = rs.getString("CN_companyName");
            insuranceAmount = rs.getString("CN_schemeAmount");
        }
        String q2 = " SELECT * FROM hire_purchesed_customer where HC_accountNumber = '"+txtSearchAccountNo.getText()+"' ";
        rs=db.getData(q2);
        while(rs.next()) {;
            lblCustomerName.setText(rs.getString("HC_fName")+" "+rs.getString("HC_lName"));
            lblCustomerAddress.setText(rs.getString("HC_address")+","+rs.getString("HC_city"));
            lblNIC.setText(rs.getString("HC_nic"));
        }
        if(lblNIC.getText().isEmpty())
        {
            lblErrorAccount.setVisible(true);
        }
        else
        {
            btnPrintCoverNote.setDisable(false);
        }
        String q3 = " SELECT * FROM bike where B_customerID = '"+lblNIC.getText()+"' ";
        rs=db.getData(q3);
        while(rs.next()) {
            bikeMake = rs.getString("B_bikeMake");
            bikeModel = rs.getString("B_bikeModel");
            bikeChassisNo = rs.getString("B_bikeChassicNumber");
            bikeEngineNo = rs.getString("B_bikeEngineNumber"); 
        }
    }

    @FXML
    private void typeAccountNumber(MouseEvent event) {
        lblErrorAccount.setVisible(false);
    }
    
}
