/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acm;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author dannyyang
 */
public class ACM {
    static int N;
    static int [][]dp;
public static int move(int m1[],int m2[], int m3[]){
    dp=new int[4][1+N];
    dp[1][0]=0;
    for(int i=1;i<=N;i++){
        dp[1][i]=Integer.MAX_VALUE;
        dp[2][i]=Integer.MAX_VALUE;
        dp[3][i]=Integer.MAX_VALUE;
    }
    for(int i=1;i<=N-2;i++){
        dp[1][i]=dp[1][i-1]+m1[i];
    }
    for(int i=2;i<=N-1;i++){
        dp[2][i]=Math.min(Math.min(dp[1][i-1], dp[2][i-1])+m2[i],dp[1][i]);
    }
    for(int i=3;i<=N;i++){
        dp[3][i]=Math.min(Math.min(dp[3][i-1], dp[2][i-1])+m3[i],dp[2][i]);
    }
   /* for(int i=1;i<=3;i++){
    for(int j=1;j<=N;j++){
        System.out.print(dp[i][j]+" ");
    }
    System.out.println();
}*/
    return dp[3][N];
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
         N=input.nextInt();
        int m1[]=new int[N+1];
        int m2[]=new int[N+1];
        int m3[]=new int[N+1];
        for(int i=1;i<=N;i++){
            m1[i]=input.nextInt();
        }
        for(int i=1;i<=N;i++){
            m2[i]=input.nextInt();
        }
        for(int i=1;i<=N;i++){
            m3[i]=input.nextInt();
        }
        int answer=Integer.MAX_VALUE;
        answer=Math.min(answer,move(m1,m2,m3));
        answer=Math.min(answer,move(m1,m3,m2));
        answer=Math.min(answer,move(m2,m1,m3));
        answer=Math.min(answer,move(m2,m3,m1));
        answer=Math.min(answer,move(m3,m2,m1));
        answer=Math.min(answer,move(m3,m1,m2));
        System.out.println(answer);
        
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