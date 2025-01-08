package simulation;

/*
    붕대감기 Level1
    https://school.programmers.co.kr/learn/courses/30/lessons/250137

    중간 디버깅에 로직 순서 잘못되서 시간 소료
    - 시간 변경 위치를 계산 중간에 둬서 변수 값이 중간에 변경됨.
 */
public class Programmers250137 {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {5, 1, 5}, 30, new int[][] {{2, 10},{9, 15}, {10, 5}, {11, 5}}));
    }
    static public int solution(int[] bandage, int health, int[][] attacks) {

        int time = 0; // 마지막 공격받은 시간
        int cHealth = health;

        // attack 시간에 따라서 총 추가 회복 시간을 계산
        for(int[] attack : attacks) {
            int aTime = attack[0];
            int d = attack[1];

            // 초당 회복한 시간
            int addHealth = (aTime - time -1) * bandage[1];

            // 추가 회복량
            addHealth += ((aTime - time - 1) / bandage[0]) * bandage[2];

            time = aTime; // 시간 변경 위치를 계산 중간에 둬서 계산이 이상하게 나옴
            cHealth += addHealth;
            if(cHealth > health) {
                cHealth = health;
            }

            cHealth -= d;
            if(cHealth <= 0) {
                return -1;
            }
        }

        // 남은 체력, 0이하면 -1
        return cHealth;
    }
}
