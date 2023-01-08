package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 비숍 
 * 
 *  비숍 자체의 특징을 알고 있어야 풀 수 있는 문제다. 
 *  하지만 알고 있어도 시간복잡도를 알아야 왜 제한시간이 10초인지 알 수 있게 된다. 
 *  
 *  핵심 
 *  1. 비숍은 체스판에서 서로 다른색(검음 or 흰)이면 만나지 않는다. 
 *     그렇기 때문에 경우의 수를 두 가지로 나눠서 하면 시간복잡도가 줄어든다. 
 *     + 특히 시간 복잡도에 대해서 더 생각해볼 수 있었다.
 *     
 *  2. 대각선 방향은 x + y, y - x 일차 방정식을 생각하여 배열로 같은 대각선 상에 있는지 확인가능하다. 
 *  
 *  
 */
public class Baek1799 {

	static int N;
	static int MAX;
	static int blackMaxCount;
	static int whiteMaxCount;
	static boolean[] upRight;
	static boolean[] upLeft;
	static int[][] board;
	static int[][] colorMap;
	static int[][] setMap;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		MAX = N * N;

		upRight = new boolean[2 * N + 1];
		upLeft = new boolean[2 * N + 1];

		setMap = new int[N][N];
		board = new int[N][N];
		colorMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if ((i + j) % 2 == 0) {
					colorMap[i][j] = 1;
				}
			}
		}

		// 보드판에서 검은색만 백트래킹
		backtracking(0, 1);

		// 흰색판에서 흰색만 백트래킹
		backtracking(0, 0);
		System.out.println(blackMaxCount + whiteMaxCount);
	}

	static void backtracking(int idx, int color) {
		if (idx == MAX) {
			// setMap 에서 비숍 세기

			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					count += setMap[i][j];
				}
			}

			if (color == 1) {
				if (blackMaxCount < count) {
					blackMaxCount = count;
				}
			} else {
				if (whiteMaxCount < count) {
					whiteMaxCount = count;
				}
			}

			return;
		}

		int y = idx / N;
		int x = idx % N;
		while (board[y][x] == 0 || colorMap[y][x] != color) {
			idx++;
			if(idx == MAX) {
				backtracking(idx, color);
				return;
			}
			y = idx / N;
			x = idx % N;
		}

		// 선택 안하는 경우
		backtracking(idx + 1, color);

		if (upRight[y + x] || upLeft[y - x + N]) {
			return;
		}
		upRight[y + x] = true;
		upLeft[y - x + N] = true;
		setMap[y][x] = 1;
		backtracking(idx + 1, color);
		upRight[y + x] = false;
		upLeft[y - x + N] = false;
		setMap[y][x] = 0;
	}
}
