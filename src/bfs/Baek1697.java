package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 	숨바꼭질
 *
 * 	bfs 알고리즘
 * 		- 노드는 (수빈이 위치, 수빈이 이동시간)
 *
 *  주의
 *   - 수빈이와 수빈이 동생 위치가 입력에서 정해져있지만
 *     계산 중간에 입력 범위보다 작거나 커질 수 있다.
 *   -- 배열을 이용해서 위치가 중복되는지 확인이 필요하여 배열 크기 선언 시 최대 크기 고려
 *   -- 마이너스 이동을 하기 때문에 인덱스가 음수가 되는 경우 예외처리 필요
 */
public class Baek1697 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());


		boolean[] discovered = new boolean[200001];
		discovered[N] = true;
		Queue<Node> q = new LinkedList<>();
		Node node = new Node(N, 0);
		q.add(node);

		while(!q.isEmpty()) {
			Node cur = q.poll();

			if(cur.getPos() == K) {
				System.out.println(cur.getTime());
				break;
			}

			int nPos = cur.getPos() + 1;
			if(0 <= nPos && nPos < 200001 && !discovered[nPos]) {
				discovered[nPos] = true;
				q.add(new Node(nPos, cur.getTime()+1));
			}

			nPos = cur.getPos() - 1;
			if(0 <= nPos && nPos < 200001 && !discovered[nPos]) {
				discovered[nPos] = true;
				q.add(new Node(nPos, cur.getTime()+1));

			}

			nPos = cur.getPos() * 2;
			if(0 <= nPos && nPos < 200001 && !discovered[nPos]) {
				discovered[nPos] = true;
				q.add(new Node(nPos, cur.getTime()+1));
			}

		}

	}

	static class Node {
		int pos;
		int time;

		public Node(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
		public int getPos() {
			return pos;
		}
		public void setPos(int pos) {
			this.pos = pos;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
	}
}
