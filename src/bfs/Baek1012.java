package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  유기농 배추
 *		전형적인 bfs 알고리즘 문제
 */
public class Baek1012 {

	static int XM;
	static int YM;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for(int tc = 0 ; tc < TC ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			XM = Integer.parseInt(st.nextToken());
			YM = Integer.parseInt(st.nextToken());
			int positions = Integer.parseInt(st.nextToken());

			for(int i = 0 ; i <= XM ; i++) {
				Arrays.fill(isDiscovered[i], false);
			}

			boolean[][] isVeg = new boolean[XM][YM];

			// 배추위치
			for(int i = 0 ; i < positions ; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				isVeg[x][y] = true;
			}

			// 위치중 배추 위치에서 bfs 탐색시작
			int total = 0;
			for(int i = 0 ; i < XM ; i++) {
				for(int j = 0 ; j < YM ; j++) {
					if(!isVeg[i][j] || isDiscovered[i][j]) {
						continue;
					}

					if(bfs(i, j, isVeg) > 0) {
						total++;
					}
				}
			}

			System.out.println(total);
		}
	}

	static boolean[][] isDiscovered = new boolean[51][51];
	static int[][] delta = {
		{1, 0},
		{-1, 0},
		{0, 1},
		{0, -1}
	};
	static int bfs(int x, int y, boolean[][] isVeg) {

		isDiscovered[x][y] = true;
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();

		xq.add(x);
		yq.add(y);

		int count = 0;

		while(!xq.isEmpty()) {
			int cx = xq.poll();
			int cy = yq.poll();
			count++;

			for(int dir = 0 ; dir < 4 ; dir++) {
				int nx = cx + delta[dir][0];
				int ny = cy + delta[dir][1];

				if(!(0 <= nx && nx < XM && 0 <= ny && ny < YM)) {
					continue;
				}

				if(isDiscovered[nx][ny] || !isVeg[nx][ny]) {
					continue;
				}

				isDiscovered[nx][ny] = true;
				xq.add(nx);
				yq.add(ny);
			}
		}

		return count;
	}
}
