/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author dannyyang
 */
public class CityGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(input.readLine());
        for (int test = 0; test < t; test++) {
            String[] tokens = input.readLine().split(" ");
            int M = Integer.parseInt(tokens[0]);
            int N = Integer.parseInt(tokens[1]);
            boolean map[][]=new boolean[M+2][N+2];
            for(int i=0;i<M;i++){
                String street=input.readLine();
                System.out.println(street.length());
                for(int j=0;j<street.length();j++){
                    if(j%2==0)
                    if(street.charAt(j)=='F'){
                        map[i+1][j/2+1]=true;
                    }
                    
                }
            }
            int v[][]=new int[N+2][M+2];
            int h[][]=new int[N+2][M+2];
            int up=0;
            int left=0;
            int a=0;
            int b=0;
            int area=0;
            for(int i=1;i<=M;i++){
                for(int j=1;j<=N;j++){
                    if(map[i][j]){
                        v[i][j]=v[i][j-1]+1;
                        h[i][j]=h[i-1][j-1]+1;
                        if(v[i][j]*h[i][j]>area){
                            
                        }
                    }
                    
                }
            }
            
            

            if (test != t - 1) {
                input.readLine();
            }
        }
    }

}
