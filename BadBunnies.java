/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bad.bunnies;

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
public class BadBunnies {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> [] edge;
    static boolean rabbit[];
    static int dtr[];
    static int Y;
    static class edging{
        int x;
        int par;
        int dis;
        edging(int x, int par, int dis){
            this.x=x;
            this.par=par;
            this.dis=dis;
        }
    }
    static Queue <edging> move =new LinkedList();
    static int N;
    public static void moving(){
        boolean went[]=new boolean [N+1];
        while(!move.isEmpty()){
            edging temp=move.poll();
            if(went[temp.x]){
                continue;
            }
            went[temp.x]=true;
            dtr[temp.x]=temp.dis;
            for(int i=0;i<edge[temp.x].size();i++){
                move.add(new edging(edge[temp.x].get(i),temp.x,temp.dis+1));
            }
        }
        
        
    }
    static int ans=Integer.MAX_VALUE;
    public static boolean check(int position, int par){
        if(position==Y){
            ans=Math.min(ans, dtr[position]);
            return true;
        }
        
        for(int i=0;i<edge[position].size();i++){
            if(par!=edge[position].get(i)){
                if(check(edge[position].get(i),position)){
                ans=Math.min(ans, dtr[position]);
                return true;
            }
            }
            
        }
        return false;
        
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        N=input.nextInt();
        int R=input.nextInt();
        edge=new ArrayList[N+1];
        
        dtr=new int[N+1];
        for(int i=0;i<=N;i++){
            edge[i]=new ArrayList();
        }
        for(int i=1;i<N;i++){
            int a=input.nextInt();
            int b=input.nextInt();
            edge[a].add(b);
            edge[b].add(a);
        }
        for(int i=0;i<R;i++){
            move.add(new edging(input.nextInt(),-1,0));
        }
        int X=input.nextInt();
        Y=input.nextInt();
        moving();
        check(X,-1);
        System.out.println(ans);
        
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