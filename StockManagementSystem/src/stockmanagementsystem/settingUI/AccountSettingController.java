/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem.settingUI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import stockmanagementsystem.util.DBConnection;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class AccountSettingController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement ps = null;

    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtConfirmPassword;
    @FXML
    private Button btnConfirm;
    @FXML
    private PasswordField txtNewPassword;
    
    String accountType = null;
    String epf = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        InputStream ins = null;
        Reader r = null;
        BufferedReader br = null;
        String s;
        String epfNumber = null;
        
        try {
        ins = new FileInputStream("tmp\\temUserData.txt");
        r = new InputStreamReader(ins, "UTF-8");
        br = new BufferedReader(r);

        epfNumber = br.readLine();
        String getManager = " SELECT * FROM manager WHERE Manager_epf = '"+ epfNumber +"'";
        rs=db.getData(getManager);
        while(rs.next()) {
            epf = rs.getString("Manager_epf");
            txtFName.setText(rs.getString("Manager_fName"));
            txtLName.setText(rs.getString("Manager_lName"));
            lblEmail.setText(rs.getString("Manager_email"));
            accountType = "Manager";
        }
        if(txtFName.getText().isEmpty())
        {
          String getEmployee = " SELECT * FROM executive WHERE Exe_epfNo = '"+ epfNumber +"'";
          rs=db.getData(getEmployee);
          while(rs.next()) {
            epf = rs.getString("Exe_epfNo");
            txtFName.setText(rs.getString("Exe_fName"));
            txtLName.setText(rs.getString("Exe_lName"));
            lblEmail.setText(rs.getString("Exe_email"));
            accountType = "Employee";
          } 
        }
        
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }    

    @FXML
    private void change_password(ActionEvent event) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, NoSuchProviderException {
//        if(txtNewPassword.)
//        {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Stock Management System");
//            alert.setHeaderText("Please fill all fields !!!!!");
//            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
//            alert.showAndWait();
//        }
//        else if(txtNewPassword.getText().matches(txtConfirmPassword.getText()))
//        {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Stock Management System");
//            alert.setHeaderText("Password do not match !!!!!");
//            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
//            alert.showAndWait();
//        }
//        else
//        {
            //Convert to password to Hashcode
            int securePassword = txtNewPassword.getText().hashCode();
            if(accountType == "Manager")
            {
               String changePW = " UPDATE manager SET Manager_password = '"+securePassword+"' WHERE Manager_epf = '"+epf+"'"; 
               db.putData(changePW);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Stock Management System");
                alert.setHeaderText("Successfully change password !!!!!");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                alert.showAndWait();
            }
            else
            {
               String changePW = " UPDATE executive SET Exe_password = '"+securePassword+"' WHERE Exe_epfNo = '"+epf+"'"; 
               db.putData(changePW);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Stock Management System");
                alert.setHeaderText("Successfully change password !!!!!");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
                alert.showAndWait();
            }
//        }
    }
//    
//    private static String getSecurePassword(String passwordToHash, byte[] salt)
//    {
//        String generatedPassword = null;
//        try {
//            // Create MessageDigest instance for MD5
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(salt);
//            byte[] bytes = md.digest(passwordToHash.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for(int i=0; i< bytes.length ;i++)
//            {
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//            }
//            generatedPassword = sb.toString();
//        }
//        catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return generatedPassword;
//    }
//     
//    //Add salt
//    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException, java.security.NoSuchProviderException
//    {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return salt;
//    }
}
