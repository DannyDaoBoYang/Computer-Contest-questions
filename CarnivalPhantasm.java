/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carnival.phantasm;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

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
public class CarnivalPhantasm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int S=input.nextInt();
        ArrayList<Integer> css[]=new ArrayList[N+1];
        for(int i=0;i<N+1;i++){
            css[i]=new ArrayList();
        }
        TreeSet <shop>[] candy=new TreeSet[102];
        for(int i=0;i<102;i++){
            candy[i]=new TreeSet();
        }
        int dis[]=new int[N+1];
        for(int i=1;i<=N;i++){
            dis[i]=input.nextInt();
        }
        for(int i=0;i<S;i++){
            int where=input.nextInt();
            int sell=input.nextInt();
            candy[sell].add(new shop(dis[where],where));
            css[where].add(sell);
        }
        
        int Q=input.nextInt();
        for(int i=0;i<Q;i++){
            String query[]=input.readLine().split(" ");
            if(query[0].equals("A")){
                int where=Integer.parseInt(query[1]);
                int sell=Integer.parseInt(query[2]);
                candy[sell].add(new shop(dis[where],where));
                css[where].add(sell);
            }
            if(query[0].equals("S")){
                int where=Integer.parseInt(query[1]);
                int sell=Integer.parseInt(query[2]);
                int dadi=css[where].indexOf(sell);
                if(dadi!=-1)
                css[where].remove(dadi);
            }
            if(query[0].equals("E")){
                int where=Integer.parseInt(query[1]);
                int k=Integer.parseInt(query[2]);
                css[where].clear();
                dis[where]=k;
            }
            if(query[0].equals("Q")){
                int sell=Integer.parseInt(query[1]);
                boolean find=false;
                while(!candy[sell].isEmpty()){
                    if(css[candy[sell].first().shop].contains(sell)){
                        if(candy[sell].first().dis==dis[candy[sell].first().shop]){
                        System.out.println(candy[sell].first().shop);
                        find=true;
                        break;
                        }
                        else{
                        candy[sell].pollFirst();
                        }
                    }
                    else{
                        
                        candy[sell].pollFirst();
                    }
                }
                if(!find){
                    System.out.println(-1);
                }
                
            }
        }
        
    }
    
    public static class shop implements Comparable<shop> {

        public int dis;
        public int shop;
        


        public shop(int dis, int shop) {

            this.dis = dis;
            this.shop = shop;
            
            
        }

        @Override
        public int compareTo(shop n) {
            if (dis > n.dis) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
