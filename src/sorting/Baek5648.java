package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 역원소 정렬 
 *  입력받은 원소를 역으로 바꾼 후 정렬하여 출력함 
 *  앞자리가 0인 경우 생략 
 *  500 -> 5
 *  123 -> 321
 *  501 -> 105
 */
public class Baek5648 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long[] arr = new long[N];
		int idx = 0;
		while(st.hasMoreTokens()) {
			arr[idx++] = Long.parseLong(st.nextToken());
		}
		
		while(idx != N) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				arr[idx++] = Long.parseLong(st.nextToken());
			}
		}
		
		// 뒤집기 
		for (int i = 0; i < arr.length; i++) {
			arr[i] = reverseVal(arr[i]);
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	/*
	 * val 값을 뒤집어서 리턴한다.
	 * 123 -> 321
	 * 50 -> 5 
	 * 
	 *  123
	 *  3 -> 32 -> 321 
	 */
	static long reverseVal(long arr) {
		long ret = 0;
		boolean flag = false;
		while(arr != 0) {
			long reminder = arr % 10;
			if(reminder != 0) {
				flag = true;
			}
			
			if(flag) {
				ret += reminder;
				ret *= 10;
			}
			arr = arr / 10;
		}

		return ret/10;
	}
}

