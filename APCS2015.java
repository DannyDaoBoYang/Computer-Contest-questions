/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apcs2015;

/**
 *
 * @author dannyyang
 */
public class APCS2015 {
    
    public static int arraysum(int [] arr){
        int a=0;
        for(int i=0;i<arr.length;i++){
            a=a+arr[i];
        }
        return a;
    }

    /**
     * @param args the command line arguments
     */
    public static int[] rowsum(int [][] arr){
        int [] sum = new int [arr.length];
        
        for(int i=0;i<arr.length;i++){
            sum[i]= arraysum(arr[i]);
        }
        return sum;
        
    }
    public static boolean isDiverse(int [][] arr){
        int [] sum = rowsum(arr);
        int count =0;
        
        for(int i=0;i<sum.length-1;i++){
        for(int x=i+1;x<sum.length;x++){
            if(sum[i]==sum[x]){
                return false;
            }
        }
        }
        return true;
    }

    public static String gethint(String Original,String Guess){
       //S1 is the example 
       //S2 is one of the "AAAAA"
        String hint="";
        for(int i=0;i<Original.length();i++){

        if(Original.charAt(i)==Guess.charAt(i)){
            hint=hint+"*";
        }
        if(Original.charAt(i)!=Guess.charAt(i)){
            hint=hint+Original.charAt(i);
        }
        else if (Original.indexOf(Guess.charAt(i))>=0){
            hint=hint+"+";
        }
         }
        
        return hint;
    }
    public static void main(String[] args) {
        int [][] m1 ={{1,2,3,4},{5,6,7,8},{9,10,11,12},{1,2,3,4}};
        int [] a= {1,2,3,4,5,6,7,8};
        isDiverse(m1);
         System.out.println(isDiverse(m1));

    }
    
}
