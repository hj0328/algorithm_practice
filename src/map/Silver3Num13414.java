package map;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Silver3Num13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int top = Integer.parseInt(st.nextToken());
        int totalCount = Integer.parseInt(st.nextToken());

        HashSet<String> clickedSet = new HashSet<>();
        Map<String, Integer> numOrderMap = new HashMap<>();
        Queue<String> list = new LinkedList<>();

        for (int i = 0; i < totalCount; i++) {
            String num = br.readLine();

            // 입력순으로 검사 시작
            // 앞서 여러번 버튼 누른애는 앞에 순서가 빠져야 한다.
            if (clickedSet.contains(num)) {
                numOrderMap.put(num, numOrderMap.get(num) + 1);
            } else {
                clickedSet.add(num);
                numOrderMap.put(num, 1);
            }

            list.add(num);
        }


        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < top; i++) {
            if (list.isEmpty()) {
                break;
            }
            String num = list.poll();

            Integer count = numOrderMap.get(num);
            if (count == 1) {
                ans.add(num);
            } else {
                numOrderMap.put(num, count - 1);
                i--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String a : ans) {
            sb.append(a).append("\n");
        }

        System.out.println(sb.toString());
    }
}
