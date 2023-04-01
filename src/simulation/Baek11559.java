package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
 * 	Puyo Puyo 시뮬레이션
 * 	 시뮬이지만 bfs 응용
 * 
 * 	1. 뿌요뿌요 체크할 때 dfs보다는 bfs 사용
 * 	   이유는 최대 길이와 동시에 연결되어 있는 뿌요를 첮아야 하기 때문
 * 
 *  2. bfs와 큐를 같이 이용 
 *     bfs수행하는 동안 지나간 뿌요들(노드)를 리스트에 넣어 연결된 총 개수 확인
 *     
 *  3. 뿌요가 내려오는 케이스 다양하게 생각해서 코너 케이스 주의
 */
public class Baek11559 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		for (int y = 0; y < 12; y++) {
			char[] charArray = br.readLine().toCharArray();
			map[y] = charArray;
		}

		// 모든 map에 대해 터지는 경우가 있다면
		// 안터지면 종료
		int ans = -1;
		while (true) {
			ans++;

			// 모든 map 검사 시 같은 색 뿌요 4개가 상하좌우 연결? -> 터짐 -> 빈 공간됨, 1연쇄 체크
			discovered = new boolean[12][6];
			boolean isBreakPuyo = breakPuyo();
			if (!isBreakPuyo) {
				break;
			}

			// 뿌요 아래로 떨어짐
			moveDown();
		}

		// 연쇄가 몇 번 연속으로 일어날지 파악하기
		System.out.println(ans);
	}

	static boolean[][] visited;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char[][] map;
	static boolean[][] discovered;

	static boolean breakPuyo() {
		boolean flag = false;
		for (int y = 0; y < 12; y++) {
			for (int x = 0; x < 6; x++) {
				if (discovered[y][x] || map[y][x] == '.') {
					continue;
				}

				discovered[y][x] = true;
				boolean isTrue = bfs(y, x, map[y][x]);
				if (isTrue) {
					flag = true;
				}
			}
		}

		return flag;
	}

	static boolean bfs(int sy, int sx, char color) {
		LinkedList<int[]> q = new LinkedList<>();
		q.add(new int[] { sy, sx });

		LinkedList<int[]> list = new LinkedList<>();
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			list.add(cur);

			for (int d = 0; d < 4; d++) {
				int ny = dir[d][0] + cur[0];
				int nx = dir[d][1] + cur[1];

				if (!(ny >= 0 && ny < 12 && nx >= 0 && nx < 6)) {
					continue;
				}

				if (map[ny][nx] != color || discovered[ny][nx]) {
					continue;
				}

				discovered[ny][nx] = true;
				q.add(new int[] { ny, nx });
			}
		}

		if (list.size() >= 4) {
			for (int[] node : list) {
				map[node[0]][node[1]] = '.';
			}

			return true;
		}

		return false;
	}

	static void moveDown() {
		for (int x = 0; x < 6; x++) {
			// 큐 추가
			LinkedList<Character> q = new LinkedList<>();
			for (int y = 11; y >= 0; y--) {
				if (map[y][x] == '.') {
					continue;
				}

				q.add(map[y][x]);
				map[y][x] = '.';
			}

			// 큐 다시 추가
			for (int y = 11; y >= 0; y--) {
				if (!q.isEmpty()) {
					Character val = q.poll();
					map[y][x] = val;
				}
			}
		}
	}

}
