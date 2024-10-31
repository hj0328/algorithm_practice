package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek17951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int e = 0;
        int s = 0;
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            e += arr[i];
        }

        // 그룹의 합이 x 이상이 되고 그룹이 k개인 i를 찾는 바이너리 서치
        // 순서대로 합이 x 이상이 되는 그룹을 만들어 최종적으로 k 개의 그룹이 완성이 되어야 한다.
        e++;
        int val = 0;
        while (s < e) {
            int mid = (s + e) / 2;
            val = 0;
            int group = 0;  // 여기서 틀려서 진행이 안됐음. 그룹의 개수가 마지막에 하나더 늘리거나 처음 시작을 1로 초기화
            for (int i = 0; i < N; i++) {
                val += arr[i];
                if(val >= mid) {
                    group++;
                    val = 0;
                }
            }

            // upper bound
            if (group > K) {
                // group 감소 -> mid 증가 -> s 증가
                s = mid + 1;
            } else if(group == K) {
                s = mid;
            }else {
                // group < K -> group 증가 시키기 -> mid 감소 -> e = mid - 1
                // group == k -> 처음으로 group > K 인 경우 찾아야 함 ->
                e = mid - 1;
            }
//            System.out.println(s + ", " + e + " group count " + group + " mid ");
        }
        System.out.println(e);
    }
}
