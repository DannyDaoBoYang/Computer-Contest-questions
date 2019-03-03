/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String a=input.next();
        int H=0;
        int V=0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)=='H'){
                H^=1;
            }
            else{
                V^=1;
            }
        }
        int grid[][]=new int[2][2];
        grid[0][0]=1;
        grid[0][1]=2;
        grid[1][0]=3;
        grid[1][1]=4;
        if(V==1){
            int temp=grid[0][0];
            int temp2=grid[1][0];
            grid[0][0]=grid[0][1];
            grid[1][0]=grid[1][1];
            grid[0][1]=temp;
            grid[1][1]=temp2;
           
        }
        if(H==1){
            int temp=grid[0][0];
            int temp2=grid[0][1];
            grid[0][0]=grid[1][0];
            grid[0][1]=grid[1][1];
            grid[1][0]=temp;
            grid[1][1]=temp2;
        }
        System.out.println(grid[0][0]+" "+grid[0][1]);
        System.out.println(grid[1][0]+" "+grid[1][1]);
    }
    
}
