/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.plus.b;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class APlusB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int z;
        z=input.nextInt();
        int [] []grid= new int[z][z];
        for(int i=0;i<z;i++){
           for(int x=0;x<z;x++)
            grid[i][x]=input.nextInt();
        
        }
        int []row=new int[z];
        int []col=new int[z];
        for(int i=0;i<z;i++){
            for(int x=0;x<z;x++){
                row[i]=row[i]+grid[i][x];
            }
                
        }
        for(int i=0;i<z;i++){
            for(int x=0;x<z;x++){
                col[x]=col[x]+grid[i][x];
            }
                
        }
        int t;
        t=input.nextInt();
        int [] solu=new int[t];
        for(int i=0;i<t;i++){
            int a,b;
            a=input.nextInt();
            b=input.nextInt();
            solu[i]=row[a-1]+col[b-1];
        }
        for(int i=0;i<solu.length;i++){
            System.out.println(solu[i]);
        }
        
        

        
    }
    
}
