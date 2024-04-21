package map;

import java.util.*;

public class Programmers92334 {
    public static void main(String[] args) {
        String[] id_list = new String[] {"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k =  2;

        // user가 신고당한 리스트
        HashMap<String, ArrayList<String>> userReportedMap = new HashMap<>();
        Arrays.stream(id_list)
                .forEach(id -> userReportedMap.put(id, new ArrayList()));

        // 날 신고한 사람 리스트 작성
        for(String r : report) {
            String[] rr = r.split(" ");
            String user = rr[0];
            String target = rr[1];

            // 한 사람이 중복 신고 가능하지만 target 구분하지 않음
            List<String> reporters = userReportedMap.get(target);
            if(!reporters.contains(user)) {
                reporters.add(user);
            }
        }

        // LinkedHashMap을 이용해서 key값 순서 유지하여 답 제출용 맵 생성
        HashMap<String, Integer> ansMap = new LinkedHashMap();
        Arrays.stream(id_list)
                .forEach(id -> ansMap.put(id, 0));

        // k 번 이상 신고당하면, 유저의 알림 개수 증가
        for(String user : id_list) {
            if(userReportedMap.get(user).size() < k) {
                continue;
            }

            // 신고한 유저의 알림 개수 + 1
            userReportedMap.get(user).stream()
                    .forEach(a -> ansMap.put(a, ansMap.getOrDefault(a, 0) + 1));
        }

        // 답 리턴
        ansMap.values().stream().mapToInt(Integer::valueOf).toArray();
    }
}
