package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr =  new int[N];
        int[] result = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder ans = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while(!stack.empty() && arr[stack.peek()] < arr[i]) { // i번째 값이 이전 값의 NGE인지 검사
                result[stack.pop()] = arr[i];
            }
            stack.push(i);  // i번째 값의 NGE를 찾기 위해 stack에 저장
            // i 번째 값에 대해 pop, push가 두번 발생하기에 전체 시작 복잡도는 O(N)
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        for (int i = 0; i < N; i++) {
            ans.append(result[i]).append(" ");
        }
        System.out.println(ans);
    }
}
