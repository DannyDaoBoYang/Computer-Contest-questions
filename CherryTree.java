/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cherry.tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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
public class CherryTree {
    static ArrayList<Integer>[] connection;
    static ArrayList<Integer>[] weight;
    static int[] tw;
    static int[] tc;
    public static int checking1(int position){
        
        for(int i=0;i<connection[position].size();i++){
            tw[position]+=checking1(connection[position].get(i));
        }
        return tw[position];
    }
    public static int checking2(int position){
        
        for(int i=0;i<connection[position].size();i++){
            tc[position]+=checking2(connection[position].get(i));
        }
        return tc[position];
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int C=input.nextInt();
        int K=input.nextInt();
        int cherry[]=new int[N+1];
         connection=new ArrayList[N+1];
         weight=new ArrayList[N+1];
         tw=new int[N+1];
         tc=new int[N+1];
        for(int i=0;i<N+1;i++){
            connection[i]=new ArrayList<Integer>();
            weight[i]=new ArrayList<Integer>();
        }
        for(int i=1;i<N+1;i++){
            tc[i]=input.nextInt();
        }
        for(int i=0;i<N-1;i++){
            int a=input.nextInt();
            int b=input.nextInt();
            int c=input.nextInt();
            tw[b]=c;
            connection[a].add(b);
            weight[a].add(c);
        }
        checking1(1);
        checking2(1);
        int cnt=0;
        for(int i=2;i<=N;i++){
            if(tw[i]<=K&&tc[i]>=C)
            cnt++;
        }
        System.out.println(cnt);
        
        
        
        
    }
    
}
