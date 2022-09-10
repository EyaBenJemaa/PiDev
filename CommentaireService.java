/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Commentaire;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Rzouga
 */
public class CommentaireService {
    
       
        Connection cn=DataSource.getInstance().getCnx();
    
    
    public void addCommetaire(Commentaire q)throws SQLDataException{
        
         
 String query ="INSERT INTO `commentaire`( `date_c`, `comment`, `user_id`,`id_evenement`) VALUES (?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
                st.setDate(1,q.getDateC());
                st.setString(2,q.getContenue());
                st.setInt(3,q.getUserId());
                st.setInt(4, q.getEvenementId());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }

   
    public boolean ModifierCommentaire(Commentaire e) throws SQLDataException {

               
                String query = "UPDATE `commentaire` SET `comment`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
                st.setString(1,e.getContenue());
                st.setInt(2,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

   
    public ObservableList<Commentaire> getAllCommentaire(int id) throws SQLDataException {

        
        List<Commentaire> list =new ArrayList<Commentaire>();
        int count =0;
        
        String requete="select * from commentaire where id_evenement="+id;
         try{
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Commentaire e = new Commentaire();
                e.setId(rs.getInt(1));
                e.setDateC(rs.getDate(4));
                e.setContenue(rs.getString((5)));
                e.setUserId(rs.getInt(2));
                e.setEvenementId(rs.getInt(3));
                  
                
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}

    
    public boolean deleteCommentaire(int idc) throws SQLDataException {

        
        
        try {
            
            Statement st=cn.createStatement();
            String req= "DELETE FROM `commentaire` WHERE `id` ="+idc;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    

        public Commentaire findCommentaireById(int id) {
        
       try {
             Commentaire e=new Commentaire();
             int c=0;
             String req="select * from commentaire where id="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                e.setId(rs.getInt(1));
                e.setDateC(rs.getDate(4));
                e.setContenue(rs.getString((5)));
                e.setUserId(rs.getInt(2));
                e.setEvenementId(rs.getInt(3));
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return e;
             }
         } catch (SQLException ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       } 
 

    
    
    
    
}
