import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node((char)('A' + i));
		}

		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			int idx1 = info[0].charAt(0) - 'A';
			if (!info[1].equals(".")) {
				int idx2 = info[1].charAt(0) - 'A';
				nodes[idx1].left = nodes[idx2];
			}
			if (!info[2].equals(".")) {
				int idx3 = info[2].charAt(0) - 'A';
				nodes[idx1].right = nodes[idx3];
			}
		}

		dfs1(nodes[0]);
		System.out.println();
		dfs2(nodes[0]);
		System.out.println();
		dfs3(nodes[0]);
    }

	public static void dfs1(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.c);
		dfs1(node.left);
		dfs1(node.right);
	}

	public static void dfs2(Node node) {
		if (node == null) {
			return;
		}
		dfs2(node.left);
		System.out.print(node.c);
		dfs2(node.right);
	}

	public static void dfs3(Node node) {
		if (node == null) {
			return;
		}
		dfs3(node.left);
		dfs3(node.right);
		System.out.print(node.c);
	}

	static class Node {
		char c;
		Node left, right;

		Node(char c) {
			this.c = c;
		}
	}
}
