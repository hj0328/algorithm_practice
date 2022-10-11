package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * 칠공주 구성
 * 	- 깊이 7깊이를 만듬 
 * 	- 서로 인접해야함 
 * 	- 구분은 필요없으나 최소 이다솜파S는 4명 이상으로 구성
 * 
 *  위 구성을 유지할 수 있는 경우의 수는? 
 *  
 *  
 *  - 모든 S에서 시작
 *   - 4방향 모두 검사, 단 depth가 1일 때는 왼쪽, 위쪽으로는 갈 필요없음 
 *  - 중복 검사 어떻게 하지??? 모든 맵을 저장해야하는지 생각함 
 *  
 *  
 *  1 맵 크기 25개 중 7개를 뽑는 경우로 본다. 중복 허용안함 
 *    맵 y, x 위치를 숫자로 표현
 *  2 뽑고나서 S가 최소 4이상인지 본다. 
 *  3 뽑고나서 모두 연결되어있는지 본다. 
 *  4 2, 3번을 모두 통과했다면 통과 
 *  
 */
public class Baek1941 {

	static char[][] map = new char[5][5];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		backTracking(0, 0);
		System.out.println(answer);
	}

	static int[][] dir = {
			{-1, 0},
			{0, -1},
			{1, 0},
			{0, 1}
	};
	
	/*
	 *  25개 중에서 중복없이 7개 뽑기
	 *  단 S는 4개이상이어야 한다
	 */
	static int answer = 0;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static void backTracking(int count, int idx) {
		if(count == 7) {
			
			// 적어도 4개 이상이 S
			int sCount = 0;
			for (int val : list) {
				int y = val / 5;
				int x = val % 5;
				
				if(map[y][x] == 'S') {
					sCount++;
				}
			}
			
			if(sCount < 4) {
				return;
			}

			// list의 모든 값들이 연결되어있는지 확인
			if(!isConnected()) {
				return;
			}
						
			answer++;
			return;
		}
		
		// 끝까지 검사했다면 종료
		if(idx == 25) {
			return;
		}

		list.add(idx);
		backTracking(count+1, idx+1);
		list.remove(list.size()-1);
		
		backTracking(count, idx+1);
	}
	
	static boolean isConnected() {
		
		int start = list.get(0);
		boolean[] discovered = new boolean[25];
		boolean[] visited = new boolean[25];
		for (int val : list) {
			visited[val] = true;
		}
		
		LinkedList<Integer> q = new LinkedList<>();
		q.add(start);
		discovered[start] = true;

		int count = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			count++;
			for (int d = 0; d < 4; d++) {
				int ny = cur/5 + dir[d][0];
				int nx = cur%5 + dir[d][1];
				
				if(!(0 <= ny && ny < 5 && 0 <= nx && nx < 5)) {
					continue;
				}
				
				int next = ny*5 + nx;
				if(!visited[next]) {
					continue;
				}
				
				if(discovered[next]) {
					continue;
				}
				
				discovered[next] = true;
				q.add(next);
				
			}
		}
		
		return count == 7;
	}
}
