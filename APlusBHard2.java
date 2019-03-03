/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.plus.b.hard.pkg2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author dannyyang
 */
public class APlusBHard2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        for (int test = 0; test < N; test++) {
            String number[] = input.readLine().split(" ");

            boolean n0 = (number[0].charAt(0) == '-');
            boolean n1 = (number[1].charAt(0) == '-');
            boolean subtra = n0 ^ n1;
            if (n0) {
                number[0] = number[0].substring(1);
            }
            if (n1) {
                number[1] = number[1].substring(1);
            }
            if (!subtra) {
                int a = number[0].length();
                int b = number[1].length();
                boolean whoa = false;
                boolean same = true;
                if (a > b) {
                    whoa = true;
                    same = false;
                } else if (b > a) {
                    same = false;
                }

                String answer = "";
                int extra = 0;
                int insteadmin = Math.min(a, b);
                for (int i = 1; i <= insteadmin; i++) {
                    int add = Integer.parseInt(number[0].substring(a - i, a - i + 1)) + Integer.parseInt(number[1].substring(b - i, b - i + 1)) + extra;
                    extra = add / 10;
                    answer = add % 10 + answer;
                }
                int index = insteadmin + 1;
                if (extra != 0) {
                    if (same) {
                        answer = extra + answer;

                    } else {
                        while (whoa && extra != 0) {
                            if (index <= a) {
                                int add = extra + Integer.parseInt(number[0].substring(a - index, a - index + 1));
                                extra = add / 10;
                                answer = add % 10 + answer;
                                index++;
                            } else {
                                answer = extra + answer;

                                break;
                            }
                        }
                        while (!whoa && extra != 0) {
                            if (index <= b) {
                                int add = extra + Integer.parseInt(number[1].substring(b - index, b - index + 1));
                                extra = add / 10;
                                answer = add % 10 + answer;
                                index++;
                            } else {
                                answer = extra + answer;

                                break;
                            }
                        }
                    }
                }
                if (whoa) {
                    answer = number[0].substring(0, a - index + 1) + answer;
                }
                if (!whoa) {
                    answer = number[1].substring(0, b - index + 1) + answer;
                }
                if (n0) {
                    System.out.println("-" + answer);
                } else {
                    System.out.println(answer);
                }
            } else {               //the subtract part
                boolean same = true;
                boolean great0 = false;
                int a = number[0].length();
                int b = number[1].length();
                if (a > b) {
                    great0 = true;
                    same = false;
                } else if (b > a) {
                    great0 = false;
                    same = false;
                }

                if (same) {
                    if (number[0].equals(number[1])) {
                        System.out.println("0");
                    } else {
                        int index = 0;
                        while (number[0].charAt(index) == number[1].charAt(index)) {
                            index++;
                        }
                        number[0] = number[0].substring(index);
                        number[1] = number[1].substring(index);
                        a = number[0].length();
                        b = number[0].length();
                        great0 = (Integer.parseInt(number[0].substring(0, 1)) > Integer.parseInt(number[1].substring(0, 1)));
                        if (great0) {
                            String answer = "";
                            int extra = 0;
                            int insteadmin = Math.min(a, b);
                            for (int i = 1; i <= insteadmin; i++) {
                                int add = Integer.parseInt(number[0].substring(a - i, a - i + 1)) - Integer.parseInt(number[1].substring(b - i, b - i + 1)) - extra;
                                if (add < 0 && add != -10) {
                                    extra = 1;
                                    answer = (10 - Math.abs(add % 10)) + answer;
                                } else if (add == -10) {
                                    extra = 1;
                                    answer = 0 + answer;
                                } else {
                                    extra = 0;
                                    answer = add + answer;
                                }

                            }

                            short move = 0;
                            while (answer.charAt(move) == '0') {
                                move++;
                            }
                            answer = answer.substring(move);
                            if (n0) {
                                System.out.println("-" + answer);
                            } else {
                                System.out.println(answer);
                            }
                        } else {
                            String answer = "";
                            int extra = 0;
                            int insteadmin = Math.min(a, b);
                            for (int i = 1; i <= insteadmin; i++) {
                                int add = Integer.parseInt(number[1].substring(a - i, a - i + 1)) - Integer.parseInt(number[0].substring(b - i, b - i + 1)) - extra;
                                if (add < 0 && add != -10) {
                                    extra = 1;
                                    answer = (10 - Math.abs(add % 10)) + answer;
                                } else if (add == -10) {
                                    extra = 1;
                                    answer = 0 + answer;
                                } else {
                                    extra = 0;
                                    answer = add + answer;
                                }
                            }

                            short move = 0;
                            while (answer.charAt(move) == '0') {
                                move++;

                            }
                            answer = answer.substring(move);
                            if (n1) {
                                System.out.println("-" + answer);
                            } else {
                                System.out.println(answer);
                            }
                        }
                    }
                } else {// not same number of 

                    if (great0) {

                        String answer = "";
                        int extra = 0;
                        int insteadmin = Math.min(a, b);
                        for (int i = 1; i <= insteadmin; i++) {
                            int add = Integer.parseInt(number[0].substring(a - i, a - i + 1)) - Integer.parseInt(number[1].substring(b - i, b - i + 1)) - extra;
                            if (add < 0 && add != -10) {
                                extra = 1;
                                answer = (10 - Math.abs(add % 10)) + answer;
                            } else if (add == -10) {
                                extra = 1;
                                answer = 0 + answer;
                            } else {
                                extra = 0;
                                answer = add + answer;
                            }

                        }
                        int index = insteadmin + 1;
                        if (extra != 0) {

                            while (extra != 0) {
                                if (index <= a) {
                                    int add = Integer.parseInt(number[0].substring(a - index, a - index + 1)) - extra;
                                    if (add >= 0) {
                                        answer = add + answer;
                                        index++;
                                        break;
                                    } else {
                                        answer = (10+add) + answer;

                                    }
                                    index++;
                                }

                            }

                        }
                        if (index <= a + 1) {
                            answer = number[0].substring(0, a - index + 1) + answer;
                        }
                        short move = 0;
                        while (answer.charAt(move) == '0') {
                            move++;
                        }

                        answer = answer.substring(move);
                        if (n0) {
                            System.out.println("-" + answer);
                        } else {
                            System.out.println(answer);
                        }
                    } else {// number[1] is greater
                        String answer = "";
                        int extra = 0;
                        int insteadmin = Math.min(a, b);
                        for (int i = 1; i <= insteadmin; i++) {
                            int add = Integer.parseInt(number[1].substring(b - i, b - i + 1)) - Integer.parseInt(number[0].substring(a - i, a - i + 1)) - extra;
                            if (add < 0 && add != -10) {
                                extra = 1;
                                answer = (10 - Math.abs(add % 10)) + answer;
                            } else if (add == -10) {
                                extra = 1;
                                answer = 0 + answer;
                            } else {
                                extra = 0;
                                answer = add + answer;
                            }

                        }
                        int index = insteadmin + 1;
                        if (extra != 0) {

                            while (extra != 0) {
                                if (index <= b) {
                                    int add = Integer.parseInt(number[1].substring(b - index, b - index + 1)) - extra;
                                    if (add >= 0) {
                                        answer = add + answer;
                                        index++;
                                        break;
                                    } else {
                                        answer = (10+add) + answer;
                                    }
                                    index++;
                                }

                            }

                        }
                        if (index <= b) {
                            answer = number[1].substring(0, b - index) + answer;
                        }
                        short move = 0;
                        while (answer.charAt(move) == '0') {
                            move++;
                        }

                        answer = answer.substring(move);
                        if (n1) {
                            System.out.println("-" + answer);
                        } else {
                            System.out.println(answer);
                        }
                    }
                }
            }
        }
    }
}
