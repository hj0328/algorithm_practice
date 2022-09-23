package recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  하노이탑 이동순서
 *
 *  정리
 *   - 대표적인 재귀함수 문제
 *   - 문제의 특징을 파악해서 필요한 함수와 함수의 인자를 정의한다.
 *   - 참고로, 백트래킹 알고리즘과 다른 점은 모든 가능성을 다 찾는 것이 아니다.
 *
 *  참고: https://st-lab.tistory.com/96
 */
public class Baek11729 {

	static int topCnt;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		topCnt = Integer.parseInt(br.readLine());

		System.out.println((1<<topCnt)-1);
		recursive(topCnt, 1, 3);
		System.out.println(sb.toString());
	}

	// n개의 원판을 기둥 a에서 기둥 b로 이동하는 방법을 출력하는 함수
	static void recursive(int n, int a, int b) {
		if(n == 1) {
			sb.append(a).append(" ").append(b).append("\n");
			return;
		}

		// n-1개 원판을 a 기둥에서 6-a-b 기둥으로 이동하는 방법 출력
		recursive(n-1, a, 6-a-b);

		// 1개 원판을 a 기둥에서 b기동으로 이동 출력
		sb.append(a).append(" ").append(b).append("\n");

		// n-1개 원판을 6-a-b기둥에서 b기둥으로 이동하는 방법 출력
		recursive(n-1, 6-a-b, b);
	}

}
