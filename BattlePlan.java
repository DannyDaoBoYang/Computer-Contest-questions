/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle.plan;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author dannyyang
 */
public class BattlePlan {

    /**
     * @param args the command line arguments
     */
    static Stack <Long> r_dis =new Stack();
    static Stack <Long> price=new Stack();
    static long cost[];
    static long dis[];
    static long answer[];
    static ArrayList<Integer> path[];
    static int N;
    public static void go(int pos, long tc){
       Stack<Long> t_d=new Stack();
       Stack<Long> t_c=new Stack();
       long totald=dis[pos];
       if(pos!=N){
           while(price.peek()>=cost[pos]){
               t_d.push(r_dis.pop());
               t_c.push((price.pop()));
               tc-=t_d.peek()*t_c.peek();
               totald+=t_d.peek();
           }
           r_dis.add(totald);
           price.add(cost[pos]);
           tc+=totald*cost[pos];
           answer[pos]=tc;
       }
        for(int i=0;i<path[pos].size();i++){
            go(path[pos].get(i),tc);
        }
        
        r_dis.pop();
        price.pop();
        while(!t_d.isEmpty()){
            r_dis.push(t_d.pop());
            price.push(t_c.pop());
        }
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        N=input.nextInt();
        path=new ArrayList[N+1];
        answer=new long[N+1];
        cost=new long[N+1];
        dis=new long[N+1];
        for(int i=0;i<=N;i++){
            path[i]=new ArrayList<Integer>();
        }
        for(int i=1;i<N;i++){
            
            int to=input.nextInt();
            path[to].add(i);
            dis[i]=input.nextInt();
            cost[i]=input.nextInt();
        }
        r_dis.push((long)0);
        price.push((long)0);
        go(N,0);
        for(int i=1;i<N;i++)
        System.out.println(answer[i]);
        
        
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
        byte[] buf = new byte[31]; // line length
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
