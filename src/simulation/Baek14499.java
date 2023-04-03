package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	주사위 굴리기 
 * 	시뮬레이션 문제
 * 	
 * 	문제 잘 읽기 
 * 		주사위가 처음 위치가 맵에서 0인줄 알았으나, 이미 입력값으로 주사위 위치가 주어졌다.
 * 		0은 맵에서 몇 개나 위치 가능함    
 */
public class Baek14499 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		yLen = Integer.parseInt(st.nextToken());
		xLen = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		int cmdCount = Integer.parseInt(st.nextToken());

		Dice dice = new Dice(sy, sx);
		map = new int[yLen][xLen];
		for (int y = 0; y < yLen; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < xLen; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < cmdCount; k++) {
			int dir = Integer.parseInt(st.nextToken());

			if (dice.rotate(dir)) {
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = dice.bottom;
				} else {
					dice.bottom = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}

				// dice.print();
				System.out.println(dice.up);
			}

		}

	}

	static int[][] map;
	static int yLen;
	static int xLen;

	static class Dice {
		int up;
		int left;
		int front;
		int right;
		int bottom;
		int back;

		int y;
		int x;

		public Dice(int y, int x) {
			this.up = 0;
			this.left = 0;
			this.front = 0;
			this.right = 0;
			this.bottom = 0;
			this.back = 0;
			this.y = y;
			this.x = x;
		}

		public boolean rotate(int dir) {
			if (!canMove(dir)) {
				return false;
			}

			int ny = this.y + delta[dir - 1][0];
			this.y = ny;

			int nx = this.x + delta[dir - 1][1];
			this.x = nx;

			int temp;
			if (dir == 1) {
				temp = up;
				up = left;
				left = bottom;
				bottom = right;
				right = temp;
			} else if (dir == 2) {
				temp = up;
				up = right;
				right = bottom;
				bottom = left;
				left = temp;
			} else if (dir == 3) {
				temp = up;
				up = front;
				front = bottom;
				bottom = back;
				back = temp;
			} else {
				temp = up;
				up = back;
				back = bottom;
				bottom = front;
				front = temp;
			}

			return true;
		}

		static int[][] delta = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

		public boolean canMove(int dir) {
			dir--;
			int ny = this.y + delta[dir][0];
			int nx = this.x + delta[dir][1];

			return (ny >= 0 && ny < yLen && nx >= 0 && nx < xLen);
		}
	}

}
