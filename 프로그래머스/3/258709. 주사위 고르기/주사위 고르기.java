import java.util.*;

class Solution {
    int[][] result;
    int[][] dice;
    int[] answer;
    int max = 0;
    
    public int[] solution(int[][] dice) {
        this.result = new int[dice.length][dice.length];
        this.dice = dice;
        
        combination(dice.length / 2, 0, 0, new boolean[dice.length]);
        
        for (int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        return answer;
    }
    
    public void combination(int n, int depth, int now, boolean[] contains) {
        if (n == depth) {
            calculate(contains);
            return;
        }
        for (int i = now; i < dice.length; i++) {
            contains[i] = true;
            combination(n, depth+1, i+1, contains);
            contains[i] = false;
        }
    }
    
    public void calculate(boolean[] contains) {
        int[] comb1 = new int[dice.length / 2];
        int[] comb2 = new int[dice.length / 2];
        
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < contains.length; i++) {
            if (contains[i]) {
                comb1[idx1++] = i;
            }
            else {
                comb2[idx2++] = i;
            }
        }
        
        List<Integer> A = new ArrayList<>();
        roll(comb1, 0, 0, A);
        List<Integer> B = new ArrayList<>();
        roll(comb2, 0, 0, B);
        
        Collections.sort(A);
        Collections.sort(B);
        
        int count = 0;
        int winnable = 0;
        for (int idx = 0; idx < A.size(); idx++) {
            while (winnable < B.size() && A.get(idx) > B.get(winnable)) {
                winnable++;
            }
            count += winnable;
        }
        
        if (max < count) {
            max = count;
            answer = comb1;
        }
    }
    
    public void roll(int[] comb, int n, int num, List<Integer> list) {
        if (n == comb.length) {
            list.add(num);
            return;
        }
        for (int i = 0; i < 6; i++) {
            roll(comb, n+1, num + this.dice[comb[n]][i], list);
        }
    }
}