package map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Programmers258712 {
    public static void main(String[] args) {
        String[] friends = new String[] {"a", "b", "c"};
        String[] gifts = new String[] {"a b", "b a"};
        var score = new HashMap<String, Integer>();

        // 데이터 가공
        var giveMap = new HashMap<String, Map<String, Integer>>();

        for(String f : friends) {
            score.put(f, 0);
            for(String r : friends) {
                if(f.equals(r)) {
                    continue;
                }
                giveMap.putIfAbsent(f, new HashMap<String, Integer>());
                giveMap.get(f).put(r, 0);
            }
        }

        for(String str : gifts) {
            String[] gg = str.split(" ");
            String giver = gg[0];
            String rcv = gg[1];

            var m = giveMap.get(giver);
            m.put(rcv, m.getOrDefault(rcv, 0) + 1);

            score.put(giver, score.getOrDefault(giver, 0) + 1);
            score.put(rcv, score.getOrDefault(rcv, 0) - 1);
        }

        // 로직
        var next = new HashMap<String, Integer>();
        for(String f : friends) {
            next.put(f, 0);

            for(String r : friends) {
                if(f.equals(r)) {
                    continue;
                }

                int give1 = giveMap.get(f).get(r);
                int give2 = giveMap.get(r).get(f);

                if(give1 == give2) {
                    if(score.get(f) == score.get(r)) {
                        continue;
                    } else if (score.get(f) > score.get(r)) {
                        next.put(f, next.getOrDefault(f, 0) + 1);
//                        next.put(r, next.getOrDefault(r, 0) - 1);
                    }
                } else {
                    if(give1 > give2) {
                        next.put(f, next.getOrDefault(f, 0) + 1);
//                        next.put(r, next.getOrDefault(r, 0) - 1);
                    }
                }
            }
        }

        System.out.println(Collections.max(next.values()));
    }
}
