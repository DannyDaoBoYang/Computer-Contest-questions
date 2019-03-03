/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crayola.lightsaber;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class CrayolaLightsaber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        String color[]={"red", "orange", "yellow", "green", "blue", "black"};
        int count[]=new int[6];
                for(int i=0;i<N;i++){
            String word=input.next();
            for(int j=0;j<6;j++){
                if(color[j].equals(word)){
                    count[j]++;
                    break;
                }
            }
        }
        int total=0;
        Arrays.sort(count);
        for(int i=0;i<6;i++){
            total+=count[i];
        }
        if(total+1<count[5]*2){
            System.out.println((total-count[5])*2+1);
        }
        else{
            System.out.println(total);
        }
        
    }
    
}
