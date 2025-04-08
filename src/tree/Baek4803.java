package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek4803 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        StringBuilder sb = new StringBuilder();
        int caseNum = 1;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;

            ArrayList<Integer>[] list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }

            int totalCount = 0;
            boolean[] found = new boolean[n + 1];
            LinkedList<int[]> q = new LinkedList<int[]>();
            for (int i = 1; i <= n; i++) {
                if(found[i]) continue;
                found[i] = true;
                q.add(new int[] {i, 0});

                boolean isCycle = false;
                while(!q.isEmpty()) {
                    int[] poll = q.poll();
                    int cur = poll[0];
                    int pre = poll[1];

                    for(int other : list[cur]) {
                        if(other == pre) continue; // 이전 경로 노드면? 스킵
                        if(other != pre && found[other]) { // 내 직전에서 온 노드가 아닌데 이미 어디선가 방문. 싸이클

                            isCycle = true;
                            // break하면 기존 큐에 있던 노드가 다른 tree에 영향을 줌 ㄴ
//                            break;
                        } else {
                            found[other] = true;
                            q.add(new int[] {other, cur});
                        }
                    }
//                    if(isCycle) break;
                }

                if (!isCycle) {
                    totalCount++;
                }
            }

            sb.append("Case ").append(caseNum).append(": ");
            caseNum++;
            if (totalCount > 1) {
                sb.append("A forest of ").append(totalCount).append(" trees.");
            } else if (totalCount == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("No trees.");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}

/*
    어라? 싸이클 확인 어케함 bfs는 노드 방문인데

    갯수세야하나
 */