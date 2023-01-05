package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek1759 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		set = new HashSet<>();
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');

		isSelected = new boolean[C];
		alphaArr = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphaArr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(alphaArr);

		ans = new StringBuilder();

		backtracking(0, 0);
		System.out.println(ans.toString());
	}

	static int L;
	static int C;
	static char[] alphaArr;
	static Set<Character> set;
	static boolean[] isSelected;
	static StringBuilder ans;

	static void backtracking(int cnt, int idx) {
		if (cnt == L) {

			// 모음 최소 1개, 자음 최소 2개
			int m = 0;
			int z = 0;
			for (int i = 0; i < C; i++) {
				if (!isSelected[i]) {
					continue;
				}

				if (set.contains(alphaArr[i])) {
					m++;
				} else {
					z++;
				}
			}

			if (!(m >= 1 && z >= 2)) {
				return;
			}

			for (int i = 0; i < C; i++) {
				if (!isSelected[i]) {
					continue;
				}

				ans.append(alphaArr[i]);
			}

			ans.append("\n");
			return;
		}

		if (idx == C) {
			return;
		}

		isSelected[idx] = true;
		backtracking(cnt + 1, idx + 1);

		isSelected[idx] = false;
		backtracking(cnt, idx + 1);
	}
}
