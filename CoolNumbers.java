/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cool.numbers;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class CoolNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int a, b;
        a=input.nextInt();
        b=input.nextInt();
        int count=0;
        for(int i=1;i<=1000;i++){
            int x=i*i*i*i*i*i;
            if(a<=x&&x<=b){
                count++;

            }
            if(x>b){
                break;
        }
        
    }
    System.out.println(count);
}
}
