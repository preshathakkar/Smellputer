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
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class EditSmell {
    static TextField tfname, tfid, tfchem, tfpin, tfdesc;
    static ChoiceBox cb;
    static String descptn,frmla,smell,text,id;
    static Label desc;
    static SmellAccessor sm = new SmellAccessor();
    public static void editSmell(){
       final Stage stage = new Stage();
               Group rootGroup = new Group();
               Scene scene = new Scene(rootGroup,500,400);
               stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Edit Smell");
                stage.getIcons().add(new Image(EditSmell.class.getResourceAsStream("../icons/saveIcon.png")));
                stage.show();
                final Smell s = new Smell();
               
                
                ArrayList<String> smc = sm.readAllSmellName();
               final GridPane grid = new GridPane();
                Button edit = new Button("Edit Smell");
                Button close = new Button("Close");

        	HBox hbButtons = new HBox();
                hbButtons.setSpacing(10.0);
                
                grid.setPadding(new Insets(20, 0, 20, 20));
                grid.setHgap(7); 
                grid.setVgap(7);
                
                Label smell1 = new Label("Select Smell :");
        GridPane.setHalignment(smell1, HPos.RIGHT);
        
         
        cb = new ChoiceBox();
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
                 //tfdesc.clear();
                SmellBean xyz = sm.getAllSmellInfoByName(text);
                 id = sm.getIdBySmellName(text);                 
                 desc = new Label("Smell Description :");
                 GridPane.setHalignment(desc, HPos.RIGHT);
                 grid.add(desc, 0, 2);
                 tfdesc = new TextField();
                 tfdesc.setText(xyz.getSmcDesc().toString());
                 grid.add(tfdesc, 1, 2);
                 
                 Label frml = new Label("Chemical Formula :");
                 GridPane.setHalignment(frml, HPos.RIGHT);
                 grid.add(frml, 0, 3);
                 tfchem = new TextField();
                 tfchem.setText(xyz.getSmcChemFormula().toString());
                 grid.add(tfchem, 1, 3);
                 
                 Label id = new Label("Smell ID :");
                 GridPane.setHalignment(id, HPos.RIGHT);
                 grid.add(id, 0, 4);
                 tfid = new TextField();
                 tfid.setText(xyz.getSmcId().toString());
                 grid.add(tfid, 1, 4);
                 
                 Label pin = new Label("PIN NO :");
                 GridPane.setHalignment(pin, HPos.RIGHT);
                 grid.add(pin, 0, 5);
                 tfpin = new TextField();
                 tfpin.setText(xyz.getSmcPin().toString());
                 grid.add(tfpin, 1, 5);
                 
                }
        });
         grid.add(smell1, 0, 0);
         grid.add(cb, 1, 0);
                
                final Label label = new Label();
                
        edit.setOnAction(new EventHandler<ActionEvent>(){
          @Override public void handle(ActionEvent e){
           if((!tfid.getText().isEmpty()) && (!text.isEmpty()) && (!tfdesc.getText().isEmpty()) &&(!tfchem.getText().isEmpty()) && (!tfpin.getText().isEmpty())){
               //SmellAccessor sm = new SmellAccessor();
               String smell =cb.getValue().toString();
                String id = sm.getIdBySmellName(smell);                 
                String pin = sm.getNameById(id);
               /* boolean check1 = sm.checkSmellId(tfid.getText());
                boolean check2 = tfid.getText().equals(id);
                boolean fn1 = sm.checkSmellPin("4");
                boolean check3 = sm.checkSmellPin(tfpin.getText());
                boolean check4 = tfpin.getText().equals(pin);
                boolean check5 = check1 && !check2;
                boolean check6 = check3 && !check4;
                boolean check7 = check5 || check6;
                boolean check8 = !check7;
                */
                      if(!((sm.checkSmellId(tfid.getText()) && !tfid.getText().equals(id)) || (sm.checkSmellPin(tfpin.getText()) && !tfpin.getText().equals(pin)))){
                          
               
               DialogResponse response = Dialogs.showConfirmDialog(stage,
    "Do you want to continue Editing Smell?", "Confirm Dialog", "Edit Confirm");
                      
                      if(response == DialogResponse.YES){
                System.out.println("YES");

                  boolean saveSmell = sm.updateSmellById(tfid.getText(), text, tfdesc.getText(), tfchem.getText(), tfpin.getText());
                  if(saveSmell){
                  label.setText("Smell Updated Successfully.");
                  //Smell.data = SmellListGenerator.generateList();
                  
                  }} 
              }
                                         else{
                          if(sm.checkSmellId(tfid.getText()) && !tfid.getText().equals(id)){
                                Dialogs.showErrorDialog(stage, "Smell Id is already in use", "Error while inserting", "Error");}
                          else{
                                Dialogs.showErrorDialog(stage, "Smell Pin is already in use", "Error while inserting", "Error");}
                          
                      }
             
           }
                      
                      else{
                      label.setText("Insert All Details.");
                }
           }
           
             });
    
                
               
                //grid.add(edit,1,8);
        
        hbButtons.getChildren().addAll(edit,close);
        grid.add(hbButtons,1,8,2,1);

                
                grid.add(label,1,10);
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
