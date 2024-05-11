package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 나무 수
        N = Integer.parseInt(st.nextToken());

        // 자른 나무 길이 합
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine());

        int max = 0;
        for(int i = 0 ; i < N ; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (max < trees[i]) {
                max = trees[i];
            }
        }

        find(max);
    }
    static int N, M;
    static int[] trees;
    static void find(int max) {

        int min = 0;

        while(min < max) {
            int cut = (min + max) / 2;

            // 총 길이 계산
            long cutLen = getCutLen(cut);
            if (cutLen >= M) {
                // 총 길이 M, 적게 잘라?
                // 총 길이 M보다 큼, 더 적게 잘라
                min = cut + 1;
            } else {
                // 총 길이 < M, 더 잘라야 함
                max = cut;
            }
        }

        // 처음 M 보다 작게 자를 때 cut len
        // cut - 1 처리 시 M 자르는 cut len 중 최대 길이
        System.out.println(min-1);
    }

    static long getCutLen(int cut) {
        long cutLen = 0;
        for(int i = 0 ; i < N ; i++) {
            int v = trees[i] - cut;
            if(v <= 0) {
                continue;
            }

            cutLen += v;
        }

        return cutLen;
    }
}
