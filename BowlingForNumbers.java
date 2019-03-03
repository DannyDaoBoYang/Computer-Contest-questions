/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling.pkgfor.numbers;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dannyyang
 */
class Reader {

    final private int BUFFER_SIZE = 1 << 16;   
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    private int http;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                break;
            }
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) {
            return -ret;
        }
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg) {
            return -ret;
        }
        return ret;
    }

    private void fillBuffer() throws IOException {

        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }
}
public class BowlingForNumbers {
static int [][] dp=new int[0][0];
public static void clean(int n,int k){
    dp=new int[0][0];
    dp=new int[n+1][k+1];
}
public static int sum(int []a, int n){
    int answer=0;
    for(int i=a.length-1;i>a.length-1-n;i--){
        answer+=a[i];
    }
    return answer;
}
public static int fun(int [] bowls,int n,int k,int w,int sum,int start){
    
    if(dp[start][k]!=0){
        return dp[start][k]+sum;
    }
    if(start>=n-k){
        return sum;
    }
    if(0==k){
    return sum;
}
   int answer=0;
    for(int i=start;i<=n+1-w;i++){
        if(bowls[i]==0){
            continue;
        }
        int plus=0;
        
        for(int c=0;c<w;c++){
            if(i+c>n){
            break;
        }
            plus+=bowls[i+c];

        }

        answer=Math.max(answer,fun(bowls,n,k-1,w,sum+plus,i+w));
       

        

    }
    dp[start][k]=answer-sum;
    return answer;
}
public static int fun2(int []bowls, int k,int w, int start, int []sum, int base){
    
    int answer=0;
    if(dp[start][k]!=0){
        return dp[start][k];
    }
    if(k==0){
        return 0;
    }
    if(k==1){
    for(int i=start;i<=bowls.length-w*k;i++){
        answer=Math.max(answer,sum[0]-sum[1]);
    }
    dp[start][k]=answer;
    return answer;
    }
    answer=fun2(bowls,k-1,w,start,sum,base);
    
    return dp[start][k];
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int nw=0;
        ArrayList<Integer> adj=new ArrayList<Integer>();
        for(int i=0;i<nw;i++){
            
        }
       int t=input.nextInt();
        int []answer=new int[t];
        for(int i=0;i<t;i++){
           int n=input.nextInt();
           int k=input.nextInt();
           int w=input.nextInt();
           int []bowls=new int[n+2];
           int []suma=new int[n+2];
           for(int j=1;j<=n;j++){
               bowls[j]=input.nextInt();
               suma[j]=suma[j-1]+bowls[j];
           }
            if(w!=1){
            clean(n+1,k+1);
           answer[i]=fun(bowls,n,k,w,0,0);
           }
           else{
                Arrays.sort(bowls);
               answer[i]=sum(bowls,k);
           }
           
    }
        for(int i=0;i<t;i++){
            System.out.println(answer[i]);
        }
   /*     int t=input.nextInt();
        int []answer=new int[t];
          for(int i=0;i<t;i++){
           int n=input.nextInt();
           int k=input.nextInt();
           int w=input.nextInt();
           int []bowls=new int[n+1];
           int []sum=new int[n+1];
           for(int j=1;j<=n;j++){
               bowls[j]=input.nextInt();
               sum[j]=sum[j-1]+bowls[j-1];
           }
           clean(n,k);
           
           fun2(bowls,k,w,1,sum,0);
           for(int h=0;h<n+1;h++){
               for(int z=0;z<k+1;z++){
                   System.out.print(dp[h][z]+"\t");
               }
               System.out.println();
           }
          }*/
}
}
