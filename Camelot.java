/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camelot;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Camelot {

    /**
     * @param args the command line arguments
     */
    static int N;
    static long x[];
    static long y[];
    public static long distance(long finx, long finy){
        long total=0;
            for(int i=0;i<N;i++){
               total+=Math.max(Math.abs(x[i]-finx), Math.abs(y[i]-finy));
            }
            return total;
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        N=input.nextInt();
        x=new long[N];
        long xp[]=new long[N];
        y=new long[N];
        long yp[]=new long[N];
        for(int i=0;i<N;i++){
            x[i]=input.nextInt();
            y[i]=input.nextInt();
            xp[i]=x[i]+y[i];
            yp[i]=x[i]-y[i];
        }
        Arrays.sort(xp);
        Arrays.sort(yp);
        long nx=xp[N/2];
        long ny=yp[N/2];
        long finx=(nx+ny)/2;
        long finy=(nx-ny)/2;
        long answer=Long.MAX_VALUE;
        answer=Math.min(answer,distance(finx+1,finy+1));
        answer=Math.min(answer,distance(finx+1,finy));
        answer=Math.min(answer,distance(finx+1,finy-1));
        answer=Math.min(answer,distance(finx,finy+1));
        answer=Math.min(answer,distance(finx,finy));
        answer=Math.min(answer,distance(finx,finy-1));
        answer=Math.min(answer,distance(finx-1,finy+1));
        answer=Math.min(answer,distance(finx-1,finy));
        answer=Math.min(answer,distance(finx-1,finy-1));
        
        System.out.println(answer);    
            
        
        
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