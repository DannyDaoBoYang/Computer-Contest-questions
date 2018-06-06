/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guess.the.number;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class GuessTheNumber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("guess 50");
        System.out.flush();
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        
            System.out.println("answer "+(50-N));
       
            
        
    
    }
    
}
