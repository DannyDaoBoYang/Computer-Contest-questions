/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biggest.zero.carbon.footprint.pkg2;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author dannyyang
 */
public class BiggestZeroCarbonFootprint2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int M=input.nextInt();
        int t=input.nextInt();
        boolean map[][]=new boolean[N+1][M+1];
        for(int i=0;i<t;i++){
            map[input.nextInt()][input.nextInt()]=true;
        }
        for(int i=0;i<=M;i++){
            map[N][i]=true;
        }
        for(int i=0;i<=N;i++){
            map[i][M]=true;
        }
        int column[][]=new int[2][M+1];
        
        
        long answer=0;
        int current=0;
        int last=1;
        for(int i=1;i<=N;i++, current^=1,last^=1){
            Stack <Long> height=new Stack();
            Stack<Long> index=new Stack();
            height.add((long)0);
            index.add((long)0);
            for(int j=1;j<=M;j++){
                
                column[current][j]=column[last][j]+1;
               // System.out.println(column[i][j]);
                
                
                answer=Math.max(answer, (j-index.peek())*i);
                if(column[current][j]!=i){
                for(int k=1;k<height.size();k++){
                    answer=Math.max(answer, (j-index.get(k-1))*height.get(k));
                }
                while(!height.isEmpty()){
                    if(height.peek()>=column[current][j]){
                        height.pop();
                        index.pop();
                    }
                    else{
                        break;
                    }
                }
                
                index.push((long)j);
                height.push((long)column[current][j]);
                }
                if(map[i][j]){
                    column[current][j]=0;
                }
            }
        }
        System.out.println(answer);
        
    }
    
}class Reader {

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