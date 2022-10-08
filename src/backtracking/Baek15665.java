package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 	중복 입력은 들어올 수 있지만, 중복 출력은 허용하지 않는다. 
 * 	중복 입력을 배열로 받고 소팅
 * 	이후 백트래킹에서 루프를 돌 때, 중복된 값을 판별하여 스킵
 */
public class Baek15665 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numArr = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numArr);
		backTracking(0, 0, -1);
		System.out.println(sb.toString());
	}

	static int N;
	static int M;
	static LinkedList<Integer> list = new LinkedList<>();
	static int[] numArr;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();
	static void backTracking(int cursur, int cnt, int val) {
		if(cnt == M) {

			for (Integer integer : list) {
				sb.append(integer).append(" ");
			}
			
			sb.append("\n");
			return;
		}
		
		int preNum = -1;
		for (int i = 0; i < N; i++) {
			
			if(preNum == numArr[i]) {
				continue;
			}
			
			preNum = numArr[i];
			list.add(numArr[i]);
			backTracking(i+1, cnt+1, numArr[i]);
			list.removeLast();
		}
	}
}
