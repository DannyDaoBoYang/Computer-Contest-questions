/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build.fences;

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

public class BuildFences {

    /**
     * @param args the command line arguments
     */
    static class edge implements Comparable<edge> {

        int x;
        int y;

        edge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(edge n) {
            if (x < n.x) {
                return -1;
            } else if (x == n.x) {
                if (y < n.y) {
                    return -1;
                } else if (y > n.y) {
                    return 1;
                }
                return 0;
            }
            return 1;
        }
    }

    static class edge2 implements Comparable<edge2> {

        int x;
        int y;

        edge2(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(edge2 n) {
            if (y < n.y) {
                return -1;
            } else if (y == n.y) {
                if (x < n.x) {
                    return -1;
                } else if (x > n.x) {
                    return 1;
                }
                return 0;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int N = input.nextInt();
        int xcoor[] = new int[N + 1];
        int ycoor[] = new int[N + 1];
        int maxy = Integer.MIN_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        int minx = Integer.MAX_VALUE;
        edge byx[] = new edge[N];
        edge2 byy[] = new edge2[N];
        for (int i = 0; i < N; i++) {
            xcoor[i] = input.nextInt();
            maxx = Math.max(maxx, xcoor[i]);
            minx = Math.min(minx, xcoor[i]);
            ycoor[i] = input.nextInt();
            maxy = Math.max(maxy, ycoor[i]);
            miny = Math.min(miny, ycoor[i]);
            byx[i] = new edge(xcoor[i], ycoor[i]);
            byy[i] = new edge2(xcoor[i], ycoor[i]);
        }
        long answer = 0;
        part1:
        {
            Arrays.sort(byx);
            for (int i = 0; i < N; i++) {
                xcoor[i] = byx[i].x;
                ycoor[i] = byx[i].y;
            }
            long area = ((long) (maxy - miny)) * ((long) (maxx - minx));
            maxy = Integer.MIN_VALUE;
            miny = Integer.MAX_VALUE;
            maxx = Integer.MIN_VALUE;
            minx = Integer.MAX_VALUE;
            
            for (int i = 0; i < N - 1; i++) {
                maxx = Math.max(maxx, xcoor[i]);
                minx = Math.min(minx, xcoor[i]);
                maxy = Math.max(maxy, ycoor[i]);
                miny = Math.min(miny, ycoor[i]);
                
                long subarea = 0;
                if (i + 1 != N - 1) {
                    boolean had=false;
                    int maxy2 = Integer.MIN_VALUE;
                    int miny2 = Integer.MAX_VALUE;
                    int maxx2 = Integer.MIN_VALUE;
                    int minx2 = Integer.MAX_VALUE;
                    for (int j = i + 1; j < N; j++) {
                        if(ycoor[j]<=maxy){
                            maxx=Math.max(maxx, xcoor[j]);
                        }
                        else{
                            had=true;
                        maxx2 = Math.max(maxx2, xcoor[j]);
                        minx2 = Math.min(minx2, xcoor[j]);
                        maxy2 = Math.max(maxy2, ycoor[j]);
                        miny2 = Math.min(miny2, ycoor[j]);
                        }
                    }
                    if(!had){
                        continue;
                    }
                    subarea = ((long) (maxy2 - miny2)) * ((long) (maxx2 - minx2));
                } else {
                    subarea = 0;
                }
                // System.out.println(area + " " + subarea + " " + area1);
                long area1 = ((long) (maxy - miny)) * ((long) (maxx - minx));
                answer = Math.max(answer, area - subarea - area1);

            }
        }
        /*
        part2:
        {
            Arrays.sort(byy);
            for (int i = 0; i < N; i++) {
                xcoor[i] = byy[i].x;
                ycoor[i] = byy[i].y;
            }
           
            maxy = Integer.MIN_VALUE;
            miny = Integer.MAX_VALUE;
            maxx = Integer.MIN_VALUE;
            minx = Integer.MAX_VALUE;
            
            for (int i = 0; i < N - 1; i++) {
                maxx = Math.max(maxx, xcoor[i]);
                minx = Math.min(minx, xcoor[i]);
                maxy = Math.max(maxy, ycoor[i]);
                miny = Math.min(miny, ycoor[i]);
                long area1 = ((long) (maxy - miny)) * ((long) (maxx - minx));
                long subarea = 0;
                if (i + 1 != N - 1) {
                    boolean had=false;
                    int maxy2 = Integer.MIN_VALUE;
                    int miny2 = Integer.MAX_VALUE;
                    int maxx2 = Integer.MIN_VALUE;
                    int minx2 = Integer.MAX_VALUE;
                    for (int j = i + 1; j < N; j++) {
                        maxx2 = Math.max(maxx2, xcoor[j]);
                        minx2 = Math.min(minx2, xcoor[j]);
                        maxy2 = Math.max(maxy2, ycoor[j]);
                        miny2 = Math.min(miny2, ycoor[j]);
                    }
                    if (minx2 <= maxx) {
                        subarea = Integer.MAX_VALUE;
                        continue;
                    }
                    subarea = ((long) (maxy2 - miny2)) * ((long) (maxx2 - minx2));
                } else {
                    subarea = 0;
                }
                // System.out.println(area + " " + subarea + " " + area1);
                 long area = ((long) (maxy - miny)) * ((long) (maxx - minx));
                answer = Math.max(answer, area - subarea - area1);

            }
        }
                */
        System.out.println(answer);

    }

}
