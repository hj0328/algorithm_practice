package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2470 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long minDiff = Long.MAX_VALUE;
        int a = 0;
        int b = 0;
        for (int i = 0; i < N - 1; i++) {
            int target = arr[i];

            int s = i + 1;
            int e = N;
            long diff = 0;
            while (s < e) {
                int mid = (s + e) / 2;  // target과 더했을 때 0에 가장 가까운 수

                diff = target + arr[mid];
                if (diff == 0) {
                    a = arr[i];
                    b = arr[mid];
                    minDiff = 0;
                    break;
                } else {
                    int abs = Math.abs(target + arr[mid]);
                    if (diff < 0) {
                        if (0 < abs) {
                            s = mid + 1;
                        } else {
                            e = mid;
                        }
                    } else {
                        if (0 < abs) {
                            e = mid;
                        } else {
                            s = mid + 1;
                        }
                    }
                }
                if (Math.abs(diff) < minDiff) {
                    minDiff = Math.abs(diff);
                    a = arr[i];
                    b = arr[mid];
                }
            }

//            System.out.println("dif " + diff);
        }
        System.out.println(a + " " + b);
    }
}
