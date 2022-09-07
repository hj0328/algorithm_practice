package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 *  팁
 *   - print함수를 만들어 중간 과정을 확인
 *   - 변수명을 더 확실히 해서 헷갈렸던 시간 줄이기
 *   - 계획을 좀 더 구체적으로 생각하기
 */
public class Baek1021 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int qSize = Integer.parseInt(st.nextToken());
		LinkedList<Integer> list = new LinkedList<>();
		for(int i = 1 ; i <= qSize ; i++) {
			list.add(i);
		}

		int deleteCnt = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int moveCnt = 0;
		for(int i = 0 ; i < deleteCnt ; i++) {
			int target = Integer.parseInt(st.nextToken());
			Integer cur = list.get(0);
			if(cur.equals(target)) {
				list.remove(0);
			} else {
				int targetIdx = 0;
				for (int j = 0 ; j < list.size() ; j++) {
					if(list.get(j).equals(target)) {
						targetIdx = j;
						break;
					}
				}

				// 오른족으로 검사
				int rightCnt = list.size() - targetIdx;

				// 왼쪽 검사
				int leftCnt = targetIdx;
				if(rightCnt > leftCnt) {
					moveLeft(target, list);
					moveCnt += leftCnt;
				} else {
					moveLeft(target, list);
					moveCnt += rightCnt;
				}
				list.remove(0);
			}
		}

		System.out.println(moveCnt);

	}

	static void moveLeft(int target, LinkedList<Integer> list) {
		while(!list.get(0).equals(target)) {
			list.add(list.removeFirst());
		}
	}

	static void moveRight(int target, LinkedList<Integer> list) {
		while(!list.get(0).equals(target)) {
			list.addFirst(list.removeLast());
		}
	}

	static void print(LinkedList<Integer> list) {
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
		System.out.println();
	}
}
