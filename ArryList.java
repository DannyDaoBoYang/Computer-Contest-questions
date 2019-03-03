/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arry.list;

/**
 *
 * @author dannyyang
 */
import java.util.ArrayList;
import java.util.Scanner;
public class ArryList {

    /**
     * @param args the command line arguments
     */
    public static int quadrant(double x, double y){
        if(x==0&&y==0)
            return 0;
        if(x>=0 && y>=0 )
            return 1;
        if(x<=0 && y>=0)
            return 2;
        if(x>=0 && y<=0)
            return 4;
        if(x<=0 && y<=0)
            return 3;
        return 3;
    
    }
    public static void main(String[] args) {
        Scanner input= new Scanner (System.in);
        int [] x=new int[100];
        ArrayList<Integer> num= new ArrayList<>();
        
        num.add(5);
        num.add(6);
        num.add(0,7);
        
    System.out.println(num.size());
    num.remove(0);
    num.add(5);
    num.set(0,100);
    for(int i=0;i<=10;i++){
        num.add(i);
    }

    
    System.out.println(num);
    
    }

    
    
}
