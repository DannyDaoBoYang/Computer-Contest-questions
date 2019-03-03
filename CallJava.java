/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package call.java;

import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class CallJava {

    
    
    public static double Minute(String d){
        String d1="am";
        String d2="pm";
        for(int i=0;i<d.length();i++){
        if(d.charAt(i)==d1.charAt(i)){
            return 0;
        }
        if(d.charAt(i)==d2.charAt(i)){
            return 720;
        }
        return 0;
    }
        return 0;
    }
    public static double hourtominute(int a, int b){
        return a*60+b;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the day of the week,you only have to enter two letters"+"\n"+
                "(First letter uppercase, seconde letter lowercase)");
        String a =input.next();
        System.out.println("Please enter the time that you start calling");
        int b= input.nextInt();
        int c= input.nextInt();
        String d= input.next();
        System.out.println("Pleace enter the length of the call in minutes");
        double e= input.nextInt();
        double f=0.0;
        double g=0.0;
        double k=0.0;
        f += hourtominute(b,c)+Minute(d)+e;
        g =f-1080;
        k +=hourtominute(b,c)+Minute(d)-1080;
        double h=0.0;
        int x=0;

        for(int i=0;i<a.length();i++){
            while(a.charAt(i)=="Mo".charAt(i)||a.charAt(i)=="Tu".charAt(i)||a.charAt(i)=="We".charAt(i)||a.charAt(i)=="Th".charAt(i)||a.charAt(i)=="Fr".charAt(i)){
            h++;
             
            if(g>=0&&h==2){
             System.out.println("$"+(0.5*(e-g)+ 0.4*g));
             break;
            }
            if(h==2&& -1080<=k&& k<=-600&& k+e<=-600){
                System.out.println(0.4*e);
                break;
            }
            if(h==2&& -1080<=k&& k<=-600&& k+e>-600){
                System.out.println("$"+0.4*(-600-k)+0.5*(e-(-600-k)));
                break;
            }
            if(h==2&&g<0&&g>=-600){
                System.out.println("$"+0.5*e);
                break;
            }
       
            break;
        } 
        
         while(a.charAt(i)=="Sa".charAt(i)||a.charAt(i)=="Su".charAt(i)){
           x++;
           double  a1=0.25;
           if(h==2){
           System.out.println("$"+a1*e);
           break;
        }
           break;
        }
        }
        
        
        
    }
    
}
