/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bob.s.primes;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dannyyang
 */
public class BobSPrimes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int n = input.nextInt();
        int m = input.nextInt();
        boolean prime[] = new boolean[n + 1];
        ArrayList<Short> useful = new ArrayList();
        Arrays.fill(prime, true);
        prime[1] = false;
        for (short i = 2; i <= n; i++) {
            if (prime[i]) {
                useful.add(i);
                for (int j = i * 2; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
        int dp[] = new int[n + 1];
        int temp[]=new int[n+1];
        
        boolean last[] = new boolean[n + 1];
        
        last[0] = true;
        ArrayList<Integer> inuse = new ArrayList<Integer>();
        boolean stop = true;

        for (int i = 1; i <= m; i++) {
            int price = input.nextInt();

            stop = true;
            for (int j = i - 1; j < n; j++) {
                if (last[j]) {
                    /*if(price==5){
                     System.out.println("here");
                     }*/
                    for (int k = 0; k < useful.size(); k++) {
                        if (useful.get(k) * price > n - j) {
                            break;
                        } else {
                            stop = false;
                            inuse.add(useful.get(k) * price + j);
                            
                                temp[useful.get(k) * price + j]=(short) (dp[j] + useful.get(k));
                            
                        }
                    }
                }
            }
            if (stop) {
                System.out.println("not primetime");
                System.exit(0);
            }

            last = new boolean[n + 1];
            temp=new int[n+1];
            for (int j = 0; j < inuse.size(); j++) {

                int in = inuse.get(j);
                
                dp[in]=temp[in];
                last[in] = true;
                

            }
            /*for(int j=0;j<=n;j++){
             if(!dp[j].isEmpty()){
             System.out.println(j);
             }
             }*/
            inuse.clear();
        }
        System.out.println("its primetime");

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
