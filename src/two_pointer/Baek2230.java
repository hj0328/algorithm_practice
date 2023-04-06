package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  주어지는 수열에서 두 수의 차이가 M이상이면서 제일 작은 경우를 구하는 프로그램
 *  문제 설명이 조금 이상하지만 예제를 보면 M이상이면서 제일 작은 수를 구하면 된다.
 *  
 *  투포인터
 *  수열을 소팅 후 투포인터를 적용하여 M이상이며 제일 작은 수 구하기
 *  left: 작은 수
 *  right: 큰 수
 */
public class Baek2230 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		Arrays.sort(arr);

		int l = 0;
		int r = 0;

		long minVal = Long.MAX_VALUE;
		while (l <= r && r < N) {
			long val = arr[r] - arr[l];

			if (val >= M) {
				if (minVal > val) {
					minVal = val;
				}
				l++;
			} else {
				r++;
			}
		}
		System.out.println(minVal);
	}

}
