package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// 기본 소팅 + map 문제
public class Baek1302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
            if(maxCount < map.get(str)) maxCount = map.get(str);
        }

        ArrayList<String> ans = new ArrayList<>();
        int finalMaxCount = maxCount;
        List<String> collect = map.entrySet().stream()
                .filter(v -> v.getValue() == finalMaxCount)
                .map(v -> v.getKey())
                .sorted()
                .collect(Collectors.toList());

        System.out.println(collect.get(0));
    }
}
