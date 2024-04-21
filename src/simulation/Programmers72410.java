package simulation;

import java.util.ArrayList;

public class Programmers72410 {
    public static void main(String[] args) {

        String new_id = "...!@BaT#*..y.abcdefghijklm";

        char[] arr = new_id.toCharArray();
        ArrayList<Character> list = new ArrayList();
        ArrayList<Character> list2 = new ArrayList();
        for(char c : arr) {
            list.add(c);
        }

        // 대문자를 소문자로 치환
        // 대문자 -> 소문자로..
        int changeChar = 'A' - 'a';
        for(char c : list) {
            if('A' <= c && c <= 'Z') {
                c -= changeChar;
            }
            list2.add(c);
        }

        list = list2;
        list2 = new ArrayList();

        for(char c : list) {
            if('a' <= c && c <= 'z' || c == '-' || c == '_' || c == '.'
                    || '0' <= c && c <= '9') {
                list2.add(c);
            }
        }

        list = list2;
        list2 = new ArrayList<>();

        // ...a...a.a..a
        // .나오면 안넣음 .이후 첫 알파벳 나오면 . 넣음
        boolean firstDot = true;
        for(char c : list) {
            if(c == '.') {
                firstDot = false;
            } else {
                if(!firstDot) {
                    firstDot = true;
                    list2.add('.');
                }
                list2.add(c);
            }
        }

        list = list2;

        if(list.size() > 0 && list.get(0) == '.') {
            list.remove(0);
        }

        if(list.size() >0 && list.get(list.size()-1) == '.') {
            list.remove(list.size()-1);
        }


        if(list.isEmpty()){
            list.add('a');
        }


        while(list.size() > 15) {
            list.remove(list.size()-1);
        }

        if(list.size() > 0 && list.get(list.size()-1) == '.') {
            list.remove(list.size()-1);
        }

        if(list.size() < 3) {
            char last = list.get(list.size()-1);
            while(list.size() != 3) {
                list.add(last);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : list) {
            sb.append(c);
        }

//        return sb.toString()
    }
}
