/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carry.number;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class CarryNumber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int [] arr = new int[7];
        double average =0;
        
        for(int i=0;i<arr.length; i++)
        {
          arr[i] =in.nextInt();
          average += arr[i];
        }
        average/=arr.length;
        System.out.println(average);
        
        for(int i=0;i<arr.length;i++)
        {
          System.out.println(arr[i]);
        }
        
        

    }
    
}
