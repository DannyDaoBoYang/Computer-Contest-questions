/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.early.christmas.present;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class AnEarlyChristmasPresent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        int arr[]=new int[N];
        for(int i=0;i<N;i++){
            arr[i]=input.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(arr[0]);
    }
    
}
