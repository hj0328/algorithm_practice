package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  [BOJ 7682 틱택토]
 *  목표: 한 줄(9글자)로 주어진 보드 상태가 게임 규칙상 가능한 최종 상태인지 판정해 "valid"/"invalid" 출력.
 *  입력:
 *      각 줄에 9글자 문자열(문자 X, O, .) — 행 우선(0~2행, 각 3칸).
 *      마지막 줄은 "end"로 입력 종료.
 *  출력: 각 상태마다 한 줄에 valid 또는 invalid
 *
 * 판정 규칙(불변식, 항상 맞아야 함)
 *      선공은 X → 최종 말 개수는 x==o 또는 x==o+1 만 가능
 *      X와 O가 동시에 승리하면 무조건 invalid
 *      승자-개수 일치: X 승리 → x==o+1, O 승리 → x==o
 *      아무도 승리 안 했으면 보드는 꽉 차야 함( dot==0 && x==5 && o==4 )
 *
 */
public class Baek7682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (!(input = br.readLine()).equals("end")) {

            char[] charArray = input.toCharArray();
            char[][] arr = new char[3][3];
            int ci = 0;
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    arr[y][x] = charArray[ci];
                    ci++;
                }
            }

            int[] count = new int[2]; // 0이 x, 1이 o
            count(arr, count);

            if (count[0] < count[1]) { // O 칸을 더 많이 두면 invalid
                System.out.println("invalid");
                continue;
            }

            int successCount = successCount(arr); // 3칸 성공 개수
            if (successCount == 2 && scColor == 'X' && count[0] == 5 && count[1] == 4) {
                System.out.println("valid");
                continue;
            }

            if (successCount == 0) {
                if ((count[0] == 5 && count[1] == 4)) {
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
                continue;
            }

            if (successCount != 1) {
                System.out.println("invalid");
                continue;
            }

            if (successCount == 1 && scColor == 'O' ) {
                // 개수 같아야 함
                if (count[0] != count[1]) {
                    System.out.println("invalid");
                    continue;
                }
                System.out.println(count[0] + ", " + count[1]);
            }

            if (successCount == 1 && scColor == 'X' ) {
                //
                if (count[0] != count[1] + 1) {
                    System.out.println("invalid");
                    continue;
                }
            }

            System.out.println("valid");
        }
    }

    static void count(char[][] arr, int[] count) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if(arr[y][x] == 'X') {
                    count[0]++;
                } else if(arr[y][x] == 'O') {
                    count[1]++;
                }
            }
        }
    }

    static char scColor;
    static int successCount(char[][] arr) {
        int successCount = 0;

        // x는 0, 위에서 아래 방향
        for (int cnt = 0; cnt < 3; cnt++) {
            int scCount = 1;
            char color = arr[0][cnt];
            if (color == '.') {
                continue;
            }
            for (int dy = 1; dy < 3; dy++) {
                if(color == arr[dy][cnt]) scCount++;
            }

            if(scCount == 3) {
                successCount++;
                scColor = color;
            }
        }

        // 왼에서 오른
        for (int cnt = 0; cnt < 3; cnt++) {
            int scCount = 1;
            char color = arr[cnt][0];
            if (color == '.') {
                continue;
            }

            for (int dx = 1; dx < 3; dx++) {
                if (color == arr[cnt][dx]) {
                    scCount++;
                }

                if(scCount == 3) {
                    successCount++;
                    scColor = color;
                }
            }

        }

        // 대각선
        int scCount = 1;
        char color = arr[0][0];
        if (color != '.') {
            for (int d = 1; d < 3; d++) {
                if(color == arr[d][d]) scCount++;

                if(scCount == 3) {
                    successCount++;
                    scColor = color;
                }
            }
        }

        scCount = 1;
        color = arr[2][0];
        if (color != '.') {
            for (int d = 1; d < 3; d++) {
                if(color == arr[2 - d][d]) scCount++;

                if(scCount == 3) {
                    successCount++;
                    scColor = color;
                }
            }
        }

        return successCount;
    }

}
