import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for (int i = 0; i < nodeinfo.length; i++) {
            pq.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        Node root = pq.poll();
        
        while (!pq.isEmpty()) {
            root.insert(pq.poll());
        }
        
        int[][] answer = new int[2][nodeinfo.length];
        Queue<Integer> q = new ArrayDeque<>();
        preorder(root, q);
        
        int idx = 0;
        while (!q.isEmpty()) {
            answer[0][idx++] = q.poll();
        }
        
        postorder(root, q);
        
        idx = 0;
        while (!q.isEmpty()) {
            answer[1][idx++] = q.poll();
        }
        
        return answer;
    }
    
    void preorder(Node node, Queue<Integer> q) {
        q.add(node.number);
        if (node.left != null) {
            preorder(node.left, q);
        }
        if (node.right != null) {
            preorder(node.right, q);
        }
    }
    
    void postorder(Node node, Queue<Integer> q) {
        if (node.left != null) {
            postorder(node.left, q);
        }
        if (node.right != null) {
            postorder(node.right, q);
        }
        q.add(node.number);
    }
    
    class Node implements Comparable<Node> {
        int number;
        int x;
        int y;
        Node left;
        Node right;
        
        Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
            left = null;
            right = null;
        }
        
        void insert(Node node) {
            if (this.x < node.x) {
                if (this.right == null) {
                    this.right = node;
                }
                else {
                    right.insert(node);
                }
            }
            else {
                if (this.left == null) {
                    this.left = node;
                }
                else {
                    left.insert(node);
                }
            }
        }
        
        @Override
        public int compareTo(Node o) {
            return o.y - this.y;
        }
    }
}