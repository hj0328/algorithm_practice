package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek1931 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayList<int[]> roomList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			roomList.add(new int[] { s, e });
		}

		// e 기준으로 오름차순 소팅
		Collections.sort(roomList, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] != o2[1]) {
					return o1[1] - o2[1];
				}

				return o1[0] - o2[0];
			}
		});

		int count = 1;
		int[] lastRoom = roomList.get(0);
		for (int i = 1; i < N; i++) {

			int[] select = roomList.get(i);
			if (lastRoom[1] > select[0]) {
				continue;
			}

			count++;
			lastRoom = select;
		}

		System.out.println(count);
	}

}
