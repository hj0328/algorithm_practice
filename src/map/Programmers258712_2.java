package map;


import java.util.Collections;
import java.util.HashMap;

public class Programmers258712_2 {
    public static void main(String[] args) {
        /*
            더 많은 선물을 준 사람 +1
            선물 지수 높은 사람 +1
                선물 지수: 준 선물 - 받은 선물

            => 다음 달 가장 많은 선물을 받은 사람의 선물 수
         */
        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        System.out.println(solution(friends,gifts));
    }

    static int solution(String[] friends, String[] gifts) {
        var score = new HashMap<String, Integer>();
        var nextGift = new HashMap<String, Integer>();
        var giftMap = new HashMap<String, HashMap<String, Integer>>();
        for (String f : friends) {
            HashMap<String, Integer> map = new HashMap<>();
            score.put(f, 0);
            nextGift.put(f, 0);
            for (String ff : friends) {
                map.put(ff, 0);
            }

            giftMap.put(f, map);
        }

        // 주고받은 선물 기록
        for (String g : gifts) {
            String[] s = g.split(" ");
            String giver = s[0];
            String rcv = s[1];

            Integer i = giftMap.get(giver).get(rcv);
            giftMap.get(giver).put(rcv, i + 1);

            score.put(giver, score.get(giver) + 1);
            score.put(rcv, score.get(rcv) - 1);
        }

        // 예측
        for (String f1 : friends) {
            for (String f2 : friends) {
                if (f1.equals(f2)) {
                    continue;
                }

                Integer f1cnt = giftMap.get(f1).get(f2);
                Integer f2cnt = giftMap.get(f2).get(f1);

                if (f1cnt != f2cnt) {
                    if (f1cnt > f2cnt) {
                        nextGift.put(f1, nextGift.get(f1) + 1);
                    }
                } else {
                    Integer f1Score = score.get(f1);
                    Integer f2Score = score.get(f2);


                    if (f1Score > f2Score) {
                        nextGift.put(f1, nextGift.get(f1) + 1);
                    }
                }
            }
        }

        return Collections.max(nextGift.values());
    }
}

