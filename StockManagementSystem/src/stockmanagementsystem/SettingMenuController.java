/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class SettingMenuController implements Initializable {

    @FXML
    private Button btnAdminAccess;
    @FXML
    private Pane settingDisplayPanel;
    @FXML
    private Button btnAddOrRemoveEmployee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void changeAdminAccess(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("settingUI/adminSetting.fxml"));
        settingDisplayPanel.getChildren().setAll(pane);
    }

    @FXML
    private void addOrRemoveEmployee(ActionEvent event) {
    }
    
}
