import java.util.*;
class Solution {
    int[] selectedDice;
    int totalDice;
    int[] answer;
    int[][] dice;
    int maxWinCount = 0;
    
    public int[] solution(int[][] dice) {
        selectedDice = new int[dice.length / 2];
        this.dice = dice;
        totalDice = dice.length;
        answer = new int[totalDice / 2];
        
        select(0, totalDice / 2, 0);
        
        return answer;
    }
    
    void select(int d, int f, int now) {
        if (d == f) {
            int winCount = roll();
            if (maxWinCount < winCount) {
                maxWinCount = winCount;
                for (int i = 0; i < totalDice / 2; i++) {
                    answer[i] = selectedDice[i] + 1;
                }
            }
            return;
        }
        for (int i = now; i < totalDice; i++) {
            selectedDice[d] = i;
            select(d + 1, f, i + 1);
        }
    }
    
    int roll() {
        List<Integer> result1 = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        
        // int[] result1 = new int[(int) Math.pow(6, totalDice / 2)];
        // int[] result2 = new int[(int) Math.pow(6, totalDice / 2)];
        
        int[] nonSelectedDice = new int[selectedDice.length];
        int idx = 0;
        for (int i = 0; i < totalDice; i++) {
            boolean isNotSelected = true;
            for (int j = 0; j < selectedDice.length; j++) {
                if (selectedDice[j] == i) {
                    isNotSelected = false;
                }
            }
            if (isNotSelected) {
                nonSelectedDice[idx++] = i;
            }
        }
        
        getSum(0, result1, selectedDice, 0);
        getSum(0, result2, nonSelectedDice, 0);
        
        
//         idx = 0;
//         for (int i = 0; i < selectedDice.length; i++) {
//             for (int j = 0; j < 6; j++) {
//                 for (int k = i + 1; k < selectedDice.length; k++) {
//                     for (int t = 0; t < 6; t++) {
//                         result1[idx++] = dice[selectedDice[i]][j] + dice[selectedDice[k]][t];
//                     }
//                 }
//             }
//         }
        
//         idx = 0;
//         for (int i = 0; i < nonSelectedDice.length; i++) {
//             for (int j = 0; j < 6; j++) {
//                 for (int k = i + 1; k < nonSelectedDice.length; k++) {
//                     for (int t = 0; t < 6; t++) {
//                         result2[idx++] = dice[nonSelectedDice[i]][j] + dice[nonSelectedDice[k]][t];
//                     }
//                 }
//             }
//         }
        
//         if (selectedDice.length == 1) {
//             for (int i = 0; i < 6; i++) {
//                 result1[i] = dice[selectedDice[0]][i];
//                 result2[i] = dice[nonSelectedDice[0]][i];
//             }
//         }
        
//         Arrays.sort(result1);
//         Arrays.sort(result2);
        
        Collections.sort(result1);
        Collections.sort(result2);
        
        int winCount = 0;
        for (int i = 0; i < result1.size(); i++) {
            winCount += search(result1.get(i), result2);
        }
//         while (idx1 < result1.length) {
//             winCount += search(result1[idx1], result2);
//             idx1++;
//         }
        
        return winCount;
        
    }
    
    void getSum(int d, List<Integer> result, int[] selected, int value) {
        if (d == selected.length) {
            result.add(value);
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            getSum(d + 1, result, selected, value + dice[selected[d]][i]);
        }
    }
    
    int search(int number, List<Integer> arr) {
        int left = 0;
        int right = arr.size();
        while (left < right) {
            int mid = (left + right) / 2;
            // if (arr[mid] == number) {
            //     while (0 <= mid && arr[mid] == number) {
            //         mid--;
            //     }
            //     return mid + 1;
            // }
            if (arr.get(mid) < number) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return right;
    }

}
