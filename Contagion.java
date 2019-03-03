/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contagion;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author dannyyang
 */
public class Contagion {
public static int binary(long [] A,long target){
   int lo = 1;
   int hi = A.length;
   
   while (lo <= hi){
      int mid = lo + (hi-lo)/2;
      if(mid<A.length)
      if (A[mid] == target){
         return mid;
      }
      else if (A[mid] < target){
         lo = mid+1;
      }
      else{
         hi = mid-1;
      }
      else{
          return A.length-1;
      }
                 }
   return lo-1;
                 }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //input 
        Reader input=new Reader();
        int N=input.nextInt();
        long x[]=new long[N+1];
        long y[]=new long[N+1];
        for(int i=1;i<=N;i++){
            x[i]=input.nextInt();
            y[i]=input.nextInt();
        }
        int X=input.nextInt();
        long xi=x[X];
        long yi=y[X];
        long time[]=new long[N+1];
        boolean went[]=new boolean[N+1];
        went[0]=true;
        went[X]=true;
        int index=-1;
            long largest=0;
            int con=0;
        for(int i=1;i<=N;i++){
            time[i]=(x[i]-xi)*(x[i]-xi)+(y[i]-yi)*(y[i]-yi);
            if(!went[i]){
                    index=i;
                    
                    largest=time[i];
                    con=i+1;
                    break;
                }
        }
        for(int i=con;i<=N;i++){
            time[i]=(x[i]-xi)*(x[i]-xi)+(y[i]-yi)*(y[i]-yi);
            if(!went[i]){
                    if(time[i]<largest){
                    index=i;
                    
                    largest=time[i];
                    }
 
                }
        }
        
        for(int i=0;i<N;i++){
            
            went[index]=true;
            
            int pindex=-1;
            
            
            for(int j=1;j<=N;j++){
                if(!went[j]){
                    
                time[j]=Math.min(time[index]+(x[j]-x[index])*(x[j]-x[index])+(y[j]-y[index])*(y[j]-y[index]),time[j]);
                
                    pindex=j;
                    largest=time[j];
                    con=j+1;
                    break;
            }
            }
            for(int j=con;j<=N;j++){
               
                if(!went[j]){
                time[j]=Math.min(time[index]+(x[j]-x[index])*(x[j]-x[index])+(y[j]-y[index])*(y[j]-y[index]),time[j]);      
                    if(time[j]<largest){
                    pindex=j;
                    largest=time[j];
                    }
                }
            }
            if(pindex==-1){
                break;
            }
            else{
                index=pindex;
            }
        }
        Arrays.sort(time);
        
        long Q=input.nextLong();
        for(long i=0;i<Q;i++){
            int count=0;
            long question=input.nextLong();
            
            int answer=binary(time,question);
            if(answer+1<=N)
            while(time[answer]==time[answer+1]){
                if(answer+2<=N){
                answer++;
                }
                else{
                    answer++;
                    break;
                }
            }
            
            System.out.println(answer);
            
        }
        
    }
    
    
}

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
