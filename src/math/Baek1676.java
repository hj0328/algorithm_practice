package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
       팩토리얼 0의 개수
 */
public class Baek1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        long val = 1;
        for (int i = 1; i <= N; i++) {
            val = val * i;
            while (val % 10 == 0) {
                count++;
                val = val / 10;
            }

            val = val % 1000000000;
        }

        System.out.println(count);
    }
}
