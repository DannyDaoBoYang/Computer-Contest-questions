/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dannyyang
 */
public class Baza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        //System.out.println((int)('a'));
        int N = Integer.parseInt(input.readLine());
        HashMap<String, Integer> where[] = new HashMap[31];
        for (int i = 1; i < 31; i++) {
            where[i] = new HashMap();
        }
        String dic[] = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            dic[i] = input.readLine();

        }

        int Q = Integer.parseInt(input.readLine());

        HashMap<String, Integer> have[] = new HashMap[31];// the wrod/ index
        for (int i = 1; i < 31; i++) {
            have[i] = new HashMap();
        }
        int answer[] = new int[Q + 1];
        int already[] = new int[Q + 1];
        String fin[] = new String[Q + 1];
        

        for (int i = 1; i <= Q; i++) {
            fin[i] = input.readLine();
            if (have[fin[i].length()].containsKey(fin[i])) {
                already[i] = have[fin[i].length()].get(fin[i]);
            } else {
                have[fin[i].length()].put(fin[i], i);
                where[fin[i].length()].put(fin[i], 0);
                String temp=fin[i].substring(0, fin[i].length()-1);
               // System.out.println(temp);
                while(!where[temp.length()].containsKey(temp)){
                    where[temp.length()].put(temp, 0);
                    temp=temp.substring(0, temp.length()-1);
                    if(temp.length()==0){
                        break;
                    }
                }
            }
            
            

        }
        for (int i = 1; i <= N; i++) {
            String temp = "";
            
            int total = 0;
            work:{
            for (int j = 0; j < dic[i].length(); j++) {
                  temp=temp+dic[i].charAt(j);
                if (where[j + 1].containsKey(temp)) {
                    int hold = where[j + 1].get(temp);
                    where[j + 1].replace(temp,hold+1);
                    total += hold + 1;
                }
                else{
                    break work;
                }
            }
            if (have[temp.length()].containsKey(temp)) {
                answer[have[temp.length()].get(temp)] = total + i;
            }
            }

        }
        for (int i = 1; i <= Q; i++) {
            if (answer[i] != 0) {
                System.out.println(answer[i]);
            } else {
                if (already[i] != 0) {
                    System.out.println(answer[already[i]]);
                } else {
                    int total = N;
                    String temp="";
                    for (int j = 0; j < fin[i].length(); j++) {
                        temp = temp + fin[i].charAt(j);
                        if (where[j+1].containsKey(temp)) {
                            total += where[j + 1].get(temp);
                        } else {
                            break;
                        }
                    }

                    System.out.println(total);
                    answer[i] = total;
                }
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
        byte[] buf = new byte[32]; // line length
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