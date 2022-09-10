/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ShowEventClientController.idE;
import entity.BadWords;
import entity.Commentaire;
import entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.CommentaireService;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ShowDetailsClientController implements Initializable {

    @FXML
    private ListView<Commentaire> listView;
    @FXML
    private Button commenter;
    @FXML
    private Font x1;
    @FXML
    private Button modefier;
    @FXML
    private ImageView imv;
    @FXML
    private Button supprimer;
    
    ObservableList<Commentaire> data;
    
    public static int idC ;
    @FXML
    private Font x2;
    @FXML
    private Label titre;
    @FXML
    private Label lieu;
    
    EvenementService es = new EvenementService();
    CommentaireService cs = new CommentaireService();

    @FXML
    private TextField contenue;
    @FXML
    private Button modif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             Evenement e = es.findEvenementById(ShowEventClientController.idE);
             titre.setText(e.getTitre());
             lieu.setText(e.getLieu());
             modif.setVisible(false);
             
             if (idC !=0 ){
             Commentaire comm = new Commentaire();
             comm = cs.findCommentaireById(idC);
             contenue.setText(comm.getContenue());
             modif.setVisible(true);
             commenter.setVisible(false);

             }
             
             imv.setImage(new Image("http://localhost/PiDev10/public/Upload/" + e.getImage()));;

         
               try {
                   System.out.println("controller.ShowDetailsClientController.initialize(gggggg"+ShowEventClientController.idE);
            data = (ObservableList<Commentaire>) cs.getAllCommentaire(ShowEventClientController.idE);
                               System.out.println("controller.hhhhhhhhh.initialize(gggggg"+data);
            listView.setItems(data);
            listView.setCellFactory((ListView<Commentaire> param) -> new ListViewCommentaireController());
            
            
            // TODO
        } catch (SQLDataException ex) {
                   Logger.getLogger(ShowEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void Commenter(ActionEvent event) throws SQLDataException {
        
        Commentaire c = new Commentaire();
        c.setContenue(contenue.getText());
        c.setEvenementId(ShowEventClientController.idE);
        c.setUserId(1);
        SimpleDateFormat   d= new SimpleDateFormat("yy-MM-dd");
        LocalDate dd = LocalDate.now();
        Date date = java.sql.Date.valueOf(dd);
       BadWords.loadConfigs();
        c.setDateC(date);
        if(contenue.getText().equals("")){
                  Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vérifier votre Champ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        }
        
                        
        else if (BadWords.filterText(contenue.getText())) {
            
       Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("cette application n'autorise pas ces termes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       
        }else{
        cs.addCommetaire(c);
                try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsClient.fxml"));
            Stage myWindow = (Stage)contenue.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void modefier(ActionEvent event) {
        
                         
        try {
            ObservableList<Commentaire> e;
            e = listView.getSelectionModel().getSelectedItems();
            
            
            for (Commentaire m : e) {
                idC=m.getId();
                
            }
            Parent root ;
            
            root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsClient.fxml"));
            Stage myWindow =  (Stage) contenue.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Les details");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }


    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
                 
        try {
            
            
            ObservableList<Commentaire> e;
            e = listView.getSelectionModel().getSelectedItems();
            
            
            for (Commentaire m : e) {
                idC=m.getId();
                
            }
            cs.deleteCommentaire(idC);
            idC=0 ;
            Parent root ;
            
            root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsClient.fxml"));
            Stage myWindow =  (Stage) contenue.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Les details");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @FXML
    private void modif(ActionEvent event) throws SQLDataException {
        
        
                                 
        try {
            ObservableList<Commentaire> e;
            e = listView.getSelectionModel().getSelectedItems();
            Commentaire cm = new Commentaire();
            cm.setContenue(contenue.getText());
            cm.setId(idC);
            
            cs.ModifierCommentaire(cm);
            idC = 0 ;

            Parent root ;
            
            root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsClient.fxml"));
            Stage myWindow =  (Stage) contenue.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Les details");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
