package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나무 자르기
 * lower bound
 * https://www.acmicpc.net/problem/2805
 *
 */
public class Baek2805_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long s = 1;
        long e = 0;
        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            e += trees[i];
        }


        while(s <= e) {
            long mid = (s + e) / 2;

            long cuts = 0;
            for(int i = 0 ; i < N ; i++) {
                if(mid < trees[i]) cuts += trees[i] - mid;
            }

            if(cuts >= M) {
                s = mid + 1;
            } else {    // 더 잘라야 함 -> mid 줄이기
                e = mid - 1;
            }
        }

//        System.out.println(s + ", " + e);
        System.out.println(e);
    }
}
