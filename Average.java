/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package average;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class Average {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        Scanner input= new Scanner(System.in);
        System.out.println("Please enter your test grades.");
        double[]a =new double[2];
        System.out.print("Test 1: ");
        a[0]=input.nextDouble();
        System.out.print("Test 2: ");
        a[1]=input.nextDouble();
        double x=(a[0]+a[1])/2;
        
        System.out.println("\n"+"Please enter your quiz grades.");
        double[]b = new double[3];
        System.out.print("Quiz 1: ");
        b[0]=input.nextDouble();
        System.out.print("Quiz 2: ");
        b[1]=input.nextDouble();
        System.out.print("Quiz 3: ");
        b[2]=input.nextDouble();
        double y=(b[0]+b[1]+b[2])/3;
        
        System.out.println("\n"+"Please enter your homework average.");
        System.out.print("Homework: ");
        double c=input.nextDouble();
        
        System.out.print("\n"+"test average: ");
        System.out.println(x);

        System.out.print("Quiz average :");
        System.out.println(y);
        
        System.out.print("Final Grade:");
        System.out.println(x*0.5+y*0.3+c*0.2);
               
  
       /* System.out.println("Enter the values for the first array, up to 10000 values, enter a negative\n" +
"number to quit");
        int []a=new int[10000];
        int []b=new int[10000];
        
       
         for(int i=0;i<a.length; i++)
         a[i]=input.nextInt();
         int x=0;
         for(int i=0;i<a.length; i++)
         while(a[i]>0){
         x++;
         a[i]=input.nextInt();}
         
         System.out.println("Enter the values for the second array, up to 10000 values, enter a negative\n" +
"number to quit");
         for(int z=0;z<b.length;z++)
         b[z]=input.nextInt();
*/
         

    }
}
