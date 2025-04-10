package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *  유니온 파인드로 싸이클 유무 찾기
 *  - 새로 연결하는 노드가 같은 boss 를 갖고 있다면 싸이클이 존재
 *  - 작은 노드 번호가 boss 번호
 *  - 유니온 시점: 노드간에 연결이 있는 경우
 */
public class Baek4803 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCount = 0;
        while (true) {
            testCount++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;

            // 유니온 초기화
            int[] boss = new int[n + 1];
            for(int i = 1 ; i <= n ; i++) boss[i] = i;

            boolean[] cycle = new boolean[n + 1];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b, boss, cycle);
            }

            // 보스 갯수 세기
            HashSet<Integer> set = new HashSet<>();
            for(int i = 1 ; i <= n; i++) {
                int bossi = find(i, boss);
                if(cycle[bossi]) continue;  // 싸이클이라면 패스

                set.add(find(i, boss));
            }

            int ans = set.size();
            sb.append("Case ").append(testCount).append(": ");
            if(ans > 1) {
                sb.append("A forest of ").append(ans).append(" trees.");
            } else if (ans == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("No trees.");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
    static void union(int a, int b, int[] boss, boolean[] cycle) {
        int bossA = find(a, boss);
        int bossB = find(b, boss);

        // 같은 집합이거나, 한 쪽이 이미 싸이클이면 모두 싸이클있는 그래프로 표시
        if(bossA == bossB || cycle[bossA] || cycle[bossB]) {
            cycle[bossA] = true;
            cycle[bossB] = true;
        }

        if(bossA < bossB) boss[bossB] = bossA;
        else boss[bossA] = bossB;
    }
    static int find(int a, int[] boss) {
        if(boss[a] == a) return a;

        return boss[a] = find(boss[a], boss);
    }
}
