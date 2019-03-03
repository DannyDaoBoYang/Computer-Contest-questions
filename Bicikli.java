/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bicikli;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dannyyang
 */
public class Bicikli {

    /**
     * @param args the command line arguments
     */
    public static long dp[];
    public static ArrayList<Integer>[]con;
    static long mod=(long)Math.pow(10, 9);
    static boolean used=false;
    public static long num(int current, boolean went[]){
        
        if(current==2){
            return 1;
        }
        if(went[current]){
            if(dp[current]==-1){
                return -1;
            }
            else{
                return dp[current];
            }
        }
        went[current]=true;
        int total=0;
        boolean possible=false;
        for(int i=0;i<con[current].size();i++){
            long temp=num(con[current].get(i),went);
            if(temp==-1){
                possible=true;
            }
            else{
                total+=temp;
                if(total>=mod){
                used=true;
                total%=mod;
                }
            }
        }
        if(total!=0&&possible){
            System.out.println("inf");
            System.exit(0);
        }
        dp[current]=total;
        return total;
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int M=input.nextInt();
        dp=new long[N+1];
        Arrays.fill(dp, -1);
        con= new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            con[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<M;i++){
            con[input.nextInt()].add(input.nextInt());
        }
        boolean went[]=new boolean[N+1];
        long answer=num(1,went);
        if(used){
        String fin="000000000000000"+answer;
        System.out.println(fin.substring(fin.length()-9));
        }
        else{
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
