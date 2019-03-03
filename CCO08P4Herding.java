/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cco.pkg08.p4.herding;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author dannyyang
 */

public class CCO08P4Herding {

    /**
     * @param args the command line arguments
     */
    
    static int N,M;
    static char [][]dir;
    static boolean went[][];
    static int index;
    public static boolean move(int y, int x, int go[][]){
        if(go[y][x]==index){
            went[y][x]=true;
            return true;
        }
        if(went[y][x]){
            return false;
        }
        else if(dir[y][x]=='Z'){
            return true;
        }
        else{
            went[y][x]=true;
            go[y][x]=index;
            boolean answer;
            if(dir[y][x]=='N'){
                 answer=move(y-1,x,go);
                 went[y-1][x]=false;
                 taking(y-1,x);
            }
            else if(dir[y][x]=='W'){
                answer=move(y,x-1,go);
                went[y][x-1]=false;
                 taking(y,x-1);
            }
            else if(dir[y][x]=='S'){
                answer=move(y+1,x,go);
                went[y+1][x]=false;
                 taking(y+1,x);
            }
            else{
                answer=move(y,x+1,go);
                went[y][x+1]=false;
                 taking(y,x+1);
            }
            
            return answer;
        }
    }
    public static void taking(int y, int x){
        if(went[y][x]){
            return;
        }
        went[y][x]=true;
        if(dir[y-1][x]=='S'){
                 taking(y-1,x);
            }
          if(dir[y][x-1]=='E'){
                taking(y,x-1);
            }
          if(dir[y+1][x]=='N'){
                taking(y+1,x);
            }
          if(dir[y][x+1]=='W'){
                taking(y,x+1);
            }
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        
        N=input.nextInt();
        M=input.nextInt();
        dir=new char[N+2][M+2];
        for(int i=0;i<=N+1;i++){
            for(int j=0;j<=M+1;j++){
                dir[i][j]='Z';
            }
        }
        went=new boolean[N+2][M+2];
        boolean incident[][]=new boolean[N+2][M+2];
        
        for(int i=1;i<=N;i++){
            String line=input.readLine();
            for(int j=1;j<=M;j++){
                
                dir[i][j]=line.charAt(j-1);
                char temp=line.charAt(j-1);
                if(dir[i][j]=='N'){
                 incident[i-1][j]=true;
            }
            else if(dir[i][j]=='W'){
               incident[i][j-1]=true;
            }
            else if(dir[i][j]=='S'){
               incident[i+1][j]=true;
            }
            else{
                incident[i][j+1]=true;
            }
            }
        }
        int answer=0;
        index=1;
        int go[][]=new int[N+2][M+2];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(!went[i][j]&&!incident[i][j]){
                
                if(move(i,j,go))
                answer++;
                }
                index++;
            }
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(!went[i][j]){
                    taking(i,j);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
    static class Reader {

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
        byte[] buf = new byte[M]; // line length
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
}