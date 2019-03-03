/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.bang.secrets;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class BigBangSecrets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int k=input.nextInt();
        char[] alpha={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String w=input.next();
        String answer="";
        for(int i=0;i<w.length();i++){
            int temp;
            for(int j=0;j<26;j++){
                if(w.charAt(i)==alpha[j]){
                    temp=j+1;
                    temp=temp-k-3*(i+1);
                    while(temp<1){
                        temp=temp+26;
                    }
                    
                    
                    
                    answer=answer+alpha[temp-1];
                }
            }
        }
        System.out.println(answer);
        
        
    }
    
}
