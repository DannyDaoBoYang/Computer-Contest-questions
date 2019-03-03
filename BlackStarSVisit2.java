/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.star.s.visit.pkg2;

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

public class BlackStarSVisit2 {

    /**
     * @param args the command line arguments
     */
    public static int horizontal(int casea) {
        int answer = 9;
        casea--;
        while (casea > 0) {
            answer *= 10;
            answer %= mod;
            casea--;
        }
        return answer;

    }

    public static int vertical(int caseb) {
        int answer = 2;
        caseb--;
        while (caseb > 4) {
            answer *= 81;
            answer %= mod;
            caseb-=4;
        }
        while (caseb > 0) {
            answer *= 3;
            answer %= mod;
            caseb--;
        }
        return answer;
    }

    public static int dou(int casec) {
        int answer = 2;
        casec--;
        while (casec >= 4) {
            answer *= 81;
            answer %= mod;
            casec-=4;
        }
        while (casec > 0) {
            answer *= 3;
            answer %= mod;
            casec--;
        }
        return answer;
    }
    static int mod=666013;
    public static long power(int pow){
        
        int answer=1;
        
        while(pow>0){
            pow--;
            answer*=10;
            answer%=mod;
        }
        return answer-answer/10;
    }
    public static long fun(int city) {
        if (city == 1) {
            return 0;
        }
        if (city % 2 == 0) {
         
            long casea = horizontal(city / 2);
         
            long caseb = vertical(city);
         
            long casec = dou(city / 2);
             System.out.println();
            System.out.println(casea);
            System.out.println(caseb);
            System.out.println(casec);
            return (long) ((power(city)- (casea + caseb - casec)%mod) % mod);
        } else {
           
            long casea = horizontal(city / 2) * 10;
            long caseb = vertical(city);
            long casec = dou(city / 2) * 3;
            
           /* System.out.println();
            System.out.println(casea);
            System.out.println(caseb);
            System.out.println(casec);*/
            return (long) ((power(city)- (casea + caseb - casec)%mod) % mod);
        }
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int N = input.nextInt();
        int Q = input.nextInt();
        for (int i = 0; i < Q; i++) {
            int quest=input.nextInt();
            long answer=fun(quest);
            
            while(answer<0){
                answer+=mod;
            }
            System.out.println(answer);
        }
    }

}
