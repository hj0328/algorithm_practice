import java.util.*;

/*
메뉴 리뉴얼
https://programmers.co.kr/learn/courses/30/lessons/72411

'각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로 했습니다.'
문제에서 위 내용에서 헷갈렸다.
단품메뉴 조합은 주어진 개수대로 만들되, 손님들이 가장 많이 주문한 조합을 만들면 된다.

 */
public class KakaoBlindProblem72411 {

    /*
        itemSelectedMap<item 조합, item 조합 선택 횟수> 초기화
        item 조합은 최소 길이 2이상
     */
    private static HashSet<String> courseMap = new HashSet<>();

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        // 손님 메뉴에서 조합 생성
        for (String order : orders) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            initOrderCombination(String.valueOf(chars), 0, "");
        }

        // 메뉴 조합이 주문한(선택된) 횟수 구하기
        List<String> courseList = new ArrayList<>(courseMap);
        HashMap<String, Integer> courseCntMap = new HashMap<>();
        for (String order : orders) {
            // order 주문에 대해

            for (String courseMenu : courseList) {
                // 코스 메뉴가 order에 속하는지 검사 후 courseCntMap에 횟수 업데이트
                char[] chars = courseMenu.toCharArray();
                int isContain = chars.length;
                for (char aChar : chars) {
                    if (order.contains(String.valueOf(aChar))) {
                        isContain--;
                    }
                }
                if (isContain != 0) {
                    continue;
                }

                if (!courseCntMap.containsKey(courseMenu)) {
                    courseCntMap.put(courseMenu,  1);
                } else {
                    courseCntMap.put(courseMenu,  courseCntMap.get(courseMenu) + 1);
                }
            }
        }

        // 메뉴 조합 역 정렬
        // 길이가 같다면 선택 횟수가 더 큰 것을 우선순위로 둔다.
        Collections.sort(courseList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                   return courseCntMap.get(o2) - courseCntMap.get(o1);
                }
                return o2.length() - o1.length();
            }
        });

        ArrayList<String> ansList = new ArrayList<>();
        for (int c : course) {
            int maxMenuCnt = 0;
            // 동일한 course에 대해서 가장 긴 course 메뉴리스트를 추가한다.
            for (String courseMenu : courseList) {
                if (courseCntMap.get(courseMenu) < 2) {
                    continue;
                }
                if (courseMenu.length() == c && maxMenuCnt <= courseCntMap.get(courseMenu)) {
                    ansList.add(courseMenu);
                    maxMenuCnt = courseCntMap.get(courseMenu);
                }
            }
        }

        Collections.sort(ansList);
        String[] ret = new String[ansList.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = ansList.get(i);
        }

        // return ret;
    }

    /*
    모든 사용자 order에 대해 생성가능한 주문 조합 생성
     */
    static void initOrderCombination(String order, int idx, String courseMenu) {
        if (idx == order.length()) {
            if (courseMenu.length() < 2) {
                return;
            }

            if (!courseMap.contains(courseMenu)) {
                courseMap.add(courseMenu);
            }
            return;
        }

        initOrderCombination(order, idx + 1, courseMenu);
        initOrderCombination(order, idx + 1, courseMenu + order.charAt(idx));
    }
}
