/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary.search.tree.test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author dannyyang
 */
public class BinarySearchTreeTest {

    /**
     * @param args the command line arguments
     */
    static class node {

        int val = 0, left = 0, right = 0, cl = 0, cr = 0;
        int count = 1;
    }
    static int aviable = 2;

    public static void add(int current, int val) {
        if (val == BST[current].val) {
            BST[current].count++;
            return;
        } else if (val < BST[current].val) {
            if (BST[val].cl != 0) {
                BST[val].cl++;
                add(BST[current].left, val);

            } else {
                BST[val].cl = 1;
                BST[val].left = aviable;
                BST[aviable].val=val;
                aviable++;
            }
        }
        else{
            if (BST[val].cr != 0) {
                BST[val].cr++;
                add(BST[current].right, val);

            } else {
                BST[val].cr = 1;
                BST[val].right = aviable;
                BST[aviable].val=val;
                aviable++;
            }
        }
    }

    public static int S(int current, int val) {
       if(val==BST[current].val){
           
       }
    }
    static node BST[] = new node[600000];

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int N = input.nextInt();
        int M = input.nextInt();

        int values[] = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            values[i] = input.nextInt();
        }
        BST[1].val = values[1];
        for (int i = 2; i <= N; i++) {
            add(1, values[i]);
        }

    }

    static class Reader {

        final private int BUFFER_SIZE = 1 << 17;
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
