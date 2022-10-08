package recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	쿼드트리
 */
public class Baek1992 {
	static int[][] map;
	static int mapSize;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		mapSize = Integer.parseInt(br.readLine());

		map = new int[mapSize][mapSize];
		for (int i = 0; i < mapSize; i++) {
			char[] charArray = br.readLine().toCharArray();
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = charArray[j];
			}
		}

		recursive(new int[] {0,0}, mapSize);
		System.out.println(sb.toString());
	}

	static StringBuilder sb = new StringBuilder();
	static void recursive(int[] point, int len) {
		// 구간 길이가 1이거나 모든 구간이 같은 색이면 0 또는 1 리턴
		if(len == 1 || isAllSameColor(point, len)) {
			int color = map[point[0]][point[1]] - '0';
			if(color == 1) {
				sb.append("1");
			} else {
				sb.append("0");
			}

			return;
		}

		sb.append("(");
		// 4개구간 순서대로 탐색
		recursive(point, len>>1);
		recursive(new int[] {point[0], point[1] + (len>>1)}, len>>1);
		recursive(new int[] {point[0] + (len>>1), point[1]}, len>>1);
		recursive(new int[] {point[0] + (len>>1), point[1] + (len>>1)}, len>>1);

		sb.append(")");
	}

	static boolean isAllSameColor(int[] point, int len) {

		int firstColor = map[point[0]][point[1]];
		for (int i = point[0]; i < point[0] + len; i++) {
			for (int j = point[1]; j < point[1] + len; j++) {
				if(firstColor != map[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
}
