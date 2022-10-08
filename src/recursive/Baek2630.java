package recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	색종이 만들기
 *
 */
public class Baek2630 {

	static int[][] paperMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int len = Integer.parseInt(br.readLine());
		paperMap = new int[len][len];

		for (int y = 0; y < len; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < len; x++) {
				paperMap[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		recursive(new int[] {0, 0}, len);
		System.out.println(whiteCount);
		System.out.println(blueCount);
	}

	/*
	 * dfs(배열에서 종이 시작 위치, 종이 길이)
	 * 	if 현재 종이가 모두 같은 색으로 구성되었는가
	 * 		종이 안자름
	 *
	 * 	else
	 * 		종이를 4개 구간으로 나눔
	 * 		dfs진행
	 *
	 * 	dfs 리턴 값
	 * 		배열: 0 인덱스 하얀종이, 1인덱스 파란종이
	 */
	static int whiteCount;
	static int blueCount;
	static void recursive(int[] point, int len) {
		if(len == 1) {
			if(paperMap[point[0]][point[1]] == 0) {
				whiteCount++;
			} else {
				blueCount++;
			}
			return;
		}

		if(isAllSameColor(point, len)) {
			//System.out.println("isAllSameColor " + point[0] + ", " + point[1] + " len " + len);
			if(paperMap[point[0]][point[1]] == 0) {
				whiteCount += 1;
			} else {
				blueCount += 1;
			}
			return;
		}

		// 4개 구간으로 나뉜다.
		recursive(point, len>>1);
		recursive(new int[] {point[0], point[1] + (len>>1)}, len>>1);
		recursive(new int[] {point[0] + (len>>1), point[1]}, len>>1);
		recursive(new int[] {point[0] + (len>>1), point[1] + (len>>1)}, len>>1);
	}

	static boolean isAllSameColor(int[] point, int len) {

		int color = paperMap[point[0]][point[1]];
		for (int i = point[0], cnt = 0; cnt < len; i++, cnt++) {
			for (int j = point[1], cnt2 = 0; cnt2 < len; j++, cnt2++) {
				if(paperMap[i][j] != color) {
					return false;
				}
			}
		}

		return true;
	}
}
