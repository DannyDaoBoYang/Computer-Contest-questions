/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivery.service;

import apple.laf.JRSUIUtils.Tree;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


/**
 *
 * @author dannyyang
 */
public class DeliveryService {

    /**
     * @param args the command line arguments
     */
    public static int po(int n){
        int pow=0;
        int num=1;
        TreeMap a=new TreeMap();
        
        while(n>num){
            num*=2;
            pow++;
        }
        return pow;
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int X=input.nextInt();
        //This is the second part of the code
        if(X==1){
            
        int D=input.nextInt();
        int fuel [] =new int[D+1];
        fuel[0]=input.nextInt();
        
        int P=input.nextInt();
        
        for(int i=0;i<P;i++){
            fuel[input.nextInt()]=input.nextInt();
        }
        int dif[]=new int[1000000];
        int pre[]=new int[1000000];
        //this is the third part of the code
        int modi=(int)Math.pow(10, 9)+7;
        
        //this is the third part f
        dif[1]=1;
        dif[1+fuel[0]]=-1;
        //this is the fourth part of the code
        for(int i=1;i<=D;i++){
            if(dif[i]<0)
            pre[i]=pre[i-1]+dif[i]+modi;
            else{
                pre[i]=pre[i-1]+dif[i];
            }
            pre[i]%=modi;
            if(fuel[i]!=0){
            dif[i+1]+=pre[i];
            dif[i+1+fuel[i]]-=pre[i];
            if(dif[i+1]>modi)
            dif[i+1]%=modi;
            
            if(dif[i+1+fuel[i]]<-modi)
            dif[i+1+fuel[i]]%=modi;
            }
        }
        //7 , 5 ,3 , 1
        //511 111111111
        //420 110100100
        //250  11111010
        //180 10110100
        //170  10101010
        //127   1111111
        //123   1111011
        // 76   1001100
        //64    1000000
        //63     111111
        
        /*for(int i=0;i<fuel.length;i++)
        System.out.print(fuel[i]+"\t");
        System.out.println();
        
        for(int i=0;i<fuel.length;i++)
        System.out.print(dif[i]+"\t");
        System.out.println();
        
        for(int i=0;i<fuel.length;i++)
        System.out.print(pre[i]+"\t");
        System.out.println();*/
        
        System.out.println(pre[D]);
        
            
        }
        else{
            int W=input.nextInt();
            if(W==0){
                System.out.println(100+" "+50+" "+1);
                System.out.println(1+" "+1);
                System.exit(0);
            }
            ArrayList<Integer> useful=new ArrayList<Integer>();
            for(int i=W;i>0;i-=(i&-i)){
                useful.add(po(i&-i));
            }
            
            System.out.print(useful.get(useful.size()-1)+2);
            System.out.print(" ");
            System.out.print(useful.get(useful.size()-1)+1);
            
            System.out.print(" ");
            System.out.println(useful.get(useful.size()-1)+1);
            
            
            boolean answer[]=new boolean[useful.get(useful.size()-1)+2];
            
            for(int i=0;i<useful.size();i++){
                answer[useful.get(i)]=true;
                
            }
            int should=useful.get(useful.size()-1)+1;
            for(int i=0;i<answer.length-1;i++){
                if(answer[i]){
                System.out.println((i+1)+" "+should);
                }
                else{
                    System.out.println((i+1)+" "+(should-1));
                }
                should--;
            }
            
            
            
            
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