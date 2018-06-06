/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beer;

/**
 *
 * @author dannyyang
 */
public class Beer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for(int a=100; a>=1; a--){
         System.out.println(a+" bottles of beer on the wall"+"\n"+
       a+" bottles of beer"+"\n"+
      "If one of those bottles should happen to fall"+"\n"
       +(a-1)+" bottles of beer on the wall"+"\n");
        }
    }
    
}
