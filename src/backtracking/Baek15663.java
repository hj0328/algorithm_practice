package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek15663 {

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
			
			if(isSelected[i] || preNum == numArr[i]) {
				continue;
			}
			
			isSelected[i] = true;
			preNum = numArr[i];
			list.add(numArr[i]);
			backTracking(i+1, cnt+1, numArr[i]);
			list.removeLast();
			isSelected[i] = false;
		}
	}
}
