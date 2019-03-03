/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package claire.elstein;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dannyyang
 */
public class ClaireElstein {
    
    static ArrayList<Integer>[] des;
    
    static int [] len;
    static int [] cnt;
    public static void gogo(int position){
        int lenp=len[position];
        int cntp=cnt[position];
        for(int i=0;i<des[position].size();i++){
            int where=des[position].get(i);
            len[where]+=lenp%1000000007+cntp%1000000007;
            cnt[where]+=cntp;
            len[where]%=1000000007;
            cnt[where]%=1000000007;
        }
        
}
        
       
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int M=input.nextInt();
        des=new ArrayList[N+1];
        len=new int[N+1];
        cnt=new int[N+1];
        
        for(int i=0;i<des.length;i++){
            des[i]=new ArrayList<>();
        }
        
        boolean nie []=new boolean[N+1]; 
        boolean fire []=new boolean[N+1]; 

        
        
        for(int i=0;i<M;i++){
          int x=input.nextInt();
          int y=input.nextInt();
              des[x].add(y);
              
              nie[x]=true;
              fire[y]=true;
          }
        
          
         for(int i=1;i<=N;i++){
             if(!fire[i]&&nie[i]){
                cnt[i]=1;
             }
         }
        
        int total=0;
        for(int i=1;i<N;i++){
            gogo(i);
        }
        
        for(int i=2;i<N+1;i++){
            if(!nie[i]){   
                total+=len[i];
                total%=1000000007;
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
