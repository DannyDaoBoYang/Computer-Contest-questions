/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicographically.least.substring;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dannyyang
 */
public class LexicographicallyLeastSubstring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String word = input.next();
        int K = input.nextInt();

        int letter = word.charAt(0);
        ArrayList<Integer> start = new ArrayList<Integer>();
        start.add(0);
        for (int i = 1; i < word.length() - K + 1; i++) {
            if (word.charAt(i) < letter) {
                start.clear();
                letter = word.charAt(i);
                start.add(i);
            } else if (word.charAt(i) == letter) {
                start.add(i);
            }
        }

        long mod = 100000007;
        long haxvalue[] = new long[word.length() + 1];
        long power[]=new long[word.length()+1];
        int seed = 131;
        power[0]=1;
        haxvalue[0] = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            haxvalue[i] = haxvalue[i - 1] * seed + word.charAt(i);
            power[i]=power[i-1]*seed;
          //  System.out.println(haxvalue[i]);
        }

        int best = start.get(0);
        for (int i = 1; i < start.size(); i++) {
            int current = start.get(i);
            int low=0;
            int high=K-1;
            while (low < high) {
                int middle=(low+high)/2;
                long vala = haxvalue[current+middle] - haxvalue[current] * power[middle];
                long valb = haxvalue[best+middle] - haxvalue[best] * power[middle];
                
                if (vala == valb) {
                    low = (int)middle + 1;
                   
                } else {
                    high = (int)middle;
                    
                }
            }
           // System.out.println(word.charAt(current+low)+" "+word.charAt(best+low));
            if (word.charAt(current+low) < word.charAt(best+low)) {
                best = current;
            }
            //  System.out.println("Next");
        }
        for (int i = best; i < best+ K; i++) {
            System.out.print(word.charAt(i));
        }
        String a=0;
       
    }

}