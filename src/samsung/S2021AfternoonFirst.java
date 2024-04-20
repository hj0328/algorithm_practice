package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S2021AfternoonFirst {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		totalFishCnt = Integer.parseInt(st.nextToken());
		int test = Integer.parseInt(st.nextToken());
		
		map = new Node[5][5];
		for(int i = 1 ; i <= 4 ; i++) {
			for(int j = 1; j <= 4 ; j++) {
				map[i][j] = new Node();
			}
		}
		
		for(int i = 0 ; i < totalFishCnt ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			Fish f = new Fish();
			f.setDir(dir);
			f.setX(x);
			f.setY(y);
			map[x][y].addFish(f);
		}
		
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= test ; t++) {
			// 물고기 복제 
			ArrayList<Fish> copy = new ArrayList<>();
			for(int y = 1; y <= 4 ; y++) {
				for(int x = 1; x <= 4 ; x++) {
					LinkedList<Fish> fishList = map[x][y].getFishList();
					for (Fish fish : fishList) {
						Fish f = new Fish();
						f.setDir(fish.getDir());
						f.setX(fish.getX());
						f.setY(fish.getY());
						copy.add(f);
					}
				}
			}
			
			// 모든 물고기 이동 
			ArrayList<Fish> movedFish = new ArrayList<>();
			for(int y = 1; y <= 4 ; y++) {
				for(int x = 1; x <= 4 ; x++) {
					LinkedList<Fish> fishList = map[x][y].getFishList();
					for (Fish fish : fishList) {
						fish.move(t);
						movedFish.add(fish);
					}
					map[x][y].setFishList(new LinkedList<>());
				}
			}
			for (Fish f : movedFish) {
				map[f.getX()][f.getY()].addFish(f);
			}
			
			
//			System.out.println("2. 물고기 이동");
//			print();
			
			// 상어 3연속 이동 
			minVal = 444;
			maxEat = 0;
			int prex = sx;
			int prey = sy;
			visited = new boolean[5][5];
			//visited[prex][prey] = true;
			sharkMove(prex, prey, 0, 0, 0);
			eatFish(prex, prey, minVal, t);
			System.out.println("3. 상어 이동, val: " + minVal + " eat: " + maxEat);
			print();
			
			// 맵에 냄새 제거 
			
			// 복제 완료
			// codpy를 map 노드에 추가
			for (Fish cfish : copy) {
				int x = cfish.getX();
				int y = cfish.getY();
				LinkedList<Fish> fishList = map[x][y].getFishList();
				fishList.add(cfish);
			}
			
			System.out.println("4. 복제 완료");
			print();
			System.out.println();
		}
		
		int ans = 0;
		for(int x = 1 ; x <= 4 ; x++) {
			for(int y = 1 ; y <= 4; y++) {
				ans += map[x][y].getFishList().size();
			}
		}
		System.out.println(ans);
	}

	static void print() {
		for(int x = 1 ; x <= 4 ; x++) {
			for(int y = 1 ; y <= 4; y++) {
				if(sx == x && sy == y) {
					System.out.print("S ");
				} else {
					System.out.print(map[x][y].getFishList().size() + " ");					
				}
			}
			System.out.println();
		}
	}
	
