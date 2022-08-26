package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * 두 수의 합
 *  - 코너 케이스 주의 
 *  -- 빼기 연산으로 인한 마이너스 인덱스
 * https://www.acmicpc.net/problem/3273
 * @author hjlee
 *
 */
public class Baek3273 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 숫자배열 초기화 
		for(int i = 0 ; i < N ; i++) {
			int v = Integer.parseInt(st.nextToken());
			arr[i] = v;
		}
		
		int X = Integer.parseInt(br.readLine());

		// arr[i]숫자별 cnt개수 증가 
		int[] cnt = new int[2000001];
		for(int i = 0 ; i < N ; i++) {
			cnt[arr[i]]++;
		}

		// arr[i]숫자가 존재하는 쌍과 X값을 완성시키는지 검사
		// 코너 케이스, 음수로 IndexOutOfBound 체크  
		Set<Integer> selected = new HashSet<Integer>();
		for(int i = 0 ; i < N ; i++) {
			int other = X - arr[i];
			if(other <= 0) continue;
			if(other != arr[i] && cnt[other] != 0 && !selected.contains(other)) {
				selected.add(other);
				selected.add(arr[i]);
			}
		}
		
		System.out.println(selected.size()/2);
	}
}
