package map;

import java.util.Collections;
import java.util.HashMap;

/*
    가장 많이 받은 선물
    https://school.programmers.co.kr/learn/courses/30/lessons/258712
    주의
    1. 동일한 두명이 선물 주고받은 경우가 둘 이상 일 때를 생각에서 빠뜨리면 틀리게 됨
    2. 데이터 2차원 처리
 */
public class Programmers258712_3 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"muzi", "ryan", "frodo", "neo"},
                new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"}));
    }

    static int solution(String[] friends, String[] gifts) {
        // 둘 사이 주고받은 선물, 더 많은 선물을 준 사람이 다음 달에 선물을 받음

        // 이전 달 주고받은 선물 기록
        HashMap<String, HashMap<String, Integer>> preGift = new HashMap<>();

        // 선물 지수
        HashMap<String, Integer> score = new HashMap<>();

        // 다음 달 받을 선물 수
        HashMap<String, Integer> nextGift = new HashMap<>();

        // preGift, score 초기화
        for (String f : friends) {
            score.put(f, 0);

            nextGift.put(f, 0);

            HashMap<String, Integer> other = new HashMap<>();
            preGift.put(f, other);

            for(String ff : friends) {
                other.put(ff, 0);
            }
        }

        // 주고받은 기록 preGift 저장
        for (String g : gifts) {
            String[] gg = g.split(" ");

            String giver = gg[0];
            String receiver = gg[1];

            score.put(giver, score.get(giver) + 1);
            score.put(receiver, score.get(receiver) - 1);

            HashMap<String, Integer> map = preGift.get(giver);
            map.put(receiver, map.get(receiver) + 1);
        }

        // 다음 달 계산하기
        for(String g : friends) {
            for(String r : friends) {
                if(g.equals(r)) continue;

                // 주고받은 기록 존재?
                Integer ggive = preGift.get(g).get(r);
                Integer rgive = preGift.get(r).get(g);

                if (ggive > rgive) {
                    nextGift.put(g, nextGift.get(g) + 1);
                } else if(ggive == rgive) {
                    Integer gScore = score.get(g);
                    Integer rScore = score.get(r);

                    if(gScore > rScore) {
                        nextGift.put(g, nextGift.get(g) + 1);
                    }
                }
            }
        }

        return Collections.max(nextGift.values());
    }

}
