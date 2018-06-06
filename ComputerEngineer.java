/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.engineer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author dannyyang
 */
public class ComputerEngineer {

    public static String (int times){
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("ukenglish.txt");
        BufferedReader dictionary = new BufferedReader(new FileReader(file));
        ArrayList<String> dic=new ArrayList<String>();
        String a=dictionary.readLine();
        
        while(!a.equals("zymurgy")){
            dic.add(a);
            a=dictionary.readLine();
        }
        File file2 = new File("Second.txt");
        BufferedReader input2 = new BufferedReader(new FileReader(file2));
        String text="";
        while(input2.ready()){
            text=text+input2.readLine();
        }
        for(int len=1;len<text.length();len++){
            String key="";
            
            
        }
        
        
        
        
       
            
            
        
    }
    
}
