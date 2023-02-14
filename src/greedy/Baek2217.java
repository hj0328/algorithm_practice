package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 	로프 
 * 	N개의 로프가 주어진다. 각 로프는 버틸 수 있는 중량이 서로 다르다.
 * 	k개를 선택하여 병렬로 무게를 들 수 있다. 그럼 동일하게 무게가 분산된다. 
 * 	로프가 주어질 때 버틸 수 있는 최대 중량 구하기
 * 
 * 	접근 
 *	1. 모든 로프를 선택하고 무게를 측정하는 방법
 *		10^9으로 시간초과나기 때문에 다른 방법필요 
 *
 *	2. 가장 무거운 중량을 버틸 수 있는 로프부터 가벼운 로프까지 k개 검사
 *		k개 루프가 선택되면, 버틸 수 있는 중량은 가장 약한 중량 * k개이기 때문에 가능
 *
 */
public class Baek2217 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 10^5
		int N = Integer.parseInt(br.readLine());

		int[] ropes = new int[N];
		for (int i = 0; i < N; i++) {
			ropes[i] = -Integer.parseInt(br.readLine());
		}

		Arrays.sort(ropes);

		int ans = 0;
		for (int k = 1; k <= N; k++) {
			int val = k * -ropes[k - 1];
			if (ans < val) {
				ans = val;
			}
		}

		System.out.println(ans);
	}

}
