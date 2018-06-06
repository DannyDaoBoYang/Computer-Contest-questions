/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime.time;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class PrimeTime {

    /**
     * @param args the command line arguments
     */
    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        for(int i=0;i<5;i++){
            String[] in=input.nextLine().split(" ");
            int times=Integer.parseInt(in[0]);
            int prime=Integer.parseInt(in[1]);
            int [] numbers=new int[in.length-2];
            for(int test=0;test<in.length;test++){
                numbers[]
            }
        }
    }
    
}
