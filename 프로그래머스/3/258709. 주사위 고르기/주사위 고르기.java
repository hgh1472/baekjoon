import java.util.*;
class Solution {
    static int n;
    static int[] com;
    static int[][] officialDice;
    static int winCount = 0;
    static int[] answerComb;

    public static int[] solution(int[][] dice) {
        n = dice.length;
        com = new int[n/2];
        answerComb = new int[n/2];
        officialDice = dice;
        getCombination(0, 0);
        return answerComb;
    }

    public static void getCombination(int depth, int idx) {
        if (depth == n / 2) {
            int win = getWinningCount();
            if (winCount < win) {
                winCount = win;
                for (int i = 0; i < n/ 2; i++) {
                    answerComb[i] = com[i] + 1;
                }
            }
            return;
        }
        for (int i = idx; i < n; i++) {
            com[depth] = i;
            getCombination(depth + 1, i+ 1);
            /**
             * 0 1/ 0 2/0 3
             *
             */
        }
    }

    public static int getWinningCount() {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        int[][] comA = new int[n/2][6];
        int[][] comB = new int[n/2][6];

        int a = 0;
        int b  = 0;
        for (int i = 0; i < n; i++) {
            boolean isContain = false;
            for (int j = 0; j < n/2; j++) {
                if (com[j] == i) {
                    isContain = true;
                    break;
                }
            }
            if (isContain) {
                comA[a++] = officialDice[i];
            } else {
                comB[b++] = officialDice[i];
            }
        }

        makeList(listA, 0, 0, comA);
        makeList(listB, 0, 0, comB);

        Collections.sort(listB);
        int count = 0;
        for (int i = 0; i < listA.size(); i++) {
            int left = 0;
            int right = listB.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (listB.get(mid) < listA.get(i)) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            count += right;
        }
        return count;
    }

    public static void makeList(List<Integer> list, int depth, int sum, int[][] dice) {
        if (depth == n / 2) {
            list.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            makeList(list, depth + 1, sum + dice[depth][i], dice);
        }
    }

}
