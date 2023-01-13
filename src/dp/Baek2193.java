package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	이친수
 * 	N자리의 수에서 0과 1로 구성된 특정 규칙이 있는 수 
 * 	규칙1. 0으로 시작 안됨
 * 	규칙2. 연속 1이 있으면 안됨 
 * 
 * 	N을 1부터 5까지 적어보며 각 이친수 간에 규칙이 있는지 확인 가능 
 *  
 *  단, 입력으로 주어지는 범위가 90까지 크기 때문에 int 형은 불가하여 long 타입사용
 * 
 */
public class Baek2193 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1 - 90
		int N = Integer.parseInt(br.readLine());

		long[][] d = new long[N + 1][2];
		d[1][0] = 0;
		d[1][1] = 1;

		for (int i = 2; i <= N; i++) {
			d[i][0] = d[i - 1][0] + d[i - 1][1];
			d[i][1] = d[i - 1][0];
		}
		
		System.out.println(d[N][0] + d[N][1]);
	}
}
