package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  빙산
 *	 - 빙산은 매년 주위 4칸의 0개수만큼 높이가 줄어든다.
 *	 	- 단, 녹을 때 옆에 빙산이 0이 되었다고 해서 근처 빙산은 영향받지 않음
 *	 - 한 덩어리 빙산이 주어질 때, 두 덩어리 이상으로 분리되는 최초 시간을 구하기
 * 	 - 단 모두 녹을 때까지 분리되지 않으면 0출력
 *
 *	 bfsCount;
 * 	 모든 맵에 대해서 0이 아닌 값에 대해 bfs 시작
 * 		bfsCount++;
 * 		주변 0개수 체크
 * 		빙산 감소
 * 			감소할 빙산의 위치와 크기를 큐에 저장
 * 		빙산 큐 추가 및 discovered true 처리
 *
 *  1차 채점 틀린 이유
 *  	1. 빙산 높이 감소와 빙산 연결지역 세는 것을 한 번에 처리하려다 틀림
 *		2. 더 구체적으로 코드 계획
 */
public class Baek2573 {

	static boolean[][] discovered;
	static int[][] map;
	static int heightY;
	static int widthX;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		heightY = Integer.parseInt(st.nextToken());
		widthX = Integer.parseInt(st.nextToken());
		map = new int[heightY][widthX];
		discovered = new boolean[heightY][widthX];

		for (int y = 0; y < heightY; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < widthX; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int year = 0;
		boolean isDivied = false;
		while(true) {
			year++;

			// 빙산 높이 감소
			discovered = new boolean[heightY][widthX];
			for (int y = 0; y < heightY; y++) {
				for (int x = 0; x < widthX; x++) {
					if(map[y][x] == 0 || discovered[y][x]) {
						continue;
					}

					bfs(y, x);
				}
			}

			// 빙산 지역 개수 세기
			discovered = new boolean[heightY][widthX];
			int bfsCount = 0;	// bfsCount가 1 보다 크면 섬이 둘 이상으로 나뉨
			for (int y = 0; y < heightY; y++) {
				for (int x = 0; x < widthX; x++) {
					if(map[y][x] == 0 || discovered[y][x]) {
						continue;
					}
					bfsCount++;
					checkArea(y, x);
				}
			}

			//print();

			if(bfsCount > 1) {
				isDivied = true;
				break;
			}
			if(isAllMelted()) {
				break;
			}

		}
		if(isDivied) {
			System.out.println(year);
		} else {
			System.out.println(0);
		}
	}

	static int[][] dir = {
			{-1, 0},
			{1, 0},
			{0, 1},
			{0, -1}
	};
	static void bfs(int sy, int sx) {
		int[] point = new int[] {sy, sx};
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(point);
		discovered[sy][sx] = true;
		Queue<int[]> meltQueue = new LinkedList<>();

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();

			int waterAreaCount = 0;
			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dir[i][0];
				int nx = cur[1] + dir[i][1];

				if(!(0 <= ny && ny < heightY && 0 <= nx && nx < widthX)) {
					continue;
				}

				if(map[ny][nx] == 0) {
					waterAreaCount++;
				} else {
					if(discovered[ny][nx]) {
						continue;
					}

					discovered[ny][nx] = true;
					queue.add(new int[] {ny, nx});
				}
			}
			if(waterAreaCount > 0) {
				meltQueue.add(new int[]{cur[0], cur[1], waterAreaCount});
			}
		}

		while(!meltQueue.isEmpty()) {
			int[] meltArea = meltQueue.poll();
			map[meltArea[0]][meltArea[1]] -= meltArea[2];
			if(map[meltArea[0]][meltArea[1]] < 0) {
				map[meltArea[0]][meltArea[1]] = 0;
			}
		}
	}

	static boolean isAllMelted()	{

		for (int y = 0; y < heightY; y++) {
			for (int x = 0; x < widthX; x++) {
				if(map[y][x] != 0) {
					return false;
				}
			}
		}

		return true;
	}

	static void checkArea(int sy, int sx) {
		int[] point = new int[] {sy, sx};
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(point);
		discovered[sy][sx] = true;

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dir[i][0];
				int nx = cur[1] + dir[i][1];

				if(!(0 <= ny && ny < heightY && 0 <= nx && nx < widthX)) {
					continue;
				}

				if(map[ny][nx] == 0 || discovered[ny][nx]) {
					continue;
				}

				discovered[ny][nx] = true;
				queue.add(new int[] {ny, nx});
			}
		}
	}

	static void print()	{
		for (int y = 0; y < heightY; y++) {
			for (int x = 0; x < widthX; x++) {
				System.out.print(map[y][x] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
