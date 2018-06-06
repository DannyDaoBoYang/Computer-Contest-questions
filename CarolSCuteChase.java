/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carol.s.cute.chase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author dannyyang
 */
public class CarolSCuteChase {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> path[];
    static ArrayList<Integer> check[];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String stock[] = input.readLine().split(" ");
        int N = Integer.parseInt(stock[0]);
        int M = Integer.parseInt(stock[1]);
        path = new ArrayList[N + 1];
        check = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            path[i] = new ArrayList<Integer>();
            check[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            String stock2[] = input.readLine().split(" ");
            int a = Integer.parseInt(stock2[0]);
            int b = Integer.parseInt(stock2[1]);
            path[a].add(b);
            check[a].add(i);
        }
        for (int i = 0; i < M; i++) {
            Queue<Integer> task = new LinkedList();
            boolean went[] = new boolean[N + 1];
            task.add(1);
            boolean find = false;
            while (!task.isEmpty()) {
                int temp = task.poll();
                if (went[temp]) {
                    continue;
                }
                if (temp == N) {
                    System.out.println("YES");
                    find = true;
                    break;
                }
                went[temp] = true;
                for (int j = 0; j < path[temp].size(); j++) {
                    if (check[temp].get(j) != i) {
                        task.add(path[temp].get(j));
                    }
                }
            }
            if (find) {

            } else {
                System.out.println("NO");
            }
        }
    }

}
