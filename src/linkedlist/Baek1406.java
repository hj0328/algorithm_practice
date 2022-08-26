package linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * 에디터
 * https://www.acmicpc.net/problem/1406
 *
 * 3가지 풀이 방법 존재
 * 	1. Node 클래스를 만들어 직접 linkedlist 구현(현 풀이)
 *	   - Node 클래스에 left node와 right node를 이용하여 linkedlist구현
 *	   - 삭제, 추가, 모든 node 출력 시에도 직접 구현
 *	   -> 단점: 구현 시간이 더 오래 걸리고, 실수할 확률이 있다.
 *	   -> 장점: 직접 구현해보니 좋다. 실행속도가 가장 빠르다.
 *
 *  2. ListIterator 이용
 *	   - 단방향인 linkedlist와 다르게 양방향 링크드 리스트
 *     - 내부적으로 element를 가리키는 cursor가 존재, 방법1의 cursur는 element 사이를 가리킴
 *	   - 이미 존재하는 라이브러리로 효율적
 *
 *  3. 2개 stack을 이용한 구현
 *	   - 2개 stack을 이용하다보니 생각하기 더 쉽고 간단함
 *
 * @author hjlee
 *
 */
public class Baek1406 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 방법1. 링크드리스트 직접 구현
		// solution1(br);

		// 방법2. ListIterator 용
		//solution2(br);

		// 방법3. stack 자료구조 2개 사
		solution3(br);
	}

	static void solution1(BufferedReader br) throws IOException {
		Node cursur = new Node();
		char[] words = br.readLine().toCharArray();
		for (char c : words) {
			Node newNode = new Node(c);
			Node left = cursur.left;
			if(left != null) {
				left.right = newNode;
				newNode.left = left;
			}
			cursur.left = newNode;
		}

		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String cmd = st.nextToken();
			if(cmd.equals("L")) {
				Node left = cursur.left;
				if(left == null) {
					continue;
				}
				cursur.left = left.left;
				cursur.right = left;
			} else if(cmd.equals("D")) {
				Node right = cursur.right;
				if(right == null) {
					continue;
				}
				cursur.right = right.right;
				cursur.left = right;
			} else if(cmd.equals("B")) {
				Node left = cursur.left;
				if(left == null) {
					continue;
				}

				Node right = cursur.right;
				if(right != null) {
					right.left = left.left;
				}
				if(left.left != null) {
					left.left.right = right;
				}

				//left.right = null;
				cursur.left = left.left;
				//left.left = null;
			} else if(cmd.equals("P")) {
				String addChar = st.nextToken();
				Node newNode = new Node(addChar.charAt(0));

				Node left = cursur.left;
				if(left != null) {
					left.right = newNode;
					newNode.left = left;
				}
				cursur.left = newNode;

				Node right = cursur.right;
				if(right != null) {
					right.left = newNode;
					newNode.right = right;
				}
			}
		}
		print(cursur);
	}

	static class Node {
		Node left;
		Node right;
		char val;
		public Node(char val) {
			this.val = val;
		}

		public Node() {
		}
	}

	static LinkedList<Character> list = new LinkedList<>();
	static void print(Node cursur) {
		Node left = cursur.left;
		while(left != null) {
			cursur.left = left.left;
			cursur.right = left;
			left = cursur.left;
		}

		StringBuilder sb = new StringBuilder();
		Node right = cursur.right;
		while(right != null) {
			sb.append(right.val);
			right = right.right;
		}

		System.out.println(sb.toString());
	}

	static void solution2(BufferedReader br) throws IOException {
		LinkedList<Character> list = new LinkedList<>();

		char[] charArray = br.readLine().toCharArray();
		for (char c : charArray) {
			list.add(c);
		}

		ListIterator<Character> listIterator = list.listIterator();
		while(listIterator.hasNext()) {
			listIterator.next();
		}

		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String cmd = st.nextToken();
			if(cmd.equals("L")) {
				if(listIterator.hasPrevious()) {
					listIterator.previous();
				}
			} else if(cmd.equals("D")) {
				if(listIterator.hasNext()) {
					listIterator.next();
				}
			} else if(cmd.equals("B")) {
				if(listIterator.hasPrevious()) {
					listIterator.previous();
					listIterator.remove();
				}
			} else if(cmd.equals("P")) {
				String addChar = st.nextToken();
				listIterator.add(addChar.charAt(0));
			}
		}

		while(listIterator.hasPrevious()) {
			listIterator.previous();
		}

		StringBuilder sb = new StringBuilder();
		while(listIterator.hasNext()) {
			sb.append(listIterator.next());
		}

		System.out.println(sb.toString());
	}

	static void solution3(BufferedReader br) throws IOException {
		LinkedList<Character> list = new LinkedList<>();

		char[] charArray = br.readLine().toCharArray();

		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		for (Character c : charArray) {
			left.push(c);
		}

		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if(cmd.equals("L")) {
				if(left.empty()) {
					continue;
				}
				right.push(left.pop());
			} else if(cmd.equals("D")) {
				if(right.empty()) {
					continue;
				}
				left.push(right.pop());
			} else if(cmd.equals("B")) {
				if(left.empty()) {
					continue;
				}

				left.pop();
			} else if(cmd.equals("P")) {
				String addChar = st.nextToken();
				left.push(addChar.charAt(0));
			}
		}

		StringBuilder sb = new StringBuilder();
		while(!left.empty()) {
			right.add(left.pop());
		}
		while(!right.empty()) {
			sb.append(right.pop());
		}

		System.out.println(sb);
	}
}
