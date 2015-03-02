/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.modules;

import com.smell.component.SmellListGenerator;
import com.smell.component.SmellTableGenerator;
import com.smell.component.controls.EditSmell;
import com.smell.component.controls.InsertSmell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import smell.SmellAccessor;
import smell.SmellBean;

/**
 *
 * @author Presha Thakkar
 */
public class SmellApi extends GridPane{
   
    SmellAccessor sm = new SmellAccessor();  
        
  
  BorderPane br = new BorderPane();
  GridPane g= new GridPane();
  Label id = new Label("ID:");
  Label name = new Label("Name:");
  Label desc = new Label("Description:");

  Label frml = new Label("Chemical Formula:");
  Label pin = new Label("Pin:");
   
  TextField tfid = new TextField();
  TextField tfname = new TextField();
               
  TextField tfdesc = new TextField();
  TextField tffrml = new TextField();
  TextField tfpin = new TextField();

  Button addbut = new Button("Add");
  Button editbut = new Button("Edit");
  Button subbut = new Button("Submit");
  Button refresh = new Button("Refresh List");      
  
  TableView tableView;
  
  ObservableList<SmellBean> smellList; 
   
   
   GridPane grid = new GridPane();
   
    public SmellApi(ObservableList<SmellBean> data){
        
        smellList=data;
        
        setPadding(new Insets(10, 20, 10, 10));
        setVgap(10);
        setHgap(10);
        
        tableView = SmellTableGenerator.makeSmellTable(data); 

        g.setConstraints(refresh, 1, 1);
        g.setConstraints(tableView, 1,2);
        
        g.getChildren().addAll(refresh,tableView);
        
        g.getRowConstraints().addAll(
               new RowConstraints(),
               new RowConstraints(40), // scale
               new RowConstraints(500),
               new RowConstraints()
                
                );
        
        br.setLeft(g);
        
        grid.setPadding(new Insets(10, 20, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        grid.setConstraints(id, 1, 1);
        grid.setConstraints(name, 1, 2);
        grid.setConstraints(desc, 1, 3);
        grid.setConstraints(frml, 1, 4);
        grid.setConstraints(pin, 1, 5);
   
        grid.setConstraints(tfid, 2, 1);
        grid.setConstraints(tfname, 2, 2);
        grid.setConstraints(tfdesc, 2, 3);
        grid.setConstraints(tffrml, 2, 4);
        grid.setConstraints(tfpin, 2, 5);
   
                    
        grid.setConstraints(addbut, 1, 7);
        grid.setConstraints(editbut, 2, 7);
                    
   
       //grid.setConstraints(subbut, 2, 7);
        
        
        
        grid.getChildren().addAll(id,name,desc,frml,pin,tfid,tfname,tfdesc,tffrml,tfpin,addbut,editbut);
		
        grid.getRowConstraints().addAll(
               new RowConstraints(30),
               new RowConstraints(), // scale
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30),
               new RowConstraints(),
               new RowConstraints(30),
               
            
            //new RowConstraints(0,0,Double.MAX_VALUE,Priority.ALWAYS, VPos.CENTER,true), // spacer
               new RowConstraints(),
            new RowConstraints(30),
            new RowConstraints()
        );
        
                       addbut.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
               InsertSmell.addSmell();}
        });
                
        editbut.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
              /*tfid.setEditable(true); 
              tfname.setEditable(true);
              tfdesc.setEditable(true);
              tffrml.setEditable(true);
              tfpin.setEditable(true);
              grid.setConstraints(subbut, 2, 7);
              subbut.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                  boolean saveSmell = sm.updateSmellById(tfid.getText(), tfname.getText(), tfdesc.getText(), tffrml.getText(), tfpin.getText());
                  
                  }
             });
              */
                  EditSmell.editSmell();
                }
             });     
   
        
                       refresh.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                
                System.out.print("hi");
                tableRefresh();
                }
        });
               
   br.setCenter(grid);
   getChildren().addAll(br); 
   
   tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SmellBean>() {

			@Override
			public void changed(ObservableValue<? extends SmellBean> observable,
					SmellBean oldValue, SmellBean newValue) {
                            System.out.print(newValue);
                            showPersonDetails(newValue);
                            tableRefresh();
                                
                                
			}
		});
        
        
       
        
    
        
    }
    
    
    private void showPersonDetails(SmellBean person) {
       
        //System.out.print(person+"Hi");
		if(person!=null){
                String a = person.getSmcId();
               tfid.setText(a);
              
               //grid.tfid.setText(a);
               //tfid.setText();
                System.out.print(person.getSmcId());
              tfid.setEditable(false);
                //tfname = new TextField();
                
                tfname.setText(person.getSmcName());
                System.out.print(person.getSmcName());
               tfname.setEditable(false);
                //tfdesc = new TextField();
                tfdesc.setText(person.getSmcDesc());
                System.out.print(person.getSmcDesc());
               tfdesc.setEditable(false);
               // tffrml = new TextField();
                tffrml.setText(person.getSmcChemFormula());
               tffrml.setEditable(false);
                //tfpin = new TextField();
                tfpin.setText(person.getSmcPin());
               tfpin.setEditable(false);
                    
                    
                  
                
         
	}
    }
    
    public void tableRefresh(){
        
                smellList = SmellListGenerator.generateList();
                tableView.setItems(smellList);
}
    
    
    
 }
