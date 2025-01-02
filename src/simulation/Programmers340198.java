package simulation;

import java.util.Arrays;

/*
    공원 돗자리 문제
    https://school.programmers.co.kr/learn/courses/30/lessons/340198

    1차 제출 틀린 원인
    - sx, sy의 최대 범위 값을 반대로 검사
    - sx, sy의 최대 범위의 값보다 1 더 큰 값을 허용함. (>, >= 차이)

    2차 제출 틀린 원인
    - 결과 값에 배열 값이 아닌 배열의 인덱스를 리턴하여 틀림

    이런 부분에서 잘못되면 특히나 프로그래머스에서는 런타임 에러잡기 쉽지않음.
 */
public class Programmers340198 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{5,3,2}, new String[][] {{"A", "A", "-1", "B", "B", "B", "B", "-1"},
                {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
                {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
                {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}}));
    }

    private static int solution(int[] mats, String[][] park) {

        Arrays.sort(mats);

        int ylen = park.length;
        int xlen = park[0].length;
        for(int i = mats.length - 1 ; i >= 0 ; i--) {
            for(int sy = 0 ; sy < ylen ; sy++) {
                for(int sx = 0 ; sx < xlen ; sx++) {
                    boolean isOk = check(sy, sx, mats[i], park);
                    if(isOk) {
                        return mats[i];
                    }
                }
            }
        }

        return -1;
    }

    public static boolean check(int sy, int sx, int mat, String[][] park) {
        for(int y = sy ; y < sy + mat; y++) {
            for(int x = sx ; x < sx + mat ; x++) {
                if(y >= park.length || x >= park[0].length) return false;

                if(!park[y][x].equals("-1")) return false;
            }
        }

        return true;
    }
}
