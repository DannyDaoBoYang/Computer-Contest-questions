/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class Avalon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        double prob=1;
        for(int i=0;i<N;i++){
            double a=input.nextInt();
            double b=input.nextInt();
            prob*=(b-a)/b;
        }
        System.out.println(prob);
    }
    
}
