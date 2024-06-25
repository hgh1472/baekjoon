import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        String result = solution(str, bomb);
        if (result.equals("")) {
            result = "FRULA";
        }
        System.out.println(result);
    }

    static String solution(String str, String bomb) {
        StringBuilder sb = new StringBuilder(str);
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {
            int bombLength = bomb.length();
            stack.append(sb.charAt(i));
            if (stack.length() >= bomb.length()) {
                int stackLength = stack.length();
                if (stack.substring(stackLength - bombLength, stackLength).equals(bomb)) {
                    stack.delete(stackLength - bombLength, stackLength);
                }
            }
        }
        return stack.toString();
    }
}