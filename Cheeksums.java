/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheeksums;

/**
 *
 * @author dannyyang
 */
public class Cheeksums {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int a=1;a<26;a++){
        for(int b=1;b<=a;b++){
        for(int c=1;c<=a;c++){
        if(3*a+2*b==26 && 3*b+2*c==16 && 3*c+2*a==18){
         System.out.println(3*a);
        }
        }
        }
        }
    
    
    }
    
}
