/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apples.to.oranges;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dannyyang
 */
public class ApplesToOranges {

    static String names[];
    static ArrayList<Double> rates[];
    static ArrayList<Integer> index[];
    static int aindex;
    static boolean reach[];
    static boolean cycle = false;

    public static boolean BFS(int current, boolean went[]) {
        if (current == aindex) {
            return true;
        }
        if (reach[current]) {
            return true;
        }
        if (went[current]) {
            return false;
        }
        went[current] = true;
        for (int i = 0; i < index[current].size(); i++) {
            boolean temp = BFS(index[current].get(i), went);
            if (temp) {
                reach[current] = true;
                break;
            }
        }
        went[current] = false;
        return reach[current];

    }

    public static boolean exchange(int current, double have, boolean went[], double last[]) {// there's cycle
        if (went[current]) {
            if (current == aindex) {
                if (have > 1) {
                    System.out.println("YA");
                    System.exit(0);
                } else {
                    return true;
                }
            } else {
                if (have > last[current]) {
                    cycle = true;
                }
                return false;
            }
        }
        last[current] = have;
        went[current] = true;
        boolean local = false;
        
        for (int i = 0; i < index[current].size(); i++) {
            cycle = false;
            boolean temp = exchange(index[current].get(i), have * rates[current].get(i), went, last);
            if (cycle) {
                local = true;
            }
            if (temp) {
                reach = true;
            }
        }
        if (local && reach) {
            System.out.println("YA");
            System.exit(0);
        }
        went[current] = false;
        return reach;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String dadi[] = input.readLine().split(" ");
        int N = Integer.parseInt(dadi[0]);
        int M = Integer.parseInt(dadi[1]);
        names = new String[N];
        rates = new ArrayList[N];
        index = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            rates[i] = new ArrayList<Double>();
            index[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N; i++) {
            names[i] = input.readLine();
        }
        Arrays.sort(names);

        for (int i = 0; i < M; i++) {
            String ex[] = input.readLine().split(" ");
            int f1 = Arrays.binarySearch(names, ex[0]);
            int f2 = Arrays.binarySearch(names, ex[1]);
            rates[f1].add(Double.parseDouble(ex[2]));
            index[f1].add(f2);
        }
        //aindex=Arrays.binarySearch(names,"APPLES");
        aindex = Arrays.binarySearch(names, "A");
        double last[] = new double[N];
        boolean went[] = new boolean[N];
        reach = new boolean[N];
        for (int i = 0; i < index[aindex].size(); i++) {
            boolean temp = BFS(aindex, went);
            if (temp) {
                reach[aindex]=true;
            }
        }
        went = new boolean[N];
        exchange(aindex, 1, went, last);
        System.out.println("NAW");

    }
}
