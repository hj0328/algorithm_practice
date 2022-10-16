package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *	부분수열의 합 
 *	- 부분수열: 원본 수열의 순서를 유지한체 부분 숫자들로 구성된 수열
 *  - 1 2 3 4 가 있다면, 1 3은 부분수열이다. 
 *  - 하지만 1 3 2 는 순서가 바뀌었기 때문에 부분수열이 아니다.
 *  
 *  시간 복잡도 
 *  - 수열의 길이가 20이기 때문에 각 수열을 선택, 선택 안함으로 하여 풀어도 시간초과 발생안함 
 *  
 *  다양한 테스트해보기
 *  ex) 4 0 
 *      2 -2 2 -2 의 경우 인덱스 1에서 원하는 부분 수열을 찾았지만 
 *      인덱스 4에서 기존에 찾은 부분수열을 포함하는 경우를 생각할 수 있다.     
 */
public class Baek1182 {

	static int count;
	static int sum = Integer.MIN_VALUE;
	static int[] seq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		count = Integer.parseInt(st.nextToken());
		sum = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		seq = new int[count];
		for (int i = 0; i < count; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(0, 0, false);
		System.out.println(answer);
	}
	
	static int answer = 0;
	static void backTracking(int idx, int seqSum, boolean lastSelected) {
		
		if(seqSum == sum && lastSelected) {
			answer++;
		}

		if(idx == count) {
			return;
		}
		
		backTracking(idx+1, seqSum + seq[idx], true);
		backTracking(idx+1, seqSum, false);
	}

}
