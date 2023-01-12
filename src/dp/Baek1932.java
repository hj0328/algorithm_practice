package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  정수 삼각형
 *  기본 dp 중에서도 nCr dp 로 볼 수 있다. 
 *  
 *  당연히 귀납법을 사용하기 때문에 초기 테스트 필수
 *  배열 내에 마이너스가 들어가기 때문에 배열의 크기를 주어지는 배열 크기보다 조금 더 크게 잡아야 한다. 
 *  귀납법의 시작 부분을 설정하는 만큼
 */
public class Baek1932 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] costs = new int[N + 2][N + 2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] d = new int[N + 2][N + 2];
		d[0][0] = costs[0][0];
		d[1][0] = costs[0][0] + costs[1][0];
		d[1][1] = costs[0][0] + costs[1][1];

		for (int y = 2; y < N; y++) {
			// x = 0 인 경우
			d[y][0] = d[y - 1][0] + costs[y][0];

			for (int x = 1; x < N; x++) {
				d[y][x] = Math.max(d[y - 1][x], d[y - 1][x - 1]) + costs[y][x];
			}
		}

		int max = 0;
		for (int x = 0; x < N; x++) {
			if (max < d[N - 1][x]) {
				max = d[N - 1][x];
			}
		}

		System.out.println(max);
	}

}
