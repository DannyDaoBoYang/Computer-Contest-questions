/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genesis;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

public class GENESIS {

    static int INF = Integer.MAX_VALUE;
    static int MAXN = 110 * 2;
    static int N;
    static int res[][] = new int[MAXN][MAXN];
    static int E[] = new int[MAXN];
    static int dist[] = new int[MAXN];
    static int M, f;
    static int par[] = new int[MAXN];

    static boolean general[];

    public static int getfront(int n) {
        return n * 2;
    }

    public static int getback(int n) {
        return n * 2 + 1;
    }

    public static void aug(int cur, int minE) {
        if (cur == getfront(1)) {
            f = minE;
            return;
        } else if (par[cur] != -1) {
            aug(par[cur], Math.min(minE, res[par[cur]][cur]));
            res[par[cur]][cur] -= f;
            res[cur][par[cur]] += f;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        N = input.nextInt();

        for (int i = 1; i < N; i++) {
            E[i] = input.nextInt();
            res[getfront(i)][getback(i)] = E[i];
        }
        M = input.nextInt();
        for (int i = 0; i < M; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            a = getback(a);
            b = getfront(b);
            res[a][b] = INF;
        }
        int mf = 0;
        while (true) {
            f = 0;
            Arrays.fill(dist, 63);
            Arrays.fill(par, -1);
            dist[getfront(1)] = 0;
            Queue<Integer> q = new LinkedList();
            q.add(getfront(1));
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == getfront(N)) {
                    break;
                }
                for (int next = getfront(1); next <= getfront(N); next++) {
                    if (res[cur][next] > 0 && dist[next] == INF) {
                        dist[next] = dist[cur] + 1;
                        q.add(next);
                        par[next] = cur;
                    }
                }
            }
            aug(getfront(N), INF);
            if (f == 0) {
                break;
            }
            mf += f;

        }
        System.out.println(mf);

    }

}
