/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caculator;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class Caculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
       int [] a = new int [10];
       int [] b = new int [10];
       
       for(int i=0;i<10;i++)
         a[i]=input.nextInt();
       for(int i=0;i<10;i++)
         b[i]=input.nextInt();
       
       System.out.println("out put:");
       
       for(int i=0;i<10;i++)
           System.out.println((a[i]+b[i]));
  }        
}
