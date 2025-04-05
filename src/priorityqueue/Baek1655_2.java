package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 *  골드2
 *  
 *  중간값 이하 절반 원소를 q1에 저장, 중간 값보다 큰 원소를 q2에 저장
 *  q1의 개수는 q2개수와 같거나 1개만 더 많은 상태를 유지해야 함
 *  새로운 값은 크기에 따라 q1, q1 중 한 곳에 소속. 이후에 개수를 조절
 *
 *  동적 자료구조 우선순위 큐 활용
 *
 *  팁
 *  여러 경우로 나눠서 로직에 이상이 없나 확인 필요
 */
public class Baek1655_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> q1 = new PriorityQueue<>((o1, o2) -> o2 - o1); // 왼쪽 중 가장 큰수
        PriorityQueue<Integer> q2 = new PriorityQueue<>();  // 오른쪽 중 가장 작은 수

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++) {
            int val = Integer.parseInt(br.readLine());

            // 위치 파악
            if(q1.isEmpty() || q1.peek() >= val) {
                q1.add(val);
                // 갯수 조절
                if (q1.size() > q2.size() + 1) {
                    q2.add(q1.poll());
                }
            } else {
                q2.add(val);
                if (q1.size() < q2.size()) {
                    q1.add(q2.poll());
                }
            }

            sb.append(q1.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
/*

    2 2 2 2 2
    1 1 3 3 3

    1
    1 3
    1 3 3, 1 1 3, 1 2 3, 1 4 3
    왼쪽 넣어. 왼쪽 peek > 오른쪽 peek?, 오른쪽을 왼쪽으로 넣음

    왼픽 > val,

    1
    1 3
    1 3 3
    1 3 3 3
        1 3 3 3 2
    1 2 3 3 3
    1 2 2 3 3 3

 */