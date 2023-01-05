package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 	Gaaaaaaaaaarden
 * 
 * 	초록 배양액, 빨간 배양액으로 꽃을 만드는데 최대 개수의 꽃을 구하기 
 * 	배양액
 * 		황토 땅에서 시작
 * 		물, 이미 배양액, 꽃은 제외하여 퍼짐
 *	
 *	1) 모든 배양액은 황토색 칸에 뿌림 
 *		모든 경우의 수에 대해 시도 
 *		dfs로 각 황토칸에 R또는G 배양액을 넣을지에 대해 탐색
 *
 *  2) R과G 배양액은 매초마다 번져나감 
 *		bfs로 퍼져나감 
 *		a위치에서 퍼지고, 다음 a위치에서 퍼질 때가 1초가 지난 것. 그 사이에 다른 b 배양액과 만나면 꽃이 됨 
 *		반대도 마찬가지 
 *		퍼지는 중 배양액을 따로 표시. 퍼질 곳에 다른 배양액과 만나면 꽃이 됨 
 *		걔는 큐에 넣지 않고 맵에 꽃 표시(이동 불가) 
 *		다시 큐에서 빠지게 되면 맵에 퍼진 것을 표시
 *
 *	3) 배양액이 모두 퍼졌을 떄 최대 꽃 수를 구하기
 *		초기화 중요! 
 		맵에 표시를 했기 때문에 초기 맵으로 다시 초기화해야 함
 *
 *
 *	피드백
 *	1. 숫자로 의미를 표현할 때 거꾸로 변수 상수를 선언해서 사용하면 덜 헷갈린다. 
 *	2. 새로운 배열을 만들어 원본 배열에 영향을 끼치지 않게 할 수 있다. 
 *	3. 복사본을 만들었을 때 초기화 잊지말기 
 *	4. 모르는걸 몰라서 틀렸다. 배양액이 퍼지는 시간에 대해서 짐작해서 틀렸다. 
 *  
 */
public class Baek18809 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int G = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		growLand = new ArrayList<>();
		// 0 호수, 1 배양액 뿌릴 수 없는 땅(흰땅), 2 배양액 뿌릴 수 있는 땅, 3은 꽃으로 가정
		map = new int[N][M];
		copyMap = new int[N][M];
		time = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					growLand.add(new int[] { i, j });
				}
			}
		}

		spread(new LinkedList<>(), new LinkedList<>(), 0, G, R);
		System.out.println(maxFlowerCount);
	}

	/*
	 * yLand의 각 위치가 초록 또는 빨간 배양액을 뿌릴경우 모두 체크
	 */
	static int[][] map;
	static int[][] copyMap;
	static int[][] time;
	static int maxFlowerCount = 0;
	static ArrayList<int[]> growLand;

	static void spread(LinkedList<int[]> redList, LinkedList<int[]> greenList, int idx, int G, int R) {

		if (G == 0 && R == 0) {

			// 모든 배양액을 놓았으니 이제 퍼질차례
			makeFlower(redList, greenList);

			int flowerCount = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copyMap[i][j] == 3) {
						flowerCount++;
					}
				}
			}

			if (maxFlowerCount < flowerCount) {
				maxFlowerCount = flowerCount;
			}

			return;
		}

		if (idx == growLand.size()) {
			return;
		}

		// idx에 초록 또는 빨간 배양액 추가
		int[] seed = growLand.get(idx);

		if (G > 0) {
			greenList.add(seed);
			spread(redList, greenList, idx + 1, G - 1, R);
			greenList.removeLast();
		}

		if (R > 0) {
			redList.add(seed);
			spread(redList, greenList, idx + 1, G, R - 1);
			redList.removeLast();
		}

		// 현재 배양토에 아무것도 심지 않는 경우
		spread(redList, greenList, idx + 1, G, R);
	}

	static final int WATER = 0;
	static final int FLOWER = 3;
	static final int GREEN_FOUND = 4;
	static final int RED_FOUND = 5;

	static int N;
	static int M;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static void makeFlower(LinkedList<int[]> redList, LinkedList<int[]> greenList) {
		LinkedList<int[]> q = new LinkedList<>();
		q.addAll(redList);
		q.addAll(greenList);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
				time[i][j] = -1;
			}
		}

		for (int[] g : greenList) {
			copyMap[g[0]][g[1]] = GREEN_FOUND;
			time[g[0]][g[1]] = 0;
		}
		for (int[] r : redList) {
			copyMap[r[0]][r[1]] = RED_FOUND;
			time[r[0]][r[1]] = 0;
		}

		while (!q.isEmpty()) {
			int[] curLand = q.poll();
			if (copyMap[curLand[0]][curLand[1]] == FLOWER) {
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int ny = curLand[0] + dir[d][0];
				int nx = curLand[1] + dir[d][1];

				if (!(ny >= 0 && ny < N && nx >= 0 && nx < M)) {
					continue;
				}

				// 호수, 꽃이라면 패스
				if (copyMap[ny][nx] == WATER || copyMap[ny][nx] == FLOWER) {
					continue;
				}

				// ny nx가 땅이라면 퍼뜨림
				if (copyMap[ny][nx] == 1 || copyMap[ny][nx] == 2) {
					copyMap[ny][nx] = copyMap[curLand[0]][curLand[1]];
					q.add(new int[] { ny, nx });
					time[ny][nx] = time[curLand[0]][curLand[1]] + 1;
				}

				// 배양액 색이 다르면 합쳐짐, ny nx는 꽃이 됨
				if (copyMap[curLand[0]][curLand[1]] != copyMap[ny][nx]
						&& time[curLand[0]][curLand[1]] + 1 == time[ny][nx]) {
					copyMap[ny][nx] = FLOWER;
				}
			}
		}
	}
}
