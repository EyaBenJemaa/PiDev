/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Commentaire;
import entity.Evenement;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewCommentaireController extends ListCell<Commentaire> {
    
    
     @Override
     public void updateItem(Commentaire e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            CommentaireItemController data = new CommentaireItemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
