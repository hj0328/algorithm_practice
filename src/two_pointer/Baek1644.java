package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

/*
 * 	소수의 연속합(골드3)
 * 
 * 	알고리즘: 에라토스테네스의 체, 투포인터 
 * 
 * 	첫 시도 틀림: 
 * 	 - while 문에서 isPrime의 index 범위 벗어남
 */
public class Baek1644 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		setPrime();

		int l = 2;
		int r = 2;
		int sub = 2;
		int count = 0;
		while (l <= r && r <= N) {
			while (l <= N && !isPrime[l]) {
				l++;
			}

			while (r <= N && !isPrime[r]) {
				r++;
			}

			if (sub >= N) {
				if (sub == N) {
					count++;
				}

				sub -= l;
				l++;
			} else {
				r++;
				while (r <= N && !isPrime[r]) {
					r++;
				}

				sub += r;
			}
		}

		System.out.println(count);
	}

	static boolean[] isPrime = new boolean[4000001];

	static void setPrime() {
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for (int d = 2; d <= 2000; d++) {
			if (!isPrime[d]) {
				continue;
			}

			int notPrimeNum = d * 2;
			for (; notPrimeNum < 4000001; notPrimeNum += d) {
				isPrime[notPrimeNum] = false;
			}
		}
	}
}
