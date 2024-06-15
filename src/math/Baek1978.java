package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer cnt = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[cnt];
        for(int i = 0 ; i < cnt ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] pri = new boolean[1001];
        makePrimary(pri);

        int ans = 0;
        for(int i = 0 ; i < cnt ; i++) {
            if(pri[arr[i]]) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static void makePrimary(boolean[] pri) {

        Arrays.fill(pri, true);
        pri[1] = false;

        for(int i = 2 ; i <= 1000 ; i++) {
            if(pri[i]){
                for(int j = i * 2 ; j <= 1000 ; j += i) {
                    pri[j] = false;
                }
            }
        }

    }
}
