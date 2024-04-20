package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S2023AfternoonFirst {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());	// 산타 수
		
		C = Integer.parseInt(st.nextToken());	// 루돌프 힘 
		D = Integer.parseInt(st.nextToken());	// 산타 힘 

		// 루돌프 -1번, 빈 공간 0번, 산타는 산타 번호
		map = new int[N+1][N+1];
		
		// 루돌프 
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		lu = new Lu(r, c);
		map[r][c] = -1;

		// 산타 
		santas = new Santa[P + 1];
		for(int i = 0 ; i < P ; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			map[sr][sc] = num;
			santas[num] = new Santa(num, sr, sc);
		}
		
		// 턴 시작 
		for(TURN = 1 ; TURN <= M ; TURN++) {
//			System.out.println("새로운 턴 " + TURN);
//			print();
//			System.out.println();
			// 모든 산타 죽으면 종료 
			boolean allFail = true;
			for(int p = 1 ; p <= P ; p++) {
				if(!santas[p].state.equals(OUT)) {
					allFail = false;
				}
			}
			if(allFail) {
				break;
			}
			
			// lu 이동 
			lu.move();
//			System.out.println("노루 이동 ");
//			print();
//			System.out.println();
			
			// santa 이동 
			for(int p = 1 ; p <= P ; p++) {
				santas[p].move(lu);
			}
//			System.out.println("산타 이동 ");
//			print();
//			System.out.println();
//			
			
			// alive + sleep 산타 +1 점
			for(int p = 1 ; p <= P ; p++) {
				if(!santas[p].state.equals(OUT)) {
					santas[p].score++;
				}				
			}
		}
		
		for(int p = 1 ; p <= P ; p++) {
			System.out.print(santas[p].score + " ");
		}
		System.out.println();
	}

	static int N, M, P, C, D, TURN;
	static int[][] map;
	
	
	static int[][] luDir = {
			{-1, 0},
			{-1, 1},
			{0, 1},
			{1, 1},
			{1, 0},
			{1, -1},
			{0, -1},
			{-1, -1}
	};
	static Lu lu;
	static class Lu {
		int r;
		int c;
		public Lu(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean isHit() {
			for(int i = 1 ; i <= P ; i++) {
				if(santas[i].state.equals(OUT)) {
					continue;
				}
				
				if(santas[i].r == r && santas[i].c == c) {
					return true;
				}
			}
			
			return false;
		}
		
		public void hit(int dir) {
			int s = 0;
			for(int i = 1 ; i <= P ; i++) {
				if(santas[i].state.equals(OUT)) {
					continue;
				}
				
				if(santas[i].r == r && santas[i].c == c) {
					s = i;
					break;
				}
			}
			
//			System.out.println(s  +"번 산타침 turn:" + TURN);
			// 산타 날라감
			santas[s].score += C;
			santas[s].state = SLEEP;
			santas[s].wakeup = TURN + 2;
			
			for(int i = 0 ; i < C ; i++) {
				santas[s].r += luDir[dir][0];
				santas[s].c += luDir[dir][1];					
			}
			
			
			LinkedList<Santa> q = new LinkedList<>();
			q.add(santas[s]);
			
			while(!q.isEmpty()) {
				Santa poll = q.poll();				

				
				if(!(poll.r >= 1 && poll.r <= N && poll.c >= 1 && poll.c <= N)) {
					poll.state = OUT;
//					System.out.println(poll.num + "번 산타 아웃");
				} else {
					// 다른 산타 존재? 
					if(map[poll.r ][poll.c] > 0) {
						santas[map[poll.r][poll.c]].r += luDir[dir][0];
						santas[map[poll.r][poll.c]].c += luDir[dir][1];
						q.add(santas[map[poll.r][poll.c]]);
					}
					
					// 매핑
					map[poll.r][poll.c] = poll.num;
				}
			}
		}
		
		public void move() {
			// 가장 가까운 거리 찾기. 그 거리에 있는 산타들 찾기 
			
			ArrayList<Santa> list = new ArrayList<>();
			for(int i = 1 ; i <= P ; i++) {
				if(santas[i].state.equals(OUT)) {
					continue;
				}
				
				list.add(santas[i]);
			}
			
			Collections.sort(list, new Comparator<Santa>() {

				@Override
				public int compare(Santa o1, Santa o2) {
					
					int o1Dist = getDist(r, c, o1.r, o1.c);
					int o2Dist = getDist(r, c, o2.r, o2.c);
					if(o1Dist != o2Dist) {
						return Integer.compare(o1Dist, o2Dist);
					} else if(o1.r != o2.r) {
						return - Integer.compare(o1.r, o2.r);
					}
					
					return - Integer.compare(o1.c, o2.c);
				}
			});

			Santa target = list.get(0);
			int sdist = Integer.MAX_VALUE;
			int dir = 0;
			for (int i = 0 ; i < luDir.length ; i++) {
				int dr = luDir[i][0] + r;
				int dc = luDir[i][1] + c;
				
				int dist = getDist(target.r, target.c, dr, dc);
				if(sdist > dist) {
					sdist = dist;
					dir = i;
				}
			}
			
			// 루돌프 매핑
			map[r][c] = 0;
			r = luDir[dir][0] + r;
			c = luDir[dir][1] + c;
			map[r][c] = -1;
			
			if(isHit()) {
				hit(dir);
			}
		}
		
	}
	
	static int getDist(int sr, int sc, int dr, int dc) {
		return ((sr-dr) * (sr-dr)) + ((sc-dc)*(sc-dc));
	}

	static Santa[] santas;
	static final String ALIVE = "ALIVE";
	static final String SLEEP = "SLEEP";
	static final String OUT = "OUT";

	static int[][] sanDir = {
			{-1, 0},
			{0, 1},
			{1, 0},
			{0, -1},
	};
	static void print() {
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Santa {
		int num;
		int r;
		int c;
		int score = 0;
		String state;
// int sleepCount = 0;
		int wakeup = 0;
		public Santa(int num, int r, int c) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.state = ALIVE;
		}

		public void move(Lu lu) {
			if(state.equals(OUT)) {
				return;
			}

			if(wakeup == TURN) {
				state = ALIVE;
//				System.out.println(num + "번 산타 깨움 ");
			}
			if(state.equals(SLEEP)) {
//				System.out.println(num + "번 산타 잠 " + wakeup );
				return;
			}
			
			int sdist = getDist(lu.r, lu.c, r, c);
			int dir = -1;
			for (int i = 0 ; i < 4 ; i++) {
				int dr = sanDir[i][0] + r;
				int dc = sanDir[i][1] + c;
				
				if(!(dr >0 && dr <= N && dc > 0 && dc <= N)) {
					continue;
				}
				if(map[dr][dc] > 0) {
					continue;
				}
				int dist = getDist(lu.r, lu.c, dr, dc);
				if(sdist > dist) {
					sdist = dist;
					dir = i;
				}
			}
			
			if(dir == -1) {
				// 가까워지는 방법이 없음
				return;
			}
			
			int nr = sanDir[dir][0] + r;
			int nc = sanDir[dir][1] + c;
//			System.out.println(num + "번 산타 " + "위치 " + nr +", " + nc);
			
			if(map[nr][nc] > 0) {
				// 다른 산타?
//				System.out.println("다른 산타");
				return;
			} else if(map[nr][nc] == -1) {
				// 돌프 충돌
//				System.out.println("충돌");
				map[r][c] = 0;
				r = nr;
				c = nc;
				score += D;
				hit(dir);
			} else {
				// 이동
				map[nr][nc] = map[r][c];
				map[r][c] = 0;
				r = nr;
				c = nc;
			}
	
		}

		private void hit(int dir) {
			state = SLEEP;
			wakeup = TURN + 2;
			
			dir += 2;
			dir = dir % 4;
			
			for(int i = 0 ; i < D ; i++) {
				r += sanDir[dir][0];
				c += sanDir[dir][1];					
			}
//			System.out.println("new dir " + dir );
//			System.out.println("r " + r + " c " + c);
//			System.out.println();
			LinkedList<Santa> q = new LinkedList<>();
			q.add(this);
			
			while(!q.isEmpty()) {
				Santa poll = q.poll();				

				
				if(!(poll.r >= 1 && poll.r <= N && poll.c >= 1 && poll.c <= N)) {
					poll.state = OUT;
				} else {
					// 다른 산타 존재? 
					if(map[poll.r ][poll.c] > 0) {
						santas[map[poll.r][poll.c]].r += sanDir[dir][0];
						santas[map[poll.r][poll.c]].c += sanDir[dir][1];
						q.add(santas[map[poll.r][poll.c]]);
					}
					
					// 매핑
					map[poll.r][poll.c] = poll.num;
				}
			}
		}
	}
}



