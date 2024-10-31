package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] pos = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }
        pos[N + 1] = L;

        Arrays.sort(pos);

        // 휴게소 없는 구간의 최대 길이 찾기. m개를 설치
        int s = 1;
        int e = L - 1;

        while(s <= e) {

            int mid = (s + e) / 2;

            int count = 0;
            for(int i = 1 ; i <= N ; i++) {
                int dist = pos[i] - pos[i - 1];
                count += dist / mid;
                if (dist % mid == 0) {
                    count--;
                }
            }

            if (count > M) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }

//            System.out.println(s + ", " + e + " count " + count + " mid " + mid);
        }

        System.out.println(s);
    }
}
