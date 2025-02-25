package simulation;

import org.assertj.core.api.Assertions;
import java.util.*;

/**
 * 개인정보 수집 유효기간
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370
 *
 * 문제에서 1달이 28일 가정한다.
 * 따라서 1년은 336일로 고정해야 한다.
 * 아무생각없이 365로 했다가 계산이 안맞아서 틀렸다.
 *
 */
public class Programmers150370 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        Assertions.assertThat(ans).containsExactly(1, 3);
    }
    static class Solution {
        public int[] solution(String today, String[] terms, String[] privacies) {

            var st = new StringTokenizer(today, ".");

            // 1달이 28일 고정이므로, 1년은 336일이다.
            Long todayLong = Long.valueOf(st.nextToken()) * 336 + Long.valueOf(st.nextToken()) * 28 + Long.valueOf(st.nextToken());

            var term = new HashMap<String, Long>();
            for(String t : terms) {
                String[]  tt = t.split(" ");
                term.put(tt[0], Long.valueOf(tt[1]));
            }

            List<Integer> list = new ArrayList<Integer>();
            int c = 1;
            for(String p : privacies) {
                String[] p1 = p.split(" ");
                String date = p1[0];
                Long add = Long.valueOf(term.get(p1[1]));
                Long expire = getExpire(date, add); // expire까지 유효
                if(todayLong >= expire) {
                    list.add(c);
                }
                c++;
            }

            return list.stream().mapToInt(Integer::intValue).toArray();
        }

        static Long getExpire(String date, Long add) {
            var st = new StringTokenizer(date, ".");
            return Long.valueOf(st.nextToken()) * 336 + Long.valueOf(st.nextToken()) * 28 + Long.valueOf(st.nextToken()) + add * 28;
        }
    }
}
