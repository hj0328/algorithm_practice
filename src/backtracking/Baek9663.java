package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * backtracking 대표적인 문제: N-Queen문
 */
public class Baek9663 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		columns = new boolean[N];

		backtracking(0);
		System.out.println(ans);
	}

	static boolean[] columns;
	static int[][] board;
	static int N;
	static int ans;
	// 왼쪽 대각선, 오른쪽 대각선, 위쪽 검사
	static int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 } };

	/*
	 * 위에서부터 백트래킹 시작 각 행에는 퀸은 하나씩만 놓는다.
	 */
	static void backtracking(int r) {
		if (r == N) {

			ans++;
			return;
		}

		// r행에서 각 c에 대해
		for (int c = 0; c < N; c++) {
			if (columns[c]) {
				continue;
			}

			// 세 방향에 대해 다른 퀸 검사
			boolean isOther = false;
			for (int d = 0; d < 3; d++) {

				// 다른 퀸이 하나라도 있으면 놓을 수 없음
				if (checkOtherQuene(r, c, d)) {
					isOther = true;
					break;
				}
			}

			if (!isOther) {
				columns[c] = true;
				board[r][c] = 1;
				backtracking(r + 1);
				board[r][c] = 0;
				columns[c] = false;
			}
		}
	}

	static boolean checkOtherQuene(int r, int c, int d) {
		int nr = r;
		int nc = c;

		while (nr >= 0 && nc >= 0 && nc < N) {
			if (board[nr][nc] == 1) {
				return true;
			}
			nr += dir[d][0];
			nc += dir[d][1];
		}

		return false;
	}
}
