/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem.settingUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import stockmanagementsystem.util.DBConnection;
import stockmanagementsystem.util.setOrderMenu;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class AdminSettingController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @FXML
    private TextField txtSearchEmployee;
    @FXML
    private Button btnEmpSearch;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblEmail;
    @FXML
    private Button btnConfirmGive;
    @FXML
    private TextField txtSearchEmployeeRemove;
    @FXML
    private Button btnSearchRemoveEmp;
    @FXML
    private Label lblFullNameRemove;
    @FXML
    private Button btnConfirmtoRemove;
    
    public int access;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void search_employee(ActionEvent event) throws ClassNotFoundException, SQLException {
        String query = " SELECT * FROM executive where Exe_epfNo = '"+txtSearchEmployee.getText()+"' ";
        rs=db.getData(query);
        while(rs.next()) {
            lblFullName.setText(rs.getString("Exe_fName")+" "+rs.getString("Exe_lName"));
            lblEmail.setText(rs.getString("Exe_email"));
            access = Integer.parseInt(rs.getString("Exe_access"));
        }
    }

    @FXML
    private void confirm_to_give(ActionEvent event) throws ClassNotFoundException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Stock Management System");
        alert.setHeaderText("Are you sure you want to give permission??");
        alert.setContentText("Press OK to give access or Press Cancel.");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(access != 1)
            {
              String update = " UPDATE executive SET Exe_access = '1' WHERE Exe_epfNo = '"+txtSearchEmployee.getText()+"' "; 
              db.putData(update);
              alert.setTitle("Stock Management System");
              alert.setHeaderText("Successfully give admin access to "+lblFullName.getText()+"!!!!!");
              alert.setContentText("");
              alert.showAndWait();
            }
        }
    }

    @FXML
    private void search_employee_remove(ActionEvent event) throws ClassNotFoundException, SQLException {
        String query = " SELECT * FROM executive where Exe_epfNo = '"+txtSearchEmployeeRemove.getText()+"' ";
        rs=db.getData(query);
        while(rs.next()) {
            lblFullNameRemove.setText(rs.getString("Exe_fName")+" "+rs.getString("Exe_lName"));
            access = Integer.parseInt(rs.getString("Exe_access"));
        }
    }

    @FXML
    private void confirm_to_remove(ActionEvent event) throws ClassNotFoundException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Stock Management System");
        alert.setHeaderText("Are you sure you want to remove permission??");
        alert.setContentText("Press OK to give access or Press Cancel.");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(access == 1)
            {
              String update = " UPDATE executive SET Exe_access = '0' WHERE Exe_epfNo = '"+txtSearchEmployeeRemove.getText()+"' "; 
              db.putData(update);
            }
        }
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Successfully remove admin access to "+lblFullName.getText()+"!!!!!");
            alert.setContentText("");
            alert.showAndWait();
    }
}