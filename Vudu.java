/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vudu;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

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

public class Vudu {

    /**
     * @param args the command line arguments
     */
    static class edge implements Comparable<edge> {

        int index;
        long su;

        edge(int index, long su) {
            this.index = index;
            this.su = su;
        }

        @Override
        public int compareTo(edge n) {
            if (su < n.su) {
                return -1;
            } else if (su == n.su) {
                if (index < n.index) {
                    return -1;
                }
                return 1;
            }
            return 1;
        }
    }
    static int BIT[];
    static int N;

    public static void update(int index) {
        for (int i = index; i <= N + 1; i += (i & -i)) {
            BIT[i]++;
        }
    }

    public static int query(int index) {
        int total = 0;
        for (int i = index; i > 0; i -= (i & -i)) {
            total += BIT[i];
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        N = input.nextInt();
        BIT = new int[N + 2];
        long sum[] = new long[N + 1];

        PriorityQueue<edge> go = new PriorityQueue();

        for (int i = 1; i <= N; i++) {
            sum[i] = input.nextLong();
        }
        long P = input.nextInt();
        go.add(new edge(1, 0));
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i] - P + sum[i - 1];
            go.add(new edge(i + 1, sum[i]));
        }

        long total = 0;
        for (int i = 0; i <= N; i++) {
            edge temp = go.poll();
            total += query(temp.index);
            update(temp.index);
        }
        System.out.println(total);

    }

}
