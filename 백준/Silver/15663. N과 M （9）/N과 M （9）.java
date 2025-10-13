import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	static Set<String> list = new HashSet<>();
	static List<String> answers = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int n = Integer.parseInt(info[0]);
		int m = Integer.parseInt(info[1]);

		boolean[] visited = new boolean[n];
		int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(num);
		combination(num, visited, "", m, 0);
		for (String s : answers) {
			System.out.println(s);
		}
	}

	public static void combination(int[] num, boolean[] visited, String result, int depth, int now) {
		if (depth == now) {
			if (list.contains(result)) {
				return;
			}
			list.add(result);
			answers.add(result);
			return;
		}
		for (int i = 0; i < num.length; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			combination(num, visited, result + num[i] + " ", depth, now+1);
			visited[i] = false;
		}
	}
}
