/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.star.s.visit;

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
public class BlackStarSVisit {

    /**
     * @param args the command line arguments
     */
    public static int horizontal(int casea){
        int answer=9;
        casea--;
        while(casea>0){
            answer*=10;
            answer%=666013;
            casea--;
        }
        return answer;
        
    }
    public static int vertical(int caseb){
        int answer=2;
        caseb--;
        while(caseb!=0){
            answer*=3;
            answer%=666013;
            caseb--;
        }
        return answer;
    }
    public static int dou(int casec){
        int answer=2;
        casec--;
        while(casec>0){
            answer*=3;
            answer%=666013;
            casec--;
        }
        return casec;
    }
    public static long fun(int city){
        if(city==1){
            return 0;
        }
        else if(city%2==0){
            long casea=horizontal(city/2);
            long caseb=vertical(city);
            long casec=dou(city/2);
            return (casea+caseb+casec)%666013;
        }
        else{
            long casea=horizontal(city/2)*10;
            long caseb=vertical(city)*3;
            long casec=dou(city/2)*3;
            return (casea+caseb+casec)%666013;
        }
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int Q=input.nextInt();
        for(int i=0;i<Q;i++){
            
            System.out.println(fun(input.nextInt()));
        }
    }
    
}
