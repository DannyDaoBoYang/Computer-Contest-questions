/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cow.hopscotch;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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
        byte[] buf = new byte[31]; // line length
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

public class CowHopscotch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int R = input.nextInt();
        int C = input.nextInt();
        int K = input.nextInt();
        int map[][] = new int[R + 2][C + 2];
        ArrayList<Integer> x[] = new ArrayList[K+1];
        ArrayList<Integer> y[] = new ArrayList[K+1];
        for (int i = 0; i <= K; i++) {
            x[i] = new ArrayList();
            y[i] = new ArrayList();
        }
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                map[i][j] = input.nextInt();
                x[map[i][j]].add(i);
                y[map[i][j]].add(j);
            }
        }

        int answer[][] = new int[R + 2][C + 2];
        int sig[][] = new int[R + 2][C + 2];
        long sum[][] = new long[R + 2][C + 2];
        long mod = 1000000007;
        sig[1][1] = -1;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                answer[i][j] = (int) ((sum[i - 1][j - 1] - sig[i][j]) % mod);
                if (answer[i][j] < 0) {
                    answer[i][j] += mod;
                }
                sum[i][j] = (sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1]) % mod;
                sum[i][j] = (sum[i][j] + answer[i][j])%mod;
                int temp = map[i][j];
                x[temp].remove(0);
                y[temp].remove(0);
                for (int w = 0; w < x[temp].size(); w++) {
                    if (x[temp].get(w) > i && y[temp].get(w) > j) {
                        sig[x[temp].get(w)][y[temp].get(w)] = (int) ((sig[x[temp].get(w)][y[temp].get(w)] + answer[i][j]) % mod);
                    }
                }

            }
        }
        System.out.println(answer[R][C]);
    }

}