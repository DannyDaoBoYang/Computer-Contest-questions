/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absolutely.acidic;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class AbsolutelyAcidic {
public static int G(int a[]){
    int b=a[0];
    for(int i=0;i<a.length;i++){
        
        if(a[i]>b)
            b=a[i];
    }
    return b;
}
public static int L(int a[]){
    int s=a[0];
    for(int i=0;i<a.length;i++){
        
        if(a[i]<s)
            s=a[i];
    }
    return s;
    
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input= new Scanner (System.in);
        int n= input.nextInt();
        int []a = new int [n];
        for(int i=0;i<a.length;i++){
            a[i]=input.nextInt();
        }
        int b=L(a);
        int c=G(a);
        System.out.println(c-b);
    }
    
}
