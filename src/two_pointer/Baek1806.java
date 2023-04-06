package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 부분합(골드4)
 * 
 * 	투포인터와 부분합 응용
 * 	
 * 	주의
 * 	 - left, right 포인터 각각이 범위에 포함되는지 여부 
 * 	 - left, right이 같은 경우
 * 
 */
public class Baek1806 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		int l = -1;
		long lSum = 0;

		int r = 0;
		long rSum = arr[0];

		int minSeq = Integer.MAX_VALUE;
		while (l <= r) {
			long val = rSum - lSum;
			if (val >= S) {
				// 부분합 합이 S이상 중 가장 짧은 것 구하기
				// l는 아직 선택 안함 
				if (l == -1) {
					l++;

					if (minSeq > r + 1) {
						minSeq = r + 1;
					}
					continue;
				}

				// l는 범위 포함 안됨, r는 범위 포함됨
				if (minSeq > r - l + 1) {
					minSeq = r - l + 1;
				}

				lSum += arr[l];
				l++;
			} else {

				if (r + 1 >= N) {
					break;
				}
				// 위치 아래로 이동
				r++;
				rSum += arr[r];
			}
		}

		if (minSeq == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(minSeq);
		}
	}

}
