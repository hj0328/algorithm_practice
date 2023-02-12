package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	팰린드롬?
 * 		수열이 팰린드롬인지 알아내는 문제 
 * 		팰린드롬이란, 읽는 방향을 앞 또는 뒤로 읽어도 같은 문자를 말한다. 
 * 
 * 	시간제한 0.5초
 * 
 * 	특징 
 * 		팰린드롬 특징을 이용하여 중복되는 연산을 줄여야 한다.
 * 		다양한 예제를 직접 만들어보고 빠진 로직이 있는지 주의해야한다. 
 * 		ex) 팰린드롬의 길이가 홀수, 짝수인 경우에 따라 나눠서 생각해야 된다. 
 * 		그렇지 않으면 틀림. 틀리는건 물론 로직을 잘못짜게 된다.  
 */
public class Baek10942 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boolean[][] isPelim = new boolean[N][N];
		for (int s = 0; s < N; s++) {
			isPelim[s][s] = true;
		}

		for (int s = 0; s < N - 1; s++) {
			if (arr[s] == arr[s + 1]) {
				isPelim[s][s + 1] = true;
			}
		}

		// 모든 dist에 대해
		for (int dist = 2; dist < N; dist++) {
			// s(start) 시작점에서 dist 간격만큼 펠린드롬인지 검사
			for (int s = 0; s + dist < N; s++) {
				// s ~ s+dist 가 펠린드롬이라면, true
				if (isPelim[s + 1][s + dist - 1] && arr[s] == arr[s + dist]) {
					isPelim[s][s + dist] = true;
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;

			if (isPelim[s][e]) {
				sb.append("1\n");
			} else {
				sb.append("0\n");
			}
		}

		System.out.print(sb.toString());
	}

}
