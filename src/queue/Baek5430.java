package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * R: 배열 뒤집기
 * D: 첫 째수 버리기, 비어있으면 에러
 *
 *	1. split보다 StringTokenizer
 *		delimiter를 이용하여 더 쉽게 분리
 *	2. 배열 뒤집기는 마지막에 수행
 *		해당 문제에서 마지막에 배열 뒤집기 수행가능
 *		삭제 기능이 배열에서 끝에서만 수행되기 때문
 *	3.
 */
public class Baek5430 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		for(int tc = 0 ; tc < TC ; tc++) {
			char[] cmds = br.readLine().toCharArray();

			int len = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");

			Deque<Integer> queue = new LinkedList<>();
			while(st.hasMoreElements()) {
				queue.add(Integer.parseInt(st.nextToken()));
			}

			boolean flag = false;
			// 1 이 정방향, 0은 반대방향
			int order = 1;
			for (char cmd : cmds) {
				if(cmd == 'R') {
					order = 1 - order;
				} else {
					// D일때만 에러 발생
					if(len == 0) {
						ans.append("error").append("\n");
						flag = true;
						break;
					}
					if(order == 1) {
						queue.pollFirst();
					} else {
						queue.pollLast();
					}
					len--;
				}
			}

			if(flag) {
				continue;
			}

			StringBuilder sb = new StringBuilder();
			while(!queue.isEmpty()) {
				// 이 방법도 처음에는 생각지 못함
				if(order == 1) {
					sb.append(queue.pollFirst());
				} else {
					sb.append(queue.pollLast());
				}
				if(queue.size() != 0) {
					sb.append(",");
				}
			}
			ans.append("[").append(sb).append("]\n");
		}
		System.out.println(ans);
	}
}
