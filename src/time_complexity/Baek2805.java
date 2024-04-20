package time_complexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2805 {

	static int[] trees;
	static long M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1 ~ 10^6
		int treeCount = Integer.parseInt(st.nextToken());
		
		// 1 ~ 2*10^9
		M = Integer.parseInt(st.nextToken());

		trees = new int[treeCount];
		st = new StringTokenizer(br.readLine());
		int maxLen = 0;
		for(int i = 0 ; i < treeCount ; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			if(maxLen < trees[i]) {
				maxLen = trees[i];
			}
		}
		
		binarySearch(maxLen);
	}
	
	// 자른 나무 합이 최소 M 이상이 되는 최대 높이 구하기
	static void binarySearch(int maxLen) {
		// l(left), r(right)은 모두 구간 포함
		long l = 0;
		long r = maxLen;
		
		// l과 r이 같아질 때 루프 종료
		while(l < r) {
			long mid = (l + r)/2;
			
			// 자른 나무 길이
			long sum = getSum(mid);
			if(M <= sum) {
				// 처음으로 M 이상이 되는 높이
				l = mid + 1;
			} else {
				// 자른 나무 길이가 목표 이하인 경우의 절단기 높이 낮춰 자를 나무 길이 늘리기
				// treeLen == M인 애중 최대 높이를 구해야 함. - 높이가 증가하는 방향
				
				r = mid;
			}
		}
		
		// sum 보다 커지는 M의 upper bound이기 때문에 -1 처리
		System.out.println(r-1);
	}

	static long getSum(long h) {
		long len = 0;
		for(int i = 0 ; i < trees.length; i++  ) {
			long cut = trees[i] - h;
			if( cut > 0) {
				len += cut;
			}
		}
		
		return len;
	}
}
