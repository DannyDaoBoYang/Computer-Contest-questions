/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.engineer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author dannyyang
 */
public class ComputerEngineer {

    static int letters[];
    static int best = 0;
    static String answer = "";
    static String dic[];

    public static boolean isletter(char a) {
        int checking = a;
        if ((checking >= 65 && checking <= 90) || (checking >= 97 && checking <= 122)) {
            return true;
        }
        return false;

    }

    public static int confirm(String current) {
        int have = Arrays.binarySearch(dic, current);
        if (have >= 0) {
            return 0;
        } else {
            for (int i = 0; i < current.length(); i++) {
                if (isletter(current.charAt(i))) {
                    return 1;
                }
            }
            return 2;
        }
    }
    public static boolean preview(int current){
        if(current==39){
            return false;
        }
        if (current > 126) {
            return true;
        }
        if(current<32){
            return true;
        }
        if(current>33&&current<43){
            return true;
        }
        if(current>48&&current<62){
            return true;
        }
        return false;
    }

    public static void trythis(int key[], int inuse) {
        //try the first 20 letters first
        if (inuse > 5) {
            return;
        }
        String current = "";
       int check=letters[inuse - 1] ^ key[inuse - 1];
       if(preview(check)){
           return;
       }
        
        boolean work = true;
        if(inuse==5)
        for (int i = 0; i < letters.length; i++) {
            check=(letters[i] ^ key[i % inuse]);
            if(preview(check)){
                work=false;
                break;
            }
            current = current + (char) (letters[i] ^ key[i % inuse]);
        }
        if(inuse==5)
        if (work) {
           // System.out.println("here");
            String single = "";
            int count = 0;
            int white=0;
            boolean having=false;
            for (int i = 0; i < letters.length; i++) {
                
                if (isletter(current.charAt(i))) {
                    single = single + current.charAt(i);
                    
                } else {
                    int record = confirm(single.toLowerCase());
                    
                    if (record == 0) {
                        count += single.length();
                    } else if (record == 1) {
                        count++;
                    } 
                    single="";  
                }
                if(current.charAt(i)==' '){
                    white++;
                    if(having){
                        white=0;
                    }
                    having=true;
                }
                else{
                    having=false;
                }
            }
            int record = confirm(single.toLowerCase());
            if (record == 0) {
                count += single.length();
            } else if (record == 1) {
                count++;
            }
            if(white>1)
            if (count > best) {
                best = count;
                answer = current;
                if(best==letters.length){
                    System.out.println(answer);
                    System.exit(0);
                }
            }
            
        }
        if (inuse < 5) {
            for (int i = 0; i <= 255; i++) {
                key[inuse] = i;
               // System.out.println(inuse);
                trythis(key, inuse + 1);
                
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Questions

        //Question 2: For the test file, is everything whinin the file in one single line.
        // Is the input in octo
        //Xor
        File file2 = new File("test.txt");
        BufferedReader input2 = new BufferedReader(new FileReader(file2));
        String text = "";
        while(input2.ready()){
          text=text+input2.readLine();
        }
        Scanner input3=new Scanner(System.in);
        Long.MAX_VALUE;
                
        

        letters = new int[text.length() / 8];
        for (int i = text.length() / 8 - 1; i >= 0; i--) {
            for (int power = 0; power < 8; power++) {
                letters[i] = letters[i] + (int) Math.pow(2, power) * Short.parseShort(text.substring(8 * (i + 1) - power - 1, 8 * (i + 1) - power));
                
            }
            
        }
        for(int i=0;i<letters.length;i++){
            System.out.print((char)(letters[i]));
        }
        System.out.println();

        File file = new File("ukenglish.txt");
        BufferedReader dictionary = new BufferedReader(new FileReader(file));
        dic = new String[82093];
        for (int i = 0; i < 82093; i++) {
            dic[i] = dictionary.readLine();
        }

        int key[] = new int[5];

        for (int i = 0; i <= 255; i++) {
            key[0] = i;
            //if((key[0]^letters[0])<91&&(key[0]^letters[0])>64){
            System.out.println(i);
            trythis(key, 1);
           // }
        }

        System.out.println(answer);
        System.out.println(best);

    }

}
