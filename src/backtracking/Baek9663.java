package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * backtracking 대표적인 문제: N-Queen문
 *  대각선 \와 대각선 / 에 있는 퀸을 백트래킹 노드에 추가하여 루프를 제거
 *  백준 채점에서 이렇게 약간의 수학적 처리로 속도가 1/5이나 줄어들었음  
 */
public class Baek9663 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		// 컬럼에 존재 유무 확인
		isUpExist = new boolean[N];

		// / 방향 존재 유무 확인
		isUpRightExist = new boolean[1 << N];

		// \ 방향 존재 유무 확인
		isUpLeftExist = new boolean[1 << N];

		backtracking(0);
		System.out.println(ans);
	}

	static boolean[] isUpExist;
	static boolean[] isUpLeftExist;
	static boolean[] isUpRightExist;

	static int[][] board;
	static int N;
	static int ans;

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

			// 세 방향에 대해 다른 퀸 검사
			if (isUpExist[c] || isUpLeftExist[r - c + N - 1] || isUpRightExist[r + c]) {
				continue;
			}

			isUpExist[c] = true;
			isUpLeftExist[r - c + N - 1] = true;
			isUpRightExist[r + c] = true;
			backtracking(r + 1);
			isUpExist[c] = false;
			isUpLeftExist[r - c + N - 1] = false;
			isUpRightExist[r + c] = false;
		}
	}

}
