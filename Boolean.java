/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgboolean;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Boolean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String in[]=input.nextLine().split(" ");
        boolean answer=true;
        for(int i=0;i<in.length-1;i++){
            answer=answer^true;
        }
        if(in[in.length-1].equals("True")){
            if(answer){
                System.out.println("True");
            }
            else{
                System.out.println("False");
            }
        }
        else{
            if(!answer){
                System.out.println("True");
            }
            else{
                System.out.println("False");
            }
        }
    }
    
}
