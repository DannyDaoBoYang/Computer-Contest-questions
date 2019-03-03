/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.weighty.problem;

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

public class AWeightyProblem {

    /**
     * @param args the command line arguments
     */
    static class edge implements Comparable<edge> {

        int value;
        double weight;

        edge(int value, double weight) {
            this.value = value;
            this.weight = weight;
        }

        @Override
        public int compareTo(edge n) {
            if (value < n.value) {
                return -1;
            }
            return 1;
        }
    }
    static double change[];
    static edge coins[];

    public static void go(int start, int end, double weight, int current) {
        if(start>=end){
            return;
        }
        change[start] = weight;
        go(start + coins[current].value, end, weight + coins[current].weight, current);
        for (int i = current-1; i >= 0; i--) {
                go(start + coins[i].value, Math.min(end,start+coins[i+1].value), weight + coins[i].weight, i);   
        }
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int C = input.nextInt();
        int D = input.nextInt();
        int K = input.nextInt();
        coins = new edge[D];
        int value[] = new int[D + 1];
        double weight[] = new double[D + 1];
        int have[] = new int[D + 1];
        double answer = 0;
        int total = 0;
        for (int i = 1; i <= D; i++) {
            coins[i - 1] = new edge(input.nextInt(), input.nextDouble());
            value[i] = coins[i - 1].value;
            weight[i] = coins[i - 1].weight;

        }

        for (int i = 0; i < K; i++) {
            int which = input.nextInt();
            have[which]++;
            total += value[which];
            answer += weight[which];
        }
        if (total < C) {
            System.out.println("too poor");
        } else {
            double dp[] = new double[total + 1];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            int current = 0;
            for (int i = 1; i <= D; i++) {
                for (int j = 1; j <= have[i]; j++) {
                    current += value[i];
                    for (int k = current; k >= value[i]; k--) {
                        if (dp[k - value[i]] != -1) {
                            dp[k] = Math.max(dp[k], dp[k - value[i]] + weight[i]);
                        }
                    }
                }
            }
            Arrays.sort(coins);
            change = new double[total- C+1];
            //System.out.println(dp[2]);
            go(0, total - C+1, 0, D - 1);
            double best=999999999;
            for(int i=0;i<change.length;i++){
                if(dp[i+C]!=-1)
            best=Math.min(best, change[i]-dp[i+C]);
            // System.out.println((i+C)+" "+(change[i]-dp[i+C]));
             //System.out.println(dp[i+C]);
             //System.out.println(change[i]);
            }
            double finalanswer=best+answer;
            finalanswer*=100;
            double atp=Math.round(finalanswer);
            atp/=100;
            System.out.printf("%.2f",atp);
        }

    }

}
