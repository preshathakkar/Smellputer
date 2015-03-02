/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component;
import com.smell.component.controls.DeleteSmell;
import com.smell.component.controls.EditSmell;
import com.smell.component.controls.InsertSmell;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import com.smell.component.controls.MenuControls;
/**
 *
 * @author Presha Thakkar
 */
public class HorizontalToolbar {
    
 //private static final Image ICON_48 = new Image(HorizontalToolbar.class.getResourceAsStream("icons/green-23.png"));
 private static final Image new_ICON = new Image(HorizontalToolbar.class.getResourceAsStream("icons/new.png"));
 private static final Image open_ICON = new Image(HorizontalToolbar.class.getResourceAsStream("icons/open.png"));
 private static final Image help_ICON = new Image(HorizontalToolbar.class.getResourceAsStream("icons/help.png"));
 private static final Image save_ICON = new Image(HorizontalToolbar.class.getResourceAsStream("icons/saveIcon.png"));
 private static final Image delete_ICON = new Image(HorizontalToolbar.class.getResourceAsStream("icons/delete.png"));
 public static ToolBar createToolBar() {
     
     
        ToolBar toolBar = new ToolBar();
       
        ImageView imageOpen = new ImageView(new_ICON);
        ImageView imageadd = new ImageView(open_ICON);
        ImageView imageSave = new ImageView(save_ICON);
        ImageView imageDelete = new ImageView(delete_ICON);
        ImageView imageHelp = new ImageView(help_ICON);
        
        Button open = new Button("Open SMC File", imageOpen);
        open.setContentDisplay(ContentDisplay.LEFT);
        
        Button delete = new Button("Delete Smell", imageDelete);
        delete.setContentDisplay(ContentDisplay.LEFT);
        Button addSmell = new Button("Add Smell", imageadd);
        addSmell.setContentDisplay(ContentDisplay.LEFT);
        Button editSmell = new Button("Edit Smell", imageSave);
        addSmell.setContentDisplay(ContentDisplay.LEFT);
        Button help = new Button("Help", imageHelp);
        help.setContentDisplay(ContentDisplay.LEFT);
        toolBar.getItems().addAll(open, new Separator(), addSmell, editSmell, delete, new Separator(), help);
        
     
       open.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                 MenuControls.open();
              }
            });
                   
       addSmell.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                   InsertSmell.addSmell();
                }
             });
       
       editSmell.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                   EditSmell.editSmell();
                }
             });
       
       delete.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                   DeleteSmell.deleteSmell();
                }
             });
       
       help.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                   MenuControls.aboutUs();
                }
             });
    
       return toolBar;
}
}
