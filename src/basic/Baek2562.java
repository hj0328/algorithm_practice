package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek2562 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int maxVal = 0;
		int maxTh = 1;
		for(int th = 1 ; th <= 9 ; th++) {
			Integer val = Integer.valueOf(br.readLine());
			
			if(maxVal < val) {
				maxVal = val;
				maxTh = th;
			}
		}

		System.out.println(maxVal);
		System.out.println(maxTh);
	}

}
