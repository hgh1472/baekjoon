import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int m = Integer.parseInt(br.readLine());
		int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int max = 0;
		int idx1 = 0;
		int idx2 = 0;
		List<Integer> answer = new ArrayList<>();
		while (true) {
			max = findCommonMax(a, b, idx1, idx2);
			if (max == 0) {
				break;
			}
			answer.add(max);
			for (int i = idx1; i < a.length; i++) {
				if (a[i] == max) {
					idx1 = i+1;
					break;
				}
			}
			for (int i = idx2; i < b.length; i++) {
				if (b[i] == max) {
					idx2 = i+1;
					break;
				}
			}
		}

		System.out.println(answer.size());
		for (Integer i : answer) {
			System.out.print(i + " ");
		}
	}

	public static int findCommonMax(int[] a, int[] b, int idx1, int idx2) {
		boolean[] contains = new boolean[101];
		for (int i = idx1; i < a.length; i++) {
			contains[a[i]] = true;
		}
		int max = 0;
		for (int i = idx2; i < b.length; i++) {
			if (contains[b[i]]) {
				if (max < b[i]) {
					max = b[i];
				}
			}
		}
		return max;
	}
}
