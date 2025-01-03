package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
    줄 세우기 (위상정렬)
    https://www.acmicpc.net/problem/2252

    1제출 틀린 이유
    - 위상 정렬을 거꾸로 출력하는데 String을 reverse해서 틀렸다.
    - 문자열을 뒤집으며 숫자가 뒤집힌 결과를 생각했다.
        - ex) 입력값이 10 인 경우는 01이 나온다.

    - 명시적인 조건이 아닌 한 String 뒤집는 것 자체를 피하기
 */
public class Baek2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] in = new int[N + 1];
        ArrayList[] list = new ArrayList[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            list[i] = new ArrayList<Integer>();
        }

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            in[f]++;

            list[s].add(f);
        }

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            ans.add(cur);

            for(Object o : list[cur]) {
                int next = (int) o;
                in[next]--;
                if (in[next] == 0) {
                    q.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = ans.size() - 1; i >= 0; i--) {
            sb.append(ans.get(i)).append(" ");
        }

//        System.out.println(sb.reverse()); ㅋㅋㅋㅋㅋ
        System.out.println(sb);
    }
}
