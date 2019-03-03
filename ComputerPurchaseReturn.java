/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.purchase.pkgreturn;

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

public class ComputerPurchaseReturn {
    static int N;
    static int T;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        T=input.nextInt();
        N=input.nextInt();
        int dp1[][]=new int[T+1][3001];
        int dp2[][]=new int[T+1][3001];
        for(int i=1;i<=T;i++){
            for(int j=0;j<=N;j++){
                dp1[i][j]=-1;
                dp2[i][j]=-1;
            }
        }
        
        for(int i=1;i<=N;i++){
            int c=input.nextInt();
            int v=input.nextInt();
            int t=input.nextInt();
            dp1[t][c]=Math.max(dp1[t][c], v);
        }
        
        int B=input.nextInt();
        for(int j=1;j<=B;j++){
            dp1[1][j]=Math.max(dp1[1][j], dp1[1][j-1]);
            dp2[1][j]=dp1[1][j];
        }
        for(int i=2;i<=T;i++){
            for(int j=1;j<=B;j++)
            dp1[i][j]=Math.max(dp1[i][j], dp1[i][j-1]);
        }
        System.out.println();
        for(int i=2;i<=T;i++){
            for(int j=2;j<=B;j++){
                for(int k=1;k<=j-1;k++){
                    if(dp1[i][k]!=-1&&dp2[i-1][j-k]!=-1){
                        dp2[i][j]=Math.max(dp2[i][j], dp1[i][k]+dp2[i-1][j-k]);
                    }
                    
                }
                dp2[i][j]=Math.max(dp2[i][j], dp2[i][j-1]);
            }
        }
       /* for(int i=0;i<=T;i++){
            for(int j=0;j<=B;j++){
                System.out.print(dp2[i][j]+"\t");
        }
            System.out.println();
        }*/
        System.out.println(dp2[T][B]);
    

}
}
