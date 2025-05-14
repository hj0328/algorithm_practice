package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Baek5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            char[] cmds = br.readLine().toCharArray();
            int count = Integer.parseInt(br.readLine());
            LinkedList<Integer> list = new LinkedList<>();
            String str = br.readLine();
            String[] split = str.substring(1, str.length() - 1).split(",");
            for (int i = 0; i < count; i++) list.add(Integer.parseInt(split[i]));

            boolean isError = false;
            int idx = 0;
            for(char c : cmds) {
                if(c == 'R') {
                    idx = idx == 0 ? list.size() - 1 : 0;
                } else {
                    if(list.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if(idx == 0) list.removeFirst();
                    else list.removeLast();
                }
            }

            if (isError) {
                sb.append("error").append("\n");
            } else {
                if (list.isEmpty()) {
                    sb.append("[]\n");
                    continue;
                }
                sb.append("[");
                if(idx == 0) {
                    while (!list.isEmpty()) {
                        sb.append(list.removeFirst()).append(",");
                    }
                } else {
                    while (!list.isEmpty()) {
                        sb.append(list.removeLast()).append(",");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("]\n");
            }
        }
        System.out.println(sb.subSequence(0,sb.length() - 1));
    }
}
