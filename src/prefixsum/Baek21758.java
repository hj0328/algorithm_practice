package prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek21758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }

        int max = 0;
        for(int i = 2; i < N ; i++) {

            // 통 제일 왼쪽, 통 제일 오른쪽
            int a = 0;
            a = sum[i - 1] + sum[N-1] - arr[i];
            max = Math.max(max, a);

            // 제일 양쪽 벌
            int b = 0;
            b = sum[i] - sum[1] + sum[N-1] - sum[i-1];
            max = Math.max(max, b);

            // 제일 왼쪽 벌, 제일 오른쪽 통
            int c = 0;
            c = (sum[N] - arr[1] - arr[i]) + (sum[N] - sum[i-1] - arr[i]);
            max = Math.max(max, c);

        }

        System.out.println(max);
    }
}
