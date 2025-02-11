package dp.knapsack;

/**
 * 도둑질
 * https://school.programmers.co.kr/learn/courses/30/lessons/42897?language=java#
 *
 * 일반적인 knapsack 문제
 */
public class Programmers42897 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[] {2, 1, 1, 5, 1}));
    }

    static class Solution {
        public int solution(int[] money) {

            int[] dp = new int[money.length + 1];
            dp[0] = money[0]; // 첫 번째집 선택
            for(int i = 1 ; i < money.length - 1; i++) {
                // i 집 선택 안함
                dp[i] = dp[i - 1];

                // i 집 선택
                if(i - 2 >= 0)
                    dp[i] = Math.max(dp[i], dp[i - 2] + money[i]);
            }
            int ans = dp[money.length - 2];

            dp = new int[money.length + 1];
            dp[1] = money[1]; // 첫 번째집 선택 안한 경우, dp[1]까지는 2번째집 선택이 최대 값
            for(int i = 1 ; i < money.length; i++) {
                // i 집 선택 안함
                dp[i] = Math.max(dp[i - 1], dp[i]);

                // i 집 선택
                if(i - 2 >= 0)
                    dp[i] = Math.max(dp[i], dp[i - 2] + money[i]);
            }

            ans = Math.max(ans, dp[money.length - 1]);
            return ans;
        }
    }
}
