import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Robot {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[52][8];
		for(int t = 1 ; t <= T ; t++) {
			
			for(int i = 0 ; i < 51 ; i++) {
					for(int k = 0 ; k < 8 ; k++) {
						dp[i][k] = 999999999;
					}
			}
			
			int n = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < n ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				if(i == 0) {
					dp[0][1] = b + c;
					dp[0][2] = a + c;
					dp[0][4] = a + b;
				} else {
					for(int th = 0 ; th < 3 ; th++) { // th번째 능력치 선택
						// dp[i][th][kind | (1<<th)]

						for(int kind = 	0 ; kind < 8 ; kind++) { // 
							
							int v = 0 ;
							if(th == 0) {
								v = b + c;
							} else  if(th == 1) {
								v = a + c;
							} else {
								v = a + b;
							}
							
//							System.out.println(th + 1+ "번째에 대해" + " 선택이 " + Integer.toBinaryString(kind));
							if(dp[i-1][(kind & ~(1<<th))] == 0) {
								continue;
							}
							 
								dp[i][kind] = Math.min(dp[i][kind],
										dp[i-1][kind & ~(1<<th)] + v);									
						 
							
//							System.out.println(Integer.toBinaryString(kind) + ": " + dp[i][kind]);
						}
					}
				}
			}
			
			
			// dp[n-1][0][7] , 
			long ans = Math.min(dp[n-1][7], dp[n-1][7]);
			ans = Math.min(ans, dp[n-1][7]);
			
			/*
			for(int i = 0 ; i < 8 ; i++) {
				System.out.println(dp[0][i]);
			}
			
			for(int i = 0 ; i < n ; i++) {
				System.out.print((i+1) + "번쨰 " );
				for(int kind = 0 ; kind < 8 ; kind++) {
					System.out.print(Integer.toBinaryString(kind) + "선택 최소  " + dp[i][kind] +", ");
				}
				System.out.println();
			}
			System.out.println();
			*/
			
			bw.append("#");
			bw.append(String.valueOf(t));
			bw.append(" ");
            if(ans == 999999999) {
                ans = -1;
            }
			bw.append(String.valueOf(ans));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	

}