import java.util.*;

class Solution {
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int ud = x - r;
        int lr = y - c;
        PriorityQueue<Move> q = new PriorityQueue<>();
        if (ud < 0) {
            for (int i = 0; i > ud; i--) {
                q.add(new Move(1, 0, 'd'));
            }
        }
        if (lr > 0) {
            for (int i = 0; i < lr; i++) {
                q.add(new Move(0, -1, 'l'));
            }
        }
        if (lr < 0) {
            for (int i = 0; i > lr; i--) {
                q.add(new Move(0, 1, 'r'));
            }
        }
        if (ud > 0) {
            for (int i = 0; i < ud; i++) {
                q.add(new Move(-1, 0, 'u'));
            }
        }
        
        int count = Math.abs(ud) + Math.abs(lr);
        if (k - count < 0) {
            return "impossible";
        }
        if ((k - count) % 2 != 0) {
            return "impossible";
        }
        
        int add = (k - count) / 2;
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        int nx = x;
        int ny = y;
        while (0 < k) {
            if (add == 0) {
                Move move = q.poll();
                sb.append(move.c);
                nx += move.dx;
                ny += move.dy;
            }
            else {
                Move move = getMovable(nx, ny, n, m);
                Move peek = q.peek();
                if (peek == null) {
                    q.add(move.getOpposite());
                    nx += move.dx;
                    ny += move.dy;
                    sb.append(move.c);
                    add--;
                }
                else if (move.compareTo(peek) < 0) {
                    q.add(move.getOpposite());
                    nx += move.dx;
                    ny += move.dy;
                    sb.append(move.c);
                    add--;
                }
                else {
                    q.poll();
                    nx += peek.dx;
                    ny += peek.dy;
                    sb.append(peek.c);
                }
            }
            k--;
        }
        
        return sb.toString();
    }
    
    Move getMovable(int nx, int ny, int n, int m) {
        if (nx < n) {
            return new Move(1, 0, 'd');
        }
        if (1 < ny) {
            return new Move(0, -1, 'l');
        }
        if (ny < n) {
            return new Move(0, 1, 'r');
        }
        return new Move(-1, 0, 'u');
    }
    
    class Move implements Comparable<Move> {
        int dx, dy;
        char c;
        
        Move(int dx, int dy, char c) {
            this.dx = dx;
            this.dy = dy;
            this.c = c;
        }
        
        @Override
        public int compareTo(Move m) {
            return this.c - m.c;
        }
        
        public Move getOpposite() {
            if (this.c == 'd') {
                return new Move(-1, 0, 'u');
            }
            if (this.c == 'l') {
                return new Move(0, 1, 'r');
            }
            if (this.c == 'r') {
                return new Move(0, -1, 'l');
            }
            return new Move(1, 0, 'd');
        }
    }
}