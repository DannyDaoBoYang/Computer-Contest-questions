/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package christmas.tree.building;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class ChristmasTreeBuilding {

    /**
     * @param args the command line arguments
     */
    static class edge {

        int y;
        int cost;

        edge(int y, int cost) {
            this.y = y;
            this.cost = cost;
        }

    }

    static class srt implements Comparable<srt> {

        long dis = 0;
        int y = 0;
        int index = 0;

        srt(long dis, int y, int index) {
            this.dis = dis;
            this.y = y;
            this.index = index;
        }

        @Override
        public int compareTo(srt n) {
            if (dis < n.dis) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    static long dis[];
    static long dis2[];
    static long D = 0;
    static int par;
    static ArrayList<edge> tree[];
    static int ban;
    static int ansy = 0;
    static int ansindex = 0;
    static int decheck[];

    public static void DFS(boolean went[], int position) {

        went[position] = true;
        long ans[] = new long[tree[position].size() + 2];

        for (int i = 0; i < tree[position].size(); i++) {
            int y = tree[position].get(i).y;
            if (went[y]) {
                continue;
            }
            DFS(went, y);
            ans[i] = dis[y] + tree[position].get(i).cost;
        }
        Arrays.sort(ans);
        dis[position] = ans[ans.length - 1];
        dis2[position] = ans[ans.length - 2];
        if (dis[position] + dis2[position] > D) {
            D = dis[position] + dis2[position];
        }
    }

    public static void DFS2(int previous, boolean went[], int position) {

        went[position] = true;
        PriorityQueue<srt> PQ=new PriorityQueue();
        

        for (int i = 0; i < tree[position].size(); i++) {
            int y = tree[position].get(i).y;
            if (went[y]) {
                
                PQ.add(new srt(0,0,-1));
                continue;
            }
            DFS2(position, went, y);
            
            PQ.add(new srt(dis[y] + tree[position].get(i).cost, y, i));
        }
        
        PQ.add(new srt(0, 0, -1));
        PQ.add(new srt(0, 0, -1));
        
        srt ansl=PQ.poll();
        dis[position] = ansl.dis;
        dis2[position] = PQ.peek().dis;
        decheck[position] = ansl.index;
        if (dis[position] + dis2[position] >= D) {
            D = dis[position] + dis2[position];
            par = position;
            ban = previous;
            ansy = ansl.y;
            ansindex = ansl.index;
        }

    }

    public static long getit(int position) {
        if (dis[position] == dis2[position]) {
            return 0;
        } else {
            long multi = 2;
            long here = checking(ansy, position, D - multi * dis2[position] - multi * tree[position].get(ansindex).cost);
            return Math.min(dis[position] - dis2[position], checking(ansy, position, D - 2 * dis2[position] - 2 * tree[position].get(ansindex).cost));
        }
    }

    public static long checking(int position, int previous, long difference) {
        if (decheck[position] == -1) {
            return Long.MAX_VALUE;
        }
        long answer = Math.abs(difference);
        answer = Math.min(answer, checking(tree[position].get(decheck[position]).y, position, difference - tree[position].get(decheck[position]).cost * 2));
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int N = input.nextInt();
        int M = input.nextInt();
        int Q = input.nextInt();
        tree = new ArrayList[N + 1];
        dis = new long[N + 1];
        dis2 = new long[N + 1];
        decheck = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList();
        }
        for (int i = 0; i < M; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();
            tree[a].add(new edge(b, c));
            tree[b].add(new edge(a, c));
        }
        if (Q == 1) {
            boolean went[] = new boolean[N + 1];
            long td = 0;
            boolean empty = true;
            for (int i = 1; i <= N; i++) {
                if (!went[i]) {
                    D = 0;

                    DFS(went, i);
                    if (!empty) {
                        td++;
                    }
                    td += D;
                    empty = false;
                }
            }

            System.out.println(td);
        } else {
            boolean went[] = new boolean[N + 1];
            long td = 1;
            long d = 0;
            for (int i = 1; i <= N; i++) {
                if (!went[i]) {
                    D = 0;
                    DFS2(0, went, i);
                    if (D >= td) {

                        long top = (D + getit(par)) / 2;
                        if (d == top) {
                            td = d + 1;
                        } else if (top > d) {
                            d = top;
                            td = d;
                        }

                    }

                }
            }

            System.out.println(td);
        }

    }

}
