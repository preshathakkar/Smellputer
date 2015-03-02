/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component;


import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import smell.SmellAccessor;
import smell.Smell;
import smell.SmellBean;

/**
 *
 * @author Presha Thakkar
 */
public class SmellEditor{
    SmellAccessor sm = new SmellAccessor();
    Smell s = new Smell();
    ArrayList<String> smc;
    SmellBean xyz = new SmellBean();
    ChoiceBox cb;
    Label msg;
    
    //ArrayList<String> smc = sm.readAllSmellName();
    String descptn,frmla,smell,text;
    TextField tfdesc,tffrml;
    

    public String getSmell() {
        return smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }
     
      GridPane gridPane = new GridPane();
    public GridPane Smell(){
      smc = sm.readAllSmellName();
      
        gridPane.setPadding(new Insets(20, 0, 20, 20));
        gridPane.setHgap(7); gridPane.setVgap(7);
        
        Label smell1 = new Label("Select Smell :");
        GridPane.setHalignment(smell1, HPos.RIGHT);
        
         
        cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(smc));
        cb.getSelectionModel().selectFirst();
                        

        
        cb.valueProperty().addListener(new ChangeListener() {
             @Override
            public void changed(ObservableValue observable, Object oldValue,
                Object newValue) {
                text =cb.getValue().toString();
                xyz = sm.getAllSmellInfoByName(text);
                 descptn = xyz.getSmcDesc().toString();
                 frmla = xyz.getSmcChemFormula().toString();
                 smell = xyz.getSmcId();
                 setSmell(smell);
                 Label desc = new Label("Smell Description :");
                 
                 GridPane.setHalignment(desc, HPos.RIGHT);
                 gridPane.add(desc, 0, 2);
                 tfdesc = new TextField(descptn);
                 tfdesc.setEditable(false);
                 gridPane.add(tfdesc, 1, 2);
                 
                 Label frml = new Label("Chemical Formula :");
                 GridPane.setHalignment(desc, HPos.RIGHT);
                 gridPane.add(frml, 0, 3);
                 TextField tffrml = new TextField(frmla);
                 tffrml.setEditable(false);
                 gridPane.add(tffrml, 1, 3);
                 
                }
        });
        
        
         gridPane.add(smell1, 0, 0);
         gridPane.add(cb, 1, 0);
         
          gridPane.getColumnConstraints().addAll(
            new ColumnConstraints(150),
            new ColumnConstraints(200)
        );
                gridPane.getRowConstraints().addAll(
               new RowConstraints(30),
               new RowConstraints(), // scale
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30)
               
         );
         
         return gridPane;
       
    }
    
    public GridPane SmellEdit(String imageSmellid){
      smc = sm.readAllSmellName();
      SmellBean sb2 = new SmellBean();
      SmellAccessor sa = new SmellAccessor();
      SmellBean test = new SmellBean();
      int flag = 0;
      String imageSmell = "";
      
      ArrayList<SmellBean> smells = sa.readAllSmell();
      
      for(int i=0;i<smells.size();i++){
      test = smells.get(i);
      System.out.print("\n"+test.getSmcId());    
      String id = test.getSmcId();      
        if(id.equals(imageSmellid)){
            flag =1;
            System.out.print("\n"+test);
            break;
        }
      }
      
      if(flag == 1){
      System.out.print("Match Found");
      imageSmell = test.getSmcName();
      }
      else{
      imageSmell = "";
      }
      
      //String imageSmell = sa.getNameById(imageSmellid);
      //System.out.print(imageSmell);
      //String imageSmell = sb2.getSmcName();
        gridPane.setPadding(new Insets(20, 0, 20, 20));
        gridPane.setHgap(7); gridPane.setVgap(7);
        
        Label smell1 = new Label("Select Smell :");
        GridPane.setHalignment(smell1, HPos.RIGHT);
        
         
        cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(smc));
        
        if(imageSmell.equals("")){
        cb.getSelectionModel().selectFirst();
        }
        
        else{
        cb.getSelectionModel().select(imageSmell);
        xyz = sm.getAllSmellInfoByName(imageSmell);
        descptn = xyz.getSmcDesc().toString();
        frmla = xyz.getSmcChemFormula().toString();
        smell = xyz.getSmcId();
        
        
        setSmell(smell);

        Label desc = new Label("Smell Description :");
        GridPane.setHalignment(desc, HPos.RIGHT);
        gridPane.add(desc, 0, 2);
        tfdesc = new TextField(descptn);
        tfdesc.setEditable(false);
        gridPane.add(tfdesc, 1, 2);
        
        Label frml = new Label("Chemical Formula :");
        GridPane.setHalignment(desc, HPos.RIGHT);
        gridPane.add(frml, 0, 3);
        tffrml = new TextField(frmla);
        tffrml.setEditable(false);
        gridPane.add(tffrml, 1, 3);
        }
        
        
        
        cb.valueProperty().addListener(new ChangeListener() {
             @Override
            public void changed(ObservableValue observable, Object oldValue,
                Object newValue) {
                text =cb.getValue().toString();
                xyz = sm.getAllSmellInfoByName(text);
                 descptn = xyz.getSmcDesc().toString();
                 frmla = xyz.getSmcChemFormula().toString();
                 smell = xyz.getSmcId();
                 setSmell(smell);
                 tfdesc.setText(descptn);
                 tffrml.setText(frmla);
                 
                }
        });
        
        
         gridPane.add(smell1, 0, 0);
         gridPane.add(cb, 1, 0);
         
          gridPane.getColumnConstraints().addAll(
            new ColumnConstraints(150),
            new ColumnConstraints(200)
        );
                gridPane.getRowConstraints().addAll(
               new RowConstraints(30),
               new RowConstraints(), // scale
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30)
               
         );
         
         return gridPane;
       
    }
}
