/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commputer.enigneer;

/**
 *
 * @author dannyyang
 */
public class CommputerEnigneer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //password NaoShua302*
        
        int m[]=new int[17];
        m[12]=1;
        m[13]=0;
        m[14]=5;
        m[15]=6;
        m[16]=0;
        //-256 255
        while(true){
        int acc=m[13];
        acc=acc+m[12];
        m[13]=acc;
        acc-=m[15];
        if(acc>0){
            break;
        }
        acc=m[16];
        acc+=m[14];
        m[16]=acc;
        
        } 
        while(m[16]>256){
            m[16]-=512;
        }
        System.out.println(m[16]);   
    }
    
}
