/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Binary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int[] b = new int[a];
        for (int i = 0; i < a; i++) {
            b[i] = input.nextInt();
        }
        String[] solu = new String[a];
        for(int i=0;i<a;i++){
            solu[i]="";
        }
        for (int i = 0; i < a; i++) {
            int power = 0;
            for (int j = 1; j <= 30; j++) {
                if (Math.pow(2, j) > b[i]) {
                    power = j - 1;
                    break;
                }
            }

            for (int j = power; j >= 0; j--) {
                int sw = (int) (b[i] / Math.pow(2, j));
                b[i] = b[i] - (int) (Math.pow(2, j)) * sw;
                solu[i] = solu[i] + sw;

            }
        }
        for (int i = 0; i < a; i++) {
            if (solu[i].length()%4 == 0) {
                continue;
            }
            else if (solu[i].length()%4 == 1) {
                solu[i]="000"+solu[i];
            }
            else if (solu[i].length()%4 == 2) {
                solu[i]="00"+solu[i];
            }
            else if (solu[i].length()%4 == 3) {
                solu[i]="0"+solu[i];
            }
        }
        
        
        for(int i=0;i<a;i++){
            for(int j=0;j<solu[i].length();j++){
                if(j%4==0&&j!=0){
                    System.out.print(" ");
                }
                System.out.print(solu[i].charAt(j));
            }
            System.out.println();
        }

    }

}
