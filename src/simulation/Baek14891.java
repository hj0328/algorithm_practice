package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 톱니바퀴
 * https://www.acmicpc.net/problem/14891
 * n번째 톱니를 시계 또는 반시계로 회전 붙어있는 톱니와 맞물려있는 자석이 다르면 반대로 돈다. 그 톱니의 맞물려있는 톱니 또한 마찬가지
 * 
 */
public class Baek14891 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		int[][] gears = new int[4][8];
		for (int i = 0; i < 4; i++) {
			char[] charArray = br.readLine().toCharArray();

			for (int j = 0; j < 8; j++) {
				gears[i][j] = charArray[j] - '0';
			}
		}

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int gearNum = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			int[] curGear = new int[8];
			for (int i = 0; i < 8; i++) {
				curGear[i] = gears[gearNum][i];
			}
			dfs(gears, gearNum, dir, START, curGear);
		}

		System.out.println(gears[0][0] + gears[1][0] * 2 + gears[2][0] * 4 + gears[3][0] * 8);
	}

	/*
	 * gearNum기어를 dir 방향에 따라 회전 영향받는 기어가 옆에 있는면 그 기어도 돌기
	 */
	static final int START = 0;
	static final int LEFT = -1;
	static final int RIGHT = 1;

	/*
	 * 현재 gearnum 검사 
	 * gearNum을 dir 방향으로 회전 
	 * 첫 시작 기어면 양쪽 영향 
	 * 
	 * from이 왼쪽이면 오른쪽 영향 
	 * from이 오른쪽이면 왼쪽 영향
	 * 
	 */
	static void dfs(int[][] gears, int gearNum, int dir, int from, int[] preGear) {
		// 기어 숫자가 아니면 종료
		if (!(gearNum >= 0 && gearNum < 4)) {
			return;
		}

		// 이전 기어와 같은 자석이 맞물려있다면 종료
		if (from == LEFT) {
			if (gears[gearNum][6] == preGear[2]) {
				return;
			}
		} else if (from == RIGHT) {
			if (gears[gearNum][2] == preGear[6]) {
				return;
			}
		}

		// 현재 기어 회전
		int[] curGear = new int[8];
		for (int i = 0; i < 8; i++) {
			curGear[i] = gears[gearNum][i];
		}
		rotate(gears, gearNum, dir);
		// print(gears);

		// 맞물린 기어 회전 요청
		if (from == START) {
			dfs(gears, gearNum - 1, -dir, RIGHT, curGear);
			dfs(gears, gearNum + 1, -dir, LEFT, curGear);

		} else if (from == LEFT) {
			dfs(gears, gearNum + 1, -dir, LEFT, curGear);

		} else {
			dfs(gears, gearNum - 1, -dir, RIGHT, curGear);
		}

		/*
		 * 왼쪽기어가 회전했었음 극이 다르면 회전 오른쪽 체크
		 */

		/*
		 * 오른쪽 기어가 회전했었음 극이 다르면 회전 왼쪽 체크
		 */
	}

	static void print(int[][] gears) {

		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(gears[i][j]);
			}
			System.out.println();
		}
	}

	static void rotate(int[][] gears, int gearNum, int dir) {
		int[] gear = gears[gearNum];
		if (dir == LEFT) {
			int temp = gear[0];
			for (int i = 1; i < 8; i++) {
				gear[i - 1] = gear[i];
			}

			gear[7] = temp;
		} else {
			int temp = gear[7];
			for (int i = 7; i >= 1; i--) {
				gear[i] = gear[i - 1];
			}

			gear[0] = temp;
		}
	}

}
