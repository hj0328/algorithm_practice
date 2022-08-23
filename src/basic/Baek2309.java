package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2309
 *  - List 보다는 배열을 사용하여 루프 줄이기
 * @author hjlee
 *
 */
public class Baek2309 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[9];
		int total = 0;
		for(int i = 0 ; i < 9 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			total += val;
			
			arr[i] = val;
		}
		
		boolean find = false; 
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = i + 1 ; j < 9 ; j++) {
				int val = total - arr[i] - arr[j];
				if(val == 100) {
					find = true;
					arr[i] = 0;
					arr[j] = 0;
					break;
				}
			}
			if(find) {
				break;
			}
		}
		
		Arrays.sort(arr);
		for(int i = 2 ; i < 9 ; i++) {
			System.out.println(arr[i]);
		}
	}
}




