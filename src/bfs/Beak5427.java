package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 	불
 *
 * 	상근 이동
 * 		- 벽 이동 불가
 * 		- 불 이동 불가
 * 		- 불 붙으려는 칸 이동 불가
 * 			- 불의 bfs 에서 discovered가 true라면 상근은 이동불가
 * 			- 불의 이동 경로를 미리 map에 표시
 * 		- 상근이 위치한 칸에 불이 올 때 다른 칸 이동가능
 * 			- 불이 상근을 발견 직전까지 상근은 이동가능
 *
 *  불 이동
 *  	- 벽 이동 불가
 *  	-- bfs의 동일한 depth 끼리는 불 이동 결과가 동일하다!
 *
 * 	1. 상근이 먼저 1초 이동 후 불이 1초 이동한다.
 * 	큐 <- (상근, 불1, 불2,,,) (상근1, 상근2, 상근3, 불, 불, 불, 불, 불), ,,,,
 *	큐에서 빼낸 값에 대해
 * 		만약 불이 상근의 위치에 도달하면 상근은 탈출 실패
 *  	상근의 위치가 건물 범위를 벗어나면 탈출 성공
 *
 *  bfs의 당연한 특징을 잊어 아이디어에서 잘못되었다.
 *   - 상근이 이동가능한 위치도 매우 많아질텐데 큐에 들어가는 상근을 항상 하나라고만 착각했다.
 *
 *
 *  2. 메모리 초과
 *   - 메모리가 충분하다고 착각
 *   - 2차원 배열을 bfs에서 매 노드를 지날 때마다 새로 생성해서 지도(불, 상근) 상태값을 전달했다.
 *     => OOM 발생
 *
 *	구현 전 더 구체적으로 계획을 세워야함
 *
 *  3. 답
 *	불 번짐 구
 *	 - 문제를 다시보면 불은 빈 공간으로 계속 이동
 *	 - 이 부분을 bfs로 처리하나 했지만 단순히 큐에 fire 위치를 넣고 전역변수 map에서 fire만 이동처리
 *
 */
public class Beak5427 {

	static int width;
	static int height;
	static int personeInitX;
	static int personeInitY;
	static char[][] map;
	static Queue<Node> fireList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			width = Integer.parseInt(st.nextToken());
			height = Integer.parseInt(st.nextToken());
			fireList = new LinkedList<>();

			map = new char[width][height];
			for (int i = 0; i < height; i++) {
				char[] charArray = br.readLine().toCharArray();
				for (int j = 0; j < charArray.length; j++) {
					map[j][i] = charArray[j];

					if(map[j][i] == '@') {
						personeInitX = j;
						personeInitY = i;
						map[j][i] = '.';
					} else if(map[j][i] == '*') {
						fireList.add(new Node(j, i, 0));
					}
				}
			}

			bfs();
		}
	}

	static int[][] dir = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	static void bfs() {
		boolean[][] discovered = new boolean[width][height];
		discovered[personeInitX][personeInitY] = true;

		Node node = new Node(personeInitX, personeInitY, 0);
		Queue<Node> q = new LinkedList<>();
		q.add(node);

		int currentDepth = -1;
		while(!q.isEmpty()) {

			Node cur = q.poll();

			// 새로운 depth일 때 불 번짐
			if(currentDepth < cur.getDepth()) {
				burn();
				currentDepth = cur.getDepth();
			}

			print(cur.getX(), cur.getY());
			for (int i = 0; i < dir.length; i++) {
				int nx = cur.getX() + dir[i][0];
				int ny = cur.getY() + dir[i][1];

				if(!isBorder(nx, ny)) {

					System.out.println(cur.getDepth());
					return;
				} else {
					// 사람 이동 및 큐에 추가
					if(map[nx][ny] != '.' || discovered[nx][ny]) {
						continue;
					}

					// 빈 공간이고 불이 이동하지 않을 위치일 때 상근이 이동
					discovered[nx][ny] = true;
					Node node2 = new Node(nx, ny, cur.getDepth()+1);
					q.add(node2);
				}
			}
		}

		System.out.println("IMPOSSIBLE");
	}

	static void print(int x, int y) {
		map[x][y] = '@';
		for(int i = 0 ; i < height ; i++) {
			for(int j = 0 ; j < width ; j++) {
				System.out.print(map[j][i]);
			}
			System.out.println();
		}
		System.out.println();
		map[x][y] = '.';
	}

	/*
	 *  빈 칸인 경우 불이 번진다.
	 *  상근이 위치는 .으로 처리한다 왜냐하면 불은 상근이가 움직이기 전의 위치에도 존재할 수 있기 때문이다.
	 *  상근이가 만약 더이상 움직일 곳이 없다면 상근이는 실패한다.
	 */
	static void burn() {
		Queue<Node> q = new LinkedList<>();
		while(!fireList.isEmpty()) {
			Node fire = fireList.poll();
			map[fire.getX()][fire.getY()] = '*';
			for (int[] delta : dir) {
				int nx = fire.getX() + delta[0];
				int ny = fire.getY() + delta[1];

				if(!isBorder(nx, ny) || map[nx][ny] != '.') {
					continue;
				}

				q.add(new Node(nx, ny, 0));
				map[nx][ny] = '*';
			}
		}

		while(!q.isEmpty()) {
			fireList.add(q.poll());
		}
	}

	static boolean isBorder(int x, int y) {
		return 0 <= x && x < width && 0 <= y && y < height;
	}

	static class Node {
		private int x;
		private int y;
		private int depth;

		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
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
		public void setDepth(int depth) {
			this.depth = depth;
		}
		public int getDepth() {
			return depth;
		}
	}

}
