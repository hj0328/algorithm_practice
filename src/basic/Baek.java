package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 별찍기 특징 정리 
 * @author hjlee
 *
 */
public class Baek {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();

		
		for(int i = 1 ; i <= N ; i++) {

			for(int j = 1 ; j <= i ; j++) {
				sb.append("*");
			}
			
			for(int j = 1 ; j <= 2*N - 2*i ; j++ ) {
				sb.append(" ");
			}
			
			for(int j = 1 ; j <= i ; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}

		for(int i = 1 ; i < N ; i++) {

			for(int j = 1 ; j <= N - i ; j++) {
				sb.append("*");
			}
			
			for(int j = 1 ; j <= 2*i ; j++ ) {
				sb.append(" ");
			}
			
			for(int j = 1 ; j <= N - i ; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		
		System.out.print(sb.toString());
	}

}
