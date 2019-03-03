/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package americanadian;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class AmeriCanadian {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner input= new Scanner(System.in);
       String [] output=new String [1];
       String [] changing= new String [1];
       String change="";
       String in="";
       String or="or";
       int position=0;
       int w=1;
       while(!in.toLowerCase().equals("quit!")){
           change="";
       in=input.next();
         output=new String[w];
        for(int i=0;i<changing.length;i++){
            output[i]=changing[i];
        }
        changing=new String[w];
        for(int i=0;i<output.length;i++){
            changing[i]=output[i];
        }
        if(in.length()<=4){
            changing[w-1]=in;
        }
        if(in.length()>4){
            
                if(in.charAt(in.length()-2)=='o'&&in.charAt(in.length()-1)=='r'){
                    for(int i=0;i<in.length()-2;i++){
                        change=change+in.charAt(i);
                        }
                changing[w-1]=change+"our";
            }
        }
        
        w++;
       }
       for(int i=0;i<output.length-1;i++)
       System.out.println(output[i]);
    }
    
}
