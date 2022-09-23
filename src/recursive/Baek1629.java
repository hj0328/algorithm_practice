package recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	A를 B번 곱한 수를 C로 나누기
 *
 *	풀이 
 *	 - 연산 작업 필요
 *
 *  주의
 *   - 모듈러 연산을 이용해 중간 계산값이 overflow되는 경우 주의
 *	 - A, B, C 모두 1이상 2,147,483,647 이하 자연수, 약 2*10^9
 *		-- A*A 도중 overflow가 발생할 수 있을까?
 *		-- A*A의 최대 가능 값은 약 4*10^18
 *		-- long 범위는 2^64 = 16*(2^10)^6 = 16*10^18
 *		-- 근사값으로 계산하였지만 두 범위가 매우 근접하기 때문에 A자체에 대한 모듈러 연산도 필요
 *		   => 이걸 하지 않아 틀림
 */
public class Baek1629 {

	static long C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// A, B, C 모두 1이상 2,147,483,647 이하 자연수,, ~ 10^9
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		System.out.println(multi(A % C, B));
	}

	static long multi(long A, long exponent) {
		if(exponent == 1) {
			return A;
		}

		long curVal = multi(A, exponent / 2);

		curVal = (curVal * curVal) % C;
		if((exponent & 1) == 1) {
			curVal = (curVal * A) % C;
		}

		return curVal % C;
	}
}
