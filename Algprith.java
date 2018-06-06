/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algprith;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class Algprith {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String OK="";
        int u=200000000;
        int b=1;
        String r="";
        while(1==1){
            System.out.println((u+b)/2);
            System.out.flush();
            r=input.next();
            if(r.equals("Lower")){
                u=((u+b)/2);
            }
            else if(r.equals("Higher")){
                b=((u+b)/2);
            }
            else if(r.equals("OK")){
                break;
            }
        }
        
    }
    
}
