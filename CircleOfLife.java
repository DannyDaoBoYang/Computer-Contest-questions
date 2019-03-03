/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circle.of.life;
import java.util.Scanner;
/**
 *
 * @author dannyyang
 */
public class CircleOfLife {

    /**
     * @param args the command line arguments
     */
    public static long seg(long T){
        long a=1;
        for(int i=0;i<64;i++){
            if(a*2<T){
                a*=2;
            }
            else{
                break;
            }
        }
       // System.out.println(a);
        return a;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long N;
        long T;
        N=input.nextLong();
        T=input.nextLong();
        boolean[] cells=new boolean[(int)N+1];
        String stream=input.next();
        for(int i=1;i<=N;i++){
            if(stream.charAt(i-1)=='0'){
                cells[i]=false;
            }
            if(stream.charAt(i-1)=='1'){
                cells[i]=true;
            }
        }
        long turns=seg(T);
        while(T>0){
            T-=turns;
            turns%=N;
            boolean []cells2=new boolean[(int)N+1];
            for(int i=1;i<=turns;i++){
                cells2[i]=cells[(int)N-(int)turns+i];
            }
            for(int i=(int) (turns+1);i<=N;i++){
                cells2[i]=cells[i-(int)turns];
            }
            for(int i=(int) N;i>N-turns;i--){
                cells2[i]^=cells[(int)turns-((int)N-i)];
            }
            for(int i=(int) (N-turns);i>=1;i--){
                cells2[i]^=cells[i+(int)turns];
            }
            cells=cells2;
            turns=seg(T);
        }
        for(int i=1;i<=N;i++){
            if(cells[i])
            System.out.print(1);
            else{
                System.out.print(0);
            }
        }
        
        
    }
}         