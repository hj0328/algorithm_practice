package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * https://www.acmicpc.net/problem/2446
 * @author hjlee
 *
 */
public class Baek2446 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();

		// 그림 윗 부분
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j < i ; j++) {
				sb.append(" ");
			}
			for(int j = 0 ; j <= 2*N - 2*i ; j++ ) {
				sb.append("*");
			}
			sb.append("\n");
		}

		// 그림 아랫 부분
		for(int i = 2 ; i <= N ; i++) {
			//  3 2 1 0 감소 
			for(int j = 0 ; j < N - i ; j++) {
				sb.append(" ");
			}
			// 3 5 7 9 증가
			for(int j = 0 ; j < 2*i - 1 ; j++ ) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
