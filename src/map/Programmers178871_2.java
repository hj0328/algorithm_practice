package map;

import java.util.Arrays;
import java.util.HashMap;

public class Programmers178871_2 {
    public static void main(String[] args) {

        Solution solution = new Solution();
        String[] ans = solution.solution(new String[]{"A", "B", "C"}, new String[]{"C", "C", "B"});

        Arrays.stream(ans).
                forEach(System.out::println);

    }

    static class Solution {
        public String[] solution(String[] players, String[] callings) {

            var orderMan = new HashMap<Integer, String>();
            var manOrder = new HashMap<String, Integer>();

            for(int i = 0; i < players.length; i++) {
                orderMan.put(i + 1, players[i]);
                manOrder.put(players[i], i + 1);
            }

            for(String s : callings) {
                // s 등수
                int th = manOrder.get(s);

                int nextTh = th - 1;
                String next = orderMan.get(nextTh);

                manOrder.put(s, nextTh);
                manOrder.put(next, th);

                orderMan.put(th, next);
                orderMan.put(nextTh, s);

            }

            var ans = new String[players.length];
            for(int i = 0 ; i < players.length ; i++) {
                ans[i] = orderMan.get(i + 1);
            }

            return ans;
        }
    }


}
