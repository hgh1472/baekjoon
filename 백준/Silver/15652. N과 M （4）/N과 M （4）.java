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

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = i+1;
		}
		dfs(nums, new int[m], m, 0, 0);

		for (String answer : answers) {
			System.out.println(answer);
		}
	}

	public static void dfs(int[] nums, int[] choices, int stop, int depth, int idx) {
		if (depth == stop) {
			StringBuilder sb = new StringBuilder();
			for (int choice : choices) {
				sb.append(choice).append(" ");
			}
			String result = sb.toString();
			if (list.contains(result)) {
				return;
			}
			list.add(result);
			answers.add(result);
			return;
		}
		for (int i = idx; i < nums.length; i++) {
			choices[depth] = nums[i];
			dfs(nums, choices, stop, depth+1, i);
		}
	}
}
