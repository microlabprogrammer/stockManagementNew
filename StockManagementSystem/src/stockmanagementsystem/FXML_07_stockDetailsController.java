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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import stockmanagementsystem.util.DBConnection;
import stockmanagementsystem.util.setOrderMenu;

/**
 * FXML Controller class
 *
 * @author DEWSRI DE MEL
 */
public class FXML_07_stockDetailsController implements Initializable {
    
    DBConnection db = new DBConnection();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @FXML
    private ComboBox<String> cboBrand;
    @FXML
    private ComboBox<String> cboModel;
    @FXML
    private ComboBox<String> cboColor;
    @FXML
    private TextField txtColor;
    @FXML
    private CheckBox chkColor;
    @FXML
    private ComboBox<String> cboQty;
    @FXML
    private TableView<setOrderMenu> orderTable;
    @FXML
    private TableColumn<setOrderMenu, String> clnBrand;
    @FXML
    private TableColumn<setOrderMenu, String> clnModel;
    @FXML
    private TableColumn<setOrderMenu, String> clnColor;
    @FXML
    private TableColumn<setOrderMenu, String> clnQty;
    @FXML
    private TableColumn<setOrderMenu, String> clnStatus;
    @FXML
    private Button btnAddToOderList;
    @FXML
    private Button btnOrderNow;
    @FXML
    private Button btnDeleteItem;
    @FXML
    private Button btnPrintStock;
    @FXML
    private ComboBox<String> cboOrderBrand;
    @FXML
    private ComboBox<String> cboOrderModel;
    @FXML
    private ComboBox<String> cboOrderColor;
    @FXML
    private Button btnInsertOrderItem;
    @FXML
    private TextField txtOrderValue;
    @FXML
    private TextField txtOrderChassicNumber;
    @FXML
    private TextField txtOrderEngineNumber;
    @FXML
    private Label lblPendingAmount;
    @FXML
    private ComboBox<String> cboNewBrand;
    @FXML
    private ComboBox<String> cboNewModel;
    @FXML
    private ComboBox<String> cboNewColor;
    @FXML
    private Button btnInsertNewItem;
    @FXML
    private TextField txtNewValue;
    @FXML
    private TextField txtNewChassicNumber;
    @FXML
    private TextField txtNewEngineNumber;
    @FXML
    private TextField txtNewColor;
    @FXML
    private TextField txtNewModel;
    
