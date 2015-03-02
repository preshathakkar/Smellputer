/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component.controls;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.sanselan.ImageReadException;
import smcImage.SmcImage;
import smell.SmellAccessor;
import smell.SmellBean;

/**
 *
 * @author Presha Thakkar
 */
public class SmellInterface {
String smell;
    public static BufferedReader input;
public static OutputStream output;
public static SerialClass obj = new SerialClass();
public static synchronized void writeData(String data) {
System.out.println("Sent: " + data);
try {
output.write(data.getBytes());
output.flush();
} catch (Exception e) {
    System.out.println("In SmellInterface");
e.printStackTrace();
System.out.println("could not write to port");
}
}
    /**
     * @param args the command line arguments
     */
    public void openWithSmell(File SmcFile) {
        
        try
{
            // TODO code application logic here
            SmellAccessor sa = new SmellAccessor();
            SmellBean s = new SmellBean();
            String SmcId = SmcImage.getSmellFromSmc(SmcFile);
            System.out.print(SmcId);
            String sm = SmcId.toString();
            System.out.print(sm);
            String smellid = SmcImage.getSmellFromSmc(SmcFile);
                    String sme;
                    
                    Boolean flag = false;
                    
                    if(smellid.startsWith("'")){
                        sme = smellid.substring(1, smellid.length()-1);
                    }
                    else{
                        sme = smellid;
                    }
            s = sa.getAllSmellInfoById(sme);
           System.out.print(s);
            smell =  s.getSmcPin();
            //System.out.print(SmcPin);
            //System.out.print(SmcPin);S
           // smell = SmcPin.toString();
            System.out.print(smell);
            try
    {
    
    int c=0;
    obj.initialize();
    input = SerialClass.input;
    output = SerialClass.output;
    InputStreamReader Ir = new InputStreamReader(System.in);
    BufferedReader Br = new BufferedReader(Ir);

    Thread t=new Thread() {
                    @Override
    public void run() {
                            obj.writeData(smell);
                            
                        
                        //catch (InterruptedException ie){}
                        
                    }  
    //catch (InterruptedException ie){}
    };

    t.start();
    //obj.writeData(smell);
    //Thread.yield();
    
    System.out.println("Started");

    obj.close();
    }
    catch(Exception e){}
    }
catch(ImageReadException ex){Logger.getLogger(SmellInterface.class.getName()).log(Level.SEVERE, null, ex);
}       catch (IOException ex) {
            Logger.getLogger(SmellInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
 