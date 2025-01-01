package simulation;

/*
    [PCCP 기출문제] 1번 / 동영상 재생기
    https://school.programmers.co.kr/learn/courses/30/lessons/340213

    테케가 아니었으면 틀렸을 문제
    예제에서 틀려서 로직 순서의 이상확인

    시뮬레이션 -> 로직 순서 주의
 */
public class Programmers340213 {
    public static void main(String[] args) {
        System.out.println(solution("34:33", "13:00"	,"00:55",	"02:55", new String[] {"next", "prev"}));
    }

    private static String solution(String video_len, String posTime, String op_start, String op_end, String[] commands) {
        int vLen = convertTime(video_len);
        int pos = convertTime(posTime);
        int start = convertTime(op_start);
        int end = convertTime(op_end);

        for(String c : commands ) {
            // 오프닝 위치
            if(start <= pos && pos <= end) {
                pos = end;
            }
            if(c.equals("next")) {
                pos += 10;
            } else {
                pos -= 10;
            }

            // 0 미만
            if (pos < 0) {
                pos = 0;
            }

            // vLen 초과
            if(pos > vLen) {
                pos = vLen;
            }
        }

        // 오프닝 위치
        if(start <= pos && pos <= end) {
            pos = end;
        }

        return convertTime(pos);
    }

    private static int convertTime(String s) {
        String[] split = s.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    private static String convertTime(int s) {
        var sb = new StringBuilder();
        int m = s / 60;
        if(m < 10) {
            sb.append(0);
        }
        sb.append(m);
        sb.append(":");

        int ss = s % 60;
        if(ss < 10) {
            sb.append("0");
        }
        sb.append(ss);

        return sb.toString();
    }
}
