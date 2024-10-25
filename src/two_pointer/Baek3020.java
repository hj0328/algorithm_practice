package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    문제를 해결방법을 찾는 걸 못찾았다.
    lower bound를 만드는 과정도 틀렸다.
    해결방법을 듣고도 lower bound인지 upper bound인지 헷갈렸다가 잘못함.
    이진 탐색 자체를 확실히 이해해야 겠다.
 */
public class Baek3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] up = new int[N/2];
        int[] down = new int[N/2];
        for(int i = 0 ; i < N ; i++) {
            int h = Integer.parseInt(br.readLine());
            if((i & 1) == 1) {
                down[i / 2] = h;
            } else {
                up[i / 2] = h;
            }
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = N;
        int count = 0;
        for (int h = 1; h <= H; h++) {
            int conflicts = binarySearch(down, N / 2, h) + binarySearch(up, N / 2, H - h + 1);
            if (min > conflicts) {
                min = conflicts;
                count = 1;
                continue;
            }
            if (min == conflicts) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }

    // N - lower bound
    static int binarySearch(int[] arr, int n, int targetH) {
        int s = 0;
        int e = n;
        while(s < e) {
            int mid = (s + e) / 2;

            if(arr[mid] < targetH) {
                s = mid + 1;
            } else {
                // arr[mid] >= target
                e = mid;
            }
        }

        return n - s;
    }
}
