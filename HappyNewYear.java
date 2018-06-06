/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happy.pkgnew.year;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class HappyNewYear {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input=new Scanner(System.in);
        int h=input.nextInt();
        int m=input.nextInt();
        int s=input.nextInt();
        int carry=s/60;
        int a=(60-(s-carry*60))%60;
        String answer="";
        if(a<10){
            answer=":0"+a;
        }
        else{
            answer=":"+a;
        }
       if(a==0)
        m+=carry;
       else{
           m+=carry+1;
       }
        carry=m/60;
        int b=(60-(m-carry*60))%60;
        if(b<10){
            answer=":0"+b+answer;
        }
        else{
            answer=":"+b+answer;
        }
        if(b==0)
        h+=carry;
        else{
            h+=carry+1;
        }
        int c=(12-(h%12))%12;
        if(c<10){
            answer="0"+c+answer;
        }
        else{
            answer=c+answer;
        }
        System.out.println(answer);
        
        
    }
    
}
