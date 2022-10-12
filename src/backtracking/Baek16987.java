package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	계란끼리 부딪치게 했을 때 최대 깰 수 있는 계란의 수는? 
 * 	
 * 	1. 제일 왼쪽 계란 선택
 * 	2. 다른 계란과 부딪침. 다른 계란이 없으면 패스 
 *  3. 들었던 계란의 오른쪽 계란을 들고 2번 반복. 그런데 오른쪽 계란이 없으면 종료  
 *  
 *  1번부터 시작해서 가능한 모든 계란에 대해 부딪치기 작업 시작
 *  계란 값이 -1이라면 이미 부서진 계란  
 *  
 *  한 번에 맞았지만 아쉽다. 
 *  만약 테케가 이렇게 다양하게 주지 않았으면 무조건 코테에서 틀림
 *  
 *  피드백 
 *   - 나름 다시 정리한다고 해서 문제 조건들을 주석으로 정리했는데 너무 간단하게 정리되서 별로 도움이 안됐다. 
 *     오히려 생략을 해서 빠진 부분이 있었다. 
 *     특히 부딪치려는데 모든 계란이 깨진 경우에 다음 인덱스의 계란으로 패스해야 함 
 *     모든 계란을 점검하고 나서 모두 깨졌는지 확인 후 백트래킹을 수행해야 함. 
 *     그렇지 않으면 시간초과 
 */
public class Baek16987 {

	static final int S = 0;
	static final int W = 1;
	static int[][] eggs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int eggCount = Integer.parseInt(br.readLine());
		eggs = new int[eggCount][2];
		
		for (int i = 0; i < eggCount; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i][S] = Integer.parseInt(st.nextToken());
			eggs[i][W] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(0);
		System.out.println(maxBrokenEgg);
	}
	
	/*
	 * 	인자
	 *  현재 선택할 계란 인덱스 
	 *  
	 *  종료 조건 
	 *   - 현재 선택할 계란이 인덱스 범위를 벗어난 경우
	 *   - 더 이상 깰 계란이 없는 경우 
	 */
	static int maxBrokenEgg = 0;
	static void backTracking(int eggIdx) {
		
		
		if(eggIdx == eggs.length) {
			int brokenEggCount = 0;
			for (int i = 0; i < eggs.length; i++) {
				//System.out.println(eggs[i][S] + " " + eggs[i][W]);
				if(eggs[i][S] == -1) {
					brokenEggCount++;
				}
			}
			//System.out.println();
			if(maxBrokenEgg < brokenEggCount) {
				maxBrokenEgg = brokenEggCount;
			}
			return;
		}
		
		// 선택 계란이 깨짐
		if(eggs[eggIdx][S] <= 0) {
			backTracking(eggIdx+1);
			return;
		}
		
		boolean noMoreBroken = true;
		for (int i = 0; i < eggs.length; i++) {
			if(i == eggIdx || eggs[i][S] <= 0) {
				continue;
			}
			
			noMoreBroken = false;
			// 현재 계란 내구성 감소
			int preEggIdxS = eggs[eggIdx][S]; 
			eggs[eggIdx][S] -= eggs[i][W];
			if(eggs[eggIdx][S] <= 0) {
				eggs[eggIdx][S] = -1;
			}
			
			// 다른 계란 내구성 감소
			int preIS = eggs[i][S];
			eggs[i][S] -= eggs[eggIdx][W];
			if(eggs[i][S] <= 0) {
				eggs[i][S] = -1;
			}
			
			backTracking(eggIdx+1);
			
			eggs[eggIdx][S] = preEggIdxS;
			eggs[i][S] = preIS;
			
		}
		
		// 하나도 안부딪치고 넘어가는 경우
		if(noMoreBroken) {
			backTracking(eggIdx+1);
		}
	}
}

