package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek1715 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            long val = Long.parseLong(br.readLine());
            pq.add(val);
        }

        long ans = 0;
        while (pq.size() != 1) {
            Long add1 = pq.poll();
            Long add2 = pq.poll();
            ans += add1 + add2;
            pq.add(add1 + add2);
        }

        System.out.println(ans);
    }
}
