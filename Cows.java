/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cows;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author dannyyang
 */
public class Cows {

    /**
     * @param args the command line arguments
     */
    static class tree implements Comparable<tree> {

        double x;
        double y;

        tree(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(tree n) {
            if (x < n.x) {
                return -1;
            } else if (x == n.x) {
                if (y < n.y) {
                    return -1;
                }
            }
            return 1;
        }
    }
    static Stack<Integer> index = new Stack();
    static Stack<Double> slope = new Stack();
    static Stack<Integer> index2 = new Stack();
    static Stack<Double> slope2 = new Stack();
    static int N;

    public static void find(int current) {
        if (current == N) {
            return;
        }
        if (arr[current].x == arr[index.peek()].x) {
            index.pop();
            slope.pop();
            if (index.isEmpty()) {
                index.add(current);
                slope.add(999999.9);
                find(current + 1);
            }
        }
        if (index.size() > 1) {
            double x = arr[current].x;
            double y = arr[current].y;
            int last = index.peek();
            double sl = (arr[last].y - y) / (arr[last].x - x);

            if (sl < slope.peek()) {
                index.push(current);
                slope.push(sl);
            } else {
                while (sl > slope.peek() && index.size() > 1) {
                    slope.pop();
                    index.pop();
                    sl = (arr[index.peek()].y - y) / (arr[index.peek()].x - x);
                }
                index.push(current);
                slope.push(sl);
            }
        } else {
            slope.add((arr[current].y - arr[index.peek()].y) / (arr[current].x - arr[index.peek()].x));
            index.add(current);

        }
        find(current + 1);
    }

    public static void find2(int current) {
        if (current == -1) {
            return;
        }

        if (arr[current].x == arr[index2.peek()].x) {
            // System.out.println("here");
            index2.pop();
            slope2.pop();
            if (index2.isEmpty()) {
                index2.add(current);
                slope2.add(999999.9);
                find2(current - 1);
            }
        }
        if (index2.size() > 1) {
          //  System.out.println("Here"+ current);
            double x = arr[current].x;
            double y = arr[current].y;
            int last = index2.peek();
            double sl = (arr[last].y - y) / (arr[last].x - x);
            // System.out.println(slope2.peek()+" "+sl);
            // System.out.println("here1 "+index2.size());
            if (sl < slope2.peek()) {
                //  System.out.println("here2");
                index2.push(current);
                slope2.push(sl);
            } else {
                while (sl > slope2.peek() && index2.size() > 1) {
                    // System.out.println("here3");
                    slope2.pop();
                    index2.pop();
                    sl = (arr[index2.peek()].y - y) / (arr[index2.peek()].x - x);
                }
                index2.push(current);
                slope2.push(sl);
            }
        } else {
            int last = index2.peek();
            index2.add(current);
            slope2.add((arr[current].y - arr[last].y) / (arr[current].x - arr[last].x));
            //  System.out.println(last);
            // System.out.println(index2.peek());
            // System.out.println(slope2.peek());

        }
        find2(current - 1);
    }

    public static double findarea(int a1, int a2) {
        return (arr[a1].x * arr[a2].y) - (arr[a1].y * arr[a2].x);
    }
    static tree[] arr;

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        N = input.nextInt();
        arr = new tree[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new tree(input.nextInt(), input.nextInt());
        }
        Arrays.sort(arr);
        index.add(0);
        slope.add(9999999999.9);
        find(1);

        index2.add(N-1);
        slope2.add(9999999.9);
        find2(index2.peek() - 1);
        
        double area1 = 0;
        double area2 = 0;
        int last = index.peek();
        int first = index.peek();
        index.pop();
        //  System.out.println(last + " 11111");
        while (!index.isEmpty()) {
            area1 += findarea(last, index.peek());
            last = index.peek();
            //   System.out.println(last + " " + area1);
            index.pop();
        }
        area1 += findarea(last, first);
       // System.out.println(area1 / 2);

        int last2 = index2.peek();
        int first2 = index2.peek();
        index2.pop();
       // System.out.println(last2 + " 222222");
        while (!index2.isEmpty()) {
            area2 += findarea(last2, index2.peek());
            last2 = index2.peek();
         //   System.out.println(last2 + " " + slope);
            index2.pop();
            
        }
        area2 += findarea(last2, first2);
       // System.out.println(area2);
        double area3 = 0;
        area3 += findarea(0, last);
        area3 += findarea(last, first);
        area3 += findarea(first, last2);
        area3 += findarea(last2, 0);
       // System.out.println(area3);
        double total = Math.abs(area1) / 2 + Math.abs(area2) / 2 + Math.abs(area3) / 2;
        System.out.println((int)(total/50));

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
