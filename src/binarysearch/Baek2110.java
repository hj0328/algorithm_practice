package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2110 {
    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] list = new int[N];
        int maxHouse = 0;
        for (int i = 0; i < N; i++) {
            list[i] = Integer.valueOf(br.readLine());
            if (maxHouse < list[i]) {
                maxHouse = list[i];
            }
        }

        Arrays.sort(list);

        binarySearch(N, C, list, maxHouse);

    }

    /*
        와이파이 거리로 포함되는 집이 처음 N-1인 경우' 에서 -1 구하기
        upper bound
     */
    static void binarySearch(int N, int C, int[] list, int maxHouse) {
        int s = 0;
        int e = maxHouse;

        while (s < e) {
            int mid = (s + e) / 2;
            int putCount = check(mid, list);
            if (putCount < C) {
                // 공유기가 C개보다 적게 둔다 = 공유기 범위가 길다 = 줄인다
                e = mid;
            } else {
                /*
                    putCount < C
                    범위가 길어서 C개 필요없음
                    범위 줄이기
                 */
                // 동일하면? 범위를 늘리는 방향으로
                s = mid + 1;
            }
//            System.out.println("mid " + mid + ", putCount " + putCount);

        }

        System.out.println(s-1);
    }

    /*
        집 N개 모두 len으로 커퍼했을때 필요한 공유기 개수
     */
    static int check(int len, int[] list) {
        int putCount = 1;
        int coverLen = list[0] + len;
        for (int i = 1; i < list.length; i++) {

            if (list[i] >= coverLen) {
                putCount++;
                coverLen = list[i] + len;
            }
        }

        return putCount;
    }
}