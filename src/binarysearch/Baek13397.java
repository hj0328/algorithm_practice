package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구간 나누기2
 * gold4
 * https://www.acmicpc.net/problem/13397
 *
 * 구간점수 최대의 최소를 구하기.
 * 1회 틀렸습니다, 구체적이지 않은 탐색으로 로직 오류
 * 1회 시간초과, 최대 값 범위를 10000이 아니라 1000을 넣어 시간초과
 *
 */
public class Baek13397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());   // 최소 M 개의 구간으로 구성필요

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;      // '구간점수 최대' 최소
        int e = 10000;  // '구간점수 최대' 최대
        while (s <= e) {
            int maxScore = (s + e) / 2;  // max score
            int groupCnt = 1;
            int min = 10000, max = 0;

            for(int i = 0 ; i < N ; i++) {  // 구간점수가 maxScore인 group의 개수구하기
                if (arr[i] < min)  min = arr[i];
                if (arr[i] > max) max = arr[i];

                if(maxScore < max - min) {
                    min = 10000;
                    max = 0;
                    i--;
                    groupCnt++;
                }
            }

            if(groupCnt > M) {
                // group 개수 감소 -> mid 감소
                s = maxScore + 1;
            } else {
                e = maxScore - 1;
            }
        }

        System.out.println(s);
    }
}
