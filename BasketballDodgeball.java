/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basketball.dodgeball;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
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
public class BasketballDodgeball {
    static ArrayList <edge>[] team1=new ArrayList[9001];
    
    static int bx=0;
    static int by=0;
    static int lx=0;
    static int ld=0;

public static int moving(int largest){
    boolean went[][]=new boolean[largest+1][9001];
        
        
        Queue <edge> move=new LinkedList();
        move.add(team1[largest].get(0));
        
        int nextmove=0;
        int thismove=1;
        int step=0;
        boolean find=false;
        
        while(!move.isEmpty()){
            step++;
            while(thismove>0){
                
                thismove--;
                edge pass=move.poll();
                went[pass.y][pass.x]=true;
                for(int i=Math.min(largest,pass.y+pass.d+1);i>=Math.max(0,pass.y-pass.d-1);i--){
                  //  System.out.println("value"+(pass.y-pass.d));
                    for(int j=0;j<team1[i].size();j++){
                        if(!went[i][j]){
                        double dis=Math.sqrt((team1[i].get(j).x-pass.x)*(team1[i].get(j).x-pass.x)+(team1[i].get(j).y-pass.y)*(team1[i].get(j).y-pass.y));
                        if(dis<=pass.d){
                            if(team1[i].get(j).d==9001){
                         //       System.out.println("here");
                                move.clear();
                                thismove=0;
                                find=true;
                                break;
                            }
                            move.add(team1[i].get(j));
                            went[i][j]=true;
                            nextmove++;
                           // System.out.println(team1[i].get(j).x+" "+team1[i].get(j).y+" "+team1[i].get(j).d+" "+step);
                        }
                        }
                    }
                    if(find){
                        
                        break;
                    }
                        
                }
                
                
            }
            thismove=nextmove;
            nextmove=0;
        }
        team1=new ArrayList[9001];
        if(find)
        return step;
        else{
            return -1;
        }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int M=input.nextInt();
        for(int i=0;i<9001;i++){
            team1[i]=new ArrayList<edge>();
        }
        int largest=0;
        
        for(int i=0;i<N;i++){
            int x=input.nextInt();
            int y=input.nextInt();
            int d=input.nextInt();
            team1[y].add(new edge(x,y,d));
            if(y>largest){
                largest=y;  
            }
            
        }
        int step=moving(largest);
        largest=0;
        for(int i=0;i<9001;i++){
            team1[i]=new ArrayList<edge>();
        }
        for(int i=0;i<M;i++){
            int x=input.nextInt();
            int y=input.nextInt();
            int d=input.nextInt();
            team1[y].add(new edge(x,y,d));
            if(y>largest){
                largest=y;  
            }
            
        }
        int step2=moving(largest);
        
        if(step>=0&&step2>=0){
            if(step==step2){
                System.out.println("SUDDEN DEATH");
            }
            else if(step>step2){
                System.out.println(":'(");
            }
            else{
                System.out.println("We are the champions!");
            }
        }
        else if(step>0){
            System.out.println("We are the champions!");
        }
        else{
            System.out.println(":'(");
        }
        
           
     
        
        
        
    }
    static class edge {
        int x;
        int d;
        int y;
        

        edge(int x,  int y,int d) {
            this.x = x;      
            this.d = d;
            this.y = y;
        }

     
    }
}
