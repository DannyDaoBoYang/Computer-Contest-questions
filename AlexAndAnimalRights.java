/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alex.and.animal.rights;

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
public class AlexAndAnimalRights {

    /**
     * @param args the command line arguments
     */
    static char map[][];
    static boolean went[][];
    static int room=0;
    public static boolean move(int x, int y){
        if(!went[x][y]){
            return false;
        }
        else{
        went[x][y]=false;
        boolean have=false;
        have=have|move(x+1,y);
        have=have|move(x-1,y);
        have=have|move(x,y+1);
        have=have|move(x,y-1);
        if(map[x][y]=='M'){
            return true;
        }
        return have;
        }
    }
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        String []dadadida=input.readLine().split(" ");
        int R=Integer.parseInt(dadadida[0]);
        int C=Integer.parseInt(dadadida[1]);
        went=new boolean[R+2][C+2];
        map=new char[R+2][C+2];
        
        
        ArrayList<Integer> Rindex=new ArrayList<Integer>();
        ArrayList<Integer> Cindex=new ArrayList<Integer>();
        for(int i=1;i<=R;i++){
            String nextrow=input.readLine();
            for(int j=0;j<C;j++){
                map[i][j+1]=nextrow.charAt(j);
                if(map[i][j+1]!='#'){
                    went[i][j+1]=true;
                }
                
            }
        }
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                if(went[i][j]){
                    
                    boolean have=move(i,j);
                    if(have){
                        room++;
                    }
                }
            }
        }
        System.out.println(room);
        
    }
    
}
