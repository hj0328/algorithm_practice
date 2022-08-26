package linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

/**
 *
 * 키로거
 * https://www.acmicpc.net/problem/5397
 * @author hjlee
 *
 */
public class Baek5397 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// solution1(br);
		solution2(br);
	}

	/*
	 *  방법1. stack 2개를 이용한 방법
	 *   - 두 stack 사이가 cursur
	 */
	static void solution1(BufferedReader br) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		for(int tc = 0 ; tc < TC ; tc++) {
			char[] charArray = br.readLine().toCharArray();

			Stack<Character> left = new Stack<>();
			Stack<Character> right = new Stack<>();
			for (char c : charArray) {
				if(c =='<') {
					if(right.empty()) {
						continue;
					}
					left.push(right.pop());
				} else if(c == '>') {
					if(left.empty()) {
						continue;
					}
					right.push(left.pop());
				} else if(c == '-') {
					if(right.empty()) {
						continue;
					}
					right.pop();
				} else {
					right.push(c);
				}
			}

			while(!right.empty()) {
				left.push(right.pop());
			}

			StringBuilder sb = new StringBuilder();
			while(!left.empty()){
				sb.append(left.pop());
			}

			System.out.println(sb.toString());
		}
	}

	/*
	 *	방법2. ListIterator를 이용한 방법
	 *    - ListIterator가 가리키는 문자가 cursur
	 *    - add 연산은 앞에 어떤 next, previous를 수행하던 해당 element의 앞에 추가
	 */
	static void solution2(BufferedReader br) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		for(int tc = 0 ; tc < TC ; tc++) {
			char[] charArray = br.readLine().toCharArray();

			ListIterator<Character> list = new LinkedList<Character>().listIterator();
			for (char c : charArray) {
				if(c =='<') {
					if(list.hasPrevious()) {
						list.previous();
					}
				} else if(c == '>') {
					if(list.hasNext()) {
						list.next();
					}
				} else if(c == '-') {
					if(list.hasPrevious()) {
						list.previous();
						list.remove();
					}
				} else {
					list.add(c);
				}
			}

			StringBuilder sb = new StringBuilder();
			while(list.hasPrevious()) {
				list.previous();
			}

			while(list.hasNext()) {
				sb.append(list.next());
			}

			System.out.println(sb.toString());
		}
	}
}
