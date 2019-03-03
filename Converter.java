/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Converter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*String what[]={"abc","bac"};
        
        Scanner input=new Scanner(System.in);
        String a=input.nextLine();
        String key=input.nextLine();
        String ori="";
        for(int i=0;i<a.length();i++){
            ori=ori+(char)(key.charAt(i%key.length())^a.charAt(i));
        }*/
        String answer="";
        String ori="";
        char b=0;
        ori=ori+b;
        b=82;
        ori=ori+b;
        b=0;
        ori=ori+b;
        b=9;
        ori+=b;
        b=29;
        ori+=b;
        b=36;
        ori+=b;
        b=0;
        ori+=b;
        b=23;
        ori+=b;
        b=9;
        ori+=b;
        b=17;
        ori+=b;
        b=97;
        ori+=b;
        b=67;
        ori+=b;
        b=2;
        ori+=b;
        b=21;
        ori+=b;
        b=90;
        ori+=b;
        
        for(int i=0;i<ori.length();i++){
            int temp=ori.charAt(i);
            System.out.println(temp);
            for(int j=7;j>=0;j--){
                if((int)(temp/Math.pow(2, j))>0){
                    answer=answer+'1';
                }
                else{
                    answer=answer+'0';
                }
                temp%=Math.pow(2, j);
            }
        }
        System.out.print(answer);
        //I live in Canada. Canada is a beautiful place. I live here.
        //I am the best student in this class. I am confident. I am happy for my mark right now.
        //How are you doing today? I'm fine. Thank you, and you?
        //This is a beautiful day.
        //Assholes Assholes Assholes Assholes Assholes Assholes Assholes Assholes Assholes Assholes 
        //Today is an important day.
        //What day is today? Today is Monday.
        //I love KFC. Five words at least.
        //NASA administrator promises not to abandon International Space Station without alternative plan
        //
        int numbers[]=new int[10];
        numbers.length;
                String S="a";
                S.l;
                        
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                input.readLine()
    }
    
}
