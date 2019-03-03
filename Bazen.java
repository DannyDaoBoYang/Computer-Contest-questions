/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazen;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Bazen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int x=input.nextInt();
        int y=input.nextInt();
        if(x==0){
            if(y==0){
                System.out.println("125.00 125.00");
            }
            else if(y<125){
                
            }
            else if(y==125){
                System.out.println("250.00 0.00");
            }
            else if(y<250){
                
            }
            else{
                System.out.println("125.00 0.00");
            }
        }
        else if(y==0){
            if(x==0){
                System.out.println("125.00 125.00");
            }
            else if(x<125){
                
            }
            else if(x==125){
                System.out.println("0.00 250.00");
            }
            else if(x<250){
                
            }
            else{
                System.out.println("0.00 125.00");
            }
        }
        else{
            if(x<125)
        }
    }
    
}
