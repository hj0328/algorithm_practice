package map;

import java.util.HashMap;
import java.util.HashSet;

/*
    신고결과받기
    https://school.programmers.co.kr/learn/courses/30/lessons/92334
    level1

    1 제출 성공
    - 마지막 답 리턴 시 Collections<Integer>를 array 로 어떻게 짧게 변경할지 고민했지만 배열을 선언해서 값 넣어주는게 제일 편한것같다.

    구조
    - 데이터 -> 자료구조 hashmap, hashset 저장 -> 로직에 따라 필요 데이터 정리
 */
public class Programmers92334_2 {
    public static void main(String[] args) {
        int[] solution = solution(new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
                2);

        for (int i : solution) {
            System.out.println(i);
        }
    }

    static int[] solution(String[] id_list, String[] report, int k) {

        // 신고당한 횟수 초기화
        var reported = new HashMap<String, HashSet<String>>();
        var mailbox = new HashMap<String, Integer>();
        for(String s : id_list) {
            reported.put(s, new HashSet<>());
            mailbox.put(s, 0);
        }

        // 신고 기록
        for(String s : report) {
            String user = s.split(" ")[0];
            String target = s.split(" ")[1];

            reported.get(target).add(user);
        }

        // k 이상 신고당한 사람의 신고자 메일함 개수 증가
        for(String s : id_list) {
            HashSet<String> users = reported.get(s);
            if(users.size() >= k ) {
                for (String u : users) {
                    mailbox.put(u, mailbox.get(u) + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];
        int i = 0;
        for(String s : id_list) {
            answer[i++] = mailbox.get(s);
        }

        return answer;
    }
}
