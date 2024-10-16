package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1202 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        // 가벼운 보석 순서대로 정렬
        Arrays.sort(arr, (int[] o1, int[] o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }

            return -o1[1] + o2[1];
        });

        // 가방 담을 크기 순서대로 정렬
        Arrays.sort(bag);

        // 우선순위: value 높은 순서
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return -o1[1] + o2[1];
            }
            return 0;
        });

        int arri = 0;
        long sum = 0;
        for (int i = 0; i < K; i++) {   // 가벼운 가방부터
            for (; arri < N; arri++) {  // 가벼운 보석부터
                if(arr[arri][0] > bag[i]){  // 보석 무게가 가방을 초과하면 break
                    break;
                } else {
                    pq.add(arr[arri]);  // i번째 가방이 담을 수 있는 보석이면 pq에 저장 -> 보석 높은 값어치 순서순으로 저장
                }
            }

            if (!pq.isEmpty()) {
                int[] poll = pq.poll(); // 가방 i가 담을 수 있는 보석 중 가장 비싼 보석 poll
                sum = sum + (long) poll[1];

            }
        }
        System.out.println(sum);
    }
}
