package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            if (val == 0) {
                if (q.size() > 0) {
                    System.out.println(q.poll());
                } else {
                    System.out.println(0);
                }
                continue;
            }
            q.add(val);
        }
    }
}
