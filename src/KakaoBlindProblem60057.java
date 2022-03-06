/**
 * 2020 KAKAO BLIND RECRUITMENT 문자열 압축
 * https://programmers.co.kr/learn/courses/30/lessons/60057
 *
 * 후기
 * 취업 후 정말 오랜만에 알고리즘 문제를 다시 풀었다.
 * 오랜만에 풀어서 그런지 시간이 생각보다 오래 걸렸다..ㅜ
 *
 *  핵심
 * 1. 다양한 입력가능한 문자열들에 대해서 고민해보기
 * 경우의 수 처럼 여러 경우들을 가능하면 최대한 생각해보면 좋을 것 같다.
 * 그렇지 않으면 코딩을 처음부터 잘못된 방향으로 진행하게 되게 된다.
 *
 * 2. 문제에서 요구하는 질문을 파악
 * 문제에서는 최소 길이 개수를 요구했지만 나는 중간에 디버깅이 쉽도록 압축 문자열을 만들었다.
 * 이 git push 가 올라가면 문자열 대신 count 변경 예정(통과 완료)
 */
public class KakaoBlindProblem60057 {
    public static void main(String[] args) {
        String s = "aaabbaccc";

        int ret = Integer.MAX_VALUE;
        int strLen = s.length();
        int maxUnit = strLen / 2 + 1;
        for (int unit = 1; unit <= maxUnit; unit++) {
            int compressedLen = 0;
            int start = 0;
            int end = unit;
            int cnt = 1;

            // unit 단위로 문자열 s를 검사
            while (end <= strLen) {
                String curUnit = s.substring(start, end);
                String nextUnit = null;
                if (end + unit <= strLen) {
                    nextUnit = s.substring(start + unit, end + unit);
                } else {
                    // end가 범위를 벗어남
                    // 1. 현재검사 단위 문자열과 다음 검사 문자열이 다름을 의미
                    // 2. 나머지 문자열 길이를 같이 재야함
                    nextUnit = "";
                }

                if (curUnit.equals(nextUnit)) {
                    // 다음 단위와 동일
                    start += unit;
                    end += unit;
                    cnt++;
                } else {
                    // 다음 문자열 단위와 동일하지 않음
                    // 현재까지 중복된 단위 압축 계산
                    if (cnt == 1) {
                        compressedLen += curUnit.length();
                    } else {
                        compressedLen += String.valueOf(cnt).length() + curUnit.length();
                        cnt = 1;
                    }
                    start = end;
                    end = start + unit;
                }
                // 나머지 존재
                if (end > strLen) {
                    compressedLen += s.substring(start).length();
                }
            }

            if (ret > compressedLen) {
                ret = compressedLen;
            }
        }

        System.out.println("최소 값 " + ret);
    }
}
