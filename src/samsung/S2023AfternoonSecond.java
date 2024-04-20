package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;


public class S2023AfternoonSecond {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		for(int q = 0 ; q < Q ; q++) {
			st = new StringTokenizer(br.readLine());;
			
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 100) {
				// 초밥 만들기
				int t = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				String name = st.nextToken();
				
				// eaters에 name 있나? 
				if(map.containsKey(name)) {
					Eater eater = map.get(name);
					eater.addDish(new int[] {x, t});
				} else {
					Eater eater = new Eater();
					eater.setName(name);
					eater.addDish(new int[] {x, t});
					map.put(name, eater);
				}
				
			} else if(cmd == 200) {
				// 입장
				int t = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				String name = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				
				Eater eater = null;
				if (map.containsKey(name)) {
					eater = map.get(name);
					eater.setEatingCount(n);
					eater.setSt(t);
					eater.setSx(x);
				} else {
					eater = new Eater();
					eater.setName(name);					
					eater.setSt(t);
					eater.setSx(x);
					eater.setEatingCount(n);
					map.put(name, eater);
				}
				
				// 테이블 확인 = 현재 시간에 맞는 음식 테이블 위치 파악 
				// 음식 있으면 최대 n개 먹기 
				ArrayList<int[]> dish = eater.getDish();
				ArrayList<int[]> newDish = new ArrayList<>();
				for (int[] d : dish) {
					int tt = d[1];
					int tx = ( d[0] + (t - d[1])) % L;
					
					if(t == tt && tx == x) {	// 시간 위치 모두 동일
						if(eater.eatingCount > 0) {
							eater.eatingCount--;								
						}
					} else {
						newDish.add(d);
					}
				}
				eater.setDish(newDish);
				
			} else {
				// 300 촬영
				// 시간 t에 남은 스시와 남은 인원
				int t = Integer.parseInt(st.nextToken());

				System.out.println("300 t: " + t);
				
				int sushi = 0;
				int people = 0;
				for (Entry<String, Eater> e : map.entrySet()) {
					Eater eater = e.getValue();
					
					// 사람 앉은 시간
					int sx = eater.getSx();
					
					
					System.out.println("name: " + eater.name + " st " + eater.getSt() );
					ArrayList<int[]> dish = eater.getDish();	// 먹거나 먹은 총 스시
					int leftFood = 0;
					for (int[] d : dish) {
						int eatTime = (L + (sx - (L +(d[0] - d[1]))%L % L) % L) % L;
						System.out.println("dish: " + d[0]);
						if(eater.getSt() != 0 && eatTime <= t) {
							System.out.println("eat time: " + eatTime );
							// 도착 후 이미 먹음 
						} else {
							// 남음 
							//eatingCount++;
							leftFood++;
						}
					}
					
					// t 이하라면 먹음 
					// 아니면 남은 초밥 
					// 남은 초밥이 없으면 사용자도 없음 
					if(leftFood > 0) {
						sushi += leftFood;
						if(eater.getSt() > 0) {
							people++;							
						}
					}
					System.out.println();
				}
				
				System.out.println(people +" " + sushi);
				System.out.println();
				// 사람, 남은 모든 초밥 수 
			}
		}
		
	}

	static int L;
	static int Q;
	static HashMap<String, Eater> map;
	static class Eater {
		int eatingCount = 0;
		String name;
		int st, sx;
		// 타겟 다 먹으면 이제 안먹어도 됨. 먹을꺼 이름 있어도 처리 안함 
		
		ArrayList<int[]> dish = new ArrayList<>();

		
		
		public int getEatingCount() {
			return eatingCount;
		}

		public void setEatingCount(int eatingCount) {
			this.eatingCount = eatingCount;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSt() {
			return st;
		}

		public void setSt(int st) {
			this.st = st;
		}

		public int getSx() {
			return sx;
		}

		public void setSx(int sx) {
			this.sx = sx;
		}

		public ArrayList<int[]> getDish() {
			return dish;
		}

		public void setDish(ArrayList<int[]> dish) {
			this.dish = dish;
		}

		
		public void addDish(int[] d) {
			dish.add(d);
		}
	}
}
