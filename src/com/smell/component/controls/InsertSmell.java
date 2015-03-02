/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import smell.SmellAccessor;

/**
 *
 * @author Presha Thakkar
 */
public class InsertSmell {
    
   static TextField tfname, tfid, tfdesc, tfchem, tfpin;
    
    public static void addSmell(){
       final Stage stage = new Stage();
               Group rootGroup = new Group();
               Scene scene = new Scene(rootGroup,500,400);
               stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Add Smell");
                stage.getIcons().add(new Image(InsertSmell.class.getResourceAsStream("../icons/open.png")));
                stage.show();
                
                HBox hbButtons = new HBox();
                hbButtons.setSpacing(10.0);
                GridPane grid = new GridPane();
                Button add = new Button("Add Smell");
                Button close = new Button("Close");
                
                grid.setPadding(new Insets(20, 0, 20, 20));
                grid.setHgap(7); 
                grid.setVgap(7);
                
                Label name = new Label("Smell Name :");
                GridPane.setHalignment(name, HPos.RIGHT);
                tfname = new TextField();
                
                Label id = new Label("Smell Id :");
                GridPane.setHalignment(id, HPos.RIGHT);
                tfid = new TextField();
                
                Label desc = new Label("Smell Description :");
                GridPane.setHalignment(desc, HPos.RIGHT);
                tfdesc = new TextField();
                
                Label chem = new Label("Chemical Formula :");
                GridPane.setHalignment(chem, HPos.RIGHT);
                tfchem = new TextField();
                
                Label pin = new Label("Smell Pin :");
                GridPane.setHalignment(pin, HPos.RIGHT);
                tfpin = new TextField();
                
                final Label label = new Label();
               
                close.setOnAction(new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                            stage.close();
                    }
                });
                
                add.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                  if((!tfid.getText().isEmpty()) && (!tfname.getText().isEmpty()) && (!tfdesc.getText().isEmpty()) &&(!tfchem.getText().isEmpty()) && (!tfpin.getText().isEmpty())){
                   SmellAccessor sm = new SmellAccessor();
                   String id = tfid.getText();
                   boolean checkid = sm.checkSmellId(id);
                   String pin = tfpin.getText();
                   boolean checkpin = sm.checkSmellPin(pin);
                   
                   if(!(sm.checkSmellId(tfid.getText()) || sm.checkSmellPin(tfpin.getText()))){
                      
                      
                      DialogResponse response = Dialogs.showConfirmDialog(stage,
    "Do you want to continue Inserting Smell?", "Confirm Dialog", "Insert Confirm");
                      
                      if(response == DialogResponse.YES){
                System.out.println("YES");
                      
                      
                  boolean saveSmell = sm.saveSmell(tfid.getText(), tfname.getText(), tfdesc.getText(), tfchem.getText(), tfpin.getText());
                  if(saveSmell){
                  label.setText("Smell Added Successfully.");
                  tfname.setText(null);
                  tfid.setText(null);
                  tfdesc.setText(null);
                  tfchem.setText(null);
                  tfpin.setText(null);
                }
                 }
                  
              }
                   else{    
                          if(sm.checkSmellId(tfid.getText())){
                                Dialogs.showErrorDialog(stage, "Smell Id is already in use", "Error while inserting", "Error");}
                          else{
                                Dialogs.showErrorDialog(stage, "Smell Pin is already in use", "Error while inserting", "Error");}
                          }

                      
                   
                   
                   
                  }else{
                      label.setText("Insert All Details.");
                }
              }
             });
    
                
                grid.add(id, 0, 1);
                grid.add(tfid, 1, 1);
                grid.add(name, 0, 2);
                grid.add(tfname, 1, 2);
                grid.add(desc, 0, 3);
                grid.add(tfdesc, 1, 3);
                grid.add(chem, 0, 4);
                grid.add(tfchem, 1, 4);
                grid.add(pin, 0, 5);
                grid.add(tfpin, 1, 5);
                
                grid.add(hbButtons,1,7,2,1);
                
                //grid.add(hbButtons, i, i1, i2, i3)
                
               // grid.add(close,1,7);
                grid.add(label,1,9);
                hbButtons.getChildren().addAll(add,close);
                
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
