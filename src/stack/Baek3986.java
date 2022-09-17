package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 *
 * 단어가 좋은 글자인지 판별하는 문제
 * 좋은 단어
 * 		A, B문자를 아치형으로 연결 시 겹치는 것이 없을 때 좋은 단어
 * 		모든 A, B는 이렇게 쌍으로 연결이 되어야 함
 *
 */
public class Baek3986 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int wordCount = Integer.parseInt(br.readLine());
		int goodWordCount = 0;
		while(wordCount-- > 0) {
			boolean isGood = true;
			Stack<Character> stack = new Stack<>();
			char[] chars = br.readLine().toCharArray();
			for (char c : chars) {
				if(stack.empty() || stack.peek() != c) {
					stack.push(c);
				} else if(stack.peek() == c) {
					stack.pop();
				} else {
					isGood = false;
					break;
				}
			}

			if(!isGood || !stack.empty()) {
				continue;
			}

			goodWordCount++;
		}
		System.out.println(goodWordCount);
	}
}
