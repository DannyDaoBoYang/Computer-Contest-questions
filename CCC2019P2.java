/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int T=input.nextInt();
        boolean notprime[]=new boolean[2000000];
        notprime[0]=true;
        notprime[1]=true;
        ArrayList<Integer> primes=new ArrayList<Integer>();
        for(int i=2;i<2000000;i++){
            if(!notprime[i]){
                primes.add(i);
                for(int j=2*i;j<2000000;j+=i){
                    notprime[j]=true;
                }
            }
        }
        for(int i=0;i<T;i++){
            int N=input.nextInt();
            int start=Math.abs(Collections.binarySearch(primes,N/2));
            int front=0;
            int back=primes.size()-1;
            while (primes.get(front)+primes.get(back)!=2*N){
                if(primes.get(front)+primes.get(back)>2*N){
                    back--;
                }
                else{
                    front++;
                }
            }
            System.out.println(primes.get(front)+" "+primes.get(back));
            
            
            
        }
     
    }
    
}
