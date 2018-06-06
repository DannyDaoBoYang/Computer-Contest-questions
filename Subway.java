/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subway;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Subway {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        int T = input.nextInt();
        double dp[][] = new double[T + 2][N + 1];
        ArrayList<Integer> path[] = new ArrayList[N + 1];
        ArrayList<Integer> cost1[] = new ArrayList[N + 1];
        ArrayList<Integer> cost2[] = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            path[i] = new ArrayList<Integer>();
            cost1[i] = new ArrayList<Integer>();
            cost2[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            int c1 = input.nextInt();
            int c2 = input.nextInt();
            path[a].add(b);
            cost1[a].add(c1);
            cost2[a].add(c2);
        }
        dp[0][1] = 1;
        dp[1][1]=-1;
        
        for (int i = 0; i < T; i++) {
            for (int j = 1; j < N; j++) {
                if (dp[i][j] != 0) {
                    double ways = 0;
                    for (int w = 0; w < path[j].size(); w++) {
                        if (cost1[j].get(w) + i <= T) {
                            ways = ways + (Math.min(cost2[j].get(w) + i, T) - (cost1[j].get(w)+i)+1);
                        }
                        
                    }
                   // System.out.println(ways);
                    for (int w = 0; w < path[j].size(); w++) {
                        if (cost1[j].get(w) + i <= T) {
                            dp[cost1[j].get(w)+i][path[j].get(w)]+=dp[i][j]*(1/ways);
                            dp[Math.min(cost2[j].get(w)+i, T)+1][path[j].get(w)]-=dp[i][j]*(1/ways);
                            
                        }
                    }
                    /*for(int w=0;w<=N;w++){
                       System.out.print(dp[i][j]+" ");
                    }
                    System.out.println();*/
                }
            }
            for(int j=1;j<=N;j++){
                dp[i+1][j]+=dp[i][j];
            }
            
        }
       // System.out.println("------");
        double total=0;
        for(int i=0;i<=T;i++){
            total+= dp[i][N]*(double)i;
            
            //System.out.println(total);
        }
        System.out.println(total);

    }

}
