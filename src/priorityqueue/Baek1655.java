package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());

            if (max.size() <= min.size()) {
                max.add(val);

                if (!min.isEmpty() && max.peek() > min.peek()) {
                    min.add(max.poll());
                    max.add(min.poll());
                }
            } else {
                min.add(val);

                if (max.peek() > min.peek()) {
                    min.add(max.poll());
                    max.add(min.poll());
                }
            }

            sb.append(max.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
