/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carol.s.cute.conquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author dannyyang
 */
public class CarolSCuteConquest {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader (System.in));
        String stock[]=input.readLine().split(" ");
        int N=Integer.parseInt(stock[0]);
        int K=Integer.parseInt(stock[1]);
        int []index=new int[K];
        int total=0;
        for(int i=0;i<K;i++){
            index[i]=Integer.parseInt(input.readLine());
        }
        
        if(K>=3){
        int mu=index[0];
       int mu2=index[2];
        //    System.out.println(must+" "+must2);
        int b=N-mu2+1;
        total+=mu*b;
        for(int fi=1;fi<K-2;fi++){
            int must=index[fi];
            
            int must2=index[fi+2];
        //    System.out.println(must+" "+must2);
            int a=N-must2+1;
            total+=(must-index[fi]+1)*a;
            
        }
        
        System.out.println(total);
        }
        else{
         System.out.println(0);
        }
    }
    
}
