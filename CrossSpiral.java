/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cross.spiral;

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

public class CrossSpiral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        int Height = input.nextInt();
        int Width = input.nextInt();
        int LtR = input.nextInt();
        int UtD = input.nextInt();
        int Steps = input.nextInt();
        boolean[][] went = new boolean[Width + 2][Height + 2];
        for (int i = 0; i <= Width + 1; i++) {
            went[i][0] = true;
            went[i][Height + 1] = true;
        }
        for (int i = 0; i <= Height + 1; i++) {
            went[0][i] = true;
            went[Width + 1][i] = true;
        }

        for (int i = 1; i <= UtD; i++) {
            for (int j = 1; j <= LtR; j++) {
                went[i][j] = true;
            }
            for (int j = Height - LtR + 1; j <= Height; j++) {
                went[i][j] = true;
            }
        }

        for (int i = Width - UtD + 1; i <= Width; i++) {
            for (int j = 1; j <= LtR; j++) {
                went[i][j] = true;
            }
            for (int j = Height - LtR + 1; j <= Height; j++) {
                went[i][j] = true;
            }
        }

       /* for (int i = 0; i < Width + 2; i++) {
            for (int j = 0; j < Height + 2; j++) {
                System.out.print(went[i][j] + "\t");
            }
            System.out.println();
        }*/
        int posx = LtR + 1;
        int posy = 1;
        int direction = 2; //up is 1, right is 2, down is 3, left is 4
        int count=0;
        int i;
        for (i = 0; i < Steps; i++) {
            if(count>3){
                break;
            }
            if (direction == 1) {
                if (!went[posy-1][posx]) {

                    if (went[posy][posx-1]) {
                        went[posy][posx] = true;
                        posy = posy - 1;
                        count=0;
                    }
                    else{
                    direction=4;
                    i--;
                    count++;
                    }
                }
             else {
                    direction=2;
                    i--;
                    count++;
                
            }
            } else if (direction == 2) {
                if (!went[posy][posx + 1]) {

                    if (went[posy - 1][posx]) {
                        went[posy][posx] = true;
                        posx = posx + 1;
                        count=0;
                    }
                    else{
                    direction=1;
                    i--;
                    count++;
                    }
                }
             else {
                    direction=3;
                    i--;
                    count++;
                
            }
        }
        else if(direction==3){
                if (!went[posy+1][posx]) {

                    if (went[posy][posx+1]) {
                        went[posy][posx] = true;
                        posy = posy + 1;
                        count=0;
                    }
                    else{
                    direction=2;
                    i--;
                    count++;
                    }
                }
             else {
                    direction=4;
                    i--;
                    count++;
                
            }
            }
            else if(direction==4){
                if (!went[posy][posx-1]) {

                    if (went[posy+1][posx]) {
                        went[posy][posx] = true;
                        posx = posx - 1;
                        count=0;
                    }
                    else{
                    direction=3;
                    i--;
                    count++;
                    }
                }
             else {
                    direction=1;
                    i--;
                    count++;
                
            }
            }
    }

    System.out.println (posx);

    System.out.println (posy);
    
}

}
