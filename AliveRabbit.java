/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alive.rabbit;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class AliveRabbit {
    public static int[] LtoG(int a,int b,int c,int d){
        int [] e={a,b,c,d};
        int f=0;
        for(int i=0;i<e.length-1;i++){
            while(e[i]>e[i+1]){
                f=e[i];
                e[i]=e[i+1];
                e[i+1]=f;
            }
            while(e[i]>e[e.length-1]){
                f=e[i];
                e[i]=e[e.length-1];
                e[e.length-1]=f;
            }
        }
        return e;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Scanner input= new Scanner(System.in);

      
      int []y=LtoG(5,4,3,2);
      for(int i=0;i<y.length;i++){
          System.out.println(y[i]);
      }
    }
    
}
