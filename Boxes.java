/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxes;
import java.util.Scanner;
import java.util.*;
/**
 *
 * @author dannyyang
 */
public class Boxes {
public static int box(int [] a, int [] b,int []c,int w){
    return a[w]*b[w]*c[w];
}
public static int[] thelength(int [] a, int [] b,int []c,int w){
    int []d=new int [3];
    d[0]=a[w];
    d[1]=b[w];
    d[2]=c[w];
    int change;
    for(int i=0;i<2;i++)
    while(d[i]>d[i+1]){
        change=d[i];
        d[i]=d[i+1];
        d[i+1]=change;
        i=0;
    }
    return d;
}
public static boolean check(int []b,int[] m){
    
    boolean change=true;
    for(int i=0;i<3;i++){
        if(b[i]<m[i]){
            change=false;
        }
    }
    return change;
}

    /**
     *
     * @param v
     * @return
     */
    public static int vect(Vector v){
    int a=(int) v.firstElement();
    
    for(int i=0;i<v.size();i++){
        if(a>(int)v.get(i)){
            a=i;
        }
    }
    return a;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        Vector v=new Vector();
        
        int b=input.nextInt();
        int []bl =new int[b];
        int []bw =new int[b];
        int []bh =new int[b];
        
        for(int i=0;i<b;i++){
            bl[i]=input.nextInt();
            bw[i]=input.nextInt();
            bh[i]=input.nextInt();
        }
        
        int m=input.nextInt();
        int []ml =new int[m];
        int []mw =new int[m];
        int []mh =new int[m];
        for(int i=0;i<m;i++){
            ml[i]=input.nextInt();
            mw[i]=input.nextInt();
            mh[i]=input.nextInt();
        }
        
        int count=0;
        //start
        for(int i=0;i<m;i++){
            v = new Vector();
        for(int x=0;x<b;x++){
            count=0;
        int [] boxlength=thelength(bl,bw,bh,x);
        int [] matterlength=thelength(ml,mw,mh,i);
        for(int h=0;h<3;h++){
            if(boxlength[h]>=matterlength[h]){
                count++;
            }
            if(count==3){
                v.addElement(box(bl,bw,bh,x));
            }
        }
        }
        if(v.size()>0){
            int a=vect(v);
        System.out.println(a);
        }
        if( v.isEmpty()){
            System.out.println("Item does not fit.");
        }
        }
       
        
        
    }
    
}
