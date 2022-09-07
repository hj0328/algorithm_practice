package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/2493
 * 1. 서로 다른 높이 N개의 탑
 * 2. 탑 꼭대기에서 왼쪽으로 레이저 발사
 * 3. 발사된 레이저는 하나의 탑에서만 수신
 * 4. 레이저를 수신 못하는 케이스 존재
 *
 * 각 탑에서 발사한 레이저는 어떤 탑에서 수신하는지 알아내기
 *
 * 여러 케이스로 stack이 들고 있을 데이터가 어떤 의미를 갖는지 분석
 *
 */
public class Baek2493 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] hs = new int[N+1];
		for(int i = 1 ; i <= N ; i++) {
			hs[i] = Integer.parseInt(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		for(int i = 1 ; i <= N ; i++) {
			int h = hs[i];

			if(stack.empty()) {
				stack.push(i);
				sb.append(0).append(" ");
			} else if(hs[stack.peek()] < h) {
				while(!stack.empty() && hs[stack.peek()] < h) {
					stack.pop();
				}

				if(!stack.empty()) {
					sb.append(stack.peek()).append(" ");
				} else {
					sb.append(0).append(" ");
				}
				stack.push(i);
			} else if(hs[stack.peek()] > h) {
				sb.append(stack.peek()).append(" ");
				stack.push(i);
			}
		}

		System.out.println(sb.toString());
	}

}
