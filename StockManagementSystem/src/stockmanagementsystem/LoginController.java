/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import stockmanagementsystem.util.DBConnection;

/**
 *
 * @author DEWSRI DE MEL
 */
public class LoginController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    @FXML
    private Label lblError;
    @FXML
    private TextField txtEPFNumber;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnEnter;
    @FXML
    private Button btnExit;
    
    @FXML
    private void enterMainMenu(ActionEvent event) throws SQLException, NoSuchAlgorithmException, NoSuchProviderException {
        
        String epfNumber = txtEPFNumber.getText();
        int password = txtPassword.getText().hashCode();
        String epfFrmDB = null;
        String passwordFrmDB = null;
        String userFirstNameFrmDB = null;
        String userLastNameFrmDB = null;
        String userEmailFrmDB = null;
        String userAccessFrmDB = null;
        int accessType = 0;
        try{
            String query = " SELECT * FROM manager where Manager_epf = '"+epfNumber+"' AND Manager_password = '"+password+"'";
            rs=db.getData(query);
            while(rs.next()) {
             epfFrmDB = rs.getString("Manager_epf");
             userFirstNameFrmDB = rs.getString("Manager_fName");
             userLastNameFrmDB = rs.getString("Manager_lName");
             passwordFrmDB = rs.getString("Manager_password");
             userEmailFrmDB = rs.getString("Manager_email");
             accessType = 1;
            }
            if(epfFrmDB != null)
            {
             FXMLLoader Loader = new FXMLLoader();
             Loader.setLocation(getClass().getResource("mainMenu.fxml"));
             try {
                Loader.load();
             } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             //Passing Employee details to Main Menu using SetText fuction
             MainMenuController display = Loader.getController();
             display.setText(epfFrmDB,userFirstNameFrmDB,userLastNameFrmDB+" (Manager)",accessType);
             
             Parent Admin_parent = Loader.getRoot();
             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             app_stage.setScene(new Scene(Admin_parent));
            }
            else
            {
              String query2 = " SELECT * FROM executive where Exe_epfNo = '"+epfNumber+"' AND Exe_password = '"+password+"'";
              rs=db.getData(query2);
              while(rs.next()) {
              epfFrmDB = rs.getString("Exe_epfNo");
              userFirstNameFrmDB = rs.getString("Exe_fName");
              userLastNameFrmDB = rs.getString("Exe_lName");
              passwordFrmDB = rs.getString("Exe_password");
              userEmailFrmDB = rs.getString("Exe_email");
              userAccessFrmDB = rs.getString("Exe_access");
              accessType = Integer.parseInt(userAccessFrmDB);
              }
             if(epfFrmDB != null)
             {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("mainMenu.fxml"));
                try {
                   Loader.load();
                } catch (IOException ex) {
                   Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                MainMenuController display = Loader.getController();
                display.setText(epfFrmDB,userFirstNameFrmDB,userLastNameFrmDB,accessType);
             
                Parent Admin_parent = Loader.getRoot();
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(new Scene(Admin_parent));
             }
             else
             {
               lblError.setText("Invalid User Name or Password");
             }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Login Error", "System error", JOptionPane.ERROR);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    @FXML
    private void exitFromLoginMenu(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enterEpf(MouseEvent event) {
        lblError.setText(null);
    }
    
    @FXML
    private void enterPassword(MouseEvent event) {
        lblError.setText(null);
    }
}
