class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if (Math.abs(x - r) + Math.abs(y - c) > k || (k - (Math.abs(x - r) + Math.abs(y - c))) % 2 == 1)
            return "impossible";
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (x < n && count + Math.abs(x - r) + Math.abs(y - c) + 2 <= k) {
            x++;
            sb.append("d");
            count++;
        }
        while (y > 1 &&  count + Math.abs(x - r) + Math.abs(y - c) + 2 <= k) {
            y--;
            count++;
            sb.append("l");
        }
        while (count + Math.abs(x - r) + Math.abs(y - c) + 2 <= k) {
            count += 2;
            sb.append("rl");
        }
        if (x - r < 0) {
            sb.append("d".repeat(Math.abs(x-r)));
        }
        if (c - y < 0) {
            sb.append("l".repeat(Math.abs(c-y)));
        }
        if (c - y > 0) {
            sb.append("r".repeat(c-y));
        }
        if (x - r > 0) {
            sb.append("u".repeat(x-r));
        }
        return sb.toString();
    }
}

