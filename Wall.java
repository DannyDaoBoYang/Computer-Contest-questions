/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wall;

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

public class Wall {

    /**
     * @param args the command line arguments
     */
    static class node {

        int l = 0, r = 0;
        int lo = 0, hi = 0;
        int lazyl = 0, lazyh = 0;

        node() {

        }
    }
    static node seg[];

    public static void build(int index, int l, int r) {
        seg[index] = new node();
        if (l == r) {
            seg[index].l = l;
            seg[index].r = r;
            seg[index].lo = 0;
            seg[index].hi = 0;
            seg[index].lazyh = Integer.MAX_VALUE;
            seg[index].lazyl = 0;
            return;
        }

        seg[index].l = l;
        seg[index].r = r;
        seg[index].lo = 0;
        seg[index].hi = 0;
        seg[index].lazyh = Integer.MAX_VALUE;
        seg[index].lazyl = 0;
        int half = (r + l) / 2;
        build(2 * index, l, half);
        build(2 * index + 1, half + 1, r);
    }

    public static void Query(int index, int l, int r) {
        if (seg[index].lo == seg[index].hi) {
            for (int i = l; i <= r; i++) {
                System.out.println(seg[index].lo);
            }
            return;
        }
        int half = (r + l) / 2;
        pass(index);
        Query(2 * index, l, half);
        Query(2 * index + 1, half + 1, r);
    }

    public static void add(int index, int l, int r, int val) {
        //lazyl
        if (seg[index].lo >= val) {
            return;
        }
        if (seg[index].l == l && seg[index].r == r) {
            seg[index].lazyl = Math.max(seg[index].lazyl, val);
            seg[index].lo = Math.max(seg[index].lo, val);
            seg[index].hi = Math.max(seg[index].hi, val);
            return;
        }
        int half = (seg[index].l + seg[index].r) / 2;

        if (half >= r) {
            passh(2 * index);
            add(2 * index, l, r, val);
            goup(index);
        } else if (half < l) {
            passh(2 * index + 1);
            add(2 * index + 1, l, r, val);
            goup(index);
        } else {
            passh(2 * index);
            passh(2 * index + 1);
            add(2 * index, l, half, val);
            add(2 * index + 1, half + 1, r, val);
            goup(index);
        }

    }

    public static void pass(int index) {
        if (seg[index].l == seg[index].r) {
            return;
        }
        passl(index);
        passh(index);
    }

    public static void passl(int index) {
        if (seg[index].l == seg[index].r) {
            return;
        }
        if (seg[index].lazyl != 0) {
            passh(index * 2);
            passh(index * 2 + 1);
            add(index * 2, seg[index * 2].l, seg[index * 2].r, seg[index].lazyl);
            add(index * 2 + 1, seg[index * 2 + 1].l, seg[index * 2 + 1].r, seg[index].lazyl);
            seg[index].lazyl = 0;
        }
    }

    public static void passh(int index) {
        if (seg[index].l == seg[index].r) {
            return;
        }
        if (seg[index].lazyh != Integer.MAX_VALUE) {
            passl(index * 2);
            passl(index * 2 + 1);
            remove(index * 2, seg[index * 2].l, seg[index * 2].r, seg[index].lazyh);
            remove(index * 2 + 1, seg[index * 2 + 1].l, seg[index * 2 + 1].r, seg[index].lazyh);
            seg[index].lazyh = Integer.MAX_VALUE;
        }
    }

    public static void goup(int index) {
        seg[index].lo = Math.min(seg[index * 2].lo, seg[index * 2 + 1].lo);
        seg[index].hi = Math.max(seg[index * 2].hi, seg[index * 2 + 1].hi);
    }

    public static void remove(int index, int l, int r, int val) {
        //lazyh
        if (seg[index].hi <= val) {
            return;
        }
        if (seg[index].l == l && seg[index].r == r) {
            seg[index].lazyh = Math.min(seg[index].lazyh, val);
            seg[index].lo = Math.min(seg[index].lo, val);
            seg[index].hi = Math.min(seg[index].hi, val);
            return;
        }
        int half = (seg[index].l + seg[index].r) / 2;

        if (half >= r) {
            passl(2 * index);
            remove(2 * index, l, r, val);
            goup(index);
        } else if (half < l) {
            passl(2 * index + 1);
            remove(2 * index + 1, l, r, val);
            goup(index);
        } else {
            passl(2 * index + 1);
            passl(2 * index);
            remove(2 * index, l, half, val);
            remove(2 * index + 1, half + 1, r, val);
            goup(index);
        }

    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int N = input.nextInt();
        int K = input.nextInt();
        seg = new node[4 * N + 10];
        build(1, 0, N - 1);
        for (int i = 0; i < K; i++) {
            int op = input.nextInt();
            int l = input.nextInt();
            int r = input.nextInt();
            int val = input.nextInt();
            if (op == 1) {
                pass(1);
                add(1, l, r, val);
            } else {
                pass(1);
                remove(1, l, r, val);
            }
        }
        Query(1, 0, N - 1);
    }

}
