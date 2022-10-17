package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class Baek11652 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		HashMap<Long, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			long val = Long.parseLong(br.readLine());
			if(!map.containsKey(val)) {
				map.put(val, 1);
			} else {
				map.put(val, map.get(val) + 1);
			}
		}
		
		
		int maxCount = 0;
		long minVal = Long.MAX_VALUE;
		for (Entry<Long, Integer> entry : map.entrySet()) {
			if(maxCount < entry.getValue() || (maxCount == entry.getValue() && minVal > entry.getKey())) {
				maxCount = entry.getValue();
				minVal = entry.getKey();
			}
		}
		
		System.out.println(minVal);
	}

}
