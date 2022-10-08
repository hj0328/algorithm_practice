package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Back15652 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		backTracking(N, M, 0);
		System.out.println(sb.toString());
	}

	static LinkedList<Integer> list = new LinkedList<Integer>();
	static StringBuilder sb = new StringBuilder();
	static void backTracking(int N, int M, int cnt) {
		if(cnt == M) {
			
			for (Integer integer : list) {
				sb.append(integer).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1 ; i <= N ; i++) {
			list.add(i);
			backTracking(N, M, cnt+1);
			list.removeLast();
		}
	}
}
