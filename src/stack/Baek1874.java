package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

/*
 * 43687521
 *
 * 12345678 ->  ->
 * 2345678 -> 1 ->
 * 345678 -> 12 ->
 * 45678 -> 123 ->
 * 5678 -> 1234 ->
 * 5678 -> 123 -> 4
 * 5678 -> 12 -> 43
 * 678 -> 125 -> 43
 * 78 -> 1256 -> 43
 * 78 -> 125 -> 436
 * 8 -> 1257 -> 436
 *  -> 12578 -> 436
 *  -> 1257 -> 4368
 *  -> 125 -> 43687
 *  -> 12 -> 436875
 *  -> 1 -> 4368752
 *  ->  -> 43687521
 *
 * 문제 특징
 *  1 stack peek은 stack 값 중 제일 큰 수
 *  2 pop된 수는 당시 stack에서 제일 큰 수가 다음 순열에 들어감
 *   2-1 pop 이후 push 가 일어나면 새로운 큰 수가 stack peek이 됨.
 *       다음 순열 포함 제일 큰 수
 *   2-2 pop을 계속하면 내림차순
 *
 *
 */
public class Baek1874 {

	static final String POP = "-\n";
	static final String PUSH = "+\n";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		LinkedList<Integer> nextSeq = new LinkedList<>();
		for(int i = 1 ; i <= N ; i++) {
			nextSeq.add(Integer.parseInt(br.readLine()));
		}

		StringBuilder ans = new StringBuilder();
		Stack<Integer> stack = new Stack<>();

		int seq = 1;
		while(!nextSeq.isEmpty()) {
			if(!stack.empty() && (stack.peek().equals(nextSeq.getFirst()))){
				// pop, nextseq의 첫번째와 stack의 첫번째가 같다면 stack을 팝 해준다.
				// 단 stack이 들어있을때만 가능하다.
				ans.append(POP);
				stack.pop();
				nextSeq.removeFirst();
			} else if(seq <= nextSeq.getFirst()) {
				// push, nextSeq의 첫번째가 최종 결과물 첫 번째 seq이하라면 stack에 seq를 같을때까지 계속 넣어준다.
				while(seq <= nextSeq.getFirst()) {
					ans.append(PUSH);
					stack.push(seq++);
				}
			} else {
				System.out.println("NO");
				return;
			}
		}

		System.out.print(ans.toString());
	}

}
