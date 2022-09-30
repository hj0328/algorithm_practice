package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 	텀 프로젝트
 * 		학생들이 팀을 짠다. 모든 학생은 팀을 짤 다른 학생을 선택할 수 있다.
 * 		자기 자신을 선택하는 경우도 가능하다. 또는 모든 학생들이 한 팀이 되어 한 팀을 이루는 경우도 가능하다.
 *		주어진 입력에서 팀을 이루지 못하는 사람의 수를 구하라
 *
 * 	문제 핵심
 * 		팀을 이루지 못한 사람을 A라는 사람이 선택한다면 A 또한 팀을 이룰 수 없다.
 *
 */
public class Baek9466 {

	static final int IN_TEAM = 1;
	static final int DONT_KNOW = 0;
	static final int NOT_IN_TEAM = -1;
	static boolean[] discovered;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		StringBuilder ans = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			int count = Integer.parseInt(br.readLine());
			int[] selectNum = new int[count+1];
			int[] hasTeam = new int [count+1];
			discovered = new boolean[count+1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= count; i++) {
				selectNum[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= count; i++) {
				if(discovered[i]) {
					continue;
				}
				bfs(selectNum, hasTeam, i);
			}

			int notInTeamCount = 0;
			for (int i = 1; i <= count; i++) {
				if(hasTeam[i] == DONT_KNOW) {
			//		System.out.print(i + " ");
					notInTeamCount++;
				}
			}
			ans.append(notInTeamCount).append("\n");
		}
		System.out.println();
	}

	/*
	 * bfs 노드란
	 * 	- 현재 학생이 선택한 다음 학생 번호
	 *
	 * 어떻게 사이클 유무를 알지
	 *  - 큐에서 중복으로 값이 들어오는 경우 사이클을 만났다 것을 의미
	 *  - 노드를 탐색하며 경로를 큐에 저장
	 *  - ex) 1 -> 2 -> 3 -> 3 에서 두 번째 3일 때 중복 방문임을 알고 종료
	 *
	 * 경로 중 같은 팀인 경우를 구분하는 방법
	 *  - 마지막 중복 값(3)과 동일한 값이 나올 때까지 큐에 들어있는 값들이 한 팀인 값들이다.
	 *    = 사이클을 의미
	 */
	static Queue<Integer> foundList = new LinkedList<>();
	static void bfs(int[] selectNum, int[] hasTeam, int startNum) {

		//System.out.println("start " + startNum);

		Queue<Integer> q = new LinkedList<>();
		q.add(startNum);
		discovered[startNum] = true;

		int cycleNum = -1;
		while(!q.isEmpty()) {
			Integer cur = q.poll();
			foundList.add(cur);

			//System.out.println("bfs " + cur);
			int next = selectNum[cur];
			if(discovered[next]) {
				cycleNum = next;
				break;
			}

			discovered[next] = true;
			q.add(next);
		}

		/*
		 * 지나친 애들이 모두 팀일 때 중간 경로들에 팀 여부 처리
		 */
		boolean cycle = false;
		while(!foundList.isEmpty()) {
			Integer foundNum = foundList.poll();
			if(foundNum == cycleNum) {
				hasTeam[foundNum] = IN_TEAM;
				cycle = true;
			}

			if(cycle) {
				hasTeam[foundNum] = IN_TEAM;
			}
		}
	}

}
