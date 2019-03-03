/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coci;
import java.util.*;
import java.io.*;
/**
 *
 * @author alexm
 */
public class Coci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int first2 [] = new int [n+1];
        int sec2 [] = new int [n+1];
        int points [][] = new int [655][655];
        for (int i = 0;i<n;i++){
            int first, second;
            String [] tokens = input.readLine().split(" ");
            first = Integer.parseInt(tokens[0]);
            second = Integer.parseInt(tokens[1]);
            points[first][second] += 1;
            first2[i] = first;
            sec2[i] = second;
        }
        for (int c = 0;c<=650;c++){
            for (int i = 1;i<=650;i++){
                    points[c][i] += points[c][i-1];
            }
        }
        for (int c = 0;c<=650;c++){
            for (int i = 1;i<=650;i++){
                    points[i][c] += points[i-1][c];
            }
        }
        for (int i = 0;i<n;i++){
            int first = first2[i];
            int sec = sec2[i];
            int high;
            int low = n;
            if (first-1>=0&&sec-1>=0){
                low = n-points[first-1][sec-1];
            }
            high = (points[650][650]-points[first][650]-points[650][sec]+points[first][sec])+1;
            if (first==650||sec==650){
                high = 1;
            }
            System.out.println(high + " " + low);
        }
    }
    
}