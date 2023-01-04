package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  백트래킹 기본 - 로또 
 *  S집합에서 K개를 뽑아 6개를 뽑는 경우들을 출력하는 것 
 *  = kC6를 모두 하나하나 출력하기
 *  
 */
public class Baek6603 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = new StringBuilder();

		while (st.hasMoreTokens()) {
			K = Integer.parseInt(st.nextToken());
			if (K == 0) {
				break;
			}

			arrS = new int[K];
			selected = new boolean[K];
			for (int i = 0; i < K; i++) {
				arrS[i] = Integer.parseInt(st.nextToken());
			}

			backtracking(0, 0);
			ans.append("\n");
			st = new StringTokenizer(br.readLine());
		}

		System.out.println(ans.toString());
	}

	static int K;
	static int[] arrS;
	static boolean[] selected;
	static StringBuilder ans;

	// 두 인자에 대한 base condition 잊지 말기
	static void backtracking(int cnt, int idx) {
		if (cnt == 6) {

			for (int i = 0; i < K; i++) {
				if (!selected[i]) {
					continue;
				}

				ans.append(arrS[i]).append(" ");
			}
			ans.append("\n");
			return;
		}

		if (idx == K) {
			return;
		}

		selected[idx] = true;
		backtracking(cnt + 1, idx + 1);

		selected[idx] = false;
		backtracking(cnt, idx + 1);
	}
}
