/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary.indexed.tree.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author dannyyang
 */

public class BinaryIndexedTreeTest {
    static int freq[]=new int[100001];
    static long bit[];
    static int arr[];
    static long pre[];
    static int N;
static void update(int idx ,int val) {
while (idx <= N){
bit[idx] += val;
idx += idx&-idx;

}
}
public static long Query(int r){
    long sum=0;
    for(int i=r;i>0;i-=i&-i){
        sum+=bit[i];
        
    }
    return sum+pre[r];
}
static void updateQ(int idx,int val) {
while (idx <= 100001){
freq[idx] += val;
idx += idx&-idx;

}
}
public static long QueryQ(int r){
    long sum=0;
    for(int i=r;i>0;i-=i&-i){
        sum+=freq[i];
        
    }
    return sum;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String first[]=input.readLine().split(" ");
        N=Integer.parseInt(first[0]);
        int M=Integer.parseInt(first[1]);
        
        pre=new long[N+1];
        bit=new long[N+1];
        arr=new int[N+1];
        String[] prearr=input.readLine().split(" ");
        for(int i=1;i<=N;i++){
            arr[i]=Integer.parseInt(prearr[i-1]);
            pre[i]=pre[i-1]+arr[i];
            updateQ(arr[i],1);
        }
        
        for(int i=0;i<M;i++){
            String str[]=input.readLine().split(" ");
            if(str[0].equals("C")){
                int a=Integer.parseInt(str[1]);
                int b=Integer.parseInt(str[2]);
                int pre=arr[a];
                
                arr[a] =b;
                updateQ(pre,-1);
                updateQ(b,1);
                update(a,b-pre);
                
            }
            else if(str[0].equals("Q")){
                System.out.println(QueryQ(Integer.parseInt(str[1])));
            }
            else if(str[0].equals("S")){
                int a=Integer.parseInt(str[1]);
                int b=Integer.parseInt(str[2]);
                System.out.println((Query(b)-Query(a-1)));
            }
            
        }
    }
    
}
