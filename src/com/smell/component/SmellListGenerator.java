/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import smell.SmellAccessor;
import smell.SmellBean;

/**
 *
 * @author Presha Thakkar
 */
public class SmellListGenerator {
    
    public static ObservableList<SmellBean> generateList(){
        SmellAccessor sm = new SmellAccessor();  
        SmellBean sb;
        ArrayList<String> smc = sm.readAllSmellName();
        String s;
        ArrayList<SmellBean> slist = new ArrayList<SmellBean>();
        SmellBean sl ;
        
        for(int i = 0; i < smc.size();i++){
           s = smc.get(i);
           sl = sm.getAllSmellInfoByName(s);
          System.out.print(s);
          slist.add(sl);
        }
        
        ObservableList<SmellBean> data = FXCollections.observableArrayList(slist);
    
        return data;
    }
    
    
}
