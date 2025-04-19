package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1717 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boss = new int[n + 1];
        for(int i = 0; i <= n ; i++) boss[i] = i;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (order == 0) { // 합집합
                union(a, b);
            } else {
                int pa = find(a);
                int pb = find(b);

                if(pa == pb) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }
    static int[] boss;
    static int find(int v) {
        if(v == boss[v]) return v;

        return boss[v] = find(boss[v]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa < pb) boss[pb] = pa;
        else boss[pa] = pb;
    }
}
