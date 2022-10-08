package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek15656 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		numArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numArr);
		backTracking(N, M, 0, 0);
		System.out.println(sb.toString());

	}

	static LinkedList<Integer> list = new LinkedList<>();
	static int[] numArr;
	static StringBuilder sb = new StringBuilder();
	static void backTracking(int N, int M, int cursur, int cnt) {
		if(cnt == M) {

			for (Integer integer : list) {
				sb.append(integer).append(" ");
			}
			
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			list.add(numArr[i]);
			backTracking(N, M, i+1, cnt+1);
			list.removeLast();
		}
	}
}
