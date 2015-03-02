/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.modules;

import smell.SmellBean;
import com.smell.component.controls.InsertSmell;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import smell.SmellAccessor;
import smell.SmellBean;

/**
 *
 * @author Presha Thakkar
 */
public class SmellApiOld extends GridPane{
   
  SmellAccessor sm = new SmellAccessor();  
  SmellBean sb;
  ArrayList<String> smc = sm.readAllSmellName();
  String s;
  ArrayList<SmellBean> slist = new ArrayList<SmellBean>();
  SmellBean sl ;
  BorderPane br = new BorderPane();
  GridPane g= new GridPane();
  
  Label id,name,desc,frml,pin;
  TextField tfid,tfname,tfdesc,tffrml,tfpin;
  Button sub;
   GridPane grid = new GridPane();
  
 private TableView<SmellBean> smellTable;
    public SmellApiOld(){
        
        setPadding(new Insets(10, 20, 10, 10));
        setVgap(10);
        setHgap(10);
       for(int i = 0; i < smc.size();i++){
           s = smc.get(i);
           sl = sm.getAllSmellInfoByName(s);
          System.out.print(s);
          
          //System.out.print();
          slist.add(sl);
        }
       ObservableList<SmellBean> data = FXCollections.observableArrayList(slist);
       TableColumn<SmellBean, String> id = new TableColumn<SmellBean,String>();
        id.setText("ID");
        id.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<SmellBean, String>("SmcId"));
        TableColumn<SmellBean, String> name = new TableColumn<SmellBean, String>();
        name.setText("Name");
        name.setMinWidth(150);
        name.setCellValueFactory(new PropertyValueFactory<SmellBean, String>("SmcName"));
        /*TableColumn emailCol = new TableColumn();
        emailCol.setText("Description");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory("desc"));
        TableColumn chemfrm = new TableColumn();
        chemfrm.setText("Chemical Formula");
        chemfrm.setMinWidth(200);
        chemfrm.setCellValueFactory(new PropertyValueFactory("frml"));
        TableColumn pin = new TableColumn();
        pin.setText("Pin No");
        pin.setMinWidth(200);
        pin.setCellValueFactory(new PropertyValueFactory("pin"));
        */
        
        TableView<SmellBean> tableView = new TableView<SmellBean>();
        tableView.setItems(data);
        tableView.getColumns().addAll(id,name);
        br.setLeft(tableView);
        
        tableView.setEditable(false);
        
       /* tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() > 1) {
                            System.out.println("double clicked!");
                            TableColumn c = (TableColumn) event.getSource();
                            System.out.println("Cell text: " + c.getText());
                            
                            
                            
                            
                        }
                    }
        });
         */       
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SmellBean>() {

			@Override
			public void changed(ObservableValue<? extends SmellBean> observable,
					SmellBean oldValue, SmellBean newValue) {
                            System.out.print(newValue);
				g = showPersonDetails(newValue);
                                
                                br.setCenter(g);
			}
		});
        
        
       
        
        getChildren().addAll(br);
    }
    
    
    private GridPane showPersonDetails(SmellBean person) {
       
        //System.out.print(person+"Hi");
		if (person != null) {
                   id = new Label();
		id.setText("ID:");
                 name = new Label();
                name.setText("Name:");
                 desc = new Label();
                desc.setText("Description:");
                 frml = new Label();
                frml.setText("Chemical Formula:");
                 pin = new Label();
                pin.setText("Pin:");
               
                String a = person.getSmcId();
               tfid = new TextField(a);
               //tfid.setText();
                System.out.print(person.getSmcId());
               tfid.setEditable(false);
                tfname = new TextField();
                tfname.setText(person.getSmcName());
                System.out.print(person.getSmcName());
               tfname.setEditable(false);
                tfdesc = new TextField();
                tfdesc.setText(person.getSmcDesc());
                System.out.print(person.getSmcDesc());
                tfdesc.setEditable(false);
                tffrml = new TextField();
                tffrml.setText(person.getSmcChemFormula());
                tffrml.setEditable(false);
                tfpin = new TextField();
                tfpin.setText(person.getSmcPin());
                tfpin.setEditable(false);
                    
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
                    
                    Button add = new Button("Add");
                    Button edit = new Button("Edit");
                    
                    grid.setConstraints(add, 1, 7);
                     grid.setConstraints(edit, 2, 7);
                     
                     add.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                   InsertSmell.addSmell();
                }
             });
                edit.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                   tfid.setEditable(true); 
                   tfname.setEditable(true);
                    tfdesc.setEditable(true);
                    tffrml.setEditable(true);
                    tfpin.setEditable(true);
                    sub = new Button("Submit");
                    grid.setConstraints(sub, 2, 7);
                     sub.setOnAction(new EventHandler<ActionEvent>(){
              @Override public void handle(ActionEvent e){
                  boolean saveSmell = sm.updateSmellById(tfid.getText(), tfname.getText(), tfdesc.getText(), tffrml.getText(), tfpin.getText());
                  
                  }
             });
              
                }
             });     
                     
                    
                  grid.getChildren().addAll(id,name,desc,frml,pin,tfid,tfname,tfdesc,tffrml,tfpin,add,edit);  
		}
                
                 grid.getColumnConstraints().addAll(
            new ColumnConstraints(80,USE_COMPUTED_SIZE,Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER,true),
            new ColumnConstraints(150,USE_COMPUTED_SIZE,Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER,true)
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
                
          return grid;      
	}

    
 }
