package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 세 수의 합
 *
 * [문제 요약]
 *      N개의 자연수가 주어짐 N <= 1000
 *      그 중 네 수 a, b, c, d를 골라 a+b+c = d를 만족시키는 가장 큰 d를 구하는 문제
 *      같은 수를 여러 번 사용 가능
 *
 * [접근 아이디어]
 *      모든 두 수의 합 (a + b)를 미리 구해 정렬 → O(N^2)
 *      d - c 가 "두 수 합 목록"에 있는지 확인 → a+b+c = d 식으로 환원
 *      d 후보는 큰 값부터 확인 → 처음 성립하는 d가 정답
 *      존재 여부 확인은 Collections.binarySearch 로 처리
 *      Collections.binarySearch는 값이 존재하면 0이상 값을 리턴함. 그렇지 않으면 음수 리턴
 *
 * [시간 복잡도]
 *      두 수 합 생성: O(N^2)
 *      각 d, c 조합 탐색: O(N^2 log N)
 *
 * [핵심]
 *      "a+b+c = d"를 "a+b = d-c"로 단순화
 *      d를 큰 값부터 검사 → 최댓값 바로 도출
 *      처음에는 a+b=d-c까지 생각했는데 그 이후에 어떻게 접근할지 몰랐다.
 *      a+b 조합을 미리 다 생성해두는 방법으로 접근할 줄이야!!
 *
 */
public class Baek2295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            long val = Long.parseLong(br.readLine());
            arr[i] = val;
        }

        var list = new ArrayList<Long>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                list.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(list);
        Arrays.sort(arr);

        for (int di = N - 1; di >= 0; di--) {
            for (int ci = 0; ci < N; ci++) {
                long target = arr[di] - arr[ci];
                int i = Collections.binarySearch(list, target);
                if (i >= 0) {
                    System.out.println(arr[di]);
                    return;
                }
            }
        }
    }
}
