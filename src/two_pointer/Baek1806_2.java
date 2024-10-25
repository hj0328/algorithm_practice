package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1806_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = -1;
        int e = 0;
        int sum = 0;
        int minLen = N;
        boolean flag = false;
        while (s <= e && e <= N) {
//            System.out.println(s + ", " + e + " sum " + sum);
            if (sum >= S) {
                if (minLen > e - s) {
                    minLen = e - s;
                }
                flag = true;
                if (s != -1) {
                    sum -= arr[s];
                }
                s++;
            } else {
                if (e < N) {
                    sum += arr[e];
                }
                e++;
            }
        }

//        if (sum >= S && minLen > e - s) {
//            minLen = e - s;
//            flag = true;
//        }

        if (flag) {
            System.out.println(minLen);
        } else {
            System.out.println(0);
        }
    }
}
