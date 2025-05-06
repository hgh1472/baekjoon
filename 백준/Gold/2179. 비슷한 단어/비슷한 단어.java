import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(' ', 0);

        int n = Integer.parseInt(br.readLine());

        String[] inputs = new String[n];
        int maxLength = 0;
        int minNumber = 300000;
        String maxPrefix = "";

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            inputs[i] = input;
            int length = 0;
            int lastSameNumber = 300000;
            String prefix = "";
            Node find = root;
            for (int j = 0; j < input.length(); j++) {
                char key = input.charAt(j);
                if (find.children.containsKey(key)) {
                    length++;
                    prefix = prefix.concat(String.valueOf(key));
                    find = find.children.get(key);
                    find.minNumber = Math.min(find.minNumber, i);
                    lastSameNumber = find.minNumber;
                }
                else {
                    find.children.put(key, new Node(key, i));
                    find = find.children.get(key);
                }
            }
            if (maxLength < length) {
                maxLength = length;
                maxPrefix = prefix;
                minNumber = lastSameNumber;
            } else if (maxLength == length && minNumber > lastSameNumber) {
                maxPrefix = prefix;
                minNumber = lastSameNumber;
            }
        }

        if (maxPrefix.isEmpty()) {
            return;
        }
        int count = 0;
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].length() < maxLength) {
                continue;
            }
            if (inputs[i].substring(0, maxLength).equals(maxPrefix)) {
                System.out.println(inputs[i]);
                count++;
            }
            if (count == 2) {
                break;
            }
        }

    }

    static class Node {
        char c;
        int minNumber;
        Map<Character, Node> children = new HashMap<>();

        public Node(char c, int minNumber) {
            this.c = c;
            this.minNumber = minNumber;
        }
    }
}
