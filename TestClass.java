/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclass;

//import for Scanner and other utility classes
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
*/
        Scanner input = new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++){
            String type=input.next();
            
            char letter[][]={{'.',',','?','!','1'},{'a','b','c','2'},{'d','e','f','3'},{'g','h','i','4'},{'j','k','l','5'},{'m','n','o','6'},{'p','q','r','s','7'},{'t','u','v','8'},{'w','x','y','z','9'},{'_','0'}};
            int x=1;
            int y=1;
            int total=0;
            for(int i=0;i<type.length();i++){
                char temp=type.charAt(i);
                int index=-1;
                int inside=-1;
                for(int j=0;j<letter.length;j++){
                    for(int k=0;k<letter[j].length;k++){
                        if(temp==letter[j][k]){
                            index=j;
                            inside=k;
                            break;
                        }
                    }
                }
                index+=1;
                int cx=0;
                int cy=0;
                if(index==1){
                    cx=1;
                    cy=1;
                }
                if(index==2){
                    cx=2;
                    cy=1;
                }
                if(index==3){
                    cx=3;
                    cy=1;
                }
                if(index==4){
                    cx=1;
                    cy=2;
                }
                if(index==5){
                    cx=2;
                    cy=2;
                }
                if(index==6){
                    cx=3;
                    cy=2;
                }
                if(index==7){
                    cx=1;
                    cy=3;
                }
                if(index==8){
                    cx=2;
                    cy=3;
                }if(index==9){
                    cx=3;
                    cy=3;
                }
                if(index==10){
                    cx=2;
                    cy=4;
                }
                total+=Math.abs(cx-x)+Math.abs(cy-y);
                x=cx;
                y=cy;
                total+=inside+1;
            
                
            }
            System.out.println(total);
            
        }

        // Write your code here

    }
}


