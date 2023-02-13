package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	돌 게임
 * 		sk와 cy가 N개의 돌을 갖고 게임을 한다. 
 * 		sk부터 시작하여 번갈아가며 돌을 1개 또는 3개 가져간다.
 * 		마지막에 들고가는 사람이 승리한 사람이다. N개 돌이 주어질 때 누가 승리할까
 * 
 * 	a번째 승리한 사람은 돌이 N+1개가 되면 b가 승리한다. 
 * 	1개가 남기 때문에 b가 가져갈 수 있기 때문이다. 
 */
public class Baek9655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		if (N % 2 == 0) {
			System.out.println("CY");
		} else {
			System.out.println("SK");
		}
	}

}
