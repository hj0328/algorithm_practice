package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] arr = new long[N]; // 심사대
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        long s = 1;
        long e = 1_000_000_000_000_000_000L; // 무조건 큰거 설정하는것도 아님

        while (s <= e) {
            long mid = (s + e) / 2;

            long count = 0;
            for (int i = 0; i < N; i++) {
                count += mid / arr[i];
                if (count >= M) {   // 없으면 long 범위 10^14 도 넘어감. 최대 10^18
                    break;
                }
            }

            if (count >= M) {    // count가 큼 -> 범위 줄이기, count == M이면, 최소 시간 구해야하니 범우 줄이기
                e = mid - 1;
            } else {
                s = mid + 1;    // count 작음 -> 시간 늘리기
            }

//            System.out.println(s + ", " + e);
        }

//        System.out.println(s + ", " + e);
        System.out.println(s);
    }
}
