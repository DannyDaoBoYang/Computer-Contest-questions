/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossing.field;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
public class CrossingField {
    static boolean [][] ways;
    public static void clean(int N){
        ways=new boolean[N+2][N+2];
    }
    public static boolean dadidadidadida( int [][]grid,boolean [][] da, int x, int y, int h ){
        
        boolean answer=false;
        if(ways[x][y]){
            return false; 
        }
        if(grid[x][y]==-1){
            return false;
        }
        if(da[x][y]){
            return false;
        }
        if(x==grid.length-2&&y==grid.length-2){
            return true;
        }
        da[x][y]=true;
        int a=Math.abs(grid[x][y]-grid[x+1][y]);
        int b=Math.abs(grid[x][y]-grid[x][y+1]);
        int c=Math.abs(grid[x][y]-grid[x-1][y]);
        int d=Math.abs(grid[x][y]-grid[x][y-1]);
        if(a<=h){
            answer=dadidadidadida(grid,da,x+1,y,h);
            if(answer){
                return true;
            }
        }
        if(b<=h){
            answer=dadidadidadida(grid,da,x,y+1,h);
            if(answer){
                return true;
            }
        }
        if(c<=h){
            answer=dadidadidadida(grid,da,x-1,y,h);
            if(answer){
                return true;
            }
        }
        if(d<=h){
            answer=dadidadidadida(grid,da,x,y-1,h);
            if(answer){
                return true;
            }
        }
        ways[x][y]=true;
        return answer;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt(),H=input.nextInt();
        int [][]grid=new int[N+2][N+2];
        for(int i=0;i<=N+1;i++){
            for(int j=0;j<=N+1;j++){
                grid[i][j]=-1;
            }
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                grid[i][j]=input.nextInt();
            }
        }
        
        boolean [][] checking=new boolean[N+2][N+2];
        
        clean(N);
        boolean answer=dadidadidadida(grid,checking,1,1,H);
        if(answer){
            System.out.println("yes");
        }
        else{
            System.out.println("no");
        }
        
    }
    
}
