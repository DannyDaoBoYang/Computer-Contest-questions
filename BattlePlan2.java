/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle.plan.pkg2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dannyyang
 */
public class BattlePlan2 {

    /**
     * @param args the command line arguments
     */
    static long ship[];
    static int N;
    static int atleast=0;
    public static int search(long value){
      //  System.out.println("Target "+value);
        if(atleast==N+1){
            return N;
        }
        if(ship[atleast]==value){
            return atleast;
        }
        while(ship[atleast]<value){
            atleast++;
            if(atleast==N+1){
                return N;
            }
            if(ship[atleast]==value){
            return atleast;
            }
            
        }
        return atleast-1;
        
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        N=input.nextInt();
        int K=input.nextInt();
        int Q=input.nextInt();
        ship=new long[N+1];
        int type[]=new int[K+1];
        long answer[]=new long[Q+1];
        ArrayList<Integer> index[]=new ArrayList[K+1];
        for(int i=0;i<=K;i++){
            index[i]=new ArrayList<Integer>();
        }
        int a[]=new int[Q+1];
        int b[]=new int[Q+1];
        
        for(int i=1;i<=N;i++){
            ship[i]=ship[i-1]+input.nextLong();
            //System.out.print(+ship[i]+" ");
        }
      //  System.out.println();
        
        for(int i=1;i<=K;i++){
            type[i]=input.nextInt();
        }
        for(int i=1;i<=Q;i++){
            index[input.nextInt()].add(i);
            a[i]=input.nextInt();
            b[i]=input.nextInt();
        }
        int block=(int) Math.sqrt(N);
       // System.out.println(block);
        for(int i=1;i<=K;i++){
            atleast=0;
            int trans[]=new int[N+1];
            int cost[]=new int[N+1];
            int direct[]=new int[N+1];
            for(int j=0;j<N;j++){
                trans[j]=search(ship[j]+type[i]);
             //   System.out.println(j+" "+trans[j]+" ");
            }
          //  System.out.println();
            for(int j=N-1;j>=0;j--){
                if(trans[j]/block>j/block||trans[j]==N){
                    cost[j]=1;
                    direct[j]=trans[j];
                }
                else if(trans[j]==j){
                    direct[j]=N+3;
                }
                else{
                    cost[j]=cost[trans[j]]+1;
                    direct[j]=direct[trans[j]];
                }
               // System.out.println(direct[j]+" "+ cost[j]);
            }
           // System.out.println();
            
            for(int j=0;j<index[i].size();j++){
               // System.out.println("j "+j);
                int current=a[index[i].get(j)]-1;
                int end=b[index[i].get(j)];
                int count=0;
                while(direct[current]<end){
                    
                    
                    count+=cost[current];
                    current=direct[current];
                  //  System.out.println("here " + count);
                  //  System.out.println(current);
                    
                }
                while(current<end){
                    
                    if(current==trans[current]){
                        answer[index[i].get(j)]=-1;
                        break;
                    }
                    else{
                        count++;
                        current=trans[current];
                    }
                   // System.out.println("here2 " + count);
                   // System.out.println(current);
                }
                if(current>=end){
                    answer[index[i].get(j)]=count;
                    
                }
                
            }
            
        }
        for(int i=1;i<=Q;i++){
            System.out.println(answer[i]);
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