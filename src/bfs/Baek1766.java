package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    위상정렬 기본 문제
 */
public class Baek1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 문제 수
        int M = Integer.parseInt(st.nextToken()); // 문제 정보 수

        ArrayList<Integer>[] next = new ArrayList[N + 1];
        for(int i = 0 ; i <= N; i++) {
            next[i] = new ArrayList<>();
        }

        int[] indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            indegree[B]++;
            next[A].add(B);
        }

        // 현재 풀수있고, 쉬운 문제 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if(indegree[i] != 0) continue;
            pq.add(i);
        }

        StringBuilder ans = new StringBuilder();
        while (!pq.isEmpty()) {
            Integer cur = pq.poll();
            ans.append(cur).append(" ");
            for (Integer i : next[cur]) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    pq.add(i);
                }
            }
        }

        System.out.println(ans);
    }
}
/*
    쉬운 문제 부터 풀기
    먼저 풀어야 할 문제부터 풀기.
 */
