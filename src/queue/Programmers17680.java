package queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
    프로그래머스- 캐시
    https://school.programmers.co.kr/learn/courses/30/lessons/17680#

    문자열
 */
public class Programmers17680 {
    public static void main(String[] args) {

        int cacheSize = 3;
        String[] cities = {"Hi", "hi", "hI"};
        int solution = Solution.solution(cacheSize, cities);
        System.out.println(solution);
    }
    static class Solution {

        /*
            첫 번째 문제 풀이
            cache 데이터의 데이터 입력 전후로 시간을 계산하는 방식
            미리 정리를 해두지 않고 문제를 풀어 로직이 뒤죽박죽이다.
            캐시 사이즈를 문제에서 정해줬는데 자료구조 사이즈를 상관없이 축가하여 문제가 계속 틀렸었다.
            캐시 사이즈가 0일 때는 값을 추가하지 못하도록 했어야 했다.
            조건으로 주어진 변수의 값에 따라 빠뜨릴 수 있는 상황들이 꼼꼼히 처리했어야 했다. 
         */
        public static int solution(int cacheSize, String[] cities) {

            /*
                cacheSize만큼 도시 이름을 캐시에 저장
                처음 캐시 크키만큼 도시 정보를 모두 저장
                cachesize + 1 부터 도시를 검색할때 캐시에 존재하는지 검사.
                캐시에 존재한다면 hit, 비용 1.
                없다면 비용 5
                이후 캐시의 제일 앞 단어 제거 후 뒤에 새 단어 추가
            */
            int answer = 0;
            LinkedList<String> cache = new LinkedList<>();
            HashMap<String, Integer> map = new HashMap();

            int cursur = 0;
            for(;cursur < cities.length; cursur++) {
                String city = cities[cursur].toLowerCase();

                // 캐시에 들어있는지 검사
                boolean isHit = false;
                if(cache.contains(city)) {
                    isHit = true;
                }

                if(isHit) {
                    answer += 1;
                } else {
                    answer += 5;
                }

                // 캐시 데이터 age(값) 증가
                for(Map.Entry<String, Integer> e : map.entrySet()) {
                    e.setValue(e.getValue() + 1);
                }

                if(isHit) {
                    // hit! 들어있는 데이터 age 0으로 초기화
                    map.put(city, 0);
                } else {
                    // 캐시가 miss고, 데이터 넣기 전 캐시에 이미 데이터가 다 차면,
                    // 가장 오랫동안 사용되지 않은 데이터 제거
                    if(cache.size() == cacheSize) {
                        String maxStr = "";
                        int count = -1;
                        for(Map.Entry<String, Integer> e : map.entrySet()) {
                            if(e.getValue() > count) {
                                count = e.getValue();
                                maxStr = e.getKey();
                            }
                        }
                        map.remove(maxStr);
                        cache.remove(maxStr);
                    }

                    if(cacheSize != 0) {
                        // miss라면 캐시에 새로운 데이터 추가
                        cache.add(cities[cursur].toLowerCase());
                        map.put(city, 0);
                    }
                }
            }
            return answer;
        }
    }
}
