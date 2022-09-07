package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek10773 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int K = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		long total = 0;
		for(int i = 0 ; i< K ; i++) {
			int val = Integer.parseInt(br.readLine());

			if(val == 0) {
				total -= stack.pop();
			} else {
				total += val;
				stack.push(val);
			}
		}

		System.out.println(total);
	}

}
