package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek15650 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 1 ~ N까지 수에서 M의 개의 수를 중복없이 고르기 
		isSelected = new boolean[N+1];
		
		
		for (int i = 1; i <= N; i++) {
			isSelected[i] = true;
			backtracking(N, M, 1, i);
			isSelected[i] = false;
		}
	}
	
	/*
	 *  1~N개 수에서 M개 수를 중복없이 선택하며 오름차순으로 출력 
	 *  cursur: N개 수 중 가장 마지막 확인한 수를 가리킴
	 *  cursur 이후의 숫자만 선택하고 M개의 수를 모두 선택했다면 오름차순으로 숫자들을 선택하게 된다.
	 */
	static boolean[] isSelected;
	static void backtracking(int N, int M, int cnt, int cursur) {
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 1; i <= N; i++) {
				if(!isSelected[i]) {
					continue;
				}
				sb.append(i).append(" ");
			}
			System.out.println(sb.toString());
			return;
		}
		
		for (int i = cursur+1; i <= N; i++) {
			isSelected[i] = true;
			backtracking(N, M, cnt+1, i);
			isSelected[i] = false;
		}
	}
}
