package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 	적록색
 */
public class Baek10026 {

	static int N;
	static char[][] map;
	static int[][] dir = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	static boolean[][] discovered;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		discovered = new boolean[N][N];
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			char[] charArray = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = charArray[j];
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(discovered[i][j]) {
					continue;
				}

				bfs(i, j);
				count++;

			}
		}
		System.out.println(count);

		for (int i = 0; i < N ; i++) {
			Arrays.fill(discovered[i], false);
		}

		count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(discovered[i][j]) {
					continue;
				}

				bfsRG(i, j);
				count++;

			}
		}
		System.out.println(count);
	}

	// 색깔별 bfs
	static void bfs(int x, int y) {

		Node node = new Node(x, y, map[x][y]);
		discovered[x][y] = true;

		Queue<Node> q = new LinkedList<>();
		q.add(node);

		while(!q.isEmpty()) {
			Node curNode = q.poll();

			for(int i = 0 ; i < 4 ; i++) {
				int nx = curNode.getX() + dir[i][0];
				int ny = curNode.getY() + dir[i][1];

				if(!(0<= nx && nx < N && 0 <= ny && ny < N)) {
					continue;
				}

				if(discovered[nx][ny] || curNode.getColor() != map[nx][ny]) {
					continue;
				}

				discovered[nx][ny] = true;
				Node newNode = new Node(nx, ny, map[nx][ny]);
				q.add(newNode);
			}
		}
	}

	// 적록색약 bfs
	// red, green 같은색으로 처리
	static void bfsRG(int x, int y) {
		Node node = new Node(x, y, map[x][y]);
		discovered[x][y] = true;

		Queue<Node> q = new LinkedList<>();
		q.add(node);

		while(!q.isEmpty()) {
			Node curNode = q.poll();

			for(int i = 0 ; i < 4 ; i++) {
				int nx = curNode.getX() + dir[i][0];
				int ny = curNode.getY() + dir[i][1];

				if(!(0<= nx && nx < N && 0 <= ny && ny < N)) {
					continue;
				}

				if(discovered[nx][ny]) {
					continue;
				}

				if(curNode.getColor() == 'G' || curNode.getColor() == 'R') {
					if(map[nx][ny] == 'B') {
						continue;
					}
				} else {
					if(curNode.getColor() != map[nx][ny]) {
						continue;
					}
				}

				discovered[nx][ny] = true;
				Node newNode = new Node(nx, ny, map[nx][ny]);
				q.add(newNode);
			}
		}
	}

	static class Node {
		private int x;
		private int y;
		private int color;
		public Node(int x, int y, int color) {
			this.x = x;
			this.y = y;
			this.color = color;
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
		public int getColor() {
			return color;
		}
		public void setColor(int color) {
			this.color = color;
		}
	}

}
