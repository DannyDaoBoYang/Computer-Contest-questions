/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alice.through.the.looking.glass;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author dannyyang
 */

public class AliceThroughTheLookingGlass {
static boolean cys[][]=new boolean[5][5];

public static boolean mag( int m, int x, int y){
    //I did not write this myself. 
    //I'm sorry. I was really close
    double power=Math.pow(5, m-1);
        int location=(int) (x/power);
        int locationy=(int) (y/power);
    if(m>1){
        
        x=(int) (x% Math.pow(5, m-1));
        y=(int) (y% Math.pow(5, m-1));
        if(location==0 ||location==4){
            
            return false;
        }
        else if(location==1 ||location==3){
            
            if(locationy==1){
            return mag(m-1,x,y);
            }
            else if(locationy==0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(location==2){
            
            if(locationy==2){
            return mag(m-1,x,y);
            }
            else if(locationy<2){
                return true;
            }
            else{
                return false;
            }
        }
        
    }
    else{
        if(cys[y][x]){
        return true;
        }
        return false;
    }
    
     return false;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
      //the small map
        
        cys[0][1]=true;
        cys[0][2]=true;
        cys[0][3]=true;
        cys[1][2]=true;
        int Test=input.nextInt();
        
        for(int www=0;www<Test;www++){
            
            int m=input.nextInt();
            int x=input.nextInt();
            int y=input.nextInt();
           boolean answer=mag(m,x,y);
            if(answer){
                System.out.println("crystal");
            }
            else{
                System.out.println("empty");
            }
            
            
        }
    }
    
}
class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        private int http;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
            
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException
        {
            
            bytesRead = din.read (buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }