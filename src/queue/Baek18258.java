package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek18258 {
	static final String NEW_LINE = "\n";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder ans = new StringBuilder();
		LinkedList<Integer> queue = new LinkedList<>();
		int TC = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < TC ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			if(cmd.equals("push")) {
				queue.add(Integer.parseInt(st.nextToken()));
			} else if(cmd.equals("pop")) {
				if(queue.isEmpty()) {
					ans.append("-1").append(NEW_LINE);
				} else {
					ans.append(queue.poll()).append(NEW_LINE);
				}
			} else if(cmd.equals("size")) {
				ans.append(queue.size()).append(NEW_LINE);
			} else if(cmd.equals("empty")) {
				if(queue.isEmpty()) {
					ans.append(1).append(NEW_LINE);
				} else {
					ans.append(0).append(NEW_LINE);
				}
			} else if(cmd.equals("front")) {
				if(queue.isEmpty()) {
					ans.append(-1).append(NEW_LINE);
				} else {
					ans.append(queue.peekFirst()).append(NEW_LINE);
				}
			} else {
				// back
				if(queue.isEmpty()) {
					ans.append(-1).append(NEW_LINE);
				} else {
					ans.append(queue.peekLast()).append(NEW_LINE);
				}
			}
		}

		System.out.println(ans.toString());
	}

}
