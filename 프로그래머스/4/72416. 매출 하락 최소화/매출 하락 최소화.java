import java.util.*;

class Solution {
    int[][] dp;
    public int solution(int[] sales, int[][] links) {
        dp = new int[sales.length][2];
        Node[] nodes = new Node[sales.length];
        for (int i = 0; i < sales.length; i++) {
            nodes[i] = new Node(i);
        }
        
        for (int[] link : links) {
            int parent = link[0] - 1;
            int child = link[1] - 1;
            nodes[parent].children.add(nodes[child]);
        }
        
        dfs(nodes[0], sales);
        return Math.min(dp[0][0], dp[0][1]);
    }
    
    int dfs(Node node, int[] sales) {
        boolean contains = false;
        int min = Integer.MAX_VALUE;
        for (Node child : node.children) {
            int result = dfs(child, sales);
            dp[node.num][1] += result;
            dp[node.num][0] += result;
            if (dp[child.num][1] <= dp[child.num][0]) {
                contains = true;
            }
            else {
                min = Math.min(min, -dp[child.num][0] + dp[child.num][1]);
            }
        }
        if (!contains && node.children.size() != 0) {
            dp[node.num][0] += min;
        }
        dp[node.num][1] += sales[node.num];
        return Math.min(dp[node.num][0], dp[node.num][1]);
    }
    
    class Node {
        int num;
        List<Node> children = new ArrayList<>();
        
        Node(int num) {
            this.num = num;
        }
    }
}