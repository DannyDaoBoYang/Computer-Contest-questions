/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic.snowman;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class ArithmeticSnowman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner input=new Scanner(System.in);
       int a=input.nextInt();
        int[] ball=new int[a];
       for(int i=0;i<a;i++){
           ball[i]=input.nextInt();
       }
      Arrays.sort(ball);
      int size=0;
     
      for(int i=0;i<a-2;i++){ 
          for(int x=i+1;x<a-1;x++){
             int d=2*ball[x]-ball[i];
               if(Arrays.binarySearch(ball, d)>=0){
                   
                   int j=Arrays.binarySearch(ball, d);
                   if(j>x){
                   if(size<ball[i]+ball[x]+ball[j]){
                       size=ball[i]+ball[x]+ball[j];
                   }
               }
               }
    
}
      }
      
      System.out.println(size);
}
}