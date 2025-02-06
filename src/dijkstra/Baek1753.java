package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  다익스트라
 *
 *  시간초과
 *  1. 모든 edge를 검사하였기에 시간초과 발생.
 *  대신, 특정 정점에 연결된 edge만 검사하도록 자료구조 변경
 *  2. 가중치 w가 동일한 중복된 edge를 검사하고 제외하는 연산을 제거하니 시간초과 해결.
 *  입력을 받는동안 모든 Edge를 검사하는 것도 시간 제한에 걸릴 수 있다는 것을 알게됨.
 *  Edge 크기도 300,000로 큰편
 *
 *  메모리 초과
 *  - 이차원 [V][V] 배열은 메모리초과 발생. 4 byts * 10^8 승 = 약 400MB, 메모리 제한 256MB 초과
 *
 */
public class Baek1753 {
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());   // 20,000
        int E = Integer.parseInt(st.nextToken());   // 300,000

        int K = Integer.parseInt(br.readLine());

        dist = new int[V + 1]; // k부터 v까지 비용
        Arrays.fill(dist, 5000000);

        ArrayList<int[]>[] edges = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[u].add(new int[]{v, w});
//            boolean update = false;
//            boolean exist = false;
//            int[] delete = null;
//            for (int[] e : edges[u]) {
//                if (e[0] == v) {
//                    exist = true;
//                    if(e[1] > w){
//                        update = true;
//                        delete = e;
//                    }
//                    break;
//                }
//            }
//            if (update) {
//                edges[u].remove(delete);
//                edges[u].add(new int[]{v, w});
//            } else if(!exist) {
//                edges[u].add(new int[]{v, w});
//            }
        }

        dist[K] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(
                (o1, o2) -> {
                    return o1[1] - o2[1];
                }
        );
        q.add(new int[] {K, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[1] > dist[cur[0]]) {
                continue;
            }

            for (int[] n : edges[cur[0]]) {
                int newCost = n[1] + dist[cur[0]];
                if (dist[n[0]] > newCost) {
                    dist[n[0]] = newCost;
                    q.add(new int[]{n[0], newCost});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int v = 1; v <= V; v++) {
            if (dist[v] == 5000000) {
                sb.append("INF").append("\n");
            } else {
                sb.append(dist[v]).append("\n");
            }
        }

        System.out.println(sb.subSequence(0, sb.length() - 1));
    }
}
