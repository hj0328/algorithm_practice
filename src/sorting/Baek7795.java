package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 	먹을 것인가 먹힐 것인가
 * 	알고리즘 효율: 소팅 < 바이너리 < 투포인터
 * 
 *  해결 방법: lowerBound binary search
 *  
 *  1. 배열 A, B를 소팅
 *  2. 배열 A의 값을 기준으로 배열 B에서 A값이상이 되는 인덱스를 찾으면 된다. 
 *  
 *  후기 
 *  오랜만에 바이너리 문제를 풀어서 lower bound 를 조금 헷갈렸다. 
 *  투 포인터 방법으로 풀려다가 이 방법도 오랜만이라 헷갈려서 다시 풀어봐야 겠다. 
 *  
 */
public class Baek7795 {

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
			for (int idx = 0; idx < aSize; idx++) {
				int upperBoundIdx = getLowerBound(arrB, arrA[idx]);
				ans += upperBoundIdx;
			}
			
			System.out.println(ans);
		}
	}
	
	// arrA의 target보다 작은 인덱스 중 arrB 인덱스
	static int getLowerBound(int[] arrB, int target) {
		
		int left = 0;
		int right = arrB.length;
		
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(arrB[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return right;
	}

}
