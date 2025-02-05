package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 놀이공원
 * https://www.acmicpc.net/problem/1561
 * 골드1
 *
 * 참고: https://baebalja.tistory.com/158
 */
public class Baek1561 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] rides = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            rides[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) {
            System.out.println(N);
            return;
        }

        // num명 이상 인원이 놀이기구 탈 때 시간
        long lt = 1;
        long rt = 60000000000L;
        while (lt <= rt) {
            long midTh = (lt + rt) / 2;

            // midTh 시간에 탈 수 있는 최대 인원
            long num = M;
            for (int i = 0; i < M; i++) {
                num += midTh / rides[i];
            }

            if (num >= N) {    // midTime번째에서의 입장인원 큼  -> 줄여야 함
                rt = midTh - 1;
            } else {
                lt = midTh + 1;
            }
        }

        // num명 이상이 놀이기구 탈때의 시간(lt) -> N번째 인원이 타게될 놀이기구 구하기
        // lt-1 시간일 때 인원구해서, 총 N명이 될때까지 타게되는 놀이기구 구하기

        // lt-1시간에 대한 인원
        // 시간 / 놀이기구 ==0 인 놀이기구
        // 인원이 n일때 마지막 탑승 번호는?
        long child = M; // M명이 타고 시작
        for (int i = 0; i < M; i++) {
            child += (lt - 1) / rides[i];
        }

        // N번째 child까지 놀이기구 세기
        for (int i = 0; i < M; i++) {
            // target 시간에 탐
            if(lt % rides[i] == 0) child++;
            if (child == N) {
                System.out.println((i+1));
                break;
            }
        }
    }
}