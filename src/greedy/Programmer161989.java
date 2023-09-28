package greedy;

/*
 *  프로그래머스 덧칠하기 
 * 	https://school.programmers.co.kr/learn/courses/30/lessons/161989?language=java#
 * 
 *  그리디 알고리즘 + 포인터? 알고리즘 
 *  
 *  설명
 *  cur는 벽을 1부터 탐색한다. 
 *  cur가 덧칠할 영역 section을 만나면 그리디하게 바로 덧칠한다. 
 *  이후 cur 위치는 덧칠한 영역의 바로 다음 칸으로 이동한다.
 *  section을 가리키는 si도 덧칠하지 않은 영역까지 이동한다. 
 *  덧칠하지 않은 영역이라 함은 cur보다 작은 영역을 의미한다.
 *  
 *  주의할 점 
 *  덧칠할 영역 section을 가리키는 si가 section 범위를 넘는 경우가 있다. (모든 영역이 다 덧칠된 경우) 
 *  이 경우를 고려해 si 를 사용전 매번 검사해야 한다. 
 *     
 */
public class Programmer161989 {

	public static void main(String[] args) {
		int solution = Solution.solution(8, 4, new int[] { 2, 3, 6 });
		System.out.println(solution);
	}

	static class Solution {
		public static int solution(int n, int m, int[] section) {
			int answer = 0;
			int si = 0;
			for (int cur = 1; cur <= n; cur++) {
				// 1부터 cur 까지는 모두 지워진 상태를 의미
				if (si < section.length && section[si] == cur) {
					// m 만큼 지우기
					answer++;
					cur += m - 1;
				}

				// 지워진 영역 빼고 si 위치 변경
				while (si < section.length) {
					if (section[si] <= cur) {
						si++;
					} else {
						break;
					}
				}
			}

			return answer;
		}
	}
}
