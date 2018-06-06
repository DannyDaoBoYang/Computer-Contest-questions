/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absolute.acidic;

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
public class AbsoluteAcidic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int [] readings=new int [1001];
        for(int i=0;i<N;i++){
            readings[input.nextInt()]++;
        }

        int a=-1;
        int b=-1;
        ArrayList <Integer>LI=new ArrayList<Integer>();
        int largest=0;
        for(int i=1;i<1001;i++){
            if(readings[i]>largest){
               // System.out.println(i+" here");
                largest=readings[i];
                LI.clear();
                LI.add(i);
                continue;
            }
             if(readings[i]==largest){
               // System.out.println(i+" here2");
                LI.add(i);
            }
        }

        
        if(LI.size()==1){
            a=LI.get(0);
            readings[a]=0;
            LI.clear();
        int largest2=0;
        for(int i=1;i<1001;i++){
            if(readings[i]>largest2){
                largest2=readings[i];
                LI.clear();
                LI.add(i);
            }
            else if(readings[i]==largest2){
                LI.add(i);
            }
        }
        if(LI.size()==1){
            System.out.println(Math.abs(a-LI.get(0)));
        }
        else{
            int numbers[]=new int[LI.size()];
            for(int i=0;i<numbers.length;i++){
                numbers[i]=LI.get(i);
            }
            Arrays.sort(numbers);
            int finala=Math.abs(a-numbers[0]);
            int finalb=Math.abs(a-numbers[numbers.length-1]);
            if(finala>finalb){
                System.out.println(finala);
                
            }
            else{
                System.out.println(finalb);
                
               
            }
        }
        }// first branch
        else{
            
            int numbers[]=new int[LI.size()];
            for(int i=0;i<numbers.length;i++){
                numbers[i]=LI.get(i);
            }
            Arrays.sort(numbers);
            System.out.println(numbers[numbers.length-1]-numbers[0]);
            
        }
        
    }
    
}
