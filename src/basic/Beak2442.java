package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 별 찍기 - 5
 *  - 예제 출력에 빈 칸 확인 꼼꼼히하기
 * https://www.acmicpc.net/problem/2442
 * @author hjlee
 *
 */
public class Beak2442 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= N ; i++) {

			for(int j = 1 ; j <= N - i ; j++) {
				sb.append(" ");
			}
			
			for(int j = 1 ; j <= (i*2 - 1); j++) {
				sb.append("*");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
