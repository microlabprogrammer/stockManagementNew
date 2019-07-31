/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author DEWSRI DE MEL
 */
public class StockManagementSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        //Remove Title Bar
        //stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        stage.setResizable(false);
        
        /*root.setOnMousePressed((MouseEvent event)->{
            double xoffset = event.getSceneX();
            double yoffset = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event)->{
            double xoffset = 0;
        stage.setX(event.getScreenX()-xoffset);
            double yoffset = 0;
        stage.setY(event.getScreenY()-yoffset);
        });*/
        
        
        //Set Icon
        Image icon = new Image(getClass().getResourceAsStream("images/hud-3149462_960_720.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Stock Management System");
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
