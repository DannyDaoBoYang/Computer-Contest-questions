/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeMap;

/**
 *
 * @author dannyyang
 */
public class BST {

    /**
     * @param args the command line arguments
     */
    static node seg[];
    static int value[];
    static int index[];
    static long answer[];
    static int count=1;
    static class node{
        int from=0;
        int to=0;
        int first=0;
        node(){
            
        }
    }
    public static void building(int root, int left, int right){
        if(left==right){
            seg[root]=new node();
            seg[root].from=seg[root].to=left;
            seg[root].first=index[count];
            count++;
            return;
        }
        seg[root]=new node();
        int mid=(left+right)/2;
        building(2*root,left,mid);
        building(2*root+1,mid+1,right);
        seg[root].from=left;
        seg[root].to=right;
        seg[root].first=Math.min(seg[2*root].first, seg[2*root+1].first);
    }
    public static int query(int root, int left, int right){
        
        if(left==seg[root].from&&right==seg[root].to){
            return seg[root].first;
        }
        int middle=(seg[root].from+seg[root].to)/2;
        if(right<=middle){
            return query(2*root, left, right);
        }
        else if(left>middle){
            return query(2*root+1,left,right);
        }
        else{
            return Math.min(query(2*root,left,middle), query(2*root+1,middle+1,right));
        }
    }
    public static void cal(int from, int to, int depth){
        if(from>to){
            return;
        }
        if(from==to){
            answer[index[from]]=depth;
            return;
        }
        
        int split=query(1,from, to);
        answer[split]=depth;
        //System.out.println(split+" "+index[split]);
        cal(from,value[split]-1,depth+1);
        cal(value[split]+1,to,depth+1);
       
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        seg=new node[4*N+4];
        value=new int[N+4];
        index=new int[N+4];
        answer=new long[N+4];
        for(int i=1;i<=N;i++){
            value[i]=input.nextInt();
            index[value[i]]=i;
        }
        building(1,1,N);
        cal(1,N,0);
      //  System.out.println();
       for(int i=1;i<=N;i++){
        //    System.out.print(answer[i]+" ");
            answer[i]+=answer[i-1];
            System.out.println(answer[i]);
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

