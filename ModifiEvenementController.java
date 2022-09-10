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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifiEvenementController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    
    public static int idmof ; 
    @FXML
    private Pane Pane;
    @FXML
    private TextField description;
    @FXML
    private Button ajout;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    
    
    EvenementService es = new EvenementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Evenement e = new Evenement();
        System.out.println("controller.azzzzzzzzzzz"+idmof+"zzzzzzz.initialize()");
        e= es.findEvenementById(idmof);
        titre.setText(e.getTitre());
        lieu.setText(e.getLieu());
        description.setText(e.getDescription());
        LocalDate dd = e.getDated().toLocalDate();
        LocalDate df = e.getDatef().toLocalDate();
        datedebut.setValue(dd);
        datefin.setValue(df);
   
    }    



    @FXML
    private void AjouterEvent(ActionEvent event) throws SQLDataException {
        
                String t= titre.getText();
                String l= lieu.getText();
                Date dd = java.sql.Date.valueOf(this.datedebut.getValue());
                Date df = java.sql.Date.valueOf(this.datefin.getValue());
                EvenementService Es= new EvenementService();
                Evenement e = new Evenement();
                e.setDated((java.sql.Date) dd);
                e.setDatef((java.sql.Date) df);
                e.setTitre(t);
                e.setLieu(l);
                e.setDescription(description.getText());
                e.setIde(idmof);
                
                Es.ModifierEvenenment(e);
                
                
                Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/listeEvenement.fxml"));
                            Stage myWindow = (Stage)titre.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ModifiEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        } 
        
                
    }

}
