/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package another.contest.pkg2.problem.pkg1.poutine;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dannyyang
 */
public class AnotherContest2Problem1Poutine {

    /**
     * @param args the command line arguments
     */
    
    
    static long distance[][];
    static long dp[][][];
    
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        distance=new long[N+1][N+1];
        
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
            distance[i][j]=input.nextInt();
            }
        }
        
        for (int k=1;k<=N;k++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    if(distance[i][k]!=0&&distance[k][j]!=0){
                        if(distance[i][j]!=0)
                             distance[i][j]=Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                        else if(i!=j){
                            distance[i][j]=distance[i][k]+distance[k][j];
                        }
                    }
                }
            }
        }
            
        
        /*System.out.println();
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(distance[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();*/
        int Q=input.nextInt();
        dp=new long[N+1][N+1][N+1];
        dp[1]=distance.clone();
        for(int t=2;t<=N;t++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    dp[t][i][j]=dp[t-1][i][j];
                    if(dp[t][i][j]!=0)
                    for(int k=1;k<=N;k++){
                        if(dp[t-1][i][k]!=0&&dp[t-1][k][j]!=0){
                            dp[t][i][j]=Math.min(dp[t][i][j],Math.max(distance[i][k], dp[t-1][k][j]));
                            
                        }
                    }
                }
            }
        }
        for(int i=0;i<Q;i++){
            int from=input.nextInt();
            int to=input.nextInt();
            int days=Math.min(N,input.nextInt());
            System.out.println(dp[days][from][to]);
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


