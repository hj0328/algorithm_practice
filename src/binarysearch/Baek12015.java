package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek12015 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] lis = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lisIdx = -1;
        for (int i = 0; i < N; i++) {
            int start = arr[i];

            // lower bound
            int s = 0;
            int e = lisIdx + 1;
            while (s < e) {
                int mid = (s + e) / 2;

                if(lis[mid] < start) {
                    s = mid + 1;
                } else {
                    e = mid;
                }
            }

            if (lisIdx == -1 || s == lisIdx + 1) {
                lisIdx++;
                lis[lisIdx] = start;
            } else {
                lis[s] = start;
            }
        }

        System.out.println(lisIdx + 1);
    }
}
