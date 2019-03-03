/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.plus.b.hard;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */

public class APlusBHard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Scanner input = new Scanner(System.in);
        
        int N = input.nextInt();
        for (int test = 0; test < N; test++) {
            String number[]=new String[2];
            number[0]=input.next();
            number[1]=input.next();
            
            boolean n0 = (number[0].charAt(0) == '-');
            boolean n1 = (number[1].charAt(0) == '-');
            if(n0)
            number[0]=number[0].substring(1);
            if(n1)
            number[1]=number[1].substring(1);
            boolean subtra = n0 ^ n1;
            if(!subtra){
                String answer="";
                int carry=0;
                //Part one
                while(!number[0].isEmpty()&&!number[1].isEmpty()){
                    
                    long a=Long.parseLong(number[0].substring(Math.max(0,number[0].length()-18)));
                    long b=Long.parseLong(number[1].substring(Math.max(0,number[1].length()-18)));
                    String c=""+(a+b+carry);
                    
                    if(c.length()==19){
                        carry=1;
                        answer=c.substring(1)+answer;
                        number[0]=number[0].substring(0,Math.max(0,number[0].length()-18));
                        number[1]=number[1].substring(0,Math.max(0,number[1].length()-18));
                    }
                    else{
                        c="000000000000000000000"+c;
                        carry=0;
                        answer=c.substring(c.length()-18)+answer;
                        number[0]=number[0].substring(0,Math.max(0,number[0].length()-18));
                        number[1]=number[1].substring(0,Math.max(0,number[1].length()-18));
                    }
                    
                    
                }
                //Part two
                if(carry==0){
                    answer=number[0]+number[1]+answer;
                }
                if(number[0].isEmpty()&&number[1].isEmpty()){
                    answer=carry+answer;
                }
                while(!number[0].isEmpty()&&carry!=0){
                   long a=Long.parseLong(number[0].substring(Math.max(0,number[0].length()-18)));
                   String c=""+(a+carry);
                   if(c.length()==19){
                        carry=1;
                        answer=c.substring(1)+answer;
                        number[0]=number[0].substring(0,Math.max(0,number[0].length()-18));
                    }
                   if(carry==0){
                       answer=number[0]+answer;
                   }
                }
                
                while(!number[1].isEmpty()&&carry!=0){
                   long a=Long.parseLong(number[1].substring(Math.max(0,number[1].length()-18)));
                   String c=""+(a+carry);
                   if(c.length()==19){
                        carry=1;
                        answer=c.substring(1)+answer;
                        number[1]=number[1].substring(0,Math.max(0,number[1].length()-18));
                    }
                   if(carry==0){
                       answer=number[1]+answer;
                   }
                   
                }
                int pos=0;
                while(answer.charAt(pos)=='0'){
                    if(pos<answer.length()-1)
                    pos++;
                    else{
                        break;
                    }
                }
                answer=answer.substring(pos);
                
                if(n0)
                System.out.println("-"+answer);
                else{
                    System.out.println(answer);
                }
            }
            if(subtra){                             //---------------------------
                
                boolean n0islarger=false;
                if(number[0].length()>number[1].length()){
                    n0islarger=true;
                }
                else if(number[0].length()<number[1].length()){
                    String temp=number[0];
                    number[0]=number[1];
                    number[1]=temp;
                    boolean temp2=n0;
                    n0=n1;
                    n1=temp2;
                }
                else if(number[0].compareTo(number[1])>0){//number[0]islarger
                    n0islarger=true;
                }
                else if(number[0].compareTo(number[1])<0){
                    String temp=number[0];
                    number[0]=number[1];
                    number[1]=temp;
                    boolean temp2=n0;
                    n0=n1;
                    n1=temp2;
                }else{
                    System.out.println(0);
                    continue;
                }
                String answer="";
                int carry=0;
                while(!number[0].isEmpty()&&!number[1].isEmpty()){
                    
                    long a=Long.parseLong((number[0].substring(Math.max(0,number[0].length()-17))));
                    long temp=(long) Math.pow(10, 17);
                    a+=temp;
                    long b=Long.parseLong(number[1].substring(Math.max(0,number[1].length()-17)));
                    String c=""+(a-b-carry);
                    
                    if(c.length()==18){
                        carry=0;
                        answer=c.substring(1)+answer;
                        number[0]=number[0].substring(0,Math.max(0,number[0].length()-17));
                        number[1]=number[1].substring(0,Math.max(0,number[1].length()-17));
                    }
                    else{
                        c="000000000000000000000"+c;
                        carry=1;
                        answer=c.substring(c.length()-17)+answer;
                        number[0]=number[0].substring(0,Math.max(0,number[0].length()-17));
                        number[1]=number[1].substring(0,Math.max(0,number[1].length()-17));
                    }
                    
                    
                }
                if(carry==0){
                    answer=number[0]+number[1]+answer;
                }
                while(!number[0].isEmpty()&&carry!=0){
                   long a=Long.parseLong(number[0].substring(Math.max(0,number[0].length()-17)));
                   long temp=(long) Math.pow(10, 17);
                    a+=temp;
                   String c=""+(a-carry);
                   if(c.length()==18){
                        carry=0;
                        answer=c.substring(1)+answer;
                        number[0]=number[0].substring(0,Math.max(0,number[0].length()-17));
                    }
                    else{
                        c="000000000000000000000"+c;
                        carry=1;
                        answer=c.substring(c.length()-17)+answer;
                        number[0]=number[0].substring(0,Math.max(0,number[0].length()-17));
                    }
                   if(carry==0){
                       answer=number[0]+answer;
                   }
                }
                
                
                int pos=0;
                while(answer.charAt(pos)=='0'){
                    if(pos<answer.length()-1)
                    pos++;
                    else{
                        break;
                    }
                }
                answer=answer.substring(pos);
                if(n0)
                System.out.println("-"+answer);
                else{
                    System.out.println(answer);
                }
                
                
            }
            
            
        }
    }
}