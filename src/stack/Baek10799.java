package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	쇠막대기
 * 	() 레이저
 *  () 나머지
 *  	((( 쇠막대기 시작 = 쇠막대기 개수 증가
 *  	))) 쇠막대기 마지막 = 쇠막대기 개수 감소
 *
 *	레이저가 발사되면 현재까지 쌓인 쇠막대기를 자른다.
 *
 *	탐색시작
 *		문자열 제잎 앞에서부터 검사
 *		(를 만나면 stack에 추가
 *		첫 )를 만나면 레이저 -> 스택 pop, 스택 크기 만큼 잘린 개수 층가
 *							(를 다시 만나면 다시 쇠막대기 끝으로 인식
 *							레이저 쌍 유무는 boolean으로 검사 첫 () 쌍만 레이저 나머지는 쇠막대기 끝을 의미하여 false처리
 *		이후 )를 계속 만나면 -> 쇠막대기 개수 감소, 0 미만으로 떨어지는건 불가
 *							) 는 쇠막대기 끝자리가 잘리기 때문에 전체 쇠막대기 수에 +1 처리
 *
 */
public class Baek10799 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] braces = br.readLine().toCharArray();

		boolean firstBrace = true;
		int cutCount = 0;
		int totalCutCount = 0;
		for (int i = 0; i < braces.length; i++) {
			if(braces[i] == '(') {
				cutCount++;
				firstBrace = true;
			} else {
				if(firstBrace) {
					cutCount--;
					if(cutCount < 0) {
						cutCount = 0;
					}
					totalCutCount += cutCount;
					firstBrace = false;
				} else {
					totalCutCount++;
					cutCount--;
					if(cutCount < 0) {
						cutCount = 0;
					}
				}
			}

		}

		System.out.println(totalCutCount);
	}

}
