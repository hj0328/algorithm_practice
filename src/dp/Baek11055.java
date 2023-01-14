package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  DP의 최장 증가 부분 수열 LIS(Longest Increasing Subsequence)에서 살짝 달라진 문제. 
 *  LIS는 가장 긴 부분 수열이지만, 이 문제는 부분 수열 합이 가장 큰 경우의 값을 찾는 문제 
 *  시간 복잡도: O(N^2)
 *  
 *  dp 정의
 *  d[i]: i 번째로 끝나는 수열에서 부분 수열의 합이 가장 큰 경우의 값
 *  단 i 이전의 모든 값들을 확인해야 해야함
 *  
 *  간단히 살펴봤을 때 이진탐색이랑 섞어서 문제가 나오기도 하는 것 같다. 
 *  참고: https://chanhuiseok.github.io/posts/algo-49/
 */
public class Baek11055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] d = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// 증가하는 원소에 대해서만
				if (arr[j] >= arr[i]) {
					continue;
				}

				// 최대 값이 증
				if (d[i] < d[j]) {
					d[i] = d[j];
				}
			}
			d[i] += arr[i];
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			if (max < d[i]) {
				max = d[i];
			}
		}

		System.out.println(max);
	}

}
