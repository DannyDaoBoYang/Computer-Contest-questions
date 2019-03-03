/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Convert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        long N=input.nextLong();
        for(int i=9;i>=0;i--){
            if(N/Math.pow(27, i)>=1){
                System.out.println(i+" "+(long)(N/Math.pow(27, i)));
                N-=(long)Math.pow(27, i)*(long)(N/Math.pow(27, i));
            }
            
        }
    }
    
}
