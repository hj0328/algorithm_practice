package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	가장 큰 정사각형 
 * 		0과 1로 구성된 2차원 배열에서 1로 구성된 가장 큰 정사각형 구하기 
 * 		직접 하나하나 답을 찾기에는 중복되는 sub problem이 많다.
 * 
 * 	특징
 * 		특정 y, x가 A 정사각형의 오른쪽, 아래 점이라고 가정한다. 
 * 		'dp[y][x]: A 정사각형 한 편의 길이' 로 정의 
 * 		
 * 		dp[y][x] = min(dp[y-1],[x], dp[y-1][x-]1, dp[y][x-1])
 * 		0010
 * 		1110
 * 		0111
 * 		0100  
 */
public class Baek1915 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][M + 1];

		for (int y = 1; y <= N; y++) {
			String readLine = br.readLine();
			for (int x = 1; x <= M; x++) {
				map[y][x] = readLine.charAt(x - 1) - '0';
			}
		}

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= M; x++) {
				if (map[y][x] == 0) {
					continue;
				}

				int leftVal = map[y][x - 1];
				int upVal = map[y - 1][x];
				int upLeftVal = map[y - 1][x - 1];

				int min = Math.min(leftVal, upVal);
				min = Math.min(min, upLeftVal);
				map[y][x] = min + 1;
			}
		}

		int ans = 0;
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= M; x++) {
				if (ans < map[y][x]) {
					ans = map[y][x];
				}
			}
		}

		System.out.println(ans * ans);
	}
}
