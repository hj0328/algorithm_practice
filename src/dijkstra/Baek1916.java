package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1916 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        var map = new HashMap<Integer, HashMap>(); // a에서 이동 가능한 다음 지역의 비용
        for (int i = 1; i <= N; i++) {
            map.put(i, new HashMap<Integer, Integer>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            HashMap<Integer, Integer> next = map.get(start);
            if(next.containsKey(end)) {
                Integer nextCost = next.get(end);
                if(nextCost > cost){
                    next.put(end, cost);
                }
            } else {
                next.put(end, cost);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startGoal = Integer.parseInt(st.nextToken());
        int endGoal = Integer.parseInt(st.nextToken());


        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        pq.add(new Node(startGoal, 0));
        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startGoal] = 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (dist[poll.end] < poll.cost) {
                continue;
            }

            HashMap<Integer, Integer> nextmap = map.get(poll.end);
            for (Map.Entry<Integer, Integer> next : nextmap.entrySet()) {
                int nextCost = next.getValue() + poll.cost;
                if (dist[next.getKey()] > nextCost) {
                    dist[next.getKey()] = nextCost;
                    pq.add(new Node(next.getKey(), nextCost));
                }
            }
        }

        System.out.println(dist[endGoal]);
    }

    static class Node {
        int end;
        int cost;
        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
