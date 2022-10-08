package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 	N과 M(5)
 * 	
 */
public class Baek15654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N];
		numArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numArr);
		
		for (int i = 0; i < N; i++) {
			list.add(numArr[i]);
			isSelected[i] = true;
			backTracking(N, M, 1);
			list.removeLast();
			isSelected[i] = false;
		}
		System.out.println(sb.toString());
	}
	
	static int[] numArr;
	
	// 중복 방지
	static boolean[] isSelected;
	
	// 순서 유지
	static LinkedList<Integer> list = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	static void backTracking(int N, int M, int cnt) {
		if(cnt == M) {
			for (Integer integer : list) {
				sb.append(integer).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(isSelected[i]) {
				continue;
			}
			isSelected[i] = true;
			list.add(numArr[i]);
			backTracking(N, M, cnt+1);
			list.removeLast();
			isSelected[i] = false;
		}
	}
}
