package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  골드4 거짓말
 */
public class Baek1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        boolean[] found = new boolean[N + 1];
        int trueCount = Integer.parseInt(st.nextToken());
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < trueCount; i++) {
            int num = Integer.parseInt(st.nextToken());
            q.add(num);
            found[num] = true;
        }

        HashSet<Integer>[] sets = new HashSet[M];
        for (int i = 0; i < M; i++) {
            sets[i] = new HashSet();
        }

        for (int i = 0; i < M; i++) { // 모든 파티에 대해 집합 만들기
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            while (count-- > 0) {
                sets[i].add(Integer.parseInt(st.nextToken()));
            }
        }


        while (!q.isEmpty()) {
            Integer trueOne = q.poll();

            for(HashSet<Integer> s : sets) {
                if(!s.contains(trueOne)) continue;

                for(Integer next : s) {
                    if(found[next]) continue;

                    found[next] = true;
                    q.add(next);
                }
            }
        }

        int count = 0;
        for(HashSet<Integer> s : sets) { // 모든 파티
            Integer i = s.stream().findAny().get();
            if(!found[i]) count++; // 진실 모르면 과장처리
        }

        System.out.println(count);
    }
}

/*
    진실을 아는 사람이 없으면 과장되게 이야기해도됨
    진실 아는 사람 및 같은 파티에 오는 사람,

    진실 집합, 아닌 집합으로 나누기
    1 집합 정리
        진실집합은 무조건 진실.
        진실 아닌 집합은.. 진실일수도잇고 아닐수도있음
    2 진실/진실 아닌 집합 구분
 */