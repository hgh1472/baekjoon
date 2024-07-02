import java.util.*;
class Solution {
    static int n;
    static List<Integer> first;
    static List<Integer> second;
    static int[][] dices;
    static int combCount;
    static int[][] comb;

    public static int[] solution(int[][] dice) {
        int[] answer = new int[n / 2];
        n = dice.length;
        dices = dice;
        getComb(n);
        first = new ArrayList<>();
        second = new ArrayList<>();
        int maxCount = 0;
        for (int i = 0; i < combCount; i++) {
            first.clear();
            second.clear();
            boolean[] visited = new boolean[n + 1];
            int[] a = comb[i];
            for (int j = 0; j < a.length; j++)
                visited[a[j]] = true;
            calculate(a, 0, 0, first);

            int[] b = new int[n / 2];
            int bIdx = 0;
            for (int j = 1; j <= n; j++) {
                if (!visited[j])
                    b[bIdx++] = j;
            }
            calculate(b, 0, 0, second);
            
            first.sort(Comparator.naturalOrder());
            second.sort(Comparator.naturalOrder());

            int winCount = calculateWinner(first, second);
            if (maxCount < winCount) {
                maxCount = winCount;
                answer = a.clone();
            }
        }
        return answer;
    }

    public static void getComb(int n) {
        int combLength = getFactorial(n) / (getFactorial(n / 2) * getFactorial(n / 2));
        comb = new int[combLength][n / 2];
        combCount = 0;
        int[] pick = new int[n / 2];
        for (int i = 1; i <= n / 2 + 1; i++) {
            pick[0] = i;
            makeComb(pick, 1);
        }
    }

    public static void makeComb(int[] pick, int count) {
        if (count == n / 2) {
            comb[combCount] = pick.clone();
            combCount++;
            return;
        }
        for (int i = pick[count - 1] + 1; i <= n; i++) {
            pick[count] = i;
            makeComb(pick, count + 1);
        }
    }

    public static void calculate(int[] a, int count, int sum, List<Integer> nums) {
        if (count == n / 2) {
            nums.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            calculate(a, count + 1, sum + dices[a[count] - 1][i], nums);
        }
    }
    public static int getFactorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++)
            result *= i;
        return result;
    }

    public static int calculateWinner(List<Integer> a, List<Integer> b) {
        int winCount = 0;
        int firstIdx = 0;
        int secondIdx = 0;

        while (firstIdx < a.size() && secondIdx < b.size()) {
            if (a.get(firstIdx) > b.get(secondIdx)) {
                secondIdx++;
            }
            else {
                winCount += secondIdx;
                firstIdx++;
            }
        }
        if (firstIdx != a.size())
            winCount += (a.size() - firstIdx) * a.size();
        return winCount;
    }
}