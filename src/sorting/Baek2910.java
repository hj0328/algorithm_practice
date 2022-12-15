package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
 *  빈도 정렬
 *   값을 정렬하는데 두 가지 기준으로 정렬
 *  
 *   문제 푸는데 시간 오래 걸림 
 */
public class Baek2910 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 수열 길이 
		int N = Integer.parseInt(st.nextToken());
		
		// 수열 중 최대 값 
		int C = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> valOrderMap = new HashMap<>();
		HashMap<Integer, Integer> valCountMap = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int next = Integer.parseInt(st.nextToken());
			
			if(valCountMap.containsKey(next)) {
				valCountMap.put(next, valCountMap.get(next) + 1);
			} else {
				valCountMap.put(next, 1);
			}
			
			if(!valOrderMap.containsKey(next)) {
				valOrderMap.put(next, valOrderMap.size());
			}
		}
		
		// 1.횟수 2. (횟수가 동일하면) 순서 에 대해 소팅 
		// list에 현재까지 입력된 수들을 추가 
		ArrayList<Integer> list = new ArrayList<>();
		for (Entry<Integer, Integer> entry : valCountMap.entrySet()) {
			list.add(entry.getKey());
		}
		
		// list를 소팅하며 1, 2 우선순위에 따라서 소팅진행
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(!valCountMap.get(o1).equals(valCountMap.get(o2))) {
					return valCountMap.get(o2).compareTo(valCountMap.get(o1));
				}
				return valOrderMap.get(o1) - valOrderMap.get(o2);
			}
		});
		
		// 출력하기 위해 소팅된 list와 각 숫자별 횟수(valCountMap)을 참고하여 sb에 추가
		StringBuilder sb = new StringBuilder();
		for (Integer val : list) {
			Integer count = valCountMap.get(val);
			while(count-- > 0) {
				sb.append(val).append(" ");
			}
		}
		
		System.out.println(sb.toString());
	}
}
