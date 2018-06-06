/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.graff.s.trip;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author dannyyang
 */
public class KingGraffSTrip {
static class edge implements Comparable<edge> {

        long current;
        long accumu;
        int pos;
        long time;
        edge(int pos, long current, long accumu, long time) {
            this.pos=pos;
            this.current = current;
            this.accumu = accumu;
            this.time=time;
        }

        @Override
        public int compareTo(edge n) {
            if (accumu < n.accumu) {
                return -1;
            } else if(accumu ==n.accumu){
                if(current<n.current){
                    return -1;
                }
                else if(current==n.current){
                    if(time<n.time){
                        return -1;
                    }
                    return 1;
                }
                return 1;
            }
            return 1;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        int N=input.nextInt();
        int M=input.nextInt();
        int X=input.nextInt();
        int Y=input.nextInt();
        long L= input.nextLong();
        ArrayList<Integer> path[]=new ArrayList[N+1];
        ArrayList<Long> cost[]=new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            path[i]=new ArrayList<Integer>();
            cost[i]=new ArrayList<Long>();
            
        }
        for(int i=0;i<M;i++){
            int a=input.nextInt();
            int b=input.nextInt();
            long c=input.nextInt();
            path[a].add(b);
            cost[a].add(c);
        }
        int S=input.nextInt();
        boolean H[]=new boolean[N+1];
        for(int i=0;i<S;i++){
            H[input.nextInt()]=true;
        }
        PriorityQueue <edge> member=new PriorityQueue();
        boolean went[]=new boolean[N+1];
        
        long pcurr[]=new long[N+1];
        
        long ptime[]=new long[N+1];
        
        member.add(new edge(X,0,0,0));
        while(!member.isEmpty()){
            edge temp=member.poll();
          //  System.out.println(temp.pos);
            
            
            if(H[temp.pos]){
                temp.current=0;
            }
            if(temp.time>L){
                continue;
            }
            if(temp.pos==Y){
                System.out.println(temp.accumu);
                System.exit(0);
            }
            if(went[temp.pos]){
                if(temp.current<pcurr[temp.pos]){
                    if(temp.time<ptime[temp.pos]){
                        pcurr[temp.pos]=temp.current;
                        ptime[temp.pos]=temp.pos;
                    }
                }
                else {
                    if(temp.time<ptime[temp.pos]){
                        
                    }
                    else{
                        continue;
                    }
                }
                
            }
            else{
                went[temp.pos]=true;
                pcurr[temp.pos]=temp.current;
                ptime[temp.pos]=temp.time;
            }
            for(int i=0;i<path[temp.pos].size();i++){
                member.add(new edge(path[temp.pos].get(i),temp.current+cost[temp.pos].get(i),Math.max(temp.current+cost[temp.pos].get(i),temp.accumu),temp.time+cost[temp.pos].get(i)));
            }
            
        }
        System.out.println(-1);
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