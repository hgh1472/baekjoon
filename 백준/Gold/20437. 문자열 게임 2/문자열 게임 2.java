import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            int k = Integer.parseInt(br.readLine());

            Map<Character, List<Integer>> map = new HashMap<>();
            for (int j = 0; j < s.length(); j++) {
                if (!map.containsKey(s.charAt(j))) {
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    map.put(s.charAt(j), list);
                }
                else {
                    map.get(s.charAt(j)).add(j);
                }
            }

            int shortest = 10001;
            int longest = 0;
            for (Character c : map.keySet()) {
                List<Integer> list = map.get(c);
                if (list.size() < k) {
                    continue;
                }
                for (int j = 0; j + k - 1 < list.size(); j++) {
                    int start = list.get(j);
                    int end = list.get(j + k - 1);
                    int length = end - start + 1;
                    shortest = Math.min(shortest, length);
                    longest = Math.max(longest, length);
                }
            }

            if (shortest == 10001) {
                System.out.println(-1);
            }
            else {
                System.out.println(shortest + " " + longest);
            }
        }

    }


}
