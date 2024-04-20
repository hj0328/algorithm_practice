package array;

/**
 * [PCCP 기출문제] 1번 / 붕대 감기
 * https://school.programmers.co.kr/learn/courses/30/lessons/250137
 *
 * 방법 1: 흐르는 시간을 기준으로 해결
 *      예제에서 문제를 해결하는 방법 그대로 푼 방법이다.
 *
 * 방법 2: 공격하는 시간을 기준으로 해결
 *      다른 사람 풀이인데 더 빠르게 문제를 풀 수 있다.
 */
public class ProgrammersLevel1Num250137 {
    public static void main(String[] args) {

        System.out.println(solution(new int[] {5, 1, 5}, 30, new int[][] {{2, 10}, {9, 15}, {10, 5}, {11, 5}}));

    }

    // 미리 이렇게 적어두기 >>
    //      [시전 시간, 초당 회복량, 추가 회복량]
    //      [공격 시간, 피해량]
    public static int solution(int[] bandage, int health, int[][] attacks) {

        int maxHealth = health;
        int bandTime = 0;
        int time = 1;
        int aIdx = 0;
        while(aIdx < attacks.length) {
            time++;
            if(aIdx < attacks.length && time == attacks[aIdx][0]) {
                // 공격당함
                bandTime = 0;
                health -= attacks[aIdx][1];
                aIdx++;
                if(health <= 0) {
                    break;
                }
            } else {
                // 회복
                bandTime++;
                health += bandage[1];
                if(health > maxHealth) {
                    health = maxHealth;
                    bandTime = 0 ;
                    continue;
                }

                // 붕대 모두 감음
                if(bandTime == bandage[0]){
                    health += bandage[2];
                    bandTime = 0;
                }

                if(health > maxHealth) {
                    health = maxHealth;
                    continue;
                }
            }
        }

        if(health <= 0) {
            return - 1;
        }

        return health;
    }
}
