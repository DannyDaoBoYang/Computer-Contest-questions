/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruno.and.fibonacci;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class BrunoAndFibonacci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int N=input.nextInt();
        String DNA=input.next();
        boolean feb[]=new boolean[N*3+1];
        feb[1]=true;
        feb[2]=true;
        int feb1=1;
        int feb2=2;
        while(feb2<N+1){
            
            int feb3=feb1+feb2;
            System.out.println(feb3);
            feb1=feb2;
            feb2=feb3;
            feb[feb2]=true;
        }
        boolean answer=true;
        for(int i=1;i<=N;i++){
            if(DNA.charAt(i-1)=='A'&&!feb[i]){
                answer=false;
                break;
            }
            else if(feb[i]&&DNA.charAt(i-1)!='A'){
                answer=false;
                break;
            }
        }
        if(answer){
            System.out.println("That's quite the observation!");
        }
        else{
            System.out.println("Bruno, GO TO SLEEP");
    }
    
}
}
