package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M(1) 백트래킹 기본 문제
public class Baek15649 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N+1];
		selected = new int[N+1];
		
		ans = new StringBuilder();
		
		backtracking(0);
		
		System.out.println(ans.toString());
	}

	// 현재 중복없이 (selectIdx+1) 개 만큼 골랐을 때 수열들을 구하기
	static int N;
	static int M;
	
	// isSelected[i]: 자연수 i를 선택했는지 안했는지 true, false
	static boolean[] isSelected;
	
	// selected[idx]: 백트래킹 중 만났던 자연수 순서대로 배열에 저장
	static int[] selected;
	static StringBuilder ans;
	static void backtracking(int selectIdx) {
		if(selectIdx == M) {
			
			for (int i = 0; i < M; i++) {
				ans.append(selected[i]).append(" ");
			}
			ans.append("\n");
			
			return;
		}
		
		for(int num = 1 ; num <= N ; num++) {
			if(isSelected[num]) {
				continue;
			}
			
			isSelected[num] = true;
			selected[selectIdx] = num;
			backtracking(selectIdx+1);
			isSelected[num] = false;
		}
	}
}
