package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int lcm = 0;
            if(M > N) {
                lcm = lcm(M, N);
            } else {
                lcm = lcm(N, M);
            }


            int max = M * N;
            for(int k = x; k <= max ; k += M) {
                int ny = (k % N);
                if (ny == 0) {
                    ny = N;
                }

                if(ny == y) {
                    sb.append(k).append("\n");
                    break;
                }
            }
            if (sb.length() == 0) {
                System.out.println(-1);
            } else {
                System.out.print(sb);
            }
        }
    }

    static int gcd(int a, int b) {
        if (b != 0) {
            return gcd(b, a  % b);
        }
        return a;
    }

    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
