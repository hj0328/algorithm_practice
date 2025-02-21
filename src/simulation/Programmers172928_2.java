package simulation;

import java.util.HashMap;
import java.util.StringTokenizer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로그래머스 공원산책
 * https://school.programmers.co.kr/learn/courses/30/lessons/172928
 *
 * 명령에 따라 공원산책, 범위나 장애물이 있다면 이동불가
 */
public class Programmers172928_2 {
    public static void main(String[] args) {
        var s = new Solution();
        int[] solution = s.solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 2", "S 2", "W 1"});
        assertThat(solution).containsExactly(2, 1);
    }
    static class Solution {
        public int[] solution(String[] park, String[] routes) {

            int H = park.length;
            int W = park[0].length();

            // s 찾기
            int sy = 0, sx = 0;
            for(int i = 0 ; i < park.length ; i++) {
                String s = park[i];
                for(int j = 0 ; j < s.length(); j++) {
                    if(s.charAt(j) == 'S') {
                        sy = i;
                        sx = j;
                    }
                }
            }

            var dirMap = new HashMap(); // 방향별 지도상 이동 위치 매핑
            dirMap.put("E", new int[] {0, 1});
            dirMap.put("N", new int[] {-1, 0});
            dirMap.put("W", new int[] {0, -1});
            dirMap.put("S", new int[] {1, 0});

            for(String r : routes) {    // 명령
                var st = new StringTokenizer(r);
                int[] dir = (int[])dirMap.get(st.nextToken());
                int len = Integer.parseInt(st.nextToken()); // 이동 거리
                int ny = sy, nx = sx;                       // 이동할 위치를 검사 및 계산
                boolean move = true;
                for(int count = 0 ; count < len; count++) {
                    ny += dir[0];
                    nx += dir[1];
                    if(!(nx >= 0 && nx < W && ny >= 0 && ny < H)) {
                        move = false;
                        break;
                    }
                    if(park[ny].charAt(nx) == 'X') {
                        move = false;
                        break;
                    }
                }

                if(!move) continue; // 이동 실패, 명령 무시
                sy = ny;
                sx = nx;
            }

            return new int[] {sy, sx};
        }
    }
}
