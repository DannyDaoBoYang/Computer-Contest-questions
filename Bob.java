/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bob;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author dannyyang
 */
public class Bob {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int M=input.nextInt();
        int [][] row=new int[N+1][M+1];
        int [][] column=new int[N+1][M+1];
        int [][] record=new int[N+1][M+1];
        int land[][]=new int [N+1][M+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                land[i][j]=input.nextInt();
            }
        }
        long total=0;
        for(int i=1;i<=N;i++){
            Stack<Integer> index=new Stack();
            Stack<Integer> height=new Stack();
            long subtotal=0;
            for(int j=1;j<=M;j++){
                row[i][j]=1;
                column[i][j]=1;
                if(land[i][j]==land[i][j-1]){
                    row[i][j]+=row[i][j-1];
                }
                if(land[i][j]==land[i-1][j]){
                    column[i][j]+=column[i-1][j];
                }
                if(row[i][j]==1){
                    index.clear();
                    height.clear();
                    index.push(j-1);
                    index.push(j);
                    height.push(0);
                    height.push(column[i][j]);
                    subtotal=column[i][j];
                }
                else{
                    while(height.peek()>=column[i][j]){
                        int deal=height.pop();
                        int where=index.pop();
                        subtotal-=deal*(where-index.peek());
                    }
                    subtotal+=column[i][j]*(j-index.peek());
                    height.push(column[i][j]);
                    index.push(j);
                    
                    
                }
                
                total+=subtotal;
            }
        }
        System.out.println(total);
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
