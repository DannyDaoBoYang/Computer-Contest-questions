/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridges.in.a.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class BridgesInAGraph {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> graph[];
    static class edge{
        int pos;
        int par;
        edge(int pos, int par){
            this.pos=pos;
            this.par=par;
        }
    }
   
    static int count=0;
    public static void go( int pos,int parent, boolean went[]){
        count++;
        went[pos]=true;
        for(int i=0;i<graph[pos].size();i++){
            
            if(graph[pos].get(i)!=parent){
                if(went[graph[pos].get(i)]){
                    continue;
                }
                go(graph[pos].get(i),pos,went);
                
            }
        }
        
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
       for(int test=1;test<=5;test++){
        int N=input.nextInt();
        int M=input.nextInt();
        graph=new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            graph[i]=new ArrayList();
        }
        int x[]=new int[M+1];
        int y[]=new int[M+1];
        
        for(int i=0;i<M;i++){
            x[i]=input.nextInt();
            y[i]=input.nextInt();
            graph[x[i]].add(y[i]);
            graph[y[i]].add(x[i]);
        }
        
        boolean went[];
        int total=0;
        for(int i=0;i<M;i++){
            went=new boolean[N+1];
            graph[x[i]].remove(graph[x[i]].indexOf(y[i]));
            graph[y[i]].remove(graph[y[i]].indexOf(x[i]));
            go(1,0,went);
            if(count!=N){
             total++;   
            // System.out.println(i);
            }
            graph[x[i]].add(y[i]);
            graph[y[i]].add(x[i]);
            count=0;
            
        }
        System.out.println(total);
        
        
        
        
        }
    }
    
}
