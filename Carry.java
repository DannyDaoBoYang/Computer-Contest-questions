/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carry;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class Carry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          Scanner in=new Scanner(System.in);
        int[]a =new int[1];
        int[]b =new int[1];
      for(int i=0;i<a.length; i++)
          a[i]=in.nextInt();
      for(int i=0;i<a.length; i++)
          b[i]=in.nextInt();
      for(int i=0;i<a.length; i++){
          System.out.println(a[i]+b[i]);
      }
           
        
 
    }
    
}
