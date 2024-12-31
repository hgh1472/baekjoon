import java.util.*;

class Solution {
    static Stack<Node> history;
    public String solution(int n, int k, String[] cmd) {
        history = new Stack<>();
        
        Node start = new Node(-1);
        Node curr = start;
        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            curr.next = node;
            node.prev = curr;
            curr = node;
        }
        Node end = new Node(-1);
        curr.next = end;
        end.prev = curr;
        
        Node cursor = start;
        for (int i = 0; i <= k; i++) {
            cursor = cursor.next;
        }
        for (String c : cmd) {
            String[] s = c.split(" ");
            if (s[0].equals("U")) {
                cursor = cursor.up(Integer.parseInt(s[1]));
            }
            if (s[0].equals("D")) {
                cursor = cursor.down(Integer.parseInt(s[1]));
            }
            if (s[0].equals("C")) {
                cursor = cursor.remove();
            }
            if (s[0].equals("Z")) {
                Node pop = history.pop();
                pop.restore();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append("O");
        while (!history.isEmpty()) {
            Node node = history.pop();
            sb.setCharAt(node.value, 'X');
        }
        return sb.toString();
    }
    
    class Node {
        int value;
        Node prev;
        Node next;
        
        Node(int value) {
            this.value = value;
            prev = null;
            next = null;
        }
        
        public Node up(int k) {
            Node cursor = this;
            for (int i = 0; i < k; i++) {
                cursor = cursor.prev;
            }
            return cursor;
        }
        
        public Node down(int k) {
            Node cursor = this;
            for (int i = 0; i < k; i++) {
                cursor = cursor.next;
            }
            return cursor;
        }
        
        public Node remove() {
            Node cursor = this;
            history.push(this);
            cursor.prev.next = this.next;
            cursor.next.prev = this.prev;
            if (cursor.next.value == -1)
                return this.prev;
            return this.next;
        }
        
        public void restore() {
            this.prev.next = this;
            this.next.prev = this;
        }
    }
}