//	static void printFish() {
//		
//		for(int x = 1 ; x <= 4 ; x++) {
//			for(int y = 1 ; y <= 4; y++) {
//				if(sx == x && sy == y) {
//					System.out.print("S ");
//				} else {
//					LinkedList<Fish> fishList = map[x][y].getFishList();
//					for (Fish f : fishList) {
//						System.out.print(f.dir+ ",");
//					}
//					System.out.print(" ");
//				}
//			}
//			System.out.println();
//		}
//	}
	
	static int sx, sy;
	
	static int totalFishCnt;
	static Node[][] map;
	static class Node {
		int smellDeleteTime;
		LinkedList<Fish> fishList = new LinkedList<>();
		
		public int getSmellDeleteTime() {
			return smellDeleteTime;
		}
		public void setSmellDeleteTime(int smellDeleteTime) {
			this.smellDeleteTime = smellDeleteTime;
		}
		public LinkedList<Fish> getFishList() {
			return fishList;
		}
		public void setFishList(LinkedList<Fish> fishList) {
			this.fishList = fishList;
		}
		public void addFish(Fish fish) {
			this.fishList.add(fish);
		}
	}
	
	static int[][] fishDir = {
			{0, 0}, // 주의
			{0, -1},
			{-1, -1}, 
			{-1, 0},
			{-1, 1},
			{0, 1},
			{1, 1},
			{1, 0},
			{1, -1}
	};
	static boolean isIn(int nx, int ny) {
		return (nx >= 1 && nx <=4 && ny >= 1 && ny <= 4);
	}
	static class Fish {
		private int x, y, dir;
		
		public void move(int turn) {
			int nx = 0;
			int ny = 0;
			boolean canMove = false;
			int d = this.dir;
			for(int cnt = 0 ; cnt <= 8 ; cnt++) {
				nx = x + fishDir[d][0];
				ny = y + fishDir[d][1];
				if(!isIn(nx, ny) || map[nx][ny].getSmellDeleteTime() >= turn || 
						(nx == sx && ny == sy)) {
					// 보더 밖 또는 냄새 안지워짐 또는 상어
					
					d = (d - 1) % 8;
					if(d == 0) {
						d = 8;
					}
				} else {
					// 물고기 이동 
					canMove = true;
					break;
				}	
			}

			if(canMove) {
				this.dir = d;
				x = nx;
				y = ny;
			}
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getDir() {
			return dir;
		}

		public void setDir(int dir) {
			this.dir = dir;
		}
	}
	
	// 3번 연속 모든 방향 이동 
	// 최대 물고기 먹기 + 최대 값
	// 물고기 먹으면 냄새
	static int[][] sharkDir = {
			{},
			{-1, 0},
			{0, -1},
			{1, 0},
			{0, 1}
	};
	static int minVal;
	static int maxEat;
	static boolean[][] visited;
	static void sharkMove(int x, int y, int cnt, int val, int eat) {
		if(!isIn(x, y)) {
			return;
		}
		
		if(cnt == 3) {
			// 가장 많이 먹는 방법 선택
			if(maxEat < eat) {
				maxEat = eat;
				sx = x;
				sy = y;
				minVal = val;
				return;
			}
			
			// 가장 많이 먹는게 동일하면 값이 작은걸로 선택
			if(maxEat == eat && minVal > val) {
				minVal = val;
				sx = x;
				sy = y;
			}
			return;
		}
		
		val *= 10;
		for(int d = 1 ; d <= 4 ; d++) {
			int nx = x + sharkDir[d][0];
			int ny = y + sharkDir[d][1];
			
			if(!isIn(nx, ny) || visited[nx][ny]) {
				continue;
			}
			
			visited[nx][ny] = true;
			eat += map[nx][ny].getFishList().size();
			sharkMove(nx, ny, cnt + 1, val + d, eat);
			visited[nx][ny] = false;
			eat -= map[nx][ny].getFishList().size();
		}
	}
	
	static void eatFish(int preX, int preY, int val, int t) {
		int[] dirs = new int[3];
		for(int cnt = 2 ; cnt >=0 ; cnt--) {
			dirs[cnt] = val%10;
			val = val / 10;
		}
		
		int x = preX;
		int y = preY;
		for(int i = 0 ; i < 3 ; i++) {
			x += sharkDir[dirs[i]][0];
			y += sharkDir[dirs[i]][1];
			
			// 물고기 있으면 먹기 
			LinkedList<Fish> fishList = map[x][y].getFishList();
			map[x][y].setFishList(new LinkedList<>());
			
			if(fishList.size() > 0) {
				// 냄새 
				map[x][y].setSmellDeleteTime(t + 2);
			}
		}
	}
}


/*



*/