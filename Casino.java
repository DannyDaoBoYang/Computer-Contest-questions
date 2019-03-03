/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casino;

/**
 *
 * @author dannyyang
 */
public class Casino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double dices[]=new double[13];
        for(int i=1;i<=6;i++){
            for(int j=1;j<=6;j++){
                dices[i+j]+=(double)1/(double)36;
            }
        }
        double total=0;
        for(int i=1;i<=13;i++){
            for(int j=1;j<=12;j++){
                if(i%j==0||j%i==0){
                    total+=dices[j]/13;
                }
            }
        }
        System.out.println(total);
    }
    
}
