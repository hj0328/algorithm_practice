package basic;

/*
    나머지가 1이 되는 최초의 수 찾기
    https://school.programmers.co.kr/learn/courses/30/lessons/87389
 */
public class Programmers87389 {
    public static void main(String[] args) {
        System.out.println(solution(10));
    }

    static private int solution(int n) {
        for(int i = 1 ; i < n ; i++) {
            if(n % i == 1) {
                return i;
            }
        }

        return 0;
    }
}
