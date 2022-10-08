package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 *  중복 입력은 들어올 수 있지만, 중복 출력은 허용하지 않는다.
 *   - N개 중 M개를 선택 
 *   - 같은 수를 여러 번 골라도 됨
 *   - 고른 수는 비내림 차순
 *   
 *   sol
 *   입력 값을 set으로 받아 중복 값 제거
 *   같은 값을 중복 선택이 가능하게 로직 수정  
 */
public class Baek15666 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		HashSet<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		N = set.size();
		numArr = new int[set.size()];
		isSelected = new boolean[set.size()];
		ArrayList<Integer> list = new ArrayList<>(set);
		for (int i = 0; i < N; i++) {
			numArr[i] = list.get(i);
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
		
		for (int i = cursur; i < N; i++) {
			
			list.add(numArr[i]);
			backTracking(i, cnt+1, numArr[i]);
			list.removeLast();
		}
	}
}
