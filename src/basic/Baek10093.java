package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10093
 *  - 입력 값 long 타입 처리 
 * @author hjlee
 *
 */
public class Baek10093 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long start = Long.parseLong(st.nextToken());
		long end = Long.parseLong(st.nextToken());
		
		if(Math.abs(start - end) <= 1) {
			System.out.println(0);
			return;
		}

		if(start > end) {
			long temp = end;
			end = start;
			start = temp;
		}
		
		System.out.println(end - start - 1);
		StringBuilder sb = new StringBuilder();
		
		for(long i = start + 1 ; i < end ; i++) {
			sb.append(i).append(" ");
			//System.out.print(i + " ");
		}
		System.out.println(sb.toString());
	}

}