/*

5 7 2 2 2
1 3
1 1 2
2 1 4


루돌프 -> 산타 이동 
1 루돌프 움직임 
	- 8방향 모두 체크하여 가장 가까운 산타에게 돌진
	 	- 기절한 산타 포함 
		- 동일한 거리라면 r이 큰 경우, r도 같다면 c가 큰 경우


2 산타 움직임 
1 ~ P 산타 
	- 기절 또는 탈락한 산타 제외
이동 
	- 루돌프에게 가까운 방향으로 이동
		- 다른 산타 or 범위 벗어나면 이동 불가 ?
		- 이동 가능한 칸이 없으면 이동안함 ?
		- 루돌프와 가까워질 방법 없으면 이동안함 ?  
		- 이동 시 상우좌하 순서에 따라 이동 

3 충돌 
루돌프로 충돌
	- 루돌프 방향으로 산타 C만큼 날라감 
	- 맵 밖이면 탈락 
	- 다른 산타 있으면 상호작용

- 우선 착지. 다른 산타는 해당 칸으로 1만큼 날라감 (점수없이 날라가는걸로 ) 
	- 맵 밖이면 탈락 

- 기절 
	- 충돌하면 다담음 부터 정상 산타됨 
	- 또 충돌 가능 
	- 기절 상태가 탈락 등이 될수있음 



 */