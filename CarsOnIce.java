/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cars.on.ice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author dannyyang
 */
public class CarsOnIce {

    /**
     * @param args the command line arguments
     */
    static class edge{
        
        int r;
        int c;
        edge(int r, int c){
            this.r=r;
            this.c=c;
        }
    }
    static ArrayList<edge> cars;
    static int [][] col; //sum from up to down
    static int [][] row; //sum from left to right
    static int rowtotal[];
    static int coltotal[];
    static int N;
    static int M;
    public static void update(int r, int c, int val){
        for(int i=r;i<=N;i+=(i&-i)){
            col[i][c]+=val;
        }
        for(int i=c;i<=M;i+=(i&-i)){
            row[r][i]+=val;
        }
    }
    public static boolean Query(int r, int c, char di){
        if(di=='N'){
            
            for(int i=r-1;i>0;i-=(i&-i)){
                if(col[i][c]!=0){
                    return false;
                }
            }
            return true;
        }
        else if(di=='W'){
            for(int i=c-1;i>0;i-=(i&-i)){
                if(row[r][i]!=0){
                    return false;
                }
            }
            return true;
        }
        else if(di=='S'){
            int answer=0;
            for(int i=r-1;i>0;i-=(i&-i)){
                answer+=col[i][c];
            }
            if(coltotal[c]-answer!=1){
                return false;
            }
            return true;
        }
        else{
            int answer=0;
           // System.out.println("here");
            for(int i=c-1;i>0;i-=(i&-i)){
                answer+=row[r][i];
            }
            if(rowtotal[r]-answer!=1){
              //  System.out.println("here");
                return false;
            }
            return true;
        }
    }
    static ArrayList<Integer> uptodown[];
    static ArrayList<Integer> lefttoright[];
    static char [][]parking=new char[N+1][M+1];
    static boolean went[][];
    public static void check(int r, int c){
        
        for(int i=0;i<uptodown[c].size();i++){
            int pr=uptodown[c].get(i);
            if(!went[pr][c]){
                if(Query(pr,c,parking[pr][c])){
                System.out.println("("+(pr-1)+","+(c-1)+")");
                update(pr,c,-1);
                coltotal[c]--;
                rowtotal[pr]--;
                went[pr][c]=true;
                
                i=-1;
                }
            }
        }
        for(int i=0;i<lefttoright[r].size();i++){
            int pc=lefttoright[r].get(i);
            if(!went[r][pc]){
                if(Query(r,pc,parking[r][pc])){
                System.out.println("("+(r-1)+","+(pc-1)+")");
                update(r,pc,-1);
                coltotal[pc]--;
                rowtotal[r]--;
                went[r][pc]=true;
                
                i=-1;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String token[]=input.readLine().split(" ");
        N=Integer.parseInt(token[0]);
        M=Integer.parseInt(token[1]);
        uptodown=new ArrayList[M+1];
        parking=new char[N+1][M+1];
        lefttoright=new ArrayList[N+1];
        for(int i=0;i<=M;i++)
            uptodown[i]=new ArrayList();
        for(int i=0;i<=N;i++)
            lefttoright[i]=new ArrayList();
        col=new int[N+1][M+1];
        row=new int[N+1][M+1];
        coltotal=new int[M+1];
        rowtotal=new int[N+1];
        cars=new ArrayList();
        for(int i=1;i<=N;i++){
            String line=input.readLine();
            for(int j=1;j<=M;j++){
                parking[i][j]=line.charAt(j-1);
                if(parking[i][j]!='.'){
                    //System.out.println("here");
                    update(i,j,1);
                    cars.add(new edge(i,j));
                    uptodown[j].add(i);
                    lefttoright[i].add(j);//
                    coltotal[j]++;
                    rowtotal[i]++;
                }
            }
        }
       // System.out.println("here");
        went=new boolean[N+1][M+1];
        for(int i=0;i<cars.size();i++){
            edge temp=cars.get(i);
            if(!went[temp.r][temp.c])
            if(Query(temp.r,temp.c,parking[temp.r][temp.c])){
                System.out.println("("+(temp.r-1)+","+(temp.c-1)+")");
                update(temp.r,temp.c,-1);
                coltotal[temp.c]--;
                rowtotal[temp.r]--;
                went[temp.r][temp.c]=true;
                check(temp.r,temp.c);
                cars.remove(i);
                if(cars.isEmpty()){
                    //System.out.println("here");
                    break;
                }
                i=-1;
            }
        }
        
        
        
        
    }
    
}
