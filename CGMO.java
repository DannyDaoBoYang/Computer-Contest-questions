/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgmo;

/**
 *
 * @author dannyyang
 */
public class CGMO {

    /**
     * @param args the command line arguments
     */
    static int total=0;
    public static void count(int current, int digit,int value){
        
        if(digit==11){
            if(current==2004){
                total+=value;
            }
            else{
                return;
            }
        }
        else{
            if(digit==1){
               for(int i=0;i<=4;i++){
                   if(i==2)
                count((int) (current+Math.pow(2, digit)*i),digit+1,value*6);
                   else{
                count((int) (current+Math.pow(2, digit)*i),digit+1,value);    
                   }
            } 
            }
            else{
            for(int i=0;i<=3;i++){
                if(i==1||i==2){
                count((int) (current+Math.pow(2, digit)*i),digit+1,value*3);
                }
                else
                    count((int) (current+Math.pow(2, digit)*i),digit+1,value);
            }
            }
            
        }
        
    }
    public static void main(String[] args) {
        
        count(0,1,1);
        System.out.println(Math.sqrt(total));
        
    }
    
}
