/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.apache.sanselan.ImageReadException;
import smcImage.SmcImage;
import smell.SmellAccessor;

/**
 *
 * @author Presha Thakkar
 */
public class SmcEditor {
   Button b,c,close;
    ImageView imageview ;
    SmellEditor sm = new SmellEditor();
    String a,x,y;
    File f,file,ss;
    SmellAccessor sa = new SmellAccessor();
   
   public void getTab(String name, File img) throws IOException{
        try {
            final Stage stage = new Stage();
                   Group rootGroup = new Group();
                   Scene scene = new Scene(rootGroup,600,500);
                   stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setTitle("SMC Editor");
                    stage.getIcons().add(new Image(SmcEditor.class.getResourceAsStream("icons/062.png")));
                    stage.show();
                    
                    BorderPane borderPane = new BorderPane();
                    
                    TabPane tb = new TabPane();
                    Group g = new Group();
                    Tab image = new Tab(name);
                    a = img.getAbsolutePath();
                    x= img.getParent();
                    f = new File(a);
                    Image wr = new Image(f.toURI().toString());
                    
                    imageview = new ImageView(wr);
                    imageview.setFitHeight(500);
                    imageview.setPreserveRatio(true);
                    
                    image.setContent(imageview);
                    Tab smell = new Tab("Smell");
                    String s = SmcImage.getSmellFromSmc(f);
                    String sme;
                    
                    Boolean flag = false;
                    
                    if(s.startsWith("'")){
                        sme = s.substring(1, s.length()-1);
                    }
                    else{
                        sme = s;
                    }
                    
                    GridPane grid = sm.SmellEdit(sme);
                    smell.setContent(grid);
                   
                    
                    tb.getTabs().addAll(image,smell);
                    tb.setSide(Side.TOP);
                    //tb.sideProperty();
                 TilePane tp = new TilePane();  
                   
                    b = new Button("Edit Smell");
                    c = new Button("Remove Smell");
                    close = new Button("Close");
                    tp.getChildren().addAll(b,c,close);
                    tp.setAlignment(Pos.CENTER);
               b.setOnAction(new EventHandler<ActionEvent>(){
                  @Override 
                  public void handle(ActionEvent e){
                    
                    try {
                        String smell = sm.getSmell();
                        Dialogs.DialogResponse response = Dialogs.showConfirmDialog(stage,
    "Do you want to continue Editing Smell in the SMC Image?", "Confirm Dialog", "Smell Change");
                      
                      if(response == Dialogs.DialogResponse.YES){
                System.out.println("YES");
                       File editSmc = SmcImage.editSmc(f, smell);
                       Dialogs.showInformationDialog(stage, "Your SMC Image has been updated",
                "SMC Image Updated", "Image Update");
                
                      }} catch (ImageReadException | IOException ex){
                        Logger.getLogger(SmcEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
                  }
                 );
               
               c.setOnAction(new EventHandler<ActionEvent>(){
                  @Override 
                  public void handle(ActionEvent e){
                    String smell = sm.getSmell();
                    try {
                        Dialogs.DialogResponse response = Dialogs.showConfirmDialog(stage,
    "Do you want to continue Delete Smell from the Image?\nAn Image of JPEG type will be generated in the same folder without smell", "Confirm Dialog", "Edit Confirm");
                      
                if(response == Dialogs.DialogResponse.YES){
                System.out.println("YES");
                        
                File smc = SmcImage.removeSmc(f, f.getParent());
                f.delete();
                Dialogs.showInformationDialog(stage, "The Smell from the image has been removed",
                "Smell Removed", "Smell Remove");
                
                stage.close();
                
                }} catch (IOException | ImageReadException ex) {
                        Logger.getLogger(SmcEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
                  }
                 );
               
               
               close.setOnAction(new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                            stage.close();
                    }
                });
               
                    borderPane.setCenter(tb);
                    borderPane.setBottom(tp);
                    borderPane.prefHeightProperty().bind(scene.heightProperty());
                    borderPane.prefWidthProperty().bind(scene.widthProperty());
                    rootGroup.getChildren().add(borderPane);
        } catch (ImageReadException ex) {
            Logger.getLogger(SmcEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     } 
}
