/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bob.and.english.pkgclass;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dannyyang
 */
public class BobAndEnglishClass {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> graph[];
    static ArrayList<Integer> time[];
    static int lvl[];
    static int par[];
    static int dis[][];
    static boolean block[];
    public static int decompose(int pos, int par, int cl, int total){ //cv means current level
        int count=1;
        
        for(int i=0;i<graph[pos].size();i++){
            
            int temp=decompose(graph[pos].get(i),pos,cl,total);
            if(temp>=total/2)
            
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int K=input.nextInt();
        int N=input.nextInt();
        graph=new ArrayList[N+1];
        time=new ArrayList[N+1];
        int pos[]=new int[K];
        for(int i=0;i<K;i++){
            pos[i]=input.nextInt();
        }
        for(int i=1;i<N;i++){
            int a=input.nextInt();
            int b=input.nextInt();
            int t=input.nextInt();
            graph[a].add(b);
            time[a].add(t);
            graph[b].add(a);
            graph[b].add(t);
        }
        
        
        
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