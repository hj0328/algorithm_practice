import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 거리두기 확인하기
 * https://programmers.co.kr/learn/courses/30/lessons/81302
 *
 */
public class Programmers81302 {

    static boolean[][] discovered = new boolean[5][5];
    static int[][] delta = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public static void main(String[] args) {
        /*
        대기실 5x5
        사람 간에 거리가 2 이하로 앉으면 안됨
        단 파티션을 막혀있으면 허용

        모든 사람에 대해서 bfs로 거리 2까지만 검사
        2이내에 사람이 있다면 이동 불가
        막혀있어도 이동 불가
         */

        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        int[] result = new int[5];
        int i = 0;
        for (String[] eachPlace : places) {
            // P에서 길이 2 만큼 bfs 탐색
            int ret = 1;
            initBfs();

            for (int y = 0 ; y < 5 ; y++) {
                for (int x = 0; x < 5; x++) {
                    if(eachPlace[y].charAt(x) == 'P') {
                        int retBfs = bfs(y, x, eachPlace);
                        if(retBfs == 0) {
                            ret = 0;
                            break;
                        }
                    }
                }
                if(ret == 0) {
                    break;
                }
            }
            result[i++] = ret;
        }
    }

    static void initBfs() {
        for (int i = 0; i < discovered.length; i++) {
            for (int j = 0; j < discovered[i].length; j++) {
                discovered[i][j] = false;
            }
        }
    }

    static int bfs(int y, int x, String[] eachPlace) {

        discovered[y][x] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(y);
        q.add(x);
        q.add(0);

        while (!q.isEmpty()) {
            int curY = q.poll();
            int curX = q.poll();
            int distance = q.poll();


            if(distance > 2) {
                continue;
            }

            for (int[] direction : delta) {
                int nextY = curY + direction[0];
                int nextX = curX + direction[1];
                int nextDistance = distance + 1;
                if ( nextY > 4 || nextY < 0 || nextX > 4 || nextX < 0) {
                    continue;
                }

                if(nextY == y && nextX == x) {
                    continue;
                }
                // 거리 2이내 사람 존재? 0 종료
                if (eachPlace[nextY].charAt(nextX) == 'P') {
                    if(nextDistance <= 2) {
                        return 0;
                    }
                }
                if (discovered[nextY][nextX]) {
                    continue;
                }

                if(eachPlace[nextY].charAt(nextX) == 'X') {
                    nextDistance++;
                }

                discovered[nextY][nextX] = true;
                q.add(nextY);
                q.add(nextX);
                q.add(nextDistance);
            }
        }

        return 1;
    }
}
