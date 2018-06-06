/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lights.going.on.and.off;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class LightsGoingOnAndOff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int R=input.nextInt();
        int L=input.nextInt();
        int rows[]=new int[R];
        for(int i=R-1;i>=0;i--){
            for(int j=L-1;j>=0;j--){
             if(input.nextInt()==1)
             rows[i]+=Math.pow(2, j);
            }
        }
        boolean possible[]=new boolean[(int)Math.pow(2, L)];
        int current=rows[0];
        for(int i=1;i<R;i++){
            possible[current]=true;
            current^=rows[i];
        }
        possible[current]=true;
        int count=0;
        for(int i=0;i<possible.length;i++){
            if(possible[i]){
                count++;
        }
    }
        System.out.println(count);
    
}
}
