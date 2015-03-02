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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.sanselan.ImageReadException;
import smcImage.SmcImage;


/**
 *
 * @author Presha Thakkar
 */
public class ImageEditor extends GridPane{
    Button b,c;
    ImageView imageview ;
    SmellEditor sm = new SmellEditor();
    String a,x,y;
    File f,file,ss;
    HBox hbButtons;
   
   public void getTab(String name, File img) throws IOException{
     
        final Stage stage = new Stage();
               Group rootGroup = new Group();
               Scene scene = new Scene(rootGroup,600,500);
               stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Image Editor");
                stage.getIcons().add(new Image(ImageEditor.class.getResourceAsStream("icons/062.png")));
                stage.show();
                
                BorderPane borderPane = new BorderPane();
                hbButtons = new HBox();
                hbButtons.setSpacing(10.0);

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
                GridPane grid = sm.Smell();
                smell.setContent(grid);
               
                
                tb.getTabs().addAll(image,smell);
                tb.setSide(Side.TOP);
                //tb.sideProperty();
               c = new Button("Close");
               c.setOnAction(new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                            stage.close();
                    }
                });

               
               
                b = new Button("Attach Smell");
           b.setOnAction(new EventHandler<ActionEvent>(){
              @Override 
              public void handle(ActionEvent e){
                try {
                    String smell = sm.getSmell();
                    if(smell!=null){
                     Dialogs.DialogResponse response = Dialogs.showConfirmDialog(stage,
    "Do you want to continue to add Smell to Image?\nA new SMC image will be created in the same folder", "Confirm Dialog", "Add Smell Confirm");
                      
                      if(response == Dialogs.DialogResponse.YES){
                System.out.println("YES");
                         
                    ss = SmcImage.saveSmc(f, smell, f.getParent());
                    Dialogs.showInformationDialog(stage, "A new SMC image generated in the same folder",
    "SMC Image Created", "Image Created");

                    
                    }}
                } catch (ImageReadException | IOException ex) {
                    Logger.getLogger(ImageEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
              }
             );
                hbButtons.getChildren().addAll(b,c);
                //grid.add(hbButtons,1,7,2,1);
                borderPane.setCenter(tb);
                borderPane.setBottom(hbButtons);
                hbButtons.setAlignment(Pos.CENTER);
                borderPane.prefHeightProperty().bind(scene.heightProperty());
                borderPane.prefWidthProperty().bind(scene.widthProperty());
                rootGroup.getChildren().add(borderPane);
    
     }
            
}
