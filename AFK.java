/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afk;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

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
public class AFK {
    static int answer=61;
    static int mat[][];
    static int l,w;
    static int xw,yw;
public static int way(int sx,int sy, int step,boolean went[][]){
    int panswer=61;
     if(sx<1||sx>l||sy<1||sy>w){
        return 61;
    }
    else if(went[sy][sx]){
        return 61;
    }
    else if(mat[sy][sx]==0){
        return 61;
    }
    else if(sy==yw&&sx==xw){
        answer=Math.min(answer, step);
        return step;
    }
    else if(answer<=step){
        return 61;
    }
    else if((mat[sy][sx]==1)){
        
    went[sy][sx]=true;
    
    panswer=way(sx,sy+1,step+1,went);
    answer=Math.min(panswer, answer);
    panswer=way(sx+1,sy,step+1,went);
    answer=Math.min(panswer, answer);
    panswer=way(sx,sy-1,step+1,went);
    answer=Math.min(panswer, answer);
    panswer=way(sx-1,sy,step+1,went);
    answer=Math.min(panswer, answer);
    }
    return answer;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        Scanner scan=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++){
        l=input.nextInt();
        w=input.nextInt();
        int sx=0;
        int sy=0;

        
        mat=new int[w+2][l+2];
        for(int i=1;i<=w;i++){
            String aaa=input.readLine();
            for(int j=1;j<=l;j++){
                char a=aaa.charAt(j-1);
                if(a=='C'){
                    sy=i;
                    sx=j;
                    mat[i][j]=1;
                    
                }
                else if(a=='W'){
                    yw=i;
                    xw=j;
                    mat[i][j]=1;
                    
                }
                else if(a=='O'){
                    mat[i][j]=1;
                }

            }
        }
        
        answer=Math.min(l*w, 61);
        boolean [][]went=new boolean[w+2][l+2];
        
        int answer2=way(sx,sy,0,went);
        if(answer2!=61){
        System.out.println(answer);
        }
        else{
           System.out.println("#notworth");
        }
    }
    
    }
}
