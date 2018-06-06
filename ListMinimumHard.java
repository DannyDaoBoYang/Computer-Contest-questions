/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list.minimum.hard;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class ListMinimumHard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        String a[]=new String[N];
        for(int i=0;i<N;i++){
            a[i]=input.next();
        }
        Arrays.sort(a);
       if(a.length==2)
        for(int i=0;i<N;i++){
            System.out.println(a[i]);
            
        }
       else{
           System.out.println("N");
       }
    }
    
}
