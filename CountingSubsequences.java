/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counting.subsequences;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class CountingSubsequences {

    

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String Pri = input.next();
        int[] times = new int[100001];
        char[] sec = new char[Pri.length() + 1];

        for (int i = 1; i <= Pri.length(); i++) {
            sec[i] = Pri.charAt(i - 1);
        }
        int m = 10007;
        
        for(int i=1;i<=Pri.length();i++){
            for(int j=i-1;j>=0;j--){
                if(sec[i]==sec[j]){
                    times[i]=(times[i-1]*2-times[j-1]+m)%m;
                    break;
                }
                if(j==0){
                    times[i]=(times[i-1]*2+1+m)%m;
                }
                
            }
        }
        System.out.println(times[Pri.length()]);


    }

}
