package basic;

import java.util.HashMap;

/*
    겹치는 선분의 길이
    3가지 선분의 둘 이상의 겹치는 길이 구하기

    map
    - 구간별(정수) 겹치는 선의 개수 계산
 */
public class Programmers120876 {
    public static void main(String[] args) {

        solution(new int[][]{{0, 1}, {2, 5}, {3, 9}});
        System.out.println(solution(new int[][]{{0, 1}, {2, 5}, {3, 9}}));
    }

    public static int solution(int[][] lines) {

        var map = new HashMap<Integer, Integer>();
        for (int[] l : lines) {
            int from = l[0];
            int to = l[1];
            for (int i = from; i < to; i++) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }

        return (int)map.values().stream().filter(v -> v > 1).count();
    }
}
