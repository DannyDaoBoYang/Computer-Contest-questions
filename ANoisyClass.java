/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.noisy.pkgclass;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

public class ANoisyClass {
    static boolean []possible= new boolean [10000];
    public static boolean cycle(ArrayList<Integer>[] connection,boolean contains[], boolean went[], int now){
        boolean answer=false;
        if(!possible[now]){
            return false;
        }
        if(went[now]){
            return true;
        }
        if(!contains[now]){
            return false;
        }
        went[now]=true;
        for(int i=0;i<connection[now].size();i++){
            answer=cycle(connection,contains,went,connection[now].get(i));
            if(answer){
                break;
            }
        }
        if(!answer){
            possible[now]=false;
        }
        return answer;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int n=input.nextInt();
        int M=input.nextInt();
        boolean contains[] =new boolean[n+1];
        boolean went[] =new boolean[n+1];
        ArrayList<Integer> startp=new ArrayList<Integer>();
        ArrayList<Integer> [] connection=new ArrayList[n+1];
        for(int j=0; j<n+1; j++){
                        connection[j]=new ArrayList<Integer>();
                        
        }
        
        for(int i=0;i<M;i++){
            int f=input.nextInt(),t=input.nextInt();
            if(!connection[f].contains(t)){
            connection[f].add(t);
            contains[t]=true;
            possible[t]=true;
            startp.add(t);
            }
        }
        boolean cycling=false;
        int start=0;
        while(startp.size()>start){
        cycling=cycle(connection, contains,went,start);
        if(cycling){
            break;
        }
        start++;
        }
        if(cycling){
            System.out.println("N");
        }
        else{
            System.out.println("Y");
        }
    }
    
}
