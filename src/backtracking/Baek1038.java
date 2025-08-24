package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * [BOJ 1038 감소하는 수]
 * 문제
 *  각 자리수가 왼쪽에서 오른쪽으로 감소하는 수를 감소하는 수라 함
 *  0도 포함
 *  N 번째 감소하는 수를 출력하라
 *  만약 존재하지 않으면 -1 출력
 *
 *  핵심
 *  1. 감소하는 수 정의
 *      각 자리수가 왼쪽에서 오른쪽으로 감소함 (따라서 각 자리는 모두 다름)
 *      예: 321, 520,,
 *  2. 감소하는 수 개수
 *      0~9 숫자 중 일부를 골라 내림차순으로 나열하는 것과 같음
 *      즉, (0~9 의 부분집합의 개수) - 1 = 1023개
 *      따라서 감소하는 수는 최대 1023개, N > 1022면 무조건 -1
 *
 *  풀이 아이디어
 *      DFS로 모든 감소하는 수 생성
 *      시작: 0~9 다음 자리는 마지막 자리보다 작은 수만 붙임
 *      생성된 수들을 List에 저장하여 오름차순으로 정렬
 *      N번째 인덱스를 출력
 *
 *  주의 사항
 *      감소하는 수의 최댓값: 9876543210 -> long 사용
 *      DFS 길이는 최대 10 -> 재귀 안전
 *      DFS 생성 순서는 정렬 필요
 */
public class Baek1038 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N > 1022) {
            System.out.println(-1);
            return;
        }

        numbers = new ArrayList<>();

        for(int i = 0 ; i <= 9; i++) dfs(i, i);

        Collections.sort(numbers);
        System.out.println(numbers.get(N));
    }

    static ArrayList<Long> numbers;

    static void dfs(long num, int last) {
        numbers.add(num);
        for (int next = 0; next < last; next++) {
            dfs(num * 10 + next, next);
        }
    }
}
