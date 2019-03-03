/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barambe;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class Barambe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String word[]=input.nextLine().split(" ");
        System.out.print(word[0]);
        for(int i=1;i<word.length;i++){
            if(!word[i].toLowerCase().equals(word[i])){
                System.out.print(". "+word[i]);
            }
            else{
                System.out.print(" "+word[i]);
            }
        }
        System.out.println(".");
    }
    
}
