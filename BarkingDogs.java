/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barking.dogs;

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
public class BarkingDogs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int D=input.nextInt();
        int wait[]=new int[D+1];
        ArrayList connection []=new ArrayList[D+1];
        for(int i=1;i<=D;i++){
            connection[i]=new ArrayList<Integer>();
        }
        for(int i=1;i<=D;i++){
            wait[i]=input.nextInt();
        }
        int F=input.nextInt();
        for(int i=0;i<F;i++){
            int from=input.nextInt();
            int to=input.nextInt();
            connection[from].add(to);
        }
        int T=input.nextInt();
        int [] position=new int[D+1];
        int [] seconds=new int[D+1];
        
        PriorityQueue<edge> PQ=new PriorityQueue();
        position[1]=-9999999;
        PQ.add(new edge(1,0-wait[1]));
        
        while(!PQ.isEmpty()){
          edge n=PQ.poll();
           int y=n.y;
           int time=n.time;
           if(position[y]<=time){
               position[y]=time+wait[y]+1;
               if(position[y]-1<=T){
                   seconds[y]++;
               }
               
               if(position[y]<=T)
               for(int i=0;i<connection[y].size();i++){
                   PQ.add(new edge((int) connection[y].get(i),position[y]-1));
               }
           }
           
        }
        for(int i=1;i<=D;i++){
            System.out.println(seconds[i]);
        }
    }
    static class edge implements Comparable<edge>{
        
        int y;
        int time;
        edge(int y, int time){
            
            this.y = y;
            this.time=time;
        }
        @Override
        public int compareTo(edge n){
            if (time>n.time){
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}
