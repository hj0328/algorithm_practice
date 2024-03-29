package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1182_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		ans = 0;
		backtracking(0, 0);
		if(S == 0) {
			ans--;
		}
		System.out.println(ans);
	}

	static int S;
	static int N;
	static int ans;
	static int[] arr;

	static void backtracking(int sum, int idx) {

		if (idx == N) {
			if (sum == S) {
				ans++;
			}
			return;
		}

		backtracking(sum, idx + 1);

		sum += arr[idx];
		backtracking(sum, idx + 1);
	}
}
