package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 가장 긴 짝수 연속한 부분 수열 (large)
 * https://www.acmicpc.net/problem/22862
 * 
 * 알고리즘: 투포인터 
 * 
 * 문제푸는 데 제한시간 내에 못품. 다시 풀어야 할 문제
 */
public class Baek22862 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int s = 0;
		int e = -1;
		int deleteCount = 0;
		int maxLen = 0;

		while (e < N && s < N) {

			if (deleteCount < K) {
				// 삭제 횟수가 더 남았나?
				e++;
				if (e < N && (arr[e] & 1) == 1) { // 홀수라면 삭제
					deleteCount++;
				}

				if (e == N) {
					break;
				}
			} else {
				// 더 삭제 불가
				if (e + 1 < N && (arr[e + 1] & 1) == 0) {
					// e+1이 짝수인 경우에만 e는 이동 가능
					e++;
					// 홀수면? 더 이상 이동불가. 길이 검사, 이후 s 증가
				} else {
					if (s < N && (arr[s] & 1) == 1) {
						deleteCount--;
					}
					s++;
				}
			}

			int len = e - s + 1 - deleteCount;
			if (maxLen < len) {
				maxLen = len;
			}
			
		}

		System.out.println(maxLen);
	}

}
