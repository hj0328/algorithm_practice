package recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	종이의 개수
 */
public class Baek1780 {

	static int[][] paper;
	static int minusCount = 0;
	static int zeroCount = 0;
	static int plusCount = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine());
		paper = new int[size][size];

		for(int y = 0 ; y < size ; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < size; x++) {
				paper[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		recursive(new Point(0, 0), size);
		System.out.println(minusCount);
		System.out.println(zeroCount);
		System.out.println(plusCount);
	}

	static void recursive(Point point, int size) {
		if(checkPaper(point, size)) {
			return ;
		}

		/*
		 * 9개로 자른다.
		 *  1. size/3
		 *  2. 9개의 start point
		 *  3. recursive 수행
		 */

		int smallerSize = size/3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int nx = point.getX() + i * smallerSize;
				int ny = point.getY() + j * smallerSize;

				recursive(new Point(ny, nx), smallerSize);
			}
		}
	}

	// 종이의 일정 구간이 동일한 숫자인지 검사
	static boolean checkPaper(Point point, int size) {
		int firstVal = paper[point.getY()][point.getX()];

		int lenY = point.getY() + size;
		int lenX = point.getX() + size;
		for (int y = point.getY(); y < lenY; y++) {
			for (int x = point.getX(); x < lenX; x++) {
				if(paper[y][x] != firstVal) {
					return false;
				}
			}
		}

		if(firstVal == -1) {
			minusCount++;
		} else if (firstVal == 0) {
			zeroCount++;
		} else {
			plusCount++;
		}
		return true;
	}

	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
	}
}
