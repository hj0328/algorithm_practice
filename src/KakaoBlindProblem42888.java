import java.util.*;

/**
 * 오픈채팅방
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 *
 */
public class KakaoBlindProblem42888 {

    static final String ENTER = "Enter";
    static final String LEAVE = "Leave";

    static final String ENTER_RESULT = "님이 들어왔습니다.";
    static final String LEAVE_RESULT = "님이 나갔습니다.";

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo",
                "Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        /*
        들어오고 나갈때 닉네임과 메시지가 출력
        닉네임 변경, 중복 닉네임 허용 -> 구분 필요
        1 채팅방 밖에서 변경
           다시 들어올 때 모두 변경
        2 채팅방 안에서 변경

        변경하면 모든 변경 기록에서 닉네임 변경

        모든 기록이 변경 된 후 문자열 배열 리턴
         */

        // uid 기준으로 result 결과 저장
        // 큐에 저장
        Queue<String> actionList = new ArrayDeque<>();
        Queue<String> enterUidList = new ArrayDeque<>();

        // change 에 따라서 uidNameMap 변경
        HashMap<String, String> uidNameMap = new HashMap<>();
        for (String s : record) {
            String[] recordRow = s.split(" ");
            String action = recordRow[0];
            String uid = recordRow[1];

            if(!uidNameMap.containsKey(uid)) {
                String nickName = recordRow[2];
                uidNameMap.put(uid, nickName);
            }

            if (ENTER.equals(action)) {
                String nickName = recordRow[2];
                if (!nickName.equals(uidNameMap.get(uid))) {
                    uidNameMap.put(uid, nickName);
                }
                actionList.add(ENTER_RESULT);
                enterUidList.add(uid);
                // 밖에서 변경 여부 검사
            } else if (LEAVE.equals(action)) {
                actionList.add(LEAVE_RESULT);
                enterUidList.add(uid);
            } else {
                // CHANGE status
                String nickName = recordRow[2];
                uidNameMap.put(uid, nickName);
            }
        }

        // 최종 결정 후 ret 배열에 저장
        // uid를 최종 nickName으로 변경
        int len = actionList.size();
        String[] ret = new String[len];
        for (int i = 0; i < len; i++) {
            String uid = enterUidList.poll();
            ret[i] = uidNameMap.get(uid) + actionList.poll();
            System.out.println(ret[i]);
        }
    }
}
