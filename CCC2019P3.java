/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccc2019p3;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class CCC2019P3 {

    /**
     * @param args the command line arguments
     */
    static boolean wonder[][];
    static int grid[][];
    public static void brushc(int j){
        if(!wonder[j][1]&&!wonder[j][3]){
            grid[j][2]=(grid[j][1]+grid[j][3])/2;
            wonder[j][2]=false;
        }
    }
    public static void brushr(int j){
        if(!wonder[1][j]&&!wonder[3][j]){
            grid[2][j]=(grid[1][j]+grid[3][j])/2;
            wonder[2][j]=false;
        }
    }
    
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        grid=new int[4][4];
        wonder=new boolean[4][4];
        double add[][]=new double[4][4];
        int sumr[]=new int[4];
        int sumc[]=new int[4];
        int count=0;
        
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                String a=input.next();
                if(a.equals("X")){
                    wonder[i][j]=true;
                    count++;
                    continue;
                }
                else
                    
                grid[i][j]=Integer.parseInt(a);
                sumr[i]+=grid[i][j];
                sumc[j]+=grid[i][j];
            }
        }
        for(int times=0;times<3;times++)
        for(int i=1;i<=3;i++){
            brushr(i);
            brushc(i);
        }
        
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
        
        
            
                    
    }
    
}
