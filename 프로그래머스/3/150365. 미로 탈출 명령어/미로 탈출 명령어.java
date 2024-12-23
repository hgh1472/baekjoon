import java.util.*;

class Solution {
    static int endX;
    static int endY;
    static int nowX;
    static int nowY;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        endX = r;
        endY = c;
        nowX = x;
        nowY = y;
        StringBuilder sb = new StringBuilder();
        int minDistance = Math.abs(x - r) + Math.abs(y - c);
        if (minDistance > k || (k - minDistance) % 2 == 1) return "impossible";
        while (!isEnd(k)) {
            if (nowX < n) {
                nowX += 1;
                sb.append("d");
            }
            else if (1 < nowY) {
                nowY -= 1;
                sb.append("l");
            }
            else if (nowY < m) {
                nowY += 1;
                sb.append("r");
            }
            else {
                nowX -= 1;
                sb.append("u");
            }
            k--;
        }
        
        while (nowX < endX) {
            nowX += 1;
            sb.append("d");
        }
        while (nowY > endY) {
            nowY -= 1;
            sb.append("l");
        }
        while (nowY < endY) {
            nowY += 1;
            sb.append("r");
        }
        while (nowX > endX) {
            nowX -= 1;
            sb.append("u");
        }
        
        return sb.toString();
    }
    
    public boolean isEnd(int k) {
        if (Math.abs(endX - nowX) + Math.abs(endY - nowY) == k) {
            return true;
        }
        return false;
    }
}