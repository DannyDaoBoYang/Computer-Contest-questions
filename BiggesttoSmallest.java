/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biggesttosmallest;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class BiggesttoSmallest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        System.out.println("number of element");
        int c= input.nextInt();
        int [] a = new int [c];
        System.out.println("element");
        for(int i=0;i<a.length;i++)
            a[i]=input.nextInt();
            
        int change;
        for(int i=0;i<a.length-1;i++){
            while(a[i]>a[i+1]){
                change= a[i];//big
                a[i]=a[i+1];
                a[i+1]=change;
                
            }
            while(a[a.length-1]<a[i]){
                change= a[i];
                a[i]=a[a.length-1];
                a[a.length-1]=change;
                
            }
        }
        System.out.println("Form the least to the greatest");
        for(int i=0;i<a.length;i++)
        System.out.println(a[i]);
    }
    }
    

