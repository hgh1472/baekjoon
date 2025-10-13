import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int n = Integer.parseInt(info[0]);
		int m = Integer.parseInt(info[1]);

		Map<Integer, Boolean> visited = new HashMap<>();
		int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(num);
		for (int i : num) {
			visited.put(i, false);
		}
		combination(num, visited, "", m, 0);
		for (String s : list) {
			System.out.println(s);
		}
	}

	public static void combination(int[] num, Map<Integer, Boolean> visited, String result, int depth, int now) {
		if (depth == now) {
			list.add(result);
			return;
		}
		for (int i = 0; i < num.length; i++) {
			if (visited.get(num[i])) {
				continue;
			}
			visited.put(num[i], true);
			combination(num, visited, result + num[i] + " ", depth, now+1);
			visited.put(num[i], false);
		}
	}
}
