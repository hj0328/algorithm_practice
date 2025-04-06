package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 *  플레 5, deque, 슬라이딩 윈도우
 *
 *  최솟값 찾기
 *  List에서 모든 L 구간에 대한 최솟값 찾기
 *
 *  덱 자료구조 활용.
 *  - 덱은 값에 대해 오를차순 정렬된 상태를 유지
 *  - 제일 왼쪽 값이 L 구간에 대한 최솟값
 *  - 덱은 {값, 값의 인덱스}를 저장. 값의 인덱스는 값이 L 구간 범위를 벗어났는지 확인하기 위한 용도
 *
 *  List에서 i번째를 덱에 넣기 전, 덱에서 위치를 정하기
 *  - i번째 값보다 큰 값이 있다면, 덱의 제일 왼쪽부터 삭제 (덱은 정렬된 상태를 유지하기 때문)
 *  - 덱이 비었거나 덱보다 큰 값이 없다면 덱 마지막에 i 번째 값 추가
 *
 *  List에서 i번째를 덱에 넣을지 고민 후 , i - L 번째는 덱에서 제외
 *  - 덱의 첫 번째 값이 i - L 번째라면 삭제
 *  - 덱의 첫 번째 값이 i - L 번째 값이 아닐 수 있기 때문에 위치를 확인
 *  - i - L 번째 값이 아닌 것은 최소 값이 아니기 때문에 이미 제거된것
 */
public class Baek11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int val = arr[i];

            if (!dq.isEmpty() && dq.peekLast()[0] <= val) {
                // 새로운 최소가 될 수 있는 값 추가
                dq.add(new int[]{val, i});
            } else {
                // val보다 작은 큰 값들 제거.
                while (!dq.isEmpty() && dq.peekLast()[0] > val) {
                    dq.pollLast();
                }
                // val 추가
                dq.add(new int[]{val, i});
            }

            if(i - L + 1 > dq.peek()[1]) { // 언제 삭제하지? 최소값이 범위를 벗어난 경우
                dq.pollFirst();
            }
            sb.append(dq.peek()[0]).append(" ");
        }
        System.out.println(sb);
    }
}
/*

    L 크기 원소에서 최소값을 바로 알 수 있는 방법
    왼쪽으로 갈수록 오래됨 & 최소 값 유지

    1 5 2 3 6 2 3 7
    1, 1 5, 1 2, 2 3, 2 3 6, 2, 2 3, 2 3 7

 */