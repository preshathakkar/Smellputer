/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

/**
 *
 * @author Presha Thakkar
 */
public class TreeGenerator {
     TreeItem<String> rootItem;
    TreeView<String> tree;
    String rootnode = null,path;
    File[] folder = null;
    File selectedFile,file;
    ImageView[] image ;
    ImageEditor t = new ImageEditor();
    //int i =0;
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
   
    public TreeView<String> getTree(File file, File selected){
        
        setFile(selected);
                System.out.print("\n");
        System.out.print(selected.getName());
        System.out.print("\n");
        selectedFile = file;
        System.out.print(selectedFile.getName());
        System.out.print("\n");
       path = selectedFile.getPath();
       System.out.print(path);
       System.out.print("\n");
       rootItem = new TreeItem<String> (selectedFile.getName().toString());
       System.out.print(rootItem);
       System.out.print("\n");
       rootnode=selectedFile.getName().toString();
       System.out.print(rootnode);
       System.out.print("\n");
       rootItem = new TreeItem<String> (rootnode);
       rootItem.setExpanded(true);
       if(selectedFile.isDirectory()){
            folder = selectedFile.listFiles();
            int count = folder.length;
            int i =0;
            while(i<count){ 
                  if((folder[i].getName().toLowerCase().endsWith("jpg"))||(folder[i].getName().toLowerCase().endsWith("png"))||(folder[i].getName().toLowerCase().endsWith("gif"))||(folder[i].getName().toLowerCase().endsWith("smc"))){ 
                  TreeItem<String> item = new TreeItem<String>(folder[i].getName().toString());
                  rootItem.getChildren().addAll(item);
                  }
                  i++;
                  }      
                 tree = new TreeView<String> (rootItem);
                 System.out.print(tree);
                 System.out.print("\n");
                 tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                 tree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

                @Override
                 public void changed(ObservableValue observable, Object oldValue,
                Object newValue) {

                 TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                 
                 String text = selectedItem.getValue();
                 File f = new File(path+"/"+text);
                 setFile(f);
                 }

                });
                 
                 
         }
    return tree;

}
}
