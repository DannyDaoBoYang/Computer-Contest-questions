/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inaho.vi;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class InahoVI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        long x=input.nextLong();
        float a=1;
        float b=0;
        for(int loop=0;loop<x;loop++){
            float tempa=(float) (Math.pow(Math.E, -Math.PI*b/2)*Math.cos(Math.PI*a/2));
            float tempb=(float) (Math.pow(Math.E, -Math.PI*b/2)*Math.sin(Math.PI*a/2));
            a=tempa;
            b=tempb;
        }
        System.out.println(a);
        System.out.println(b);
    }
    
}
