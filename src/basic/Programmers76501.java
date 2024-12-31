package basic;

/*
    프로그래머스 basic 문제
    https://school.programmers.co.kr/learn/courses/30/lessons/76501
 */
public class Programmers76501 {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {4, 7, 12}, new boolean[] {true, false, true}));
    }
    private static int solution(int[] absolutes, boolean[] signs) {
        int val = 0;
        int i = 0;
        for(boolean s : signs) {
            if(s) {
                val += absolutes[i++];
            } else {
                val -= absolutes[i++];
            }
        }

        return val;
    }
}
