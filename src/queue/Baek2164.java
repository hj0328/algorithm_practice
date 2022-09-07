package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Baek2164 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		LinkedList<Integer> list = new LinkedList<>();

		for(int i = 1 ; i <= N ; i++) {
			list.add(i);
		}

		while(list.size() != 1) {
			list.removeFirst();
			list.add(list.removeFirst());
		}

		System.out.println(list.get(0));
	}

}
