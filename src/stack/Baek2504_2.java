package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek2504_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> st = new Stack<>();
        char[] arr = br.readLine().toCharArray();
        boolean isFirstPush = false;
        boolean isFirstPop = false;
        long answer = 0;
        long val = 1;
        long add = 0;
        for(int i = 0  ; i < arr.length ; i++) {
            char c = arr[i];
            if(c == '(' || c == '[') {
                isFirstPop = true;
                st.push(c);

                if(isFirstPush) {
                    answer += add;
                    isFirstPush = false;
                }

                if(c == '(') {
                    val *= 2;
                } else {
                    val *= 3;
                }
            } else {
                if (st.empty()) {
                    System.out.println(0);
                    return;
                }
                isFirstPush = true;
                if(isFirstPop) {
                    add = val;
                }
                isFirstPop = false;

                if(c == ')' && st.peek() == '(') {
                    st.pop();
                    val /= 2;
                } else if(c == ']' && st.peek() == '[') {
                    st.pop();
                    val /= 3;
                } else {
                    System.out.println(0);
                    return;
                }
            }

//            System.out.println("val " + val + " add  " + add + " answer " + answer);
        }

        if (!st.empty()) {
            System.out.println(0);
        } else {
            answer += add;
            System.out.println(answer);
        }
    }
}
