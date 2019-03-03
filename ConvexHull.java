/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convex.hull;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

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
public class ConvexHull {

    /**
     * @param args the command line arguments
     */
    static class road implements Comparable<road> {

        int position=0;
        int wear = 0;
        int time = 0;

        road(int position, int time, int wear) {
            this.wear = wear;
            this.position = position;
            this.time = time;
        }

        @Override
        public int compareTo(road n) {
            if (time > n.time) {
                return 1;
            } else if (time == n.time) {
                if (wear < n.wear) {
                    return 1;
                }
                return -1;
            } else {
                return -1;
            }
        }
    }
static class ro{
    int to;
    int wear;
    int time;
    ro(int to, int wear, int time){
        this.to=to;
        this.wear=wear;
        this.time=time;
    }
}
    static ArrayList <ro> map[];
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int K=input.nextInt();
        int N=input.nextInt();
        int M=input.nextInt();
        PriorityQueue<road> PQ = new PriorityQueue();
        map=new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            map[i]=new ArrayList();
        }
        for(int i=0;i<M;i++){
            int from=input.nextInt();
            int to=input.nextInt();
            int time=input.nextInt();
            int wear=input.nextInt();
            map[from].add(new ro(to,wear,time));
            map[to].add(new ro(from,wear,time));
        }
        int A=input.nextInt();
        int B=input.nextInt();
        int answer=-1;
        PQ.add(new road(A,0,K));
        boolean went []=new boolean[N+1];
        int LW[]=new int[N+1]; //Amoror remain
        while(!PQ.isEmpty()){
            
            road temp=PQ.poll();
            if(temp.wear<=0){
                continue;
            }
            if(temp.position==B){
                answer=temp.time;
                break;
            }
            if(went[temp.position]){
                if(LW[temp.position]>=temp.wear){
                    continue;
                }
                LW[temp.position]=temp.wear;
            }
            else{
                went[temp.position]=true;
                LW[temp.position]=temp.wear;
            }
            for(int i=0;i<map[temp.position].size();i++){
                ro des=map[temp.position].get(i);
                PQ.add(new road(des.to,temp.time+des.time,temp.wear-des.wear));
            }
        }
        System.out.println(answer);
        
    }
    
}
