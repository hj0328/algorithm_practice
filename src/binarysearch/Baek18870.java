package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
        }

        ArrayList<Integer> sortArray = new ArrayList<>();

        set.iterator().forEachRemaining(sortArray::add);
        Collections.sort(sortArray);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int dst = arr[i];

            int left = 0;
            int right = sortArray.size() - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;

                if (sortArray.get(mid) >= dst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            sb.append(left).append(" ");
        }
        System.out.println(sb);
    }
}
