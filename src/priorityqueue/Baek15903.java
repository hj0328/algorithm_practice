package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 *  실버1
 *
 *  우선순위 큐 문제.
 *  큐에서 가장 작은 2개 수를 찾아야 하는데,
 *  큐의 값은 입력에 따라서 계속 변경될 수 있어서 우선순위 큐로 매번 최소 값을 구한다.
 */
public class Baek15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long val = Long.parseLong(st.nextToken());
            pq.add(val);
        }

        for(int i = 0 ; i < count ; i++) {
            long a = pq.poll();
            long b = pq.poll();

            pq.add(a + b);
            pq.add(a + b);
        }

        long ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.println(ans);
    }
}
