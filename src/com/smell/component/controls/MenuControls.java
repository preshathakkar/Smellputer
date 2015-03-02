/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component.controls;

import com.smell.component.SmcImageViewer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;
//import smellInterface.SmellInterface;

/**
 *
 * @author Presha Thakkar
 */
public class MenuControls {
     
    final static Stage link = new Stage(); 
       
     public static void open(){
    String currentDir = System.getProperty("user.dir") + File.separator;
                  FileChooserBuilder fcb = FileChooserBuilder.create();
                  FileChooser fc = fcb.title("Open Dialog").initialDirectory(new File(currentDir)).build();
                  FileChooser.ExtensionFilter extFilterSMC = new FileChooser.ExtensionFilter("SMC files (*.smc)", "*.SMC", "*.smc");
                  fc.getExtensionFilters().addAll(extFilterSMC);       
                  File selectedFile = fc.showOpenDialog(link);
                  System.out.print(selectedFile.getName());
                  SmellInterface sm = new SmellInterface(); 
                  SmcImageViewer sv = new SmcImageViewer();
        try {
            sv.getTab(selectedFile.getName(), selectedFile);
        } catch (IOException ex) {
            Logger.getLogger(MenuControls.class.getName()).log(Level.SEVERE, null, ex);
        }
                  sm.openWithSmell(new File(selectedFile.getAbsolutePath()));
                  
            
            
    }
     
   public static void aboutUs(){
       final Stage stage = new Stage();
                Group rootGroup = new Group();
                Scene scene = new Scene(rootGroup, 300, 200, Color.WHITESMOKE);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("About Us");
                //stage.getIcons().add(new Image(MenuControls.class.getResourceAsStream("../icons/062.png")));
                stage.show();
                Text text = new Text(20, 110, "Smellputer");
                text.setFill(Color.DODGERBLUE);
                text.setEffect(new Lighting());
                text.setFont(Font.font(Font.getDefault().getFamily(), 50));
                rootGroup.getChildren().add(text);
   }
   
}
