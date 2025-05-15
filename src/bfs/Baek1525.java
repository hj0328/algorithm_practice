package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek1525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sy = 0, sx = 0;
        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 0) {
                    sy = i;
                    sx = j;
                }
            }
        }

        ans = Integer.MAX_VALUE;
        bfs(sy, sx, arr);
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }

    static int ans;
    static int[][] delta = new int[][]{
            {-1, 0}, {1, 0}, {0, 1}, {0, -1}
    };
    static void bfs(int sy, int sx, int[][] arr) {
        char[] chars = new char[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int idx = i * 3 + j;
                chars[idx] = (char) (arr[i][j] + '0');
            }
        }


        String str = new String(chars);
        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put(str, 0);

        LinkedList<String> q = new LinkedList<>();
        q.add(str);

        while (!q.isEmpty()) {
            String cur = q.poll();

            if (cur.equals("123456780")) {
                ans = countMap.get(cur);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int idx = cur.indexOf('0');

                int ny = idx / 3 + delta[i][0];
                int nx = idx % 3 + delta[i][1];
                if(!(ny >= 0 && ny < 3 && nx >= 0 && nx < 3)) continue;

                char[] charArray = cur.toCharArray();
                char temp = charArray[ny * 3 + nx];
                charArray[ny * 3 + nx] = charArray[idx];
                charArray[idx] = temp;

                String newS = new String(charArray);
                if(countMap.containsKey(newS)) continue;
                countMap.put(newS, countMap.get(cur) + 1);

                q.add(newS);
            }

        }
    }
}
