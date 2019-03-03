/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circular.cities;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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
public class CircularCities {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        for(int test=0;test<10;test++){
            int N=input.nextInt();
            int M=input.nextInt();
            int student[]=new int[N];
            int teacher[]=new int[N];
            for(int i=0;i<N;i++){
                student[i]=input.nextInt();
            }
            for(int i=0;i<N;i++){
             teacher[i]=input.nextInt();   
            }
            Arrays.sort(student);
            Arrays.sort(teacher);
            int total=0;
            for(int i=0;i<N;i++){
                total+=Math.abs(Math.min(Math.abs(student[i]-teacher[i]), student[i]+M-teacher[i]));
            }
            for(int i=1;i<N;i++){
                int sub=0;
                for(int j=0;j<N;j++){
                sub+=Math.abs(Math.min(Math.abs(student[(j+i)%N]-teacher[j]), student[(j+i)%N]+M-teacher[j]));
                }
                if(sub>total*total){
                    break;
                }
                else if(sub<total){
                    total=sub;
                }
                
            }
            for(int i=1;i<N;i++){
                int sub=0;
                for(int j=0;j<N;j++){
                sub+=Math.abs(Math.min(Math.abs(student[j]-teacher[(j+i)%N]), student[j]+M-teacher[(j+i)%N]));
                }
                if(sub>total*total){
                    break;
                }
                else if(sub<total){
                    total=sub;
                }
            }
            System.out.println(total);
                
        }
    }
    
}
