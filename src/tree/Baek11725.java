package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  실버2, 트리의 부모 찾기
 *  연결 상태를 보고, 트리구조를 파악하는 문제
 *
 */
public class Baek11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] connect = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            connect[i] = new ArrayList();
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] check = new boolean[N + 1]; // i child가 이미 검사 대상인지 체크
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                q.add(new int[]{1, b});
                check[b] = true;
            }

            if(b == 1) {
                q.add(new int[] {1, a});
                check[a] = true;
            }

            connect[a].add(b);
            connect[b].add(a);
        }

        int[] parent = new int[N + 1]; // i 노드의 parent 노드 표시
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int p = poll[0];
            int child = poll[1];
            parent[child] = p;
            for (Integer nextChild : connect[child]) {
                if(check[nextChild]) continue;
                q.add(new int[]{child, nextChild});
                check[nextChild] = true;
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }
}
