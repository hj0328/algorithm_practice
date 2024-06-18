package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek4796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = 0;
        while (true) {
            t++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (L == 0 && P == 0 && V == 0) {
                return;
            }

            int ans = 0;
            ans = (V / P) * L ;
            if(V % P < L) {
                ans += V % P;
            } else {
                ans += L;
            }

            System.out.println("Case " + t + ": " + ans);
        }
    }
}
