/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.travel;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class CityTravel {
static class edge implements Comparable<edge> {
        int date;
        int md;

        edge(int date, int md) {
            this.date = date;
            this.md = md;
        }

        @Override
        public int compareTo(edge n) {
            if (date < n.date) {
                return -1;
            }
            return 1;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int S=input.nextInt();
        double X=input.nextInt();
        int N=input.nextInt();
        edge da[]=new edge[N];
        
        for(int i=0;i<N;i++){
            da[i]=new edge(input.nextInt(),input.nextInt());
        }
        Arrays.sort(da);
        
        int count=0;
        int dis=0;
        int day=1;
        
        while(day<=S){
            if(count>=N){
               // System.out.println(day);
                System.out.println((int)(Math.ceil((double)(S-dis)/X))+day-1);
               // System.out.println("here");
                break;
            }
            if(da[count].date!=day){
                
                if((int)(Math.ceil((double)(S-dis)/X))+day-1<da[count].date){
                    System.out.println((int)(Math.ceil((double)(S-dis)/X))+day-1);
                    break;
                }
                else{
                    dis+=(da[count].date-day)*X;
                    day=da[count].date;
                 //   System.out.println("dis"+dis);
                }
                
                
            }
            else{
                
                dis+=da[count].md;
                if(dis>=S){
                    System.out.println(day);
                    break;
                }
                day=da[count].date+1;
                count++;
               
            }
        }
        
    }
    
}
