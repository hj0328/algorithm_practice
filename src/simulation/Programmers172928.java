package simulation;

/*
공원 산책
지나다니는 길을 'O', 장애물을 'X'로 나타낸 직사각형 격자 모양의 공원에서 로봇 강아지가 산책을 하려합니다.
산책은 로봇 강아지에 미리 입력된 명령에 따라 진행하며, 명령은 다음과 같은 형식으로 주어집니다.

["방향 거리", "방향 거리" … ]
예를 들어 "E 5"는 로봇 강아지가 현재 위치에서 동쪽으로 5칸 이동했다는 의미입니다. 로봇 강아지는 명령을 수행하기 전에 다음 두 가지를 먼저 확인합니다.

주어진 방향으로 이동할 때 공원을 벗어나는지 확인합니다.
주어진 방향으로 이동 중 장애물을 만나는지 확인합니다.
위 두 가지중 어느 하나라도 해당된다면, 로봇 강아지는 해당 명령을 무시하고 다음 명령을 수행합니다.
공원의 가로 길이가 W, 세로 길이가 H라고 할 때, 공원의 좌측 상단의 좌표는 (0, 0), 우측 하단의 좌표는 (H - 1, W - 1) 입니다
https://school.programmers.co.kr/learn/courses/30/lessons/172928

 */
public class Programmers172928 {
    public static void main(String[] args) {
        String[] park = {"SOO", "OOO", "OOO"};
        String[] routes = {"E 2", "S 2", "W 1"};

        // s찾기
        int y = -1;
        int x = -1;
        int width = park[0].length();
        for(int i = 0 ; i < park.length ; i++) {
            for(int j = 0 ; j < width ; j++) {
                if(park[i].charAt(j) == 'S') {
                    y = i;
                    x = j;
                    break;
                }
            }
            if(y != -1) {
                break;
            }
        }

        // 이동
        for(int i = 0 ; i < routes.length; i++) {

            boolean canMove = true;
            String[] route = routes[i].split(" ");
            int dist = Integer.parseInt(route[1]);
            int ny = y;
            int nx = x;
            for(int m = 0 ; m < dist ; m++) {
                char dir = route[0].charAt(0);
                if(dir == 'N') {
                    ny += delta[0][0];
                    nx += delta[0][1];
                } else if(dir == 'E') {
                    ny += delta[1][0];
                    nx += delta[1][1];
                } else if(dir == 'S') {
                    ny += delta[2][0];
                    nx += delta[2][1];
                } else {
                    ny += delta[3][0];
                    nx += delta[3][1];
                }

                if(ny >= park.length || ny < 0 || nx < 0 || nx >= width) {
                    canMove = false;
                    break;
                }
                if(park[ny].charAt(nx) == 'X') {
                    canMove = false;
                    break;
                }

            }
            //System.out.println(y + ", " + x);
            if(!canMove) {
                //System.out.println("can not move");
                continue;
            }

            y = ny;
            x = nx;
        }

        int[] answer = {y, x};
        // return answer;
    }

    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
}
