package map;

import java.util.Collections;
import java.util.HashMap;


/*
    가장 많이 받은 선물
    https://school.programmers.co.kr/learn/courses/30/lessons/258712
 */
public class ProgrammersLevel1258712 {
    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

        // string이 상대방에게 준 서물 기록
        HashMap<String, HashMap<String, Integer>> record = new HashMap<>();
        HashMap<String, Integer> score = new HashMap<>();
        HashMap<String, Integer> nextMonthScore = new HashMap<>();

        for(String f : friends) {
            score.put(f, 0);
            nextMonthScore.put(f, 0);
            record.put(f, new HashMap());
        }

        // 선물 주고받기 데이터 저장
        for(String g : gifts) {
            String[] gg = g.split(" ");
            String giver = gg[0];
            String receiver = gg[1];

            HashMap<String, Integer> r = record.get(giver);
            r.put(receiver, r.getOrDefault(receiver, 0) + 1);

            score.put(giver, score.getOrDefault(giver, 0) + 1);
            score.put(receiver, score.getOrDefault(receiver, 0) - 1);
        }

        // 다음달 선물 계산
        // 주고받은 기록있다면, 더준 사람의 선물을 하나 더 받음
        // 주고받은 기록이 없거나 동일하게 주고 받았다면 score 를 기준으로 비교
        // score도 동일하면 아무것도 안함

        // giver 기준으로 먼저 다음달 받을 선물 계산
        for (String giver : friends) {
            for (String receiver : friends) {
                if (giver.equals(receiver)) {
                    continue;
                }

                Integer giveCnt = record.get(giver).getOrDefault(receiver, 0);
                Integer receiveCnt = record.get(receiver).getOrDefault(giver, 0);

                if(giveCnt > receiveCnt) {
                    nextMonthScore.put(giver, nextMonthScore.getOrDefault(giver, 0) + 1);
                } else if (giveCnt == receiveCnt) {
                    Integer giverScore = score.get(giver);
                    Integer receiverScore = score.get(receiver);

                    if(giverScore > receiverScore) {
                        nextMonthScore.put(giver, nextMonthScore.getOrDefault(giver, 0) + 1);
                    }
                }
            }
        }

        System.out.println(Collections.max(nextMonthScore.values()));
    }

}
/*

import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {

        HashMap<String, Integer> fMap = new HashMap();
        list = new ArrayList();
        for(String f : friends) {
            list.add(new Friend(f));
            fMap.put(f, 0);
        }

        for(String g : gifts) {
            String[] gg = g.split(" ");

            String giver = gg[0];
            String reciever = gg[1];

            Friend gf = find(giver);
            gf.giveTo(reciever);

            Friend rf = find(reciever);
            rf.fromCount++;
        }

        for(int i = 0 ; i < friends.length ; i++) {

            for(int j = i + 1 ; j < friends.length ; j++) {

                String aName = friends[i];
                String bName = friends[j];

                Friend a = find(friends[i]);
                Friend b = find(friends[j]);

                if(!a.map.containsKey(bName) && !b.map.containsKey(aName)) {
                    // 둘다 주고받지 않음
                    Integer aCount = a.calculate();
                    Integer bCount = b.calculate();

                    if(aCount > bCount) {
                        fMap.put(aName, fMap.get(aName) + 1);
                    }
                    if(aCount < bCount) {
                        fMap.put(bName, fMap.get(bName) + 1);
                    }
                } else {
                    // 한 명만 주고 받거나 둘다 주고받음
                    Integer aCount = a.map.get(bName);
                    if(aCount == null) {
                        aCount = 0;
                    }
                    Integer bCount = b.map.get(aName);
                    if(bCount == null) {
                        bCount = 0;
                    }

                    if(aCount == bCount && aCount == 0) {
                        throw new RuntimeException();
                    }
                    if(aCount > bCount) {
                        fMap.put(aName, fMap.get(aName) + 1);
                    } else if(aCount < bCount) {
                        fMap.put(bName, fMap.get(bName) + 1);
                    } else  {
                        // 같은 수 주고받음
                        Integer aCal = a.calculate();
                        Integer bcal = b.calculate();

                        // 선물 지수 비교
                        if(aCal > bcal) {
                            fMap.put(aName, fMap.get(aName) + 1);
                        } else if(aCal < bcal) {
                            fMap.put(bName, fMap.get(bName) + 1);
                        }
                    }
                }
            }
        }

        for(int i = 0 ; i < friends.length ; i++) {

            System.out.print(friends[i] + " => ");
            Friend ff = find(friends[i]);
            ff.map.forEach((key, value) -> System.out.print(key + ", " + value + " / "));

            System.out.println();
        }

        int max = 0;
        for(Map.Entry<String, Integer> e : fMap.entrySet() ){
            String name = e.getKey();
            Integer count = e.getValue();

            Friend f = find(name);
            System.out.println(name + ", " + count + ", " + f.calculate());

            if(max < e.getValue()){
                max = e.getValue();
            }
        }


        return max;
    }

    static ArrayList<Friend> list;
    static Friend find(String name) {

        for(Friend f : list) {
            if(f.name.equals(name)) {
                return f;
            }
        }

        return null;
    }

    static class Friend {
        String name;
        public Friend(String name) {
            this.name = name;
        }

        HashMap<String, Integer> map = new HashMap();
        Integer toCount = 0;
        Integer fromCount = 0;

        public void giveTo(String f) {
            if(map.get(f) == null) {
                map.put(f, 1);
            } else {
                map.put(f, map.get(f) + 1);
            }
            toCount++;
        }

        public Integer calculate() {
            return toCount - fromCount;
        }
    }
}

    선물 기록?
        더 많이 준 사람이 +1
    else
        선물 지수 비교

*/
