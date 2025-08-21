package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

/**
 *  [BOJ 1744 수 묶기 — 핵심 요약]
 *  1) 목표: 수들을 “더하기/곱하기”로 묶어 최댓값
 *  2) 그리디 규칙
 *    - 양수(>1)는 큰 수끼리 곱하는 게 이득 (예: 3*2=6 > 3+2=5)
 *    - '1'은 곱하면 손해 → 무조건 더한다 (1*b = b < 1+b)
 *    - 음수는 절댓값 큰 것끼리(작은 값부터) 곱해 양수로 만든다 (−3 * −2 = 6)
 *    - 남는 음수 1개는 0이 있으면 0과 곱해 버리기(=0), 없으면 그냥 더한다(손해지만 어쩔 수 없음)
 */
public class Baek1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        LinkedList<Integer> pList = new LinkedList<>();
        LinkedList<Integer> mList = new LinkedList<>();
        int zeroCount = 0;

        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            if (val > 0) {
                pList.add(val);
            } else if (val == 0) {
                zeroCount++;
            } else {
                // 음수
                mList.add(val);
            }
        }

        // 양수는 내림차순(큰 수 먼저)
        Collections.sort(pList, (o1, o2) -> {
            return - o1 + o2;
        });
        // 음수는 오름차순
        Collections.sort(mList);

        long ans = 0;
        while (pList.size() > 1) {
            int a = pList.poll();
            int b = pList.poll();
            long multi = a * b;
            long add = a + b;
            ans += (multi > add) ? multi : add;
        }

        if (!pList.isEmpty()) {
            ans += pList.poll();
        }

//        System.out.println(mList);
        while(mList.size() > 1) {
            int a = mList.poll();
            int b = mList.poll();
            int multi = a * b;
            int add = a + b;
            ans += (multi > add) ? multi : add;
        }

        if (!mList.isEmpty()) {
            if (zeroCount == 0) {
                ans += mList.poll();
            }
        }

        System.out.println(ans);
    }
}
