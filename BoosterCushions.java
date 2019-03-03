/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booster.cushions;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class BoosterCushions {

    /**
     * @param args the command line arguments
     */
    static long people[];
    public static int sum(int from, int to){
        int total=0;
        for(int i=from;i<=to;i++){
            total+=people[i];
        }
        return total;
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        int M=input.nextInt();
        int K=input.nextInt();
        people=new long[K];
        for(int i=0;i<K;i++){
            people[i]=input.nextInt();
        }
        Arrays.sort(people);
        
        int front=0;
        int end=K-1;
        long total=0;
        while(front<=end){
            long current=people[end];
            if(front+N>=end){
                
                total=total+(current+current+(end-front))*(end-front+1)/2-sum(front, end);
                break;
            }
            else{
                
                total=total+(current+current+N-1)*N/2-sum(front, front+(int)N-2)-people[end];
                //System.out.println(total);
                front=front+N-1;
                end=end-1;
            }
        }
        System.out.println(total);
            
    }
    
}
