package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  보물 
 *  배열 A와 B에 있는 각 원자를 곱하고, 그 결과들을 모두 더하여 가장 작은 수를 구하기
 *  배열 A만 순서를 바꿀 수 있다. 
 *  
 *  접근 
 *  	가장 작은 수가 되려면 각 원소도 결과 값이 최소가 되어야 함 
 *  
 * 	A = 6 1 1 1 0 
 * 	B = 1 2 3 7 8
 * 	값 = 6 2 3 7 = 18 
 * 	가장 큰 수에 가장 작은 수를 곱하기 
 */

public class Baek1026 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] B = new int[N];

		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = -Integer.parseInt(st1.nextToken());
			B[i] = Integer.parseInt(st2.nextToken());
		}
		Arrays.sort(A);
		Arrays.sort(B);

		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += -A[i] * B[i];
		}

		System.out.println(ans);
	}

}
