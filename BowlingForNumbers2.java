/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlingfornumbers2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
public class BowlingForNumbers2 {
    static int[] dp = new int[0];
    static int[][] dp2 = new int[0][0];


    public static void clean(int n, int k) {
        dp = new int[0];
        dp = new int[n + 1];
        dp2 = new int[0][k + 1];
        dp2 = new int[k + 1][n + 1];

    }

    public static int sum(int start, int end, int arr[]) {
        int answer = 0;
        for (int i = start; i <= end; i++) {
            answer += arr[i];
        }
        return answer;
    }

    public static void fun(int arr[], int width) {
        for (int i = 1; i < arr.length - width + 1; i++) {
            dp[i] = sum(i, i + width - 1, arr);
        }
    }

    public static int arrange(int width, int k, int start, int base) {
        int answer = 0;

        if (start >= dp.length - width * k) {

            return base;
        }
        if (dp2[k][start] != 0) {
            return base + dp2[k][start];
        }

        if (k == 0) {
            return base;
        }
        for (int i = start; i <= dp.length - width * k; i++) {
            int arra = arrange(width, k - 1, i + width, base + dp[i]);
            answer = Math.max(answer, arra);
        }
        dp2[k][start] = answer - base;

        return answer;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int nw = 0;

        for (int i = 0; i < nw; i++) {

        }
        int t = input.nextInt();
        int[] answer = new int[t];
        for (int i = 0; i < t; i++) {
            int n = input.nextInt();
            int k = input.nextInt();
            int w = input.nextInt();
            int[] bowls = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                bowls[j] = input.nextInt();
            }
            clean(n, k);

            fun(bowls, w);
            answer[i] = arrange(w, k, 1, 0);

        }
        for (int i = 0; i < dp.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < dp2.length; i++) {
            for (int j = 0; j < dp2[i].length; j++) {
                System.out.print(dp2[i][j] + "\t");
            }

            System.out.println();
        }
        for (int i = 0; i < t; i++) {
            System.out.println(answer[i]);
        }
    }

}
