package prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long count = 0;
        int[] sum = new int[N];
        long[] reminder = new long[M];
        st = new StringTokenizer(br.readLine());
        sum[0] = (Integer.parseInt(st.nextToken())) % M;
        reminder[sum[0]]++;
        if (sum[0] % M == 0) {
            count++;
        }
        for (int i = 1; i < N; i++) {
            int val = Integer.parseInt(st.nextToken());
            sum[i] = (sum[i - 1] + val) % M;
            reminder[sum[i]]++;

            if (sum[i] == 0) {
                count++;
            }
        }

        for (int i = 0; i < M; i++) {
            if (reminder[i] > 0) {
                count += (reminder[i] * (reminder[i] - 1)) / 2;
            }
        }

        System.out.println(count);
    }
}
