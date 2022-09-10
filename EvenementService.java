/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evenement;
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
public class EvenementService {
    
    
        Connection cn=DataSource.getInstance().getCnx();
    
    
    public void addEvenement(Evenement q)throws SQLDataException{
        
         
 String query ="INSERT INTO `evenements`( `lieu`, `titre`, `nb_participant`,`datadeb`,`date_fin`,`description`,`image`) VALUES (?,?,?,?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
                st.setString(1,q.getLieu());
                st.setString(2,q.getTitre());
                st.setInt(3,q.getNbrparticipant());
                st.setDate(4, (Date) q.getDated());
                st.setDate(5, (Date) q.getDatef());
                st.setString(6, q.getDescription());
                st.setString(7,q.getImage());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }

   
    public boolean ModifierEvenenment(Evenement e) throws SQLDataException {

               
                String query = "UPDATE `evenements` SET `lieu`=?,`nb_participant`=?,`description`=?,`datadeb`=?,`date_fin`=?,`titre`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
                st.setString(1,e.getLieu());
                st.setInt(2,e.getNbrparticipant());
                st.setString(3,e.getDescription());
                st.setDate(4,e.getDated());
                st.setDate(5,e.getDatef());
                st.setString(6,e.getTitre());
                st.setInt(7,e.getIde());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

   
    public ObservableList<Evenement> getAllEvenement() throws SQLDataException {

        
        List<Evenement> list =new ArrayList<Evenement>();
        int count =0;
        
        String requete="select * from evenements";
         try{
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenement e = new Evenement();
                e.setIde(rs.getInt(1));
                e.setLieu(rs.getString(3));
                e.setTitre(rs.getString(2));
                e.setNbrparticipant(rs.getInt(4));
                e.setDated(rs.getDate(5));
                e.setDatef(rs.getDate(6));
                e.setDescription(rs.getString(7));
                e.setImage(rs.getString(8));
                  
                
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

    
    public boolean deleteEvenement(int idEvenement) throws SQLDataException {

        
        
        try {
            
            Statement st=cn.createStatement();
            String req= "DELETE FROM `evenements` WHERE `id` ="+idEvenement;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
    public List<Evenement> afficheEvenement(String titre) throws SQLDataException
    {

       List<Evenement> list=new ArrayList<Evenement>();
           try {
               String req="SELECT * FROM `evenements` where `titre`='"+titre+"'";
               Statement st;
                 st = cn.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                Evenement e = new Evenement();
                e.setIde(rs.getInt(1));
                e.setLieu(rs.getString(3));
                e.setTitre(rs.getString(2));
                e.setNbrparticipant(rs.getInt(4));
                e.setDated(rs.getDate(5));
                e.setDatef(rs.getDate(6));
                e.setDescription(rs.getString(7));
                e.setImage(rs.getString(8));
               
                           list.add(e);
            
                       }    
           } catch (SQLException ex) {
               Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
           }
          return list;
    }

    
    public boolean ModifierEvenenmentPlace(Evenement e) throws SQLDataException {
        String query = "UPDATE `evenements` SET `nbreparticipants`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
            
                st.setInt(1,e.getNbrparticipant());
                st.setInt(2,e.getIde());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
        public Evenement findEvenementById(int id) {
        
       try {
             Evenement e=new Evenement();
             int c=0;
             String req="select * from evenements where id="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                e.setIde(rs.getInt(1));
                e.setLieu(rs.getString(3));
                e.setTitre(rs.getString(2));
                e.setNbrparticipant(rs.getInt(4));
                e.setDated(rs.getDate(5));
                e.setDatef(rs.getDate(6));
                e.setDescription(rs.getString(7));
                e.setImage(rs.getString(8));

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
