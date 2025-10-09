import java.util.*;

class Solution {
    Deque<Node> stack;
    boolean[] notContains;
    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n+2];
        nodes[0] = new Node(-1);
        nodes[n+1] = new Node(-1);
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
            nodes[i].prev = nodes[i-1];
            nodes[i-1].next = nodes[i];
        }
        nodes[n].next = nodes[n+1];
        Node cursor = nodes[1 + k];
        
        notContains = new boolean[n+1];
        stack = new ArrayDeque<>();
        for (int i = 0; i < cmd.length; i++) {
            String[] info = cmd[i].split(" ");
            if (info[0].equals("U")) {
                int move = Integer.parseInt(info[1]);
                cursor = cursor.up(move);
            }
            if (info[0].equals("D")) {
                int move = Integer.parseInt(info[1]);
                cursor = cursor.down(move);
            }
            if (info[0].equals("C")) {
                stack.add(cursor);
                notContains[cursor.number] = true;
                cursor = cursor.remove();
            }
            if (info[0].equals("Z")) {
                Node pop = stack.removeLast();
                pop.rollback();
                notContains[pop.number] = false;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (notContains[i]) {
                sb.append("X");
            }
            else {
                sb.append("O");
            }
        }
        return sb.toString();
    }
    
    class Node {
        int number;
        Node prev, next;
        
        Node(int number) {
            this.number = number;
        }
        
        Node up(int move) {
            Node prev = this;
            for (int i = 0; i < move; i++) {
                prev = prev.prev;
            }
            return prev;
        }
        
        Node down(int move) {
            Node next = this;
            for (int i = 0; i < move; i++) {
                next = next.next;
            }
            return next;
        }
        
        Node remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            if (this.next.number == -1) {
                return this.prev;
            }
            return this.next;
        }
        
        void rollback() {
            this.prev.next = this;
            this.next.prev = this;
        }
    }
}