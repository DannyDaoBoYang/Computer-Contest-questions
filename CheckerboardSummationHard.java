/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard.summation.hard;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
public class CheckerboardSummationHard {

    static int bitodd[][];
    static int biteven[][];
    static int value[][];
    static int M;
    static int N;

    static void updateodd(int idx, int idy, int val) {
        for (int i = idx; i <= M; i += i & -i) {
            for (int j = idy; j <= N; j += j & -j) {
                bitodd[i][j] += val;
            }
        }
    }

    static void updateeven(int idx, int idy, int val) {
        for (int i = idx; i <= M; i += i & -i) {
            for (int j = idy; j <= N; j += j & -j) {
                biteven[i][j] += val;
            }
        }
    }

    public static long Queryodd(int r, int c) {
        long sum = 0;
        for (int i = r; i > 0; i -= i & -i) {
            for (int j = c; j > 0; j -= j & -j) {
                sum += bitodd[i][j];
            }
        }
        return sum;
    }

    public static long Queryeven(int r, int c) {
        long sum = 0;
        for (int i = r; i > 0; i -= i & -i) {
            for (int j = c; j > 0; j -= j & -j) {
                sum += biteven[i][j];
            }
        }
        return sum;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        
        M = input.nextInt();
        N = input.nextInt();
        bitodd = new int[M+2][N+2];
        biteven = new int[M+2][N+2];
        value = new int[M+2][N+2];
        int word = input.nextInt();
        while (word!=0) {
            if (word==1) {
                int a = input.nextInt();
                int b = input.nextInt();
                int c = input.nextInt();
                if ((a % 2 == 0 && b % 2 == 0) || (a % 2 != 0 && b % 2 != 0)) {
                    updateeven(a, b, c - value[a][b]);
                    value[a][b] = c;
                } else {
                    updateodd(a, b, c - value[a][b]);
                    value[a][b] = c;
                }
            } else {
                int a = input.nextInt();
                int b = input.nextInt();
                int c = input.nextInt();
                int d = input.nextInt();
                if ((a % 2 == 0 && b % 2 == 0) || (a % 2 != 0 && b % 2 != 0)) {
                  long total = Queryeven(c, d) - Queryeven(a - 1, d) - Queryeven(c, b - 1) + Queryeven(a - 1, b - 1);
                  long total2 = Queryodd(c, d) - Queryodd(a - 1, d) - Queryodd(c, b - 1) + Queryodd(a - 1, b - 1);
                  System.out.println(total - total2);
                } else {
                  long total = Queryeven(c, d) - Queryeven(a - 1, d) - Queryeven(c, b - 1) + Queryeven(a - 1, b - 1);
                  long total2 = Queryodd(c, d) - Queryodd(a - 1, d) - Queryodd(c, b - 1) + Queryodd(a - 1, b - 1);
                  System.out.println(total2 - total);
                }
            }
            word = input.nextInt();
        }
    }

}
