/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivery.service.ii;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class DeliveryServiceII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        int D=input.nextInt();
        int dis[]=new int[N+1];
        for(int i=1;i<=N;i++){
            dis[i]=input.nextInt();
        }
        int posstart=0;
        int posend=0;
        int negstart=0;
        int negend=0;
        for(int i=0;i<D;i++){
            int p1=input.nextInt();
            int p2=input.nextInt();
            if(dis[p1]>dis[p2]){
                posstart=Math.min(posstart, dis[p2]);
                posend=Math.max(posend, dis[p1]);
            }
            if(dis[p1]<dis[p2]){
                negstart=Math.max(negstart, dis[p2]);
                negend=Math.min(negend, dis[p1]);
            }
        }
        if(posstart==posend){
         System.out.println(negstart-negend);
         System.exit(0);
        }
        else if(negstart==negend){
         System.out.println(posend-posstart);
         System.exit(0);
        }
        int temp1=negstart-Math.min(negend, posstart)+(posend-Math.min(negend, posstart));
        int temp2=Math.max(posend, negstart)-posstart+(Math.max(posend, negstart)-negend);
        int answer=Math.min(temp1, temp2);
        System.out.println(answer);
    }
    
}
