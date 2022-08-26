package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek11328 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		int[] charCnts = new int['z' - 'a' + 1];
		for(int t = 0 ; t < TC ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] charArray = st.nextToken().toCharArray();
			
			for (char c : charArray) {
				charCnts[c-'a']++;
			}
			
			char[] charArray2 = st.nextToken().toCharArray();
			for (char c : charArray2) {
				charCnts[c-'a']--;
			}

			boolean isSuccess = true;
			for (int cnt : charCnts) {
				if(cnt != 0) {
					isSuccess = false;
					break;
				}
			}
			if(isSuccess) {
				System.out.println("Possible");
			} else {
				System.out.println("Impossible");
			}
			
			Arrays.fill(charCnts, 0);
		}
	}

}
