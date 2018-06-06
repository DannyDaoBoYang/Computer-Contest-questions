/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prom.night;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
public class PromNight {

    /**
     * @param args the command line arguments
     */
    static  boolean [][] choose;
    static boolean important[];
    static int N;
    static int M;
    static int answer=0;
    static ArrayList<Integer> [] male;
    static ArrayList<Integer> [] female;
    static int taken[];
    static boolean notwork[][];
    
    public static boolean move(int from, int index, int require,boolean within[]){
        if(within[index]){
            return false;
        }
        within[index]=true;
        for(int i=0;i<male[index].size();i++){
            if(taken[male[index].get(i)]==-1&&male[index].get(i)!=require){
                taken[male[index].get(i)]=index;
                taken[require]=-1;
                
                return true;
            }
            else if(male[index].get(i)!=require&&!within[taken[male[index].get(i)]]){
                
                    boolean ok=move(index,taken[male[index].get(i)],male[index].get(i),within);
                    
                    if(ok){
                        taken[male[index].get(i)]=index;
                        taken[require]=-1;
                        notwork[from][index]=false;
                        return true;
                    
                    }
            }
        }
        
        return false;
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        N=input.nextInt();
        M=input.nextInt();
        male=new ArrayList[N+1];
        female=new ArrayList[M+1];
        for(int i=0;i<=N;i++){
            male[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<=M;i++){
            female[i]=new ArrayList<Integer>();
        }
        notwork=new boolean[N+1][N+1];
        int impo=input.nextInt();
        boolean im[]=new boolean[M+1];
        
        for(int i=0;i<impo;i++){
            im[input.nextInt()]=true;
        }
      
        
        for(int i=2;i<=N;i++){
            int t=input.nextInt();
            for(int j=0;j<t;j++){
                int temp=input.nextInt();
                    if(im[temp]){
                        male[i].add(temp);
                        female[temp].add(i);
                    }
            }
        }
        taken=new int[M+1];
        Arrays.fill(taken, -1);
        for(int i=2;i<=N;i++){
            boolean within[]=new boolean[N+1];
            move(0,i,0,within);
        }
        for(int i=1;i<=M;i++){
            if(taken[i]!=-1){
                answer++;
            }
        }
        System.out.println(impo-answer);
        
        
    }
    
}
