package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 올바른 괄호열의 값
 *
 *	올바른 괄호열 체크
 *	괄호열의 값 계산
 *	여기서 stack이란
 *		괄호 연산의 중간 계산결과와 괄호를 포함하는 자료구조
 *
 *		( 만나면 ( 추가
 *		[ 만나면 [ 추가
 *		) 만나면
 *			stack: 전체 해야할 계산들 추가
 *			stack pop * 2 후 다시 push
 *		] 만나면
 *			stack: 전체 해야할 계산들 추가
 *			stack pop * 3 후 다시 push
 *
 * 	하지만
 *	아이디어가 틀렸다.
 *		스택에 소문자로 계산결과와 괄호를 저장하니 틀렸다.
 *		왜냐하면 소문자와 중간계산 결과가 동일한 경우 잘못된 로직으로 처리된다.
 *
 *  분배법칙으로 계산
 *  	분배법칙으로 계산한다고 가정
 *  	열린 괄호 시 중간 곱셈 결과 값에 곱하기 계산
 *  	닫힌 괄호 시 중간 곱셉 결과 값에 나누기 계산, answer 에 중간 곱셈결과 추가하기
 *			단 연속되는 닫힌 괄호의 경우 계산을 하지 않음.
 *			왜냐하면 매칭되는 열린괄호에서 이미 계산을 한 것이기 때문
 *
 */
public class Baek2504 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] str = br.readLine().toCharArray();

		if(isCorrect(str)) {
			System.out.println(calculateBracket(str));
		} else {
			System.out.println(0);
		}
	}

	static boolean isCorrect(char[] str) {
		Stack<Character> stack = new Stack<>();
		for (Character c : str) {
			if(c == '(' || c == '[') {
				stack.push(c);
			} else if(!stack.empty()){
				if(stack.peek() == '(' && c == ')') {
					stack.pop();
				} else if(stack.peek() == '[' && c == ']') {
					stack.pop();
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		if(!stack.empty()) {
			return false;
		}

		return true;
	}

	static int calculateBracket(char[] str) {
		int answer = 0;
		int multi = 1;
		boolean isUsed = false;
		for (Character c : str) {
			if(c == '(') {
				multi *= 2;
				isUsed = false;
			} else if(c == '[') {
				multi *= 3;
				isUsed = false;
			} else if(c == ')') {
				if(!isUsed) {
					answer += multi;
					isUsed = true;
				}
				multi /= 2;
			} else if(c == ']') {
				if(!isUsed) {
					answer += multi;
					isUsed = true;
				}
				multi /= 3;
			}
		}

		return answer;
	}
}
