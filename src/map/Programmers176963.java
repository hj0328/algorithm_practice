package map;

import java.util.HashMap;

public class Programmers176963 {
    public static void main(String[] args) {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = {{"may", "kein", "kain", "radi"},{"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}};

        HashMap<String, Integer> nameYearMap = new HashMap();
        for(int i = 0 ; i < name.length; i++) {
            nameYearMap.put(name[i], yearning[i]);
        }

        int[] answer = new int[photo.length];
        for(int i = 0 ; i< photo.length ; i++) {
            for(int j = 0 ; j < photo[i].length; j++) {
                if (nameYearMap.get(photo[i][j]) == null) {
                    continue;
                }
                answer[i] += nameYearMap.get(photo[i][j]);
            }
        }

    }
}
