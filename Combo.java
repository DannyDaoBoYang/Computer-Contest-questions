/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combo;

/**
 *
 * @author dannyyang
 */
public class Combo {

    /**
     * @param args the command line arguments
     */
    public static String guess_sequence(int N){
    String use="ABXY";
    String answer="";
   
    String check1=0;
    String check2=0;
    String left=0;
    int case1=press("AB");
    if(case1!=0){
        if(press("A")==1){
            
            answer="A";
            check1="B";
            check2="C";
            left="D";
        }
        else{
            
            answer="B";
            check1="A";
            check2="C";
            left="D";
        }
    }
    else{
    if(press("X")==1){
        
        answer="C";
        check1="A";
            check2="B";
            left="D";
    }
    else{
        
        answer="D";
        check1="A";
            check2="B";
            left="C";
    }
    }
    for(int i=2;i<N;i++){
        string current=answer+check1+answer+check2+check1+answer+check2+check2+answer+check2+left;
        int temp=press(current);
        if(temp==answer.length()){
            answer=answer+left;
        }
        else if(temp==answer.length()+1){
            answer=answer+check1;
        }
        else{
            answer=answer+check2;
        }
    }
    int temp1=press(answer+check1);
    int temp2=press(answer+check2);
    if(temp1==N){
        answer=answer+check1;
    }
    else if(temp2==N){
        answer=answer+check2;
    }
    else{
        answer=answer+left;
    }
    return answer;
}
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
