/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beautiful.trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class BeautifulTrees {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> branch[];
    static int answer=0;
    static boolean possible[];
    static boolean went[];
    public static int move(int pos, int parents){
        if(!possible[pos]){
            return 0;
        }
        went[pos]=true;
        int best=0;
        int sbest=0;
        for(int i=0;i<branch[pos].size();i++){
            if(branch[pos].get(i)==parents){
                continue;
            }
            int temp=move(branch[pos].get(i),pos);
            
            if(temp>best){
                sbest=best;
                best=temp;
            }
            else if(temp>sbest){
                sbest=temp;
            }
        }
        answer=Math.max(best+sbest+1,answer);
        return best+1;
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        possible=new boolean[N+1];
        went=new boolean[N+1];
        
        branch=new ArrayList[N+1];
        
        for(int i=0;i<=N;i++){
            branch[i]=new ArrayList<Integer>();
        }
        for(int i=1;i<=N;i++){
            long strength=input.nextLong();
            long root=(long)Math.sqrt(strength);
            if(root*(root+1)==strength){
                possible[i]=true;
            }
        }
        for(int i=1;i<N;i++){
            int a=input.nextInt();
            int b=input.nextInt();
            branch[a].add(b);
            branch[b].add(a);
        }
        for(int i=1;i<=N;i++){
            if(!went[i]&&possible[i]){
                move(i,0);
            }
        }
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