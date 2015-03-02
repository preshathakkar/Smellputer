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
public class SmcImageViewer extends GridPane{
    ImageView imageview ;
    SmellViewer sm = new SmellViewer();
    String a,x,y;
    File f,file,ss;
    HBox hbButtons;
   
   public void getTab(String name, File img) throws IOException{
        try {
            final Stage stage = new Stage();
                   Group rootGroup = new Group();
                   Scene scene = new Scene(rootGroup,600,500);
                   stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setTitle("SMC Image Viewer");
                    stage.getIcons().add(new Image(ImageEditor.class.getResourceAsStream("icons/062.png")));
                    stage.show();
                    
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
                    GridPane grid = sm.Smell(sme);
                        
                    //GridPane grid = sm.Smell();
                    smell.setContent(grid);
                   
                    
                    tb.getTabs().addAll(image,smell);
                    tb.setSide(Side.TOP);
                    //tb.sideProperty();
                    rootGroup.getChildren().add(tb);
        } catch (ImageReadException ex) {
            Logger.getLogger(SmcImageViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     }

    
    
}
