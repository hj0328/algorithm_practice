package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    분수찾기, 실버5
    https://www.acmicpc.net/problem/1193

    분류: 등차수열
    1제출 정답
 */
public class Baek1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());

        int k = 0;
        int n = 0;
        for (; n <= 10000000; n++) {
            k = 1 + n * (n-1) / 2;
            if (k > x) {
                break;
            }
        }
        n--;
        k = 1 + n * (n-1) / 2;
        int p = 0;
        int c = 0;
        if (n % 2 == 0) {
            p = 1;
            c = n;
        } else {
            p = n;
            c = 1;
        }

        while (k != x) {
            k++;
            if (n % 2 == 0) {
                p++;
                c--;
            } else {
                p--;
                c++;
            }
        }

        System.out.println(p + "/" + c);
    }
}
