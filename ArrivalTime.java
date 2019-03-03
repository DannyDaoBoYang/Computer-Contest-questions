/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrival.time;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class ArrivalTime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String time=input.next();
        char[] convert={'0','1','2','3','4','5','6','7','8','9'};
        int hour=0,minute=0;
        for(int i=0;i<2;i++){
            for(int x=0;x<10;x++){
                if(i==0&&time.charAt(i)==convert[x]){
                    hour=hour+x*10;
                    break;
                }
                else if(i==1&&time.charAt(i)==convert[x]){
                    hour=hour+x;
                    break;
                }
            }
        }
        for(int i=3;i<4;i++){
            for(int x=0;x<10;x++){
                if(i==3&&time.charAt(i)==convert[x]){
                    minute=minute+x*10;
                    break;
                }
            }
        }
        int tt=hour*60+minute;
        int tt1=0;
        
        if(tt<=300){
            tt=tt+120;
        }
        else if(tt<390){
            tt1=420-tt;
            tt=420+(120-tt1)*2;
        }
        else if(tt<420){
            tt1=420-tt;
            tt=600+30-tt1;
        }
        else if(tt<600){
            tt1=(600-tt)/2;
            tt=600+120-tt1;
        }
        else if(tt<780){
            tt=tt+120;
        }
        else if(tt<900){
            tt1=900-tt;
            tt=900+(120-tt1)*2;
        }
        else if(tt<1140){
            tt1=(1140-tt)/2;
            tt=1140+120-tt1;
            
        }
        else if(tt>=1140){
            tt=tt+120;
        }
        hour=(tt/60)%24;
        minute=tt%60;
        String ohour="0"+hour;
        String ominute="0"+minute;
        System.out.println(ohour.substring(ohour.length()-2)+":"+ominute.substring(ominute.length()-2));

    }
    
}
