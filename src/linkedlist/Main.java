package linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		ListIterator<Integer> listIterator = new LinkedList<Integer>().listIterator();
		for(int i = 1 ; i <= N ; i++) {
			listIterator.add(i);
		}

		while(listIterator.hasPrevious()) {
			listIterator.previous();
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int cnt = N;
		while(cnt > 0) {
			int th = K;
			Integer cur = null;
			while(th > 0) {
				th--;
				if(listIterator.hasNext()) {
					cur = listIterator.next();
				} else {
					while(listIterator.hasPrevious()) {
						listIterator.previous();
					}
					if(listIterator.hasNext()) {
						cur = listIterator.next();
					}
				}
			}
			listIterator.remove();
			sb.append(cur);
			cnt--;
			if(cnt == 0) {
				sb.append(">");
			} else {
				sb.append(", ");
			}
		}
		System.out.println(sb.toString());
	}

}
