/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cable.tv;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class CableTV {

        

        public static void creat(){
        
                    
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N = input.nextInt();
        int M = input.nextInt();
        ArrayList<Byte>[] sca;//safe connection
        ArrayList<Byte>[] scb;//safe connection
        ArrayList<Byte>[] uca;//unsfae unsafeconnection
        ArrayList<Byte>[] ucb;//unsfae unsafeconnection
        sca = new ArrayList[10001];//safe x connection
        scb = new ArrayList[10001];//safe y connection
        uca = new ArrayList[10001];//unsfa x unsafeconnection
        ucb = new ArrayList[10001];//unsafe y connection


                for(int j=0;j<=10000;j++){
                    sca[j]=new ArrayList<Byte>();
                    scb[j]=new ArrayList<Byte>();
                    uca[j]=new ArrayList<Byte>();
                    ucb[j]=new ArrayList<Byte>();
                }
        for (int i = 0; i < M; i++) {
            byte a = (byte)input.nextInt();
            byte b = (byte)input.nextInt();
            int c = input.nextInt();
            int d = input.nextInt();
            if (d == 0) {
                sca[c].add(a);
                scb[c].add(b);
                
            } else {
                uca[c].add(a);
                ucb[c].add(b);
                
            }
        }
         ArrayList<Byte>[] umbra=new ArrayList[N+1];//tree connection
         
         for(int i=0;i<N+1;i++){
             umbra[i]=new ArrayList<Byte>();
         }
        byte pointer[]=new byte[N+1];
        
        int total=0;
        for(int i=1;i<1001;i++){
            for(int j=0;j<sca[i].size();j++){
                byte a=sca[i].get(j);
                byte b=scb[i].get(j);
                byte leadera=pointer[a];
                byte leaderb=pointer[b];
                if(leadera==0&&leaderb==0){
                 umbra[a].add(a);
                 umbra[a].add(b);
                 pointer[a]=a;
                 pointer[b]=a;
                 total+=i;
                 continue;
                 
                }
                if(leadera==0){
                    umbra[leaderb].add(a);
                    pointer[a]=leaderb;
                    total+=i;
                    continue;
                }
                if(leaderb==0){
                    umbra[leadera].add(b);
                    pointer[b]=leadera;
                    total+=i;
                    continue;
                }
                if(leadera==leaderb){
                    continue;
                }
                if(leadera!=leaderb){
                    if(umbra[leadera].size()>umbra[leaderb].size()){
                        //move b to a
                        for(int h=0;h<umbra[leaderb].size();h++){
                            umbra[leadera].add(umbra[leaderb].get(h));
                            pointer[umbra[leaderb].get(h)]=leadera;
                        }
                        total+=i;
                    }
                    else{
                        //move a to b
                        for(int h=0;h<umbra[leadera].size();h++){
                            umbra[leaderb].add(umbra[leadera].get(h));
                            pointer[umbra[leadera].get(h)]=leaderb;
                        }
                        total+=i;
                    }
                }
                
            }
        }
        int not=0;
        for(int i=1;i<1001;i++){
            for(int j=0;j<uca[i].size();j++){
                byte a=uca[i].get(j);
                byte b=ucb[i].get(j);
                byte leadera=pointer[a];
                byte leaderb=pointer[b];
                if(leadera==0&&leaderb==0){
                 umbra[a].add(a);
                 umbra[a].add(b);
                 pointer[a]=a;
                 pointer[b]=a;
                 total+=i;
                 not++;
                 continue;
                 
                }
                if(leadera==0){
                    umbra[leaderb].add(a);
                    pointer[a]=leaderb;
                    total+=i;
                    not++;
                    continue;
                }
                if(leaderb==0){
                    umbra[leadera].add(b);
                    pointer[b]=leadera;
                    total+=i;
                    not++;
                    continue;
                }
                if(leadera==leaderb){
                    continue;
                }
                if(leadera!=leaderb){
                    if(umbra[leadera].size()>umbra[leaderb].size()){
                        //move b to a
                        for(int h=0;h<umbra[leaderb].size();h++){
                            umbra[leadera].add(umbra[leaderb].get(h));
                            pointer[umbra[leaderb].get(h)]=leadera;
                        }
                        total+=i;
                        not++;
                    }
                    else{
                        //move a to b
                        for(int h=0;h<umbra[leadera].size();h++){
                            umbra[leaderb].add(umbra[leadera].get(h));
                            pointer[umbra[leadera].get(h)]=leaderb;
                        }
                        total+=i;
                        not++;
                    }
                }
                
            }
        }
        System.out.println(not+" "+total);
        
        
        


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
