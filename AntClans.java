/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant.clans;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author dannyyang
 */
public class AntClans {

    /**
     * @param args the command line arguments
     */
    static long dp[];
    static int N;
    static int k;
    static int path[][];
    static int father[];

    public static int shortest(int need) {
        PriorityQueue<edge> minimum = new PriorityQueue();
        int[] indexs = new int[k];
        father = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            father[i] = i;
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (((1 << i) & need) != 0) {
                indexs[count] = i + 1;
                count++;
            }
        }
        
        for (int i = 0; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                if (path[indexs[i]][indexs[j]] != 0) {
                    minimum.add(new edge(indexs[i], indexs[j], path[indexs[i]][indexs[j]]));
                }
            }
        }
        int piece = k;
        int cost = 0;
        while (piece != 1 && !minimum.isEmpty()) {
            edge temp = minimum.poll();
            // System.out.println(temp.cost);
            int a = parent(temp.a);
            int b = parent(temp.b);
            if (a != b) {
                father[b] = a;
                cost += temp.cost;
                piece--;
            }
        }
        if (piece == 1) {
            // System.out.println("Here");
            return cost;
        } else {
            return -1;
        }
    }

    public static int parent(int current) {
        if (father[current] != father[father[current]]) {
            father[current] = parent(father[current]);

        }
        return father[current];
    }

    public static long check(int mask) {
        //System.out.println(Integer.bitCount(mask));
        // System.out.println(mask);
        
        if (dp[mask] != Integer.MAX_VALUE) {
            return dp[mask];
        }
        if (Integer.bitCount(mask) == k) {
            dp[mask] = shortest(mask);
            // System.out.println(dp[mask]);
            return dp[mask];
        }
        
        int where[]=new int[Integer.bitCount(mask)];
        int index[]=new int[k];
        int temp=mask;
        for(int i=where.length-1;i>=0;i--){
         where[i]=Integer.highestOneBit(temp);
         temp-=where[i];
         
        }
        for(int i=0;i<k;i++){
            index[i]=where.length-k+i;
        }
        long times=1;
        int current=where[where.length-1];
        
            for(int i=1;i<k;i++){
                    current+=where[where.length-1-i];
            }
            for(int i=where.length-1;i>where.length-k;i--){
                times*=i;
            }
            for(int i=2;i<=k-1;i++){
                times/=i;
            }
            
            for(int k=0;k<=times;k++){
                    //System.out.println(current);
                    long a = check(current);
                    long b = check(current ^ mask);
                    if (a != -1 && b != -1) {
                        dp[mask] = Math.min(dp[mask], a + b);
                    }
                    if(k==times){
                        break;
                    }
                    current=fix(current, where, index);
            }
            
        
        if (dp[mask] == Integer.MAX_VALUE) {
            dp[mask] = -1;
        }
        return dp[mask];
    }
    public static int fix(int current, int where[],int index[]){
        /*
        System.out.println("Value1 "+current);
        for(int i=15;i>=0;i--){
            if(((1<<i)&current)!=0){
                System.out.print("1");
            }
            else{
                System.out.print("0");
            }
                
        }
        System.out.println();
        for(int i=0;i<where.length;i++){
            System.out.print(where[i]+" ");
        }
        System.out.println();
        for(int i=0;i<k;i++){
            System.out.print(index[i]+" ");
        }
        System.out.println();
        */
        int checking=0;
        while(index[checking]<=checking){
            checking++;
           // System.out.println("here");
        }
        
        current-=where[index[checking]];
        //System.out.println(current);
        index[checking]--;
        current+=where[index[checking]];
        
        int count=1;
        for(int i=checking-1;i>=0;i--){
           // System.out.println("here");
            current-=where[index[i]];
            index[i]=index[checking]-count;
            current+=where[index[i]];
            count++;
        }
        
        /*System.out.println("Value2 "+current);
        for(int i=15;i>=0;i--){
            if(((1<<i)&current)!=0){
                System.out.print("1");
            }
            else{
                System.out.print("0");
            }
                
        }
        System.out.println();
        */
        return current;
        
        
        
    }
    static class edge implements Comparable<edge> {

        int a;
        int b;
        int cost;

        edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(edge n) {
            if (cost < n.cost) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        
        N = input.nextInt();
        int m = input.nextInt();
        k = input.nextInt();
        dp = new long[1 << N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        if (N % k != 0) {
            System.out.println(-1);
        } else {
            path = new int[N + 1][N + 1];
            for (int i = 0; i < m; i++) {
                int a = input.nextInt();
                int b = input.nextInt();
                int c = input.nextInt();
                if (path[a][b] != 0) {
                    path[a][b] = Math.min(path[a][b], c);
                    path[b][a] = Math.min(path[b][a], c);
                } else {
                    path[a][b] = c;
                    path[b][a] = c;
                }
            }
            k = N / k;
            long answer = check((1 << N) - 1);
            System.out.println(answer);
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
        byte[] buf = new byte[32]; // line length
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
