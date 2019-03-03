/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cake.balancing;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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

public class CakeBalancing {
    static int Li[];
    static int Ri[];
    static int answer=Integer.MAX_VALUE;
    static int W;
public static void Lfirst(int LsR,int indexl, int indexR, int indexlb,int indexRb,int steps, boolean Righturn){
    
    if(Righturn){
        
        int count=0;
        while(LsR-Ri[indexR]>=-W){
            LsR-=Ri[indexR];
            indexR--;
            count++;
            if(indexR==indexRb-1){
                
                 answer=Math.min(steps+1, answer);
                
                break;
            }
        }
        if(indexR!=indexRb-1)
        while(LsR-Ri[indexRb]>=-W){
            LsR-=Ri[indexRb];
            indexRb++;
            count++;
            if(indexR==indexRb-1){
                
                 answer=Math.min(steps+1, answer);
                
                
                break;
            }
        }
      //  System.out.println(count);
        if(indexR!=indexRb-1){
            Lfirst(LsR,indexl,indexR,indexlb,indexRb,steps+1,!Righturn);
        }
        
        
        
    }
    else{
        int count=0;
        while(LsR+Li[indexl]<=W){
            LsR+=Li[indexl];
            indexl--;
            count++;
            if(indexl==indexlb-1){
                
                answer=Math.min(steps+1, answer);
                
                break;
            }
        }
        if(indexl!=indexlb-1)
        while(LsR+Li[indexlb]<=W){
            LsR+=Li[indexlb];
            indexlb++;
            count++;
            if(indexl==indexlb-1){
                
                answer=Math.min(steps+1, answer);
                
                break;
            }
        }
     //   System.out.println(count);
        if(indexl!=indexlb-1){
            Lfirst(LsR,indexl,indexR,indexlb,indexRb,steps+1,!Righturn);
        }
        
    }
     
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       Reader input=new Reader();
       int L=input.nextInt();
       int R=input.nextInt();
       W=input.nextInt();
       Li=new int[L+1];
       Ri=new int[R+1];
       
       for(int i=1;i<=L;i++){
           Li[i]=input.nextInt();
       }
       for(int i=1;i<=R;i++){
           Ri[i]=input.nextInt();
       }
       Arrays.sort(Li);
       Arrays.sort(Ri);
       Lfirst(0,L,R,1,1,1,false);
       Lfirst(0,L,R,1,1,1,true);
       System.out.println(answer);
       
       
    }
    
}
