import java.util.*;

class Solution {
    Node root;
    int edgeCount;
    public int solution(int k, int[] num, int[][] links) {
        Node[] nodes = new Node[num.length];
        int total = 0;
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i, num[i]);
            total += num[i];
        }
        
        boolean[] hasParent = new boolean[nodes.length];
        for (int i = 0; i < links.length; i++) {
            if (links[i][0] != -1) {
                nodes[i].left = nodes[links[i][0]];
                hasParent[links[i][0]] = true;
            }
            if (links[i][1] != -1) {
                nodes[i].right = nodes[links[i][1]];
                hasParent[links[i][1]] = true;
            }
        }
        
        for (int i = 0; i < hasParent.length; i++) {
            if (!hasParent[i]) {
                root = nodes[i];
                break;
            }
        }
        
        return binarySearch(1, 10000 * 10000, k);
    }
    
    int binarySearch(int start, int end, int k) {
        int answer = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = (start + end) / 2;
            
            edgeCount = 0;
            int result = dfs(root, mid);
            if (result != -1) {
                if (edgeCount <= k-1) {
                    answer = Math.min(answer, mid);
                    end = mid-1;
                }
                else {
                    start = mid+1;
                }
            }
            else {
                start = mid+1;
            }
        }
        return answer;
    }
    
    int dfs(Node cur, int n) {
        if (cur == null) {
            return 0;
        }
        
        if (cur.count > n) {
            return -1;
        }
        int left = dfs(cur.left, n);
        if (left == -1) {
            return -1;
        }
        int right = dfs(cur.right, n);
        if (right == -1) {
            return -1;
        }
        if (cur.count + left + right <= n) {
            return cur.count + left + right;
        }
        // 둘 다 끊는 경우
        if (cur.count + left > n && cur.count + right > n) {
            edgeCount += 2;
            return cur.count;
        }
        // 왼쪽만 초과
        if (cur.count + left > n) {
            edgeCount++;
            return cur.count + right;
        }
        // 오른쪽만 초과
        if (cur.count + right > n) {
            edgeCount++;
            return cur.count + left;
        }
        // 다 합차면 초과하지만, 각각은 가능
        else {
            edgeCount++;
            return cur.count + Math.min(left, right);
        }
    }
    
    class Node {
        Node left, right;
        int number, count;
        
        Node(int number, int count) {
            this.number = number;
            this.count = count;
            this.left = null;
            this.right = null;
        }
    }
}