package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i++) {
            int target = Integer.valueOf(st.nextToken());
            sb.append(find(target, arr)).append('\n');
        }
        System.out.print(sb.toString());
    }

    static int find(int target, int[] arr) {
        int s = 0;
        int e = arr.length - 1;

        while(s <= e) {
            int mid = (s + e) / 2;
            if(target == arr[mid]) {
                return 1;
            } else if(target > arr[mid]) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return 0;
    }
}
