/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.integer;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class BigInteger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        String line=input.next();
        long a=0;
        long total=0;
        String [] number={"0","1","2","3","4","5","6","7","8","9"};
        for(int i=0;i<N;i++){
            String here=line.substring(i,i+1);
            if(here.equals("-")){
            a=a/10;
            }
            else{
                a=a*10+Integer.parseInt(here);
            }
            total+=a;
        }
        System.out.println(total);
    }
    
}
