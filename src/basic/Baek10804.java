package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 카드 역배치
 * https://www.acmicpc.net/problem/10804 
 * @author hjlee
 *
 */
public class Baek10804 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[21];
		for(int i = 1 ; i <= 20 ; i++) {
			arr[i] = i;
		}
		
		int[] temp = new int[21];
		for(int i = 0 ; i < 10 ; i++) {		
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int len = end - start;
			for(int j = start ; j <= end ; j++) {
				temp[j] = arr[j];
			}
			
			for(int j = start ; j <= end ; j++) {
				arr[j] = temp[j + len];
				len -= 2;
			}
		}
		
		for(int i = 1 ; i <= 20 ; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
