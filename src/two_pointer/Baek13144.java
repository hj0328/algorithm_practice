package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek13144 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] visited = new boolean[100001];
		visited[0] = true;
		int s = 0;
		int e = 0;
		long ans = 0;
		while (s < N) {
			while (e < N) {
				if(visited[arr[e]]) {
					break;
				}
				visited[arr[e]] = true;
				e++;
			}

			ans += e - s;
			visited[arr[s]] = false;
			s++;
		}

		System.out.println(ans);
	}

}
