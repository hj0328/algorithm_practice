package prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] sum = new int[N];
        st = new StringTokenizer(br.readLine());
        sum[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            sum[i] += sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 가장 처음 K개 선택: index 0 부터 k - 1 까지
        int max = sum[K - 1];
        for (int i = 0; i + K < N; i++) {
            int val = sum[i + K] - sum[i];
            if (max < val) {
                max = val;
            }
        }
        System.out.println(max);
    }
}
