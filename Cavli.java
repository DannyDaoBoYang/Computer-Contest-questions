/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavli;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Cavli {

    /**
     * @param args the command line arguments
     */
    static class indicate implements Comparable<indicate> {

        int xy;
        int index;

        indicate(int xy, int index) {
            this.xy = xy;
            this.index = index;
        }

        @Override
        public int compareTo(indicate n) {
            if (xy < n.xy) {
                return -1;
            }
            return 1;
        }
    }

    static class info {

        double x;
        double y;
        int index;

        info(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class node {

        int aj[] = new int[2];

        node(int l, int r) {
            this.aj[0] = l;
            this.aj[1] = r;
        }
    }

    public static double add(int index, int start) {
        int left = determine(index, start, 0);
        int right = determine(index, start, 1);
        area -= finda(left, right, left, 0);
        area += findarea(left, right, index);
        connection[index] = new node(left, right);
        connection[left].aj[1] = index;
        connection[right].aj[0] = index;
        return area;
    }

    public static double finda(int left, int right, int current, double ar) {
        if (current == right) {
            double temp = (base[right].x * base[left].y - base[right].y * base[left].x) / 2;
            return Math.abs(ar + temp);

        } else {
            int tar = connection[current].aj[1];
            double temp = (base[current].x * base[tar].y - base[current].y * base[tar].x) / 2;
            return finda(left, right, tar, ar + temp);
        }

    }

    public static int determine(int index, int current, int direction) {

        int tar = connection[current].aj[direction];
        //  System.out.println(index+" "+current+" "+tar);
        double slope = (base[tar].y - base[current].y) / (base[tar].x - base[current].x);
        double indexside = base[index].y + (base[current].x - base[index].x) * slope;
        if (indexside == base[current].y) {
            return determine(index, tar, direction);
        }
        int reference = connection[tar].aj[direction];
        double referenceside = base[reference].y + (base[current].x - base[reference].x) * slope;
        // System.out.println(indexside+" "+base[current].y+" "+referenceside+" "+reference);
        if ((indexside <= base[current].y) != (referenceside <= base[current].y)) {
            return determine(index, tar, direction);
        }
        return current;
    }

    public static void update(int index) {
        if (base[index].y > base[umi].y) {
            umi = index;
        } else if (base[index].y < base[dmi].y) {
            dmi = index;
        }
        if (base[index].x < base[lmi].x) {
            lmi = index;
        } else if (base[index].x > base[rmi].x) {
            rmi = index;
        }
    }

    public static double findarea(int a, int b, int c) {
        double temp1 = base[a].x * base[b].y - base[a].y * base[b].x;
        double temp2 = base[b].x * base[c].y - base[b].y * base[c].x;
        double temp3 = base[c].x * base[a].y - base[c].y * base[a].x;
        return Math.abs(temp1 + temp2 + temp3) / 2;

    }

    static info[] base;
    static node[] connection;
    static double area = 0;
    static int lmi, rmi, umi, dmi;
    static int N;

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        //System.out.println(Integer.MAX_VALUE);
        N = input.nextInt();
        indicate[] ver = new indicate[N];
        indicate[] hor = new indicate[N];
        base = new info[N];
        for (int i = 0; i < N; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            ver[i] = new indicate(y, i);
            hor[i] = new indicate(x, i);
            base[i] = new info(x, y, i);

        }
        Arrays.sort(ver);
        Arrays.sort(hor);

        int order[] = new int[N];
        double answer[] = new double[N - 2];
        int lowerver = 0;
        int upperver = N - 1;
        int lowerhor = 0;
        int upperhor = N - 1;

        boolean taken[] = new boolean[N];
        char pro[] = new char[N];
        pro[N - 1] = 'R';
        pro[N - 2] = 'R';
        for (int i = 0; i < N - 2; i++) {
            pro[i] = (char) input.read();
        }
        for (int i = 0; i < N; i++) {
            if (pro[i] == 'L') {
                int current = hor[lowerhor].index;
                while (taken[current]) {
                    lowerhor++;
                    current = hor[lowerhor].index;
                }
                order[i] = current;
                taken[current] = true;

            } else if (pro[i] == 'R') {
                int current = hor[upperhor].index;
                while (taken[current]) {
                    upperhor--;
                    current = hor[upperhor].index;
                }
                order[i] = current;
                taken[current] = true;

            } else if (pro[i] == 'D') {
                int current = ver[lowerver].index;
                while (taken[current]) {
                    lowerver++;
                    current = ver[lowerver].index;
                }
                order[i] = current;
                taken[current] = true;

            } else {
                int current = ver[upperver].index;
                while (taken[current]) {
                    upperver--;
                    current = ver[upperver].index;
                }
                order[i] = current;
                taken[current] = true;

            }
        }

        int a = order[N - 1];
        int b = order[N - 2];

        int c = order[N - 3];

        area = findarea(a, b, c);
        answer[N - 3] = area;
        ver = new indicate[3];
        hor = new indicate[3];
        ver[0] = new indicate((int) base[a].y, a);
        ver[1] = new indicate((int) base[b].y, b);
        ver[2] = new indicate((int) base[c].y, c);
        hor[0] = new indicate((int) base[a].x, a);
        hor[1] = new indicate((int) base[b].x, b);
        hor[2] = new indicate((int) base[c].x, c);
        Arrays.sort(ver);
        Arrays.sort(hor);
        lmi = hor[0].index;
        //  System.out.println(lmi);
        rmi = hor[2].index;
        //  System.out.println(rmi);
        umi = ver[2].index;
        //  System.out.println(umi);
        dmi = ver[0].index;
        //  System.out.println(dmi);
        connection = new node[N + 1];
        connection[a] = new node(b, c);
        connection[b] = new node(c, a);
        connection[c] = new node(a, b);
        for (int i = N - 4; i >= 0; i--) {
            if (pro[i] == 'U') {
                answer[i] = add(order[i], umi);
            } else if (pro[i] == 'D') {
                answer[i] = add(order[i], dmi);
            } else if (pro[i] == 'L') {
                answer[i] = add(order[i], lmi);
            } else {
                answer[i] = add(order[i], rmi);
            }
            update(order[i]);

            //System.out.println(answer[i]);
        }
        for (int i = 0; i < N - 2; i++) {
            System.out.printf("%.1f", answer[i]);
            System.out.println();
        }

    }

    static class Reader {

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
            byte[] buf = new byte[N + 1]; // line length
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

        public byte read() throws IOException {
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

}
