/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Evenement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLDataException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;
import service.EvenementService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button ajout;
    @FXML
    private Pane Pane;
    
    
    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    @FXML
    private ImageView Event_img;
    @FXML
    private TextField description;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("C:/xampp/htdocs/PiDev10/public/Upload/event" + c + ".jpg");
        lien = "event" + c + ".jpg";
        
        
        
        
        
    }    


    
    
        @FXML
    private void uploadImage(ActionEvent event) throws MalformedURLException, SQLDataException {
        
        
                  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            Event_img.setImage(image);
    }
    }
    
    

    @FXML
    private void AjouterEvent(ActionEvent event) throws SQLDataException, ParseException {
        
        

       
        
          if( titre.getText().equals("")  || lieu.getText().equals("") || description.getText().equals(0) || datefin.getValue()== null  || datedebut.getValue()==null  ){
              
              
              
                 Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vérifier votre champs").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
          }

  
               else{
        LocalDate d = LocalDate.now();  
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = java.sql.Date.valueOf(d);
        Date dd=  new java.sql.Date(  new Date(datedebut.getEditor().getText()).getTime());
                
                
        Date df = new java.sql.Date(  new Date(datefin.getEditor().getText()).getTime());

            Date datedeb = sdformat.parse(dd.toString());
            Date datefn = sdformat.parse(df.toString());
            Date now = sdformat.parse(d.toString());
            
            SimpleDateFormat dateFormat = new
            SimpleDateFormat ("yyyy-MM-dd");
            Date date1 = dateFormat.parse(dd.toString());
            Date date2 = dateFormat.parse(df.toString());
            
            
            if (date1.after(date2) || date2.before(date1))
            {
                
                                 Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vérifier votre Date").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                
                
            }
            else{
                copier(pfile,pDir);
                String t= titre.getText();
                String l= lieu.getText();
             


                EvenementService Es= new EvenementService();
                Evenement e= new Evenement();
                
                e.setDatef((java.sql.Date) df);
                e.setDated((java.sql.Date) dd);
                e.setDescription(description.getText());
                e.setImage(lien);
                e.setLieu(l);
                e.setTitre(t);
                e.setNbrparticipant(0);

                Es.addEvenement(e);

                
                Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/listeEvenement.fxml"));
                            Stage myWindow = (Stage)datedebut.getScene().getWindow();
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
          
    }
    
     public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

     
    private void cancel(ActionEvent event) {
          
        try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("/gui/listeEvenement.fxml"));
            Stage myWindow = (Stage)datedebut.getScene().getWindow();
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

