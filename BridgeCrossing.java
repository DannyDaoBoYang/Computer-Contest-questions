/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgecrossing;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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
public class BridgeCrossing {
static int M;
static int Q;
static int maxtotal=0;
static ArrayList<Integer> answer;
static int [][] regionmax;
public static void cross(int person,ArrayList<Integer> change, int time){
    
    if(time>=maxtotal){
        
    }
    else if(person==Q+1){
        if(time<maxtotal){
            answer.clear();
            answer.addAll(change);
            maxtotal=time;
            
        }
    }
    else{
        for(int i=person;i<person+M&&i<=Q;i++){
            change.add(i);
            
            cross(i+1,change,time+regionmax[person][i]);
            
            change.remove(change.size()-1);
        }
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        M=input.nextInt();
        Q=input.nextInt();
        String name[]=new String[Q+1];
        int time[]=new int[Q+1];
        regionmax=new int[Q+1][Q+1];
        
        answer=new ArrayList<Integer>();
        answer.add(0);
        for(int i=1;i<=Q;i++){
            name[i]=input.readLine();
            time[i]=input.nextInt();
            regionmax[i][i]=time[i];
            maxtotal+=time[i];
            answer.add(i);
        }
        
        for(int i=1;i<M;i++){
            for(int j=1;j<=Q-i;j++){
                regionmax[j][j+i]=Math.max(regionmax[j][j+i-1],time[j+i]);
            }
        }
        /*
        for(int i=0;i<=Q;i++){
            for(int j=0;j<=Q;j++){
                System.out.print(regionmax[i][j]+" \t");
                
            }
            System.out.println();
        }*/
        ArrayList<Integer> change=new ArrayList<Integer>();
        change.add(0);
        cross(1,change,0);
        System.out.println("Total Time: "+maxtotal);
        
        for(int i=0;i<answer.size()-1;i++){
            
            for(int j=answer.get(i)+1;j<=answer.get(i+1);j++){
                System.out.print(name[j]+" ");
            }
            System.out.println();
        }
        
        
        
        
}
    
}
