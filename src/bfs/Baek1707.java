package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * 이분탐색
 * https://www.acmicpc.net/problem/1707
 *
 */
public class Baek1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            visited = new boolean[V + 1];
            groups = new int[V + 1];
            ArrayList[] edges = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                edges[i] = new ArrayList();
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges[u].add(v);
                edges[v].add(u);
            }

            ans = true;
            for (int v = 1; v <= V; v++) {
                if(visited[v]) continue;
                visited[v] = true;
                dfs(v, edges, 0);   // node를 방문하며, 그룹 번호 부여 (0또는1)

                if(!ans) break; // 이분 그래프가 아니면 바로 종료
            }

            if (ans) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean[] visited;
    static int[] groups;
    static boolean ans;
    static void dfs(int v, ArrayList<Integer>[] edges, int group) {
        ArrayList<Integer> list = edges[v];
        for(int u : list) { // 인접한 모든 node에 대해
            if(visited[u] && groups[u] == group) { // 이미 group 부여한 노드가 동일한 그룹이면, 이분 그래프가 아님
                ans = false;
                return;
            }
            groups[u] = 1 - group;  // 노드에 group 번호 부여

            if (!visited[u]) {
                visited[u] = true;
                dfs(u, edges, 1 - group);
            }
        }
    }
}
