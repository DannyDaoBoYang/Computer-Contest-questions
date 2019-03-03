/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author dannyyang
 */
/**
 *
 * @author dannyyang
 */
public class Main {

    static class Node {
        int l = 0, r = 0;
        long val = 0;
        long lazy = 0;
        Node(){
            
        }
    }
static Node seg[];
    static long tor[];
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int N = input.nextInt();
        int K = input.nextInt();
        int day = (int) Math.ceil((double) N / (double) K);
        
      //  System.out.println(day);

        long dp[][] = new long[N + 1][day + 1];
        tor = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            tor[i] = input.nextInt();
        }
        

        dp[1][1] = tor[1];
        //  System.out.println(dp[1][1]);
        
        
        for (int i = 2; i <= N; i++) {
            int passed = (int) Math.ceil((double) i / (double) K);
            long max=tor[i];
            for (int j = i-1; j >= i - K ; j--) {
                    if (dp[j][passed - 1] != 0) {
                        dp[i][passed] = Math.max(dp[j][passed - 1] + max, dp[i][passed]);
                    }
                    if(passed+1<=day)
                    if (dp[j][passed+1] != 0) {
                        dp[i][passed+1] = Math.max(dp[j][passed] + max, dp[i][passed+1]);
                    }
                max = Math.max(max, tor[j]);
                if (j == 1) {
                    break;
                }
            }
            if (i <= K) {
                dp[i][1] = Math.max(max, dp[i][1]);
            }
        }
        /* System.out.println();
         for(int i=0;i<=N;i++){
         for(int j=0;j<=day;j++){
         System.out.print(dp[i][j]+" ");
         }
         System.out.println();
         }*/
        System.out.println(dp[N][day]);

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