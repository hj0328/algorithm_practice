package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Back15651 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		backTracking(N, M, 0, 1);
		System.out.println(sb.toString());
	}
	
	/*
	 * 1~N자리 중 M개의 수를 중복을 허용하여 선택하는데 오름차순으로 출력한다. 
	 */
	static LinkedList<Integer> list = new LinkedList<Integer>();
	static StringBuilder sb = new StringBuilder();
	static void backTracking(int N, int M, int cnt, int cursur) {
		if(cnt == M) {

			for (Integer integer : list) {
				sb.append(integer).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = cursur ; i <= N ; i++) {
			list.add(i);
			backTracking(N, M, cnt+1, i);
			list.removeLast();
		}
	}
}
