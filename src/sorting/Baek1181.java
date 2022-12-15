package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

/*
 * 단어 정렬 
 * 	1 단어 중복 제거 
 *  2 짧은 단어 순서 
 *  3 사전 순서 
 */
public class Baek1181 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		HashSet<String> set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		
		String[] arr = new String[set.size()];
		Iterator<String> iterator = set.iterator();
		for (int i = 0 ; i < set.size() ; i++) {
			arr[i] = iterator.next();
		}
		
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length() != o2.length()) {
					return o1.length() - o2.length();
				}
				return o1.compareTo(o2);
			}
		});
		
		for (int i = 0; i < set.size(); i++) {
			System.out.println(arr[i]);
		}
	}

}
