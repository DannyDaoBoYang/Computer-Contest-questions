/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chances.of.winning;

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
public class ChancesOfWinning {
    static int T;
public static int winning(int left,int []score, int []againsta, int []againstb){
    int answer=0;
    if(left==0){
        int here=score[T];
        for(int i=0;i<score.length;i++){
            if(score[i]>here){
                return 0;
            }
            if(score[i]==here&&i!=T){
                return 0;
            }
        }
        return 1;
    }
    int a=againsta[left-1];
    int b=againstb[left-1];
    
    score[a]+=3;
    answer+=winning(left-1,score,againsta,againstb);
    score[a]-=3;
    
    score[b]+=3;
    answer+=winning(left-1,score,againsta,againstb);
    score[b]-=3;
            
    score[a]++;
    score[b]++;
    answer+=winning(left-1,score,againsta,againstb);
    score[a]--;
    score[b]--;
    return answer;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
         T=input.nextInt();
        int G=input.nextInt();
        int [] againsta=new int[6-G+1];
        int [] againstb=new int[6-G+1];
        int [] score=new int[5];
        boolean [][] played=new boolean[5][5];
        for(int i=0;i<G;i++){
            int a=input.nextInt();
            int b=input.nextInt();
            int c=input.nextInt();
            int d=input.nextInt();
            played[a][b]=true;
            played[b][a]=true;
            if(c>d){
                score[a]+=3;
            }
            else if(d>c){
                score[b]+=3;
            }
            else{
                score[a]++;
                score[b]++;
            } 
        }
        int now=0;
        for(int i=1;i<5;i++){
            for(int j=i+1;j<5;j++){
                if(!played[i][j]){
                    againsta[now]=i;
                    againstb[now]=j;
                    now++;
                }
            }
        }
        
        
         
         int answer=winning(6-G, score,againsta,againstb);
         System.out.println(answer);
        }
   
}
