/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afk2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
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

public class AFK2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        Scanner input=new Scanner(System.in);
        
        int t = input.nextInt();
        
        for (int test = 0; test < t; test++) {

            int l = input.nextInt();           
            int w = input.nextInt();
            boolean[][] visited = new boolean[w + 2][l + 2];
            int[][] mat = new int[w + 2][l + 2];
            for (int i = 0; i < w + 2; i++) {
                for (int j = 0; j < l + 2; j++) {
                    visited[i][j] = true;
                }
            }

            int sx = 0, sy = 0, ex = 0, ey = 0;
            
            for (int i = 1; i <= w; i++) {
                String a = input.next();
               
                for (int j = 1; j <= l; j++) {
                    char temp = a.charAt(j-1);
                    if (temp == 'O') {
                        visited[i][j] = false;
                        mat[i][j] = 1;
                    } else if (temp == 'X') {
                        
                    } else if (temp == 'W') {
                        visited[i][j] = false;
                        ex = j;
                        ey = i;
                        mat[i][j] = 2;
                    } else if (temp == 'C') {
                        visited[i][j] = false;
                        sx = j;
                        sy = i;
                        mat[i][j] = 1;
                    }
                }
            }
            int step = 0;
            
            Queue<Integer> X = new LinkedList<Integer>();
            Queue<Integer> Y = new LinkedList<Integer>();
            X.add(sx);
            Y.add(sy);
            
            boolean answer = false;
            visited[sy][sx] = true;
            int loop = 1;
            int ploop = 0;
            
            while (!X.isEmpty()) {
                ploop = 0;
                step++;
                
                for (int i = 0; i < loop; i++) {
                    int x = X.poll();
                    int y = Y.poll();
                    
                    if (mat[y][x] == 0) {
                        
                    } 
                    else if (mat[y][x] == 1) {
                        visited[y][x] = true;
                        if (!visited[y - 1][x]) {
                            ploop++;
                            visited[y - 1][x] = true;
                            X.add(x);
                            Y.add(y - 1);
                        }if (!visited[y][x + 1]) {
                            ploop++;
                            visited[y][x + 1] = true;
                            X.add(x + 1);
                            Y.add(y);
                        } if (!visited[y + 1][x]) {
                            ploop++;
                            visited[y + 1][x] = true;
                            X.add(x);
                            Y.add(y + 1);
                        } if (!visited[y][x - 1]) {
                            ploop++;
                            visited[y][x - 1] = true;
                            X.add(x - 1);
                            Y.add(y);
                        }

                    } else if (mat[y][x] == 2) {
                        answer=true;
                        break;
                    }

                }
                if (answer) {
                    break;
                }
                loop = ploop;
            }
            if (answer&&step<61) {
                System.out.println(step-1);
            } else {
                System.out.println("#notworth");
            }
        }
    }

}
