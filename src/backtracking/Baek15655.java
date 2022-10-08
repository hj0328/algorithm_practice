package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek15655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		numArr = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numArr);
		backTracking(N, M, 0, 0);
		System.out.println(sb.toString());
	}

	static boolean[] isSelected;
	static int[] numArr;
	static StringBuilder sb = new StringBuilder();
	static void backTracking(int N, int M, int cursur, int cnt) {
		if(cnt == M) {
			
			for (int i = 0; i < N; i++) {
				if(!isSelected[i]) {
					continue;
				}
				
				sb.append(numArr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = cursur; i < N; i++) {
			isSelected[i] = true;
			backTracking(N, M, i+1, cnt+1);
			isSelected[i] = false;
		}
	}
}
