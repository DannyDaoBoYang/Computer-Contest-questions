/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bond;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author dannyyang
 */
public class Bond {

    /**
     * @param args the command line arguments
     */
    static int N;

    public static int change(int a, int current[]) {
        int stat = Integer.bitCount(a);
        int re = 0;
        while (current[re] <= re) {
            re++;
            if (re == stat) {
                return 0;
            }
        }
        //System.out.println("a "+a);
        a -= (1 << current[re]);
       // System.out.println("curent "+current[re]);
        current[re]--;
        a += (1<<current[re]);
        int count = 1;
        for (int i = re - 1; i >= 0; i--) {
            a -= (1 << current[i]);
            current[i] =current[re]-count;
            a += (1<<current[i]);
            count++;
        }
       // System.out.println("a "+a);
        return a;
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        N = input.nextInt();
        double p[][] = new double[N][N]; //people mission
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                p[i][j] = input.nextInt();
            }
        }
        double dp[][] = new double[2][(1 << N)];//max probability of ith mission completed
        for (int i = 0; i < N; i++) {
            dp[0][(1 << i)] = p[i][0];
        }
        int cur=0;
        int nex=1;
        for (int i = 0; i < N - 1; i++,cur^=1,nex^=1) {
                int current[] = new int[i + 1];
                int check = 0;
                for (int j = 0; j <= i; j++) {
                    check += (1 << (N - j - 1));
                    current[i - j] = N - j - 1;
                  //  System.out.println(N-j-1);
                }
                
            while (true) {
              //  System.out.println("Check "+check);
                for (int j = 0; j < N; j++) {
                    int newcheck = (1 << (N - j - 1));
                    if ((newcheck & check) != 0) {
                        continue;
                    }
                 //   System.out.println("Newcheck "+newcheck);
                    dp[nex][newcheck | check] = Math.max(dp[nex][newcheck | check], dp[cur][check] * p[N-j-1][i+1]);
                 //   System.out.println("dp "+dp[i + 1][newcheck | check]);
                }
                check = change(check, current);
                if (check == 0) {
                    break;
                }
            }

        }
      /*  for(int i=0;i<N;i++){
            for(int j=0;j<(1<<N);j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }*/
        System.out.println(dp[cur][(1 << N) - 1]/Math.pow(100, N)*100);

    }

}

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
