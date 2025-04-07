package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 *  노드1번에서 노드 i까지 k번째 최소 시간
 *
 *  다익스트라 + 우선순위 큐 (역순)
 *  - 우선순위 큐: 각 노드에 방문할 때 최소 비용. 크기 k 유지. peek는 k번쨰 최소 비용이 됨
 *  - 기존 다익스트라의 동작 수정.
 *    > 기존의 최소 비용을 먼저 방문하는 것으로 최소 비용순으로 노드를 방문하게 됨
 *
 *  다익스트라 고민할 때, 언제 탐색을 멈추지에 대해서 제대로 생각을 못했다.
 *  처음 K개가 큐에 다차면 끝나는걸로 할까 했지만, 그걸로는 충분히지 않았음.
 *  총 개수가 k개 일 때, 우선순위 큐가 더 이상 업데이트 안되면 종료!
 */
public class Baek1854 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 1,000    3
        int m = Integer.parseInt(st.nextToken());   // 250,000  5
        int k = Integer.parseInt(st.nextToken());   // 100 k번째 최단 경로 구하기 2

        ArrayList<int[]>[] edges = new ArrayList[n + 1];
        PriorityQueue<Integer>[] nScore = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
            nScore[i] = new PriorityQueue<Integer>(Collections.reverseOrder());   // 노드 i에 방문한 시간들
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[s].add(new int[]{e, cost});
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {return o1[1] - o2[1];});
        q.add(new int[]{1, 0}); // 노드번호, cost
        nScore[1].add(0);       // 시작점은 0으로 이동 가능 > 이거 설정안해서 틀림

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curNode = cur[0];

            for (int[] other : edges[curNode]) {
                int nextCost = cur[1] + other[1];
                if (nScore[other[0]].size() < k) {
                    nScore[other[0]].add(nextCost);
                    q.add(new int[] {other[0], nextCost});
                } else {
                    if(nScore[other[0]].peek() > nextCost) {
                        nScore[other[0]].poll();
                        nScore[other[0]].add(nextCost);
                        q.add(new int[] {other[0], nextCost});
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
//            System.out.println("size" + nScore[i].size());
            if(nScore[i].size() == k) sb.append(nScore[i].peek()).append("\n");
            else sb.append(-1).append("\n");
        }
        System.out.print(sb);
    }
}
