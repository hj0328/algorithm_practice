package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek33574 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // (10^3) * 4
        int T = Integer.parseInt(br.readLine());
        LinkedList<Integer> list = new LinkedList<>();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            var o = Integer.parseInt(st.nextToken());
            var x = Integer.parseInt(st.nextToken());

            if (o == 1) {
                if(x == 1) {
                    Collections.sort(list);
                } else {
                    list.sort(Collections.reverseOrder());
                }
            } else {
                var target = Integer.parseInt(st.nextToken());
                list.add(x, target);
            }
        }

        int size = list.size();
        System.out.println(size);
        if (size != 0) {
            var sb = new StringBuilder();
            for(int i = 0 ; i < size ; i++) {
                sb.append(list.get(i)).append(" ");
            }
            System.out.println(sb);
        }
    }
}
