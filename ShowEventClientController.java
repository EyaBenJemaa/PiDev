/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowEventClientController implements Initializable {

    @FXML
    private ListView<Evenement> listView;
   
    ObservableList<Evenement> data;
    
    public static int idE ;

   
    @FXML
    private Button details;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            EvenementService cs = new EvenementService();
            data = (ObservableList<Evenement>) cs.getAllEvenement();   
            listView.setItems(data);
            listView.setCellFactory((ListView<Evenement> param) -> new ListViewEvent());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void ShowDetail(ActionEvent event) {
        
         
        try {
            ObservableList<Evenement> e;
            e = listView.getSelectionModel().getSelectedItems();
            
            
            for (Evenement m : e) {
                idE=m.getIde();
                
            }
            Parent root ;
            
            root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsClient.fxml"));
            Stage myWindow =  (Stage) details.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Les details");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }


    @FXML
    private void handleClose(ActionEvent event) {
    }


    

}

    

