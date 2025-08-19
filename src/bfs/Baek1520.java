package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1520 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());   // ~ 500
        int N = Integer.parseInt(st.nextToken());   // ~ 500

        int[][] map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (i, j) 부터 목적지 (M - 1, N - 1) 까지 이동 경로 개수 = 메모이제이션
        dp = new int[M][N];

        // 초기화. -1이면 아직 경로 계산하지 않은 것
        // 처음에 0으로 뒀다가 시간초과 발생
        // 0의미가 계산하지 않음과 경로가 없음이라는 두가지 의미가 생겨서 시간초과가 발생한 것
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ret = 0;
        for (int d = 0; d < 4; d++) {
            int ni = delta[d][0];
            int nj = delta[d][1];

            if(!(ni >= 0 && ni < M && nj >= 0 && nj < N)) continue;

            if(map[0][0] <= map[ni][nj]) continue;

            ret += dfs(ni, nj, M, N, map);
        }

        if (ret == -1) {
            System.out.println(0);
        } else {
            System.out.println(ret);
        }
    }

    static int[][] delta = {
            {-1, 0,}, {1,0}, {0, 1}, {0, -1}
    };
    static int dfs(int i, int j, int M, int N, int[][] map) {
        if (i == M - 1 && j == N - 1) { // 목적지 달성 시 경로 1회 리턴
            return 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ret = 0;
        for (int d = 0; d < 4; d++) {
            int ni = i + delta[d][0];
            int nj = j + delta[d][1];

            if(!(ni >= 0 && ni < M && nj >= 0 && nj < N)) continue;

            if(map[i][j] <= map[ni][nj]) continue;

            ret += dfs(ni, nj, M, N, map);
        }

        return dp[i][j] = ret;
    }

    static int[][] dp;

}
/*
    시간복잡도: 칸 2500개
    dfs는 4^2500 -> 시간 초과

 */