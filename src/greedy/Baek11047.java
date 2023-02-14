package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	동전 0
 * 	N종류의 동전으로 M 금액 만드는데 필요한 최소 동전 구하기
 *  동전의 개수는 매우 많다. 단 i > 1 일 때, i번째 동전은 i-1번째 동전의 배수다.
 * 	
 * 	그리기 알고리즘
 * 		금액 M보다 작은 동전 중 가장 큰 동전을 선택한다. 
 * 		'i번째 동전은 i-1번째 동전의 배수'조건이 없다면 귀류법으로 풀 수 없음.. 예외 존재하기 때문
 * 	
 * 
 * 	주의 
 * 		테스트를 여러가지 안하니 1트에 틀렸다. 
 * 		- 루프 거꾸로 수행 시, 시작과 끝 반대임으로 조심하기
 */
public class Baek11047 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] coins = new int[N];
		for (int i = 0; i < N; i++) {
			int c = Integer.parseInt(br.readLine());
			coins[i] = c;
		}

		int val = M;
		int totalCount = 0;
		while (val != 0) {
			int large = N - 1;
			for (; large >= 0; large--) {
				if (coins[large] <= val) {
					break;
				}
			}

			int count = val / coins[large];
			val = val - count * coins[large];
			totalCount += count;
		}

		System.out.println(totalCount);
	}

}
