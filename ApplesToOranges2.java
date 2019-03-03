/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apples.to.oranges.pkg2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author dannyyang
 */
public class ApplesToOranges2 {

static ArrayList<Integer> index[];
static double rates[][];
static String names[];
static int aindex;
    /**
     * @param args the command line arguments
     */
    public static int binarySearch(String[] a, String x) {
    int low = 0;
    int high = a.length - 1;
    int mid;

    while (low <= high) {
        mid = (low + high) / 2;

        if (a[mid].compareTo(x) < 0) {
            low = mid + 1;
        } else if (a[mid].compareTo(x) > 0) {
            high = mid - 1;
        } else {
            return mid;
        }
    }

    return -1;
}
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String dadi[]=input.readLine().split(" ");
        int N=Integer.parseInt(dadi[0]);
        int M=Integer.parseInt(dadi[1]);
        names=new String[N];
        rates=new double[N][N];
        index=new ArrayList[N];
        for(int i=0;i<N;i++){
            index[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<N;i++){
            names[i]=input.readLine();
        }
        Arrays.sort(names);
                
        for(int i=0;i<M;i++){
            String ex[]=input.readLine().split(" ");
            int f1=binarySearch(names,ex[0]);
            int f2=binarySearch(names,ex[1]);
            rates[f1][f2]=Double.parseDouble(ex[2]);
            
            index[f1].add(f2);
        }
        
       /* for(int i=0;i<names.length;i++){
            System.out.print(names[i]+" ");
        }
        System.out.println();*/
        aindex=binarySearch(names,"APPLES");
        //System.out.println(aindex);
        boolean answer=false;
        boolean went[]=new boolean[N];
        Queue <edge> gogo =new LinkedList ();
        int move=1;
        int pmove=0;
        int round=0;
        double value[]=new double[N];
       //System.out.println("-----------");
        gogo.add(new edge(1,aindex));
        while(!gogo.isEmpty()){
            round++;
            for(int i=0;i<move;i++){
                edge temp=gogo.poll();
                if(went[temp.index]){
                    if(temp.index==aindex){
                        if(temp.value>1){
                            answer=true;
                            gogo.clear();
                            break;
                        }
                    }
                    if(temp.value>value[temp.index]){
                        value[temp.index]=temp.value;
                        for(int j=0;j<index[temp.index].size();j++){
                          // System.out.println("Part1 round"+round+" "+temp.index+" "+index[temp.index].get(j));
                            gogo.add(new edge(temp.value*rates[temp.index][index[temp.index].get(j)],index[temp.index].get(j)));
                         //  System.out.println(temp.value*rates[temp.index][index[temp.index].get(j)]);
                            pmove++;
                        }
                    }
                    
                }
                else{
                    went[temp.index]=true;
                    value[temp.index]=temp.value;
                        for(int j=0;j<index[temp.index].size();j++){
                         // System.out.println("Part2 round "+round+" "+temp.index+" "+index[temp.index].get(j));
                            gogo.add(new edge(temp.value*rates[temp.index][index[temp.index].get(j)],index[temp.index].get(j)));
                           // System.out.println(temp.value);
                            //System.out.println(rates[temp.index][index[temp.index].get(j)]);
                          // System.out.println(temp.value*rates[temp.index][index[temp.index].get(j)]);
                            pmove++;
                        }
                }
            }
            move=pmove;
            pmove=0;
        }
        
        if(!answer)
        System.out.println("NAW");
        else{
            System.out.println("YA");
        }
        
        
    
}
     static class edge{

        double value;
        int index;

        edge(double value, int index) {

            this.value = value;
            this.index = index;
        }

        
    }
}