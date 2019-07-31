/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import stockmanagementsystem.util.DBConnection;
import stockmanagementsystem.util.getBikeData;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_03_viewBikeController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @FXML
    private ComboBox<String> cmbBikeBrand;
    @FXML
    private ComboBox<String> cmbBikeModel;
    @FXML
    private ComboBox<String> cmbBikeColor;
    @FXML
    private TableView<getBikeData> bikeTable;
    @FXML
    private TableColumn<getBikeData, String> clnBrand;
    @FXML
    private TableColumn<getBikeData, String> clnModel;
    @FXML
    private TableColumn<getBikeData, String> clnValue;
    @FXML
    private TableColumn<getBikeData, String> clnChassicNumber;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;
    @FXML
    private ImageView image9;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;
    @FXML
    private ImageView image13;
    @FXML
    private ImageView image14;
    @FXML
    private ImageView image15;
    @FXML
    private ImageView image16;
    @FXML
    private ImageView image17;
    @FXML
    private ImageView image18;
    @FXML
    private ImageView image19;
    @FXML
    private ImageView image20;
    @FXML
    private ImageView image21;
    @FXML
    private Button btnSearch;
    
    String bikeBrand = null;
    String bikeModel = null;
    String bikeColor = null;
    String bikeValue = null;
    String bikeChaNumber = null;
    
    ObservableList<getBikeData> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
         String query1 = " SELECT DISTINCT SS_bikeMake FROM showroom_stock";
         rs=db.getData(query1);
         while(rs.next()) {
           bikeBrand = rs.getString("SS_bikeMake");
           cmbBikeBrand.setEditable(false);
           cmbBikeBrand.getItems().addAll(bikeBrand);
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

    @FXML
    private void select_bikeBrand(ActionEvent event) {
        try{
         String query2 = " SELECT DISTINCT SS_bikeModel FROM showroom_stock";
         rs=db.getData(query2);
         while(rs.next()) {
           bikeModel = rs.getString("SS_bikeModel");
           cmbBikeModel.setEditable(false);
           cmbBikeModel.getItems().addAll(bikeModel);
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

    @FXML
    private void select_bikeModel(ActionEvent event) {
        cmbBikeColor.getSelectionModel().clearSelection();
        cmbBikeColor.getItems().clear();
        btnSearch.setDisable(true);
        
        try{
         String selectColor = " SELECT DISTINCT B_bikeColor FROM bike WHERE B_bikeModel = '"+cmbBikeModel.getSelectionModel().getSelectedItem()+"'";
         rs=db.getData(selectColor);
         while(rs.next()) {
           bikeColor = rs.getString("B_bikeColor");
           
           cmbBikeColor.setEditable(false);
           cmbBikeColor.getItems().addAll(bikeColor);
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
        setBikeImage(cmbBikeModel.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void select_bikeColor(ActionEvent event) {
        btnSearch.setDisable(false);
    }

    @FXML
    private void search_bike(ActionEvent event) {
        //Clear Table
        oblist.clear();
        
        //Retrive Data to table
        int count = 0;
        try{
        String bikeValueChassic = " SELECT * FROM bike WHERE B_bikeMake = '"+cmbBikeBrand.getSelectionModel().getSelectedItem()+"' AND B_bikeModel = '"+cmbBikeModel.getSelectionModel().getSelectedItem()+"' AND B_bikeColor = '"+cmbBikeColor.getSelectionModel().getSelectedItem()+"'";
        rs=db.getData(bikeValueChassic);
         while(rs.next()) {
             if(rs.getString("B_customerID") == null)
             {
               bikeValue = rs.getString("B_bikeValue");
               bikeChaNumber = rs.getString("B_bikeChassicNumber");

               oblist.add(new getBikeData(cmbBikeBrand.getSelectionModel().getSelectedItem(), cmbBikeModel.getSelectionModel().getSelectedItem(), "Rs. "+bikeValue, bikeChaNumber));

               clnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
               clnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
               clnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
               clnChassicNumber.setCellValueFactory(new PropertyValueFactory<>("chassicNumber"));
               bikeTable.setItems(oblist);
               count = count + 1;
             }
         }
         if(count == 0)
         {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Selected bike model is Empty");
            alert.setContentText("");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
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
    
    public void setBikeImage(String bikeModel) {
        //Claer all images
        image1.setVisible(false);
        image2.setVisible(false);
        image3.setVisible(false);
        image4.setVisible(false);
        image5.setVisible(false);
        image6.setVisible(false);
        image7.setVisible(false);
        image8.setVisible(false);
        image9.setVisible(false);
        image10.setVisible(false);
        image11.setVisible(false);
        image12.setVisible(false);
        image13.setVisible(false);
        image14.setVisible(false);
        image15.setVisible(false);
        image16.setVisible(false);
        image17.setVisible(false);
        image18.setVisible(false);
        image19.setVisible(false);
        image20.setVisible(false);
        image21.setVisible(false);
        
        //Set images
        switch(bikeModel)
        {
            case "HUNK DOUBLE DISC":
            image1.setVisible(true);
            break;
            
            case "DASH VX":
            image2.setVisible(true);
            break;
            
            case "PLEASURE METAL SHEET WHEEL":
            image3.setVisible(true);
            break;
            
            case "DASH LX":
            image4.setVisible(true);
            break;
            
            case "HF DELUXE CAST KICK":
            image5.setVisible(true);
            break;
            
            case "PLEASURE LX":
            image6.setVisible(true);
            break;
           
            case "XTREME SPORTS":
            image7.setVisible(true);
            break;
            
            case "SPLENDOR I SMART":
            image8.setVisible(true);
            break;
            
            case "GLAMOUR DISC SELF CAST":
            image9.setVisible(true);
            break;
            
            case "PLEASURE":
            image10.setVisible(true);
            break;
            
            case "DAWN SPOKE SELF":
            image11.setVisible(true);
            break;
            
            case "DUET LX":
            image12.setVisible(true);
            break;
            
            case "SPLENDOR PRO CAST SELF":
            image13.setVisible(true);
            break;
            
            case "HUNK SINGLE DISC":
            image14.setVisible(true);
            break;
            
            case "HF DELUXE CAST SELF":
            image15.setVisible(true);
            break;
  
            case "PASSION PRO DISC CAST SELF":
            image16.setVisible(true);
            break;
            
            case "KARIZMA R":
            image17.setVisible(true);
            break;
            
            case "SUPER SPLENDOR":
            image18.setVisible(true);
            break;
            
            case "DAWN 125":
            image19.setVisible(true);
            break;
            
            case "KARIZMA ZMR 2":
            image20.setVisible(true);
            break;
            
            case "PLEASURE METAL SHT WHEEL":
            image21.setVisible(true);
            break;
            
            default:
            System.out.println("image not found....");
          }
    }

    @FXML
    private void select_chassisNumber(MouseEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        //Get selected Chassis number and save it on temparary location
        ObservableList<getBikeData> selectedItems = bikeTable.getSelectionModel().getSelectedItems();
        
        String chassisNumber = selectedItems.get(0).getChassicNumber();
        
        PrintWriter writer = new PrintWriter("tmp\\temDataChassisNo.txt", "UTF-8");
        writer.println(chassisNumber);
        writer.close();
    }
}
