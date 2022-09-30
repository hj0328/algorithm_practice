package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	나이트의 이동
 *
 *	전형적인 bfs 알고리즘
 *  고민했던 부분
 *  	- 나이트가 최소로 이동 시 중복해서 똑같은 곳을 밟는 경우가 있을지
 *  	- 최소가 되려면 똑같은 부분을 또 밟으면 안됨.
 *  	- 따로 제한 사항들도 없으니 문제없음
 */
public class Baek7562 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			int len = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken()); // start
			int sx = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int ty = Integer.parseInt(st.nextToken()); // target
			int tx = Integer.parseInt(st.nextToken());

			int[][] map = new int[len][len];

			boolean[][] discovered = new boolean[len][len];
			discovered[sy][sx] = true;

			int[] point = new int[] {sy, sx};

			Queue<int[]> q = new LinkedList<>();
			q.add(point);

			while(!q.isEmpty()) {
				int[] curPoint = q.poll();

				// 처음으로 (ty, tx)에 값이 들어오면 그 값이 최솟값이므로 break;
				if(map[ty][tx] != 0) {
					break;
				}

				for(int i = 0 ; i < 8 ;i++) {	// 모든 방향에 대해 탐색
					int ny = curPoint[0] + dir[i][0];
					int nx = curPoint[1] + dir[i][1];

					if(!(0 <= ny && ny < len && 0 <= nx && nx < len)) {
						continue;
					}

					// 이미 발견했으면 pass, bfs 특성 상 발경한 것은 이미 최초
					if(discovered[ny][nx]){
						continue;
					}

					discovered[ny][nx] = true;
					map[ny][nx] = map[curPoint[0]][curPoint[1]] + 1;
					q.add(new int[] {ny, nx});
				}
			}

			System.out.println(map[ty][tx]);
		}
	}

	static int[][] dir = {
			{-2, -1},
			{-1, -2},
			{-2, 1},
			{-1, 2},
			{1, -2},
			{2,-1},
			{1, 2},
			{2, 1}
	};
}