    ObservableList<setOrderMenu> oblist = FXCollections.observableArrayList();
    @FXML
    private Label lblErrorFill;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cboOrderBrand.setEditable(false);
        cboOrderBrand.getItems().addAll("Hero Honda");
        cboNewBrand.setEditable(false);
        cboNewBrand.getItems().addAll("Hero Honda");
        try{
         String query1 = " SELECT DISTINCT SS_bikeMake FROM showroom_stock";
         rs=db.getData(query1);
         while(rs.next()) {
           cboBrand.setEditable(false);
           cboBrand.getItems().addAll(rs.getString("SS_bikeMake"));
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
        viewTableData();
    }
    
    private void viewTableData()
    {
        oblist.clear();
        try{
            String bikeValueChassic = " SELECT * FROM order_stock WHERE orderStatus = 'Pending' ";
            rs=db.getData(bikeValueChassic);
            while(rs.next()) {
                oblist.add(new setOrderMenu(rs.getString("orderBrand"), rs.getString("orderModel"), rs.getString("orderColor"), rs.getString("orderQty"), rs.getString("orderStatus")));

                clnBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
                clnModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
                clnColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
                clnQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
                clnStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
                orderTable.setItems(oblist);
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
    private void select_Brand(ActionEvent event) throws ClassNotFoundException, SQLException {
        cboModel.setDisable(false);
        
        String query2 = " SELECT DISTINCT SS_bikeModel FROM showroom_stock";
        rs=db.getData(query2);
        while(rs.next()) {
           cboModel.setEditable(false);
           cboModel.getItems().addAll(rs.getString("SS_bikeModel"));
        }
    }

    @FXML
    private void select_Model(ActionEvent event) throws ClassNotFoundException, SQLException {
        cboColor.setDisable(false);
        cboColor.getSelectionModel().clearSelection();
        cboColor.getItems().clear();
        cboQty.getSelectionModel().clearSelection();
        cboQty.getItems().clear();
        chkColor.setSelected(false);
        txtColor.setText("");
        txtColor.setDisable(true);
        btnAddToOderList.setDisable(true);
        
        String selectColor = " SELECT DISTINCT B_bikeColor FROM bike WHERE B_bikeModel = '"+cboModel.getSelectionModel().getSelectedItem()+"'";
        rs=db.getData(selectColor);
        while(rs.next()) {
           cboColor.setEditable(false);
           cboColor.getItems().addAll(rs.getString("B_bikeColor"));
        }
        
        for(int x=1; x<=10; x++)
        {
         cboQty.setEditable(false);
         cboQty.getItems().addAll(""+x+"");
        }
    }

    @FXML
    private void checkColor(MouseEvent event) {
        if(chkColor.isSelected())
        {
          if(txtColor.isDisable() == false)
          {
            txtColor.setDisable(true);
            cboColor.setDisable(false);
          }
        }
        else
        {
          if(txtColor.isDisable() == true)
          {
            txtColor.setDisable(false);
            cboColor.getSelectionModel().clearSelection();
            cboColor.setDisable(true);
            cboQty.setDisable(false);
          }
        }
    }

    @FXML
    private void addItems_to_orderList(ActionEvent event) throws ClassNotFoundException, SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String color;
        String status = "Pending";
        if(chkColor.isSelected())
        {
            color = txtColor.getText();
        }
        else
        {
            color = cboColor.getSelectionModel().getSelectedItem();
        }
        //check order
        String orderID = null;
        int oldQty = 0, newQty;
        String query1 = " SELECT * FROM order_stock WHERE orderStatus = 'Pending' AND orderModel = '"+ cboModel.getSelectionModel().getSelectedItem() +"' AND orderColor = '"+ cboColor.getSelectionModel().getSelectedItem() +"' ";
        rs=db.getData(query1);
        while(rs.next()) {
          orderID = rs.getString("orderID");
          oldQty = Integer.parseInt(rs.getString("orderQty"));
        }
        if(orderID == null)
        {
           String insertNewOrder = " INSERT INTO order_stock (orderDate, orderBrand, orderModel, orderColor, orderQty, orderStatus) VALUES('" +localDate+ "', '" +cboBrand.getSelectionModel().getSelectedItem()+ "', '" +cboModel.getSelectionModel().getSelectedItem()+ "', '" +color+ "', '" +cboQty.getSelectionModel().getSelectedItem()+ "', '" +status+ "') "; 
           db.putData(insertNewOrder);
           
           oblist.add(new setOrderMenu(cboBrand.getSelectionModel().getSelectedItem(), cboModel.getSelectionModel().getSelectedItem(), color, cboQty.getSelectionModel().getSelectedItem(), status));
           
           clnBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
           clnModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
           clnColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
           clnQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
           clnStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
           orderTable.setItems(oblist); 
        }
        else
        {
           newQty = oldQty + Integer.parseInt(cboQty.getSelectionModel().getSelectedItem());
           String updateOrderStock = " UPDATE order_stock SET orderQty = '"+newQty+"' WHERE  orderID = '"+orderID+"' "; 
           db.putData(updateOrderStock);
           oblist.clear();
           String query2 = " SELECT * FROM order_stock WHERE orderStatus = 'Pending' ";
           rs=db.getData(query2);
           while(rs.next()) {
             oblist.add(new setOrderMenu(rs.getString("orderBrand"), rs.getString("orderModel"), rs.getString("orderColor"), rs.getString("orderQty"), rs.getString("orderStatus")));
             
             clnBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
             clnModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
             clnColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
             clnQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
             clnStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
             orderTable.setItems(oblist);
           }
        }
    }

    @FXML
    private void order_stock(ActionEvent event) throws ClassNotFoundException, SQLException {
        //Email to be develop
        
        //Update order items
        String updateOrderStock = " UPDATE order_stock SET orderStatus = 'Ordered' WHERE  orderStatus = 'Pending' "; 
        db.putData(updateOrderStock);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stock Management System");
        alert.setHeaderText("Successfully Ordered!!!!!");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
        alert.showAndWait();
        oblist.clear();
    }

    @FXML
    private void delete_selected_item(ActionEvent event) throws ClassNotFoundException, SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Stock Management System");
        alert.setHeaderText("Are you sure you want to delete this record??");
        alert.setContentText("Press OK to delete record or Press Cancel.");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            ObservableList<setOrderMenu> selectedItems = orderTable.getSelectionModel().getSelectedItems();
            String brand = selectedItems.get(0).getBrand();
            String model = selectedItems.get(0).getModel();
            String color = selectedItems.get(0).getColor();
            String qty = selectedItems.get(0).getQty();

            String update = " UPDATE order_stock SET orderStatus = 'Removed' WHERE orderBrand = '"+brand+"' AND orderModel = '"+model+"' AND orderColor = '"+color+"' AND orderQty = '"+qty+"' AND orderStatus = 'Pending' "; 
            db.putData(update);

            viewTableData();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void print_pending_stock(ActionEvent event) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        int n = 3;
        FileChooser chooser = new FileChooser();
        Window ownerWindow = null;
        File fullPath = chooser.showSaveDialog(ownerWindow);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Pending Stock");  

        HSSFRow rowhead = sheet.createRow((short)0);
        rowhead.createCell(2).setCellValue("Pending Stock Details");
        
        HSSFRow rowColumn = sheet.createRow((short)2);
        rowColumn.createCell(0).setCellValue("Brand");
        rowColumn.createCell(1).setCellValue("Model");
        rowColumn.createCell(2).setCellValue("Color");
        rowColumn.createCell(3).setCellValue("Qty");
        rowColumn.createCell(4).setCellValue("Status");
        
        String query = " SELECT * FROM order_stock WHERE orderStatus = 'Ordered' ";
        rs=db.getData(query);
        while(rs.next()) {
          HSSFRow row = sheet.createRow((short)n);
          row.createCell(0).setCellValue(rs.getString("orderBrand"));
          row.createCell(1).setCellValue(rs.getString("orderModel"));
          row.createCell(2).setCellValue(rs.getString("orderColor"));
          row.createCell(3).setCellValue(rs.getString("orderQty")); 
          row.createCell(4).setCellValue(rs.getString("orderStatus")); 
          n = n + 1;
        }
        //----Save Excel File
        FileOutputStream fileOut = new FileOutputStream(fullPath + ".xls");
        workbook.write(fileOut);
        fileOut.close();
//        workbook.close();
        System.out.println("Excel file has been generated!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Successfully !!!!!");
            alert.setContentText("Excel file has been generated!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
    }

    @FXML
    private void select_Order_Brand(ActionEvent event) throws ClassNotFoundException, SQLException {
        cboOrderModel.setDisable(false);
        String query = " SELECT DISTINCT orderModel FROM order_stock";
        rs=db.getData(query);
        while(rs.next()) {
           cboOrderModel.setEditable(false);
           cboOrderModel.getItems().addAll(rs.getString("orderModel"));
        }
    }

    @FXML
    private void select_Order_Model(ActionEvent event) throws ClassNotFoundException, SQLException {
        cboOrderColor.setDisable(false);
        cboOrderColor.getSelectionModel().clearSelection();
        cboOrderColor.getItems().clear();
        lblPendingAmount.setText("");
        btnInsertOrderItem.setDisable(true);
        txtOrderValue.setText("");
        txtOrderChassicNumber.setText("");
        txtOrderEngineNumber.setText("");
        txtOrderValue.setDisable(true);
        txtOrderChassicNumber.setDisable(true);
        txtOrderEngineNumber.setDisable(true);
        String query = " SELECT DISTINCT orderColor FROM order_stock WHERE orderModel = '"+ cboOrderModel.getSelectionModel().getSelectedItem() +"' ";
        rs=db.getData(query);
        while(rs.next()) {
           cboOrderColor.setEditable(false);
           cboOrderColor.getItems().addAll(rs.getString("orderColor"));
        }
    }

    @FXML
    private void addOrderItem(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(txtOrderValue.getText() == "" || txtOrderChassicNumber.getText() == "" || txtOrderEngineNumber.getText() == "")
        {
            lblErrorFill.setVisible(true);
        }
        else
        {
            //insert bike to bike table
            String newBike = " INSERT INTO bike (B_bikeMake, B_bikeModel, B_bikeValue, B_bikeColor, B_bikeChassicNumber, B_bikeEngineNumber, B_customerID) VALUES('" +cboOrderBrand.getSelectionModel().getSelectedItem()+ "', '" +cboOrderModel.getSelectionModel().getSelectedItem()+ "', '" +txtOrderValue.getText()+ "', '" +cboOrderColor.getSelectionModel().getSelectedItem()+ "', '" +txtOrderChassicNumber.getText()+ "', '" +txtOrderEngineNumber.getText()+ "', NULL) "; 
            db.putData(newBike);
            //get Order & update order table
            int oldQty, newQty = 0;
            String orderId = null;
            String getOrder = " SELECT * FROM order_stock WHERE orderModel = '"+cboOrderModel.getSelectionModel().getSelectedItem()+"' AND orderColor = '"+cboOrderColor.getSelectionModel().getSelectedItem()+"' AND orderStatus = 'Ordered' ";
            rs=db.getData(getOrder);
            while(rs.next()) {
                orderId = rs.getString("orderID");
                oldQty = Integer.parseInt(rs.getString("orderQty"));
                newQty = oldQty - 1;
            }
            if(newQty <= 0)
            {
               String updateOrder = " UPDATE order_stock SET orderQty = '"+newQty+"' AND orderStatus = 'Received' WHERE orderID = '"+orderId+"'"; 
               db.putData(updateOrder); 
            }
            else
            {
               String updateOrder = " UPDATE order_stock SET orderQty = '"+newQty+"' WHERE orderID = '"+orderId+"'"; 
               db.putData(updateOrder); 
            }
            //update Showroom Stock
            int oldQtyStock, newQtyStock = 0;
            String stID = null;
            String getStock = " SELECT * FROM showroom_stock WHERE SS_bikeModel = '"+cboOrderModel.getSelectionModel().getSelectedItem()+"' AND SS_bikeColor = '"+cboOrderColor.getSelectionModel().getSelectedItem()+"' ";
            rs=db.getData(getStock);
            while(rs.next()) {
                stID = rs.getString("SS_ID");
                oldQtyStock = Integer.parseInt(rs.getString("SS_stockQuantity"));
                newQtyStock = oldQtyStock + 1;
            }
            String updateShowroomStock = " UPDATE showroom_stock SET SS_stockQuantity = '"+newQtyStock+"' WHERE SS_ID = '"+stID+"'"; 
            db.putData(updateShowroomStock);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Successfully add new item!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
            txtOrderValue.setText("");
            txtOrderChassicNumber.setText("");
            txtOrderEngineNumber.setText("");
            lblPendingAmount.setText(String.valueOf(newQty)+" pending items");
        }
    }

    @FXML
    private void select_New_Brand(ActionEvent event) throws SQLException, SQLException, ClassNotFoundException {
        cboNewModel.setDisable(false);
        txtNewValue.setDisable(false);
        txtNewChassicNumber.setDisable(false);
        txtNewEngineNumber.setDisable(false);
        btnInsertNewItem.setDisable(false);
        String query = " SELECT DISTINCT B_bikeModel FROM bike ";
        rs=db.getData(query);
        while(rs.next()) {
           cboNewModel.setEditable(false);
           cboNewModel.getItems().addAll(rs.getString("B_bikeModel"));
        }
    }

    @FXML
    private void select_New_Model(ActionEvent event) throws ClassNotFoundException, SQLException {
        cboNewColor.setDisable(false);
        cboNewColor.getSelectionModel().clearSelection();
        cboNewColor.getItems().clear();
        txtNewValue.setText("");
        txtNewChassicNumber.setText("");
        txtNewEngineNumber.setText("");
        txtNewModel.setText("");
        txtNewColor.setText("");
        String query = " SELECT DISTINCT B_bikeColor FROM bike WHERE B_bikeModel = '"+ cboNewModel.getSelectionModel().getSelectedItem() +"' ";
        rs=db.getData(query);
        while(rs.next()) {
           cboNewColor.setEditable(false);
           cboNewColor.getItems().addAll(rs.getString("B_bikeColor"));
        }
    }

    @FXML
    private void addNewItem(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(cboNewModel.getSelectionModel().getSelectedItem() == null && txtNewModel.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Please select or enter new model");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait(); 
        }
        else if(cboNewColor.getSelectionModel().getSelectedItem() == null && txtNewColor.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Please select or enter new color");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
        }
        else if(txtNewValue.getText().isEmpty() || txtNewChassicNumber.getText().isEmpty() || txtNewEngineNumber.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Please fill all fields");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
        }
        else
        {
            //insert bike to bike table
            String bikeModel = null;
            String bikeColor = null;
            if(cboNewModel.getSelectionModel().getSelectedItem() != null)
            {
                bikeModel = cboNewModel.getSelectionModel().getSelectedItem();
            }
            else
            {
                bikeModel = txtNewModel.getText();
            }
            if(cboNewColor.getSelectionModel().getSelectedItem() != null)
            {
               bikeColor = cboNewColor.getSelectionModel().getSelectedItem(); 
            }
            else
            {
               bikeColor = txtNewColor.getText();
            }
            String newBike = " INSERT INTO bike (B_bikeMake, B_bikeModel, B_bikeValue, B_bikeColor, B_bikeChassicNumber, B_bikeEngineNumber, B_customerID) VALUES('" +cboNewBrand.getSelectionModel().getSelectedItem()+ "', '" +bikeModel+ "', '" +txtNewValue.getText()+ "', '" +bikeColor+ "', '" +txtNewChassicNumber.getText()+ "', '" +txtNewEngineNumber.getText()+ "', NULL) "; 
            db.putData(newBike);
            //update Showroom Stock
            int oldQtyStock, newQtyStock = 0;
            String stID = null;
            String getStock = " SELECT * FROM showroom_stock WHERE SS_bikeModel = '"+cboNewModel.getSelectionModel().getSelectedItem()+"' AND SS_bikeColor = '"+cboNewColor.getSelectionModel().getSelectedItem()+"' ";
            rs=db.getData(getStock);
            while(rs.next()) {
                stID = rs.getString("SS_ID");
                oldQtyStock = Integer.parseInt(rs.getString("SS_stockQuantity"));
                newQtyStock = oldQtyStock + 1;
            }
            String updateShowroomStock = " UPDATE showroom_stock SET SS_stockQuantity = '"+newQtyStock+"' WHERE SS_ID = '"+stID+"'"; 
            db.putData(updateShowroomStock);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management System");
            alert.setHeaderText("Successfully add new item!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("images/hud-3149462_960_720.png").toString()));
            alert.showAndWait();
            cboNewBrand.getSelectionModel().clearSelection();
            cboNewModel.getItems().clear();
            cboNewColor.getSelectionModel().clearSelection();
            cboNewColor.getItems().clear();
            txtNewModel.setText("");
            txtNewColor.setText("");
            txtNewValue.setText("");
            txtNewChassicNumber.setText("");
            txtNewEngineNumber.setText("");
            txtNewValue.setDisable(true);
            txtNewChassicNumber.setDisable(true);
            txtNewEngineNumber.setDisable(true);
            btnInsertNewItem.setDisable(true);
        }
    }

    @FXML
    private void select_Qty(ActionEvent event) {
        btnAddToOderList.setDisable(false);
    }

    @FXML
    private void select_color(ActionEvent event) {
        cboQty.setDisable(false);
    }

    @FXML
    private void select_Order_Color(ActionEvent event) throws ClassNotFoundException, SQLException {
        int count = 0;
        txtOrderValue.setDisable(false);
        txtOrderChassicNumber.setDisable(false);
        txtOrderEngineNumber.setDisable(false);
        btnInsertOrderItem.setDisable(false);
        String query = " SELECT * FROM order_stock WHERE orderModel = '"+ cboOrderModel.getSelectionModel().getSelectedItem() +"' AND orderColor = '"+ cboOrderColor.getSelectionModel().getSelectedItem() +"' AND orderStatus = 'Ordered' ";
        rs=db.getData(query);
        while(rs.next()) {
           count = count + Integer.parseInt(rs.getString("orderQty")); 
        }
        if(count == 0)
        {
            lblPendingAmount.setText("No Pending items");
            btnInsertOrderItem.setDisable(true);
            txtOrderValue.setText("");
            txtOrderChassicNumber.setText("");
            txtOrderEngineNumber.setText("");
            txtOrderValue.setDisable(true);
            txtOrderChassicNumber.setDisable(true);
            txtOrderEngineNumber.setDisable(true);
        }
        else
        {
            lblPendingAmount.setText(count+" pending items");
        }
    }   
}
