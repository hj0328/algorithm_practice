package prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        // down[h]: 높이 1부터 h 까지의 아래 장애물 개수
        int[] down = new int[H + 1];

        // up[h]: 높이 1부터 h 까지의 위 장애물 개수
        int[] up = new int[H + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            if((i & 1) == 0) {
                down[h]++;
            } else {
                up[h]++;
            }
        }

        // 누접합 계산
        for (int h = 2; h <= H; h++) {
            down[h] += down[h - 1];
            up[h] += up[h - 1];
        }

        int minConflict = N;
        int count = 0;
        for (int h = 1; h <= H; h++) {
            int conflict = (down[H] - down[h - 1]) + (up[H] - up[H - h]);
            if (minConflict > conflict) {
                minConflict = conflict;
                count = 1;
            } else if(minConflict == conflict) {
                count++;
            }
        }

        System.out.println(minConflict + " " + count);
    }
}
