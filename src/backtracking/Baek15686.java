package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/15686
 * 백트래킹 문제 
 */
public class Baek15686 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// r, c 1부터 시작 
		map = new int[N+1][N+1];
		chicks = new ArrayList<>();
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j<= N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					totalChickenCount++;
					chicks.add(new int[] {i, j});
				} else if(map[i][j] == 1) {
					totalHomeCount++;
				}
			}
		}
		visited = new boolean[totalChickenCount+1];
		
		// 0: r행, 1: c열
		homes = new int[totalHomeCount][2];
		int homeIdx = 0;
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j<= N ; j++) {
				if(map[i][j] == 1) {
					homes[homeIdx][0] = i;
					homes[homeIdx][1] = j;
					homeIdx++;
				}
			}
		}
		
		
		// 치킨 집 1개에서 M개까지 선택하는 모든 경우 탐색  
		// 선택한 치킨집에 대해서만 집에서 치킨 거리 구해서 최소 거리 구하기 
		
		
		dfs(0, 0);
		System.out.println(min);
		
	}

	static int N;
	static int M;
	static int totalChickenCount;
	static int[][] map;
	
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	static void dfs(int count, int chickenIdx) {
		if(count > M) {
			return;
		}
		
		if(chickenIdx > totalChickenCount) {
			return;
		}
		
		//print();
		// 최소 치킨 거리 검사 
		int totalValue = countTotalValue();
		if(totalValue != 0 && min > totalValue) {	// 0은 답이 절대 아님 
			min = totalValue;
		}
		
		// 현재 치킨 집 선택 안함 
		dfs(count, chickenIdx + 1);
		
		// 현재 치킨 집 선택 
		visited[chickenIdx] = true;
		dfs(count + 1, chickenIdx + 1);
		visited[chickenIdx] = false;
	}
	
	static int[][] homes;
	static ArrayList<int[]> chicks;
	static int totalHomeCount;
	// 모든 집에서 선택한 치킨집까지의 거리 중 최소 거리의 합 구하기
	static int countTotalValue() {
		int totalValue = 0;
		for(int i = 0 ; i < totalHomeCount ; i++) {
			int r = homes[i][0];
			int c = homes[i][1];
			
			int minVal = Integer.MAX_VALUE;
			for(int cIdx = 0 ; cIdx < totalChickenCount ; cIdx++ ) {
				if(!visited[cIdx]) {
					continue;
				}
				
				int[] chick = chicks.get(cIdx);
				int val = Math.abs(r - chick[0]) + Math.abs(c - chick[1]);
				
				if(minVal > val) {
					minVal = val;
				}
			}
			
			if(minVal == Integer.MAX_VALUE) {
				minVal = 0;
			}
			totalValue += minVal;
		}
		
		return totalValue;
	}
	
	static void print() {
		int[][] printMap = new int[N+1][N+1];
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1; j <= N ; j++) {
				printMap[i][j] = map[i][j];
			}
		}
		for(int cIdx = 0 ; cIdx < totalChickenCount ; cIdx++ ) {
			int[] chick = chicks.get(cIdx);
			if(!visited[cIdx]) {
				printMap[chick[0]][chick[1]] = 0;
			}
		}
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1; j <= N ; j++) {
				System.out.print(printMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
