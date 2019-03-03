/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balance;

import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class Balance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String S = input.nextLine();
        int front = 0;
        int back = 0;
        boolean used=false;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                front++;
            } else if (S.charAt(i) == ')') {
                back++;
                if(used&&back-front>=2){
                    System.out.println("NO");
                    System.exit(0);
                    
                }
                else if(back -front >2){
                    System.out.println("NO");
                    System.exit(0);
                }
                else if (back - front == 2) {
                    used=true;
                    back--;
                    front++;
                }
                
            }
        }
        front=0;
        back=0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == '(') {
                front++;
                if(used&&front-back>=2){
                    System.out.println("NO");
                    System.exit(0);
                }
                else if(front -back >2){
                    System.out.println("NO");
                    System.exit(0);
                }
                else if (front - back == 2) {
                    front--;
                    back++;
                    used=true;
                }
            } else if (S.charAt(i) == ')') {
                back++;
                
            }
        }
        
        System.out.println("YES");
    }
}
