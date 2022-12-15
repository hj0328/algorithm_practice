package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek7795_twopointer {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCount = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < testCount; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int aSize = Integer.parseInt(st.nextToken());
			int bSize = Integer.parseInt(st.nextToken());
			
			int[] arrA = new int[aSize];
			int[] arrB = new int[bSize];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < aSize; j++) {
				arrA[j] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < bSize; j++) {
				arrB[j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arrA);
			Arrays.sort(arrB);
			
			long ans = 0;
			int point = 0;
			for (int idx = 0; idx < aSize; idx++) {
				while(point < bSize && arrB[point] < arrA[idx]) {
					point++;
				}
				
				ans += point;
			}
			
			System.out.println(ans);
		}
	}

}
