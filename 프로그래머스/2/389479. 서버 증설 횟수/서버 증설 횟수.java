class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] possible = new int[24];
        for (int i = 0; i < 24; i++) {
            possible[i] = m-1;
        }
        int server = 1;
        for (int i = 0; i < 24; i++) {
            if (players[i] < possible[i]) {
                continue;
            }
            int add = (players[i] - possible[i]) / m;
            if ((players[i] - possible[i]) % m != 0) {
                add++;
            }
            for (int j = 0; j < k; j++) {
                if (i+j >= 24) {
                    break;
                }
                possible[i+j] += m * add;
            }
            answer += add;
        }
        return answer;
    }
}