/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackandwhite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 *
 * @author Alexander-Ma
 */
public class BlackAndWhite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
        int [][] s = new int [N+2][N+2];
        for (int i = 0;i < M;i++){
            st = new StringTokenizer(in.readLine());
            int x1=Integer.parseInt(st.nextToken())+1, y1 = Integer.parseInt(st.nextToken())+1;
            int w =Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
            int x2 = x1+w, y2 = y1+h;
            s[x1][y1]++;
            s[x2][y2]++;
            s[x1][y2]--;
            s[x2][y1]--;
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(s[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        int cnt = 0;
        for (int i = 1;i<=N;i++){
            for (int j=1;j<=N;j++){
                s[i][j]+=s[i-1][j]+s[i][j-1]-s[i-1][j-1];
                System.out.print(s[i][j]+"\t");
                if (s[i][j]%2!=0)cnt++;
            }
            System.out.println();
        }
        System.out.println(cnt);
    }
    
}
    
    

