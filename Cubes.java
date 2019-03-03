/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubes;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class Cubes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   System.out.println("Please enter a positive number");
   Scanner in=new Scanner(System.in);
   int n=in.nextInt();
   
   int [] arr=new int [n];
   arr[0]=1;
   arr[1]=1;
   for(int i=2;i<n;i++){
       arr[i]=arr[i-1]+arr[i-2];
   }
    System.out.println(arr[n-1]);
}
