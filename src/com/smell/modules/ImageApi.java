/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.modules;


import com.smell.component.ImageEditor;
import com.smell.component.SmcEditor;
import com.smell.component.TreeGenerator;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;

/**
 *
 * @author Presha Thakkar
 */
public class ImageApi extends GridPane{
    final static Stage link = new Stage();
    Label Label1 = new Label(" Selected Image ");
    TreeItem<String> rootItem;
    TreeView<String> tree1;
    String rootnode = null;
    File[] folder = null;
    File url,selectedFile;
    ImageView imageview = null ;
    Button b = null;
    ImageEditor sm = new ImageEditor();
    TreeGenerator tr = new TreeGenerator();
    SmcEditor smc = new SmcEditor();
        
    public ImageApi(){
       
        setPadding(new Insets(10, 20, 10, 10));
        setVgap(10);
        setHgap(10);
        Button button1 = new Button("Select Image Tree");
        
        button1.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                try{ 
                  setPadding(new Insets(10, 20, 10, 10));
                  setVgap(10);
                  setHgap(10);
                  File Folder;
                 
                  String currentDir = System.getProperty("user.dir") + File.separator;
                  FileChooserBuilder fcb = FileChooserBuilder.create();
                  FileChooser fc = fcb.title("Open Dialog").initialDirectory(new File(currentDir)).build();
                  //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG", "*.png");
            FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF", "*.gif");
            FileChooser.ExtensionFilter extFilterSMC = new FileChooser.ExtensionFilter("SMC files (*.smc)", "*.SMC", "*.smc");
            fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterGIF, extFilterSMC);
       
                  selectedFile = fc.showOpenDialog(link);
                  File file = selectedFile.getParentFile();
                  b = new Button();
                  b.setMaxWidth(Double.MAX_VALUE);
                  b.setVisible(false);
                 System.out.print(file.getAbsolutePath());
                 System.out.print(selectedFile.getAbsolutePath());
                  tree1 = tr.getTree(file,selectedFile);
                  tree1.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

                @Override
                 public void changed(ObservableValue observable, Object oldValue,
                Object newValue) {

                 TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                // do what ever you want 
                selectedFile= tr.getFile();
                   Image wr = new Image(selectedFile.toURI().toString());
                   imageview.setImage(wr);
                   imageview.setFitHeight(350);
                   imageview.setPreserveRatio(true);
                   b.setText(selectedFile.getName().toString());
                   b.setVisible(true);
                   b.setOnAction(new EventHandler<ActionEvent>(){
                 @Override public void handle(ActionEvent e){
                     if(selectedFile.toString().endsWith(".smc")){
                     try {
                            smc.getTab(b.getText(),selectedFile);
                        } catch (IOException ex) {
                            Logger.getLogger(TreeGenerator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 }else{      
                     try {
                                sm.getTab(b.getText(),selectedFile);
                            } catch (IOException ex) {
                                Logger.getLogger(ImageApi.class.getName()).log(Level.SEVERE, null, ex);
                            }
                }
                }
             });
                
                }

                });
                   GridPane.setConstraints(tree1, 0, 2, 1, 10
                , HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

                 
                GridPane.setConstraints(b, 1, 5, 1, 2
                , HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                        
                
                   
                
                  getChildren().addAll(tree1,b);
                }
                catch(Exception ex){
                //System.out.print(ex);
               }
              }
            });
        
        GridPane.setConstraints(button1, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        imageview = new ImageView();
        
        GridPane.setConstraints(Label1, 1, 0, 1, 1
                , HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        
        GridPane.setConstraints(imageview, 1, 3, 1, 2
                , HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
          
        
       getColumnConstraints().addAll(
            new ColumnConstraints(50,200,Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER,true),
            new ColumnConstraints(500,400,Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER,true)
        );
      
       getRowConstraints().addAll(
            new RowConstraints(30),
            new RowConstraints(), // scale
            new RowConstraints(0,0,Double.MAX_VALUE,Priority.ALWAYS, VPos.CENTER,true) // spacer
            
        );
               
        getChildren().addAll(button1, Label1, imageview);
       
    }
    
  }
