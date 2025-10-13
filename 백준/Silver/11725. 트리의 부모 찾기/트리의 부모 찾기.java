import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static int[] parents;
	static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		parents = new int[n+1];
		visited = new boolean[n+1];
		Node[] nodes = new Node[n+1];
		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < n-1; i++) {
			int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			nodes[nums[0]].edges.add(nodes[nums[1]]);
			nodes[nums[1]].edges.add(nodes[nums[0]]);
		}

		dfs(nodes[1], 0);
		StringBuffer sb = new StringBuffer();
		for (int i = 2; i <= n; i++) {
			sb.append(parents[i]).append("\n");
		}
		System.out.print(sb);
    }

	public static void dfs(Node node, int root) {
		if (visited[node.number]) {
			return;
		}
		visited[node.number] = true;
		parents[node.number] = root;
		for (Node edge : node.edges) {
			dfs(edge, node.number);
		}
	}

	static class Node {
		int number;
		List<Node> edges = new ArrayList<>();

		Node(int number) {
			this.number = number;
		}
	}
}
