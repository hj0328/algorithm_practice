package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 	토마토
 *  bfs 문제
 *   - 3차원공간에서 bfs 알고리즘 수행
 *
 *  왜 시간이 오래걸렸지
 *   - bfs 어떻게 응용해서 사용할지 시간 소요
 *   - 토마토 상태를 0 -1 1 로 표현되었기 때문에 헷갈렸다.
 *
 *   -> 상수로 표현했다면 고민한 시간이 줄었을 것이다.
 */
public class Baek7569 {

	static int N, M, H;
	static int[][][] box;
	static boolean[][][] discovered;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());	// 가로 2 ~ 100
		N = Integer.parseInt(st.nextToken());	// 세로 2 ~ 100
		H = Integer.parseInt(st.nextToken());	// 높이 1 ~ 100

		box = new int[M][N][H];
		discovered = new boolean[M][N][H];

		for(int h = 0 ; h < H ; h++) {
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < M ; j++) {
					box[j][i][h] = Integer.parseInt(st.nextToken());
				}
			}
		}


		// 박스의 모든 익은 토마토 확인
		Queue<Tomato> q = new LinkedList<>();
		for(int h = 0 ; h < H ; h++) {
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					//box[j][i][h]
					if(box[j][i][h] != 1) {
						continue;
					}

					discovered[j][i][h] = true;
					Tomato tomato = new Tomato(j, i, h, 0);
					q.add(tomato);
				}
			}
		}

		if(q.isEmpty()) {
			System.out.println(-1);
		} else {
			int date = bfs(q);
			if(isAllTomatoOk()) {
				System.out.println(date);
			} else {
				System.out.println(-1);
			}
		}

	}

	/*
	 * 	bfs
	 * 	시작: 확인한적 없고, 익은 토마토
	 *  큐 추가: 확인한적 없고, 익지 않은 토마토, 현재 검사하는 토마토(1)은 2로 바꾼다. -> -1 한 것이 날짜
	 *  6방향에 대해 검사
	 *  접근할 때마다 날짜를 추가로 저장
	 *
	 *  새로 익은 토마토 개수 리턴
	 */
	static int[][] dir = {
			{-1, 0, 0},
			{1, 0, 0},
			{0, 1, 0},
			{0, -1, 0},
			{0, 0, 1},
			{0, 0, -1},
	};
	static int bfs(Queue<Tomato> q) {

		int retDate = 0;
		while(!q.isEmpty()) {
			Tomato cur = q.poll();	// 익은 토마토

			if(retDate < cur.getDate()) {
				retDate = cur.getDate();
			}

			for(int i = 0 ; i < 6 ; i++) {
				int nx = cur.getX() + dir[i][0];
				int ny = cur.getY() + dir[i][1];
				int nh = cur.getH() + dir[i][2];


				if(!(0 <= nx && nx < M && 0 <= ny && ny < N && 0 <= nh && nh < H)) {
					continue;
				}

				if(discovered[nx][ny][nh] || box[nx][ny][nh] != 0) {
					continue;
				}

				discovered[nx][ny][nh] = true;
				box[nx][ny][nh] = 1;
				Tomato tomato = new Tomato(nx, ny, nh, cur.getDate()+1);
				q.add(tomato);
			}
		}

		return retDate;
	}

	static boolean isAllTomatoOk() {
		for(int h = 0 ; h < H ; h++) {
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(box[j][i][h] == 0) { // 익지 않은 토마토
						return false;
					}
				}
			}
		}
		return true;
	}

	static class Tomato {
		private int x;
		private int y;
		private int h;
		private int date;

		public Tomato(int x, int y, int h, int date) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.date = date;
		}

		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getH() {
			return h;
		}
		public void setH(int h) {
			this.h = h;
		}
		public int getDate() {
			return date;
		}
		public void setDate(int date) {
			this.date = date;
		}
	}
}
