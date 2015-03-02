/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import smell.SmellAccessor;
import smell.SmellBean;

/**
 *
 * @author Presha Thakkar
 */
public class SmellTableGenerator {
    
    //SmellTableGenerator(){}
    
    public static TableView makeSmellTable(ObservableList<SmellBean> data){
        
       
    
        TableColumn id = new TableColumn();
        id.setText("ID");
        id.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<SmellBean, String>("SmcId"));
        TableColumn name = new TableColumn();
        name.setText("Name");
        name.setMinWidth(150);
        name.setCellValueFactory(new PropertyValueFactory<SmellBean, String>("SmcName"));
       
        TableView tableView = new TableView();
        tableView.setItems(data);
        tableView.getColumns().addAll(id,name);
        
        

    
    return tableView;
    }
    
}
