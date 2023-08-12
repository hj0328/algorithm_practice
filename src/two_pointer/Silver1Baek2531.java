package two_pointer;

/*
회전초밥
https://www.acmicpc.net/problem/2531
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Silver1Baek2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int couponNumber = Integer.parseInt(st.nextToken());

        int[] dishes = new int[N];
        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }

        // <스시 종류, 갯수>
        HashMap<Integer, Integer> map = new HashMap<>();
        int s = 0;
        map.put(dishes[s], 1);

        int e = 1;
        int totalSelectCount = 1; // 중복 포함 총 개수
        int uniqueCount = 1;
        int maxUnique = 1;
        while (s < N) {
            if (totalSelectCount < k) {
                // e 추가
                if (map.containsKey(dishes[e]) && map.get(dishes[e]) > 0) {
                    map.put(dishes[e], map.get(dishes[e]) + 1);
                } else {
                    map.put(dishes[e], 1);
                    uniqueCount++;
                }
                totalSelectCount++;

                // e 증가
                e++;
                if (e == N) {
                    e = 0;
                }
            } else {
                // 최대 종류 개수 검사
                int card = 0;
                if(!map.containsKey(couponNumber) || map.get(couponNumber) == 0) {
                    card++;
                }

                //int eatDish = getEatDish(map);
                if (maxUnique < uniqueCount + card) {
                    maxUnique = uniqueCount + card;
                }

                // s 이동
                if (map.get(dishes[s]) == 1) {
                    uniqueCount--;
                }

                map.put(dishes[s], map.get(dishes[s]) - 1);
                totalSelectCount--;
                s++;
            }
        }

        System.out.println(maxUnique);
    }
}
