package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<String, String[]> tree = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String p = st.nextToken();
            String c1 = st.nextToken();
            String c2 = st.nextToken();

            tree.put(p, new String[] {c1, c2});
        }

        // 전위순회
        StringBuilder sb = new StringBuilder();

        preorder("A", sb, tree);
        sb.append("\n");
        inorder("A", sb, tree);
        sb.append("\n");
        postorder("A",sb, tree);

        System.out.println(sb);
    }
    static void preorder(String root, StringBuilder sb, HashMap<String, String[]> tree) {
        sb.append(root);

        String[] child = tree.get(root);
        if (!child[0].equals(".")) {
            preorder(child[0], sb, tree);
        }
        if (!child[1].equals(".")) {
            preorder(child[1], sb, tree);
        }
    }

    static void inorder(String root, StringBuilder sb, HashMap<String, String[]> tree) {

        String[] child = tree.get(root);
        if (!child[0].equals(".")) {
            inorder(child[0], sb, tree);
        }
        sb.append(root);
        if (!child[1].equals(".")) {
            inorder(child[1], sb, tree);
        }
    }

    static void postorder(String root, StringBuilder sb, HashMap<String, String[]> tree) {

        String[] child = tree.get(root);
        if (!child[0].equals(".")) {
            postorder(child[0], sb, tree);
        }

        if (!child[1].equals(".")) {
            postorder(child[1], sb, tree);
        }
        sb.append(root);
    }
}
