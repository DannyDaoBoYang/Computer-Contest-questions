/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowsalexander;
import java.util.*;
import java.io.*;
/**
 *
 * @author Alexander Ma
 */
public class Cowsalexander {

    /**
     * @param args the command line arguments
     */
    public static double CCW (Points one, Points two, Points three){
        return (two.x-one.x)*(three.y-one.y) - (two.y-one.y)*(three.x-one.x);
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int points = Integer.parseInt(input.readLine());
        Points [] arr = new Points[points];
        for (int i = 0;i<points;i++){
            String [] tokens = input.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            arr[i] = (new Points(x, y));
        }
        Arrays.sort(arr);
        ArrayList <Points2> left = new ArrayList();
        double x1 = arr[0].x;
        double y1 = arr[0].y;
        for (int i = 1;i<points;i++){
            if (y1==arr[i].y){
                left.add(new Points2(arr[i].x, arr[i].y, -999999999));
            }
            else {
                double angle = -(x1-(double)arr[i].x)/(y1-(double)arr[i].y);
                left.add(new Points2(arr[i].x, arr[i].y, angle));
            }
        }
        Collections.sort(left);
        Stack <Points> s = new Stack();
        s.push(new Points(arr[0].x, arr[0].y));
        s.push(new Points(left.get(0).x, left.get(0).y));
        s.push(new Points(left.get(1).x, left.get(1).y));
        for (int i = 2;i<left.size();i++){
            Points one, two, three;
            
            two = s.peek();
            s.pop();
            one = s.peek();
            s.push(two);
            three = new Points(left.get(i).x, left.get(i).y);
            if (CCW(one, two, three)>0){
                s.push(three);
            }
            else if(s.size()>2){
                s.pop();
                i--;
            }
        }
        Points [] final2 = new Points[s.size()];
        int count = 0;
        while (!s.empty()){
            Points s2 = s.pop();
            final2[count] = s2;
            count++;
        }
        int a = 0;
        int b = 0;
        for (int i = 0;i<final2.length;i++){
            if (i==final2.length-1){
                a += final2[i].x*final2[0].y;
                b += final2[i].y*final2[0].x;
            }
            else {
                a += final2[i].x*final2[i+1].y;
                b += final2[i].y*final2[i+1].x;
            }
        }
        
        double area = 0.5 * Math.abs(a - b);
        System.out.println((int)(area)/50);
    }
    
    static class Points implements Comparable<Points>{
        double x;
        double y;
        Points(double x, double y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Points n){
            if (n.y<y){
                return 1;
            }
            else if (n.y==y){
                if (n.x<x){
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else {
                return -1;
            }
        }
    }
    
    static class Points2 implements Comparable<Points2>{
        double x;
        double y;
        double angle;
        Points2(double x, double y, double angle){
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
        @Override
        public int compareTo(Points2 n){
            if (n.angle<angle){
                return 1;
            }
            return -1;
        }
    }
}
