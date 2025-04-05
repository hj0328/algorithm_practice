package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  골드5
 *  최소한의 강의실을 사용하여 모든 강의를 배정
 *  - 강의를 최대한 연속적으로 들어야 함
 *  - 모든 강의 시작시간기준으로 정렬
 *  - 우선순위 큐로 가장 빨리 끝나는 강의 추적
 *
 *  정렬
 *      > 시간순서에 따른 스케줄링
 *      > 진행중인 강의실의 종료시간과 다음 강의실의 시작 시간 비교 가능
 *      > 그리디 선택을 위한 기초 작업! 매 시점에서 "재사용 가능한 강의실 중 가장 빨리 끝나는 강의실 선택"
 *  그리디
 *      > "재사용 가능한 강의실 중 가장 빨리 끝나는 강의실 선택"
 *      > 앞으로 더 많은 강의를 강의실에 배정가능
 *
 *  새로운 강의실이 필요한 경우
 *      > 기존 강의실과 겹치는 경우
 *  빠르게 기존 강의실과 겹치는지 확인
 *      > 가장 빨리 끝나는 강의와 새로운 강의가 겹치면, 이미 들은 다른 강의와는 비교할 필요없다.
 *      > 새로운 강의실 추가!
 */
public class Baek11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] lectures = new int[N][2];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lectures[i][0] = s;
            lectures[i][1] = e;
        }

        Arrays.sort(lectures, (o1, o2) -> { // 입력값이 어떻게 들어오는지 모르니 우선 정렬!
            if(o1[0] != o2[0]) return o1[0] - o2[0];
            else return o1[1] - o2[1];
        });

        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for(int i = 0 ; i < N ; i++) {
            int[] l = lectures[i];

            if(pq.isEmpty() || pq.peek()[1] > l[0]) { // 비었거나 겹침 -> 새로운 강의실
                ans++;
            } else { // 안겹침 -> 기존 강의실 정보빼고 새로운 강의실 정보 넣기
                pq.poll();
            }
            pq.add(l);
        }

        System.out.println(ans);
    }
}
/*
    이전 강의랑 겹치는지 아닌지 어떻게 알지?
    이전 강의 하나하나 다 검사하기?
    heap: 끝나는 시간이 가장 작은 값 부터 검사하지
 */