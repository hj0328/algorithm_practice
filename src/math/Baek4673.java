package math;

import java.util.Arrays;

/*
    실버 5
    self number
    https://www.acmicpc.net/problem/4673


 */
public class Baek4673 {

    public static void main(String[] args) {

        boolean[] isSelf = new boolean[10001];
        Arrays.fill(isSelf, true);

        for (int i = 1; i <= 10000; i++) {
            if (isSelf[i]) {
                setSelf(i, isSelf);
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (isSelf[i]) {
                System.out.println(i);
            }
        }
    }

    private static void setSelf(int i, boolean[] isSelf) {
        if (i > 10000) {
            return;
        }

        int val = i;
        int add = 0;
        while (val >= 10) {
            add += val % 10;
            val = val / 10;
        }

        add += val + i;

        if (add <= 10000) {
            isSelf[add] = false;
        }

        setSelf(add, isSelf);
    }
}
