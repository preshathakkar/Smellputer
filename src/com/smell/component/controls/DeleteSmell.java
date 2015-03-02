/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component.controls;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import smell.Smell;
import smell.SmellAccessor;
import smell.SmellBean;

/**
 *
 * @author Presha Thakkar
 */
public class DeleteSmell {
    static Label tfname = new Label();
    static Label tfdesc = new Label();
    static Label tfpin = new Label();
    static Label tfchem = new Label();
    static Label tfid = new Label();
    //static Label tfname, tfid, tfchem, tfpin, tfdesc;
    static ChoiceBox cb = new ChoiceBox();
    static String descptn,frmla,smell,text,id;
    static Label desc = new Label("Smell Description :");
    static Label frml = new Label("Chemical Formula :");
    static Label smid = new Label("Smell ID :");
    static Label pin = new Label("PIN NO :");
    static Label smell1 = new Label("Select Smell :");
   
    
    
    static SmellAccessor sm = new SmellAccessor();
    public static void deleteSmell(){
       final Stage stage = new Stage();
               Group rootGroup = new Group();
               Scene scene = new Scene(rootGroup,500,400);
               stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Delete Smell");
                stage.getIcons().add(new Image(DeleteSmell.class.getResourceAsStream("../icons/delete.png")));
                stage.show();
                final Smell s = new Smell();
               //tfdesc.setAlignment(Pos.CENTER);
                
                ArrayList<String> smc = sm.readAllSmellName();
               final GridPane grid = new GridPane();
               	HBox hbButtons = new HBox();
                hbButtons.setSpacing(10.0);
 
               
               
               Button edit = new Button("Remove Smell");
                Button close = new Button("Close");
                
                grid.setPadding(new Insets(20, 0, 20, 20));
                grid.setHgap(7); 
                grid.setVgap(7);
                
                
        GridPane.setHalignment(smell1, HPos.RIGHT);
        
         

        cb.setItems(FXCollections.observableArrayList(smc));
        cb.getSelectionModel().selectFirst();
        
        close.setOnAction(new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                            stage.close();
                    }
                });
        
        cb.valueProperty().addListener(new ChangeListener() {
             @Override
            public void changed(ObservableValue observable, Object oldValue,
                Object newValue) {
                text =cb.getValue().toString();
                 tfdesc.setText(null);
                SmellBean xyz = new SmellBean();
                 xyz = sm.getAllSmellInfoByName(text);
                 id = sm.getIdBySmellName(text);
                 tfdesc.setText(xyz.getSmcDesc());
                 tfchem.setText(xyz.getSmcChemFormula().toString());
                 tfid.setText(xyz.getSmcId().toString());
                 tfpin.setText(xyz.getSmcPin().toString());
                 
                }
        });
        GridPane.setHalignment(frml, HPos.RIGHT);
        GridPane.setHalignment(desc, HPos.RIGHT);
        GridPane.setHalignment(smid, HPos.RIGHT);
        GridPane.setHalignment(pin, HPos.RIGHT);
         grid.add(smell1, 0, 0);
         grid.add(tfdesc, 1, 2);
         grid.add(desc, 0, 2);
         grid.add(frml, 0, 3);
         grid.add(tfchem, 1, 3);
         grid.add(smid, 0, 4);
         grid.add(tfid, 1, 4);
         grid.add(pin, 0, 5);
         grid.add(tfpin, 1, 5);
         grid.add(cb, 1, 0);
                
                final Label label = new Label();
                
            edit.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                  DialogResponse response = Dialogs.showConfirmDialog(stage,
    "Do you want to continue Deleting Smell?", "Confirm Dialog", "Delete Confirm");
                      
                      if(response == DialogResponse.YES){
                System.out.println("YES");

                  
                  sm.removeSmellById(id);
                  label.setText("Smell Removed Successfully.");
                  tfname.setText(null);
                  tfid.setText(null);
                  tfdesc.setText(null);
                  tfchem.setText(null);
                  tfpin.setText(null);
                  
                  
               }}
             });
    
                
               
                //grid.add(edit,1,8);
                
                grid.add(label,1,10);
                
                hbButtons.getChildren().addAll(edit,close);

                grid.add(hbButtons,1,8,2,1);

                
                grid.prefHeightProperty().bind(scene.heightProperty());
                grid.prefWidthProperty().bind(scene.widthProperty());
                grid.getColumnConstraints().addAll(
            new ColumnConstraints(150),
            new ColumnConstraints(200)
        );
                grid.getRowConstraints().addAll(
               new RowConstraints(30),
               new RowConstraints(), // scale
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30),
               
            
            new RowConstraints(0,0,Double.MAX_VALUE,Priority.ALWAYS, VPos.CENTER,true), // spacer
            new RowConstraints(30),
            new RowConstraints()
        );
                
                rootGroup.getChildren().add(grid);
    }
}
