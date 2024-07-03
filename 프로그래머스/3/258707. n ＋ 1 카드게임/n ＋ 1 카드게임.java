import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        HashMap<Integer, Boolean> containCard = new HashMap<>();
        int[] pairRound = new int[n + 1];
        boolean[] isDiscard = new boolean[n+1];
        for (int i = 0; i < n; i++) {
            if (i < n/3) {
                containCard.put(cards[i], true);
                pairRound[(n+1) - cards[i]] = 0;
            }
            else {
                pairRound[(n+1) - cards[i]] = (i - n/3) / 2 + 1;
            }
        }

        int round = 1;
        int possibleRound = 0;
        // 처음에 뽑은 카드 중 쌍이 있는지 확인
        List<Integer> removeCard = new ArrayList<>();
        for (Integer key : containCard.keySet()) {
            if (containCard.containsKey((n + 1) - key)) {
                removeCard.add(key);
                possibleRound++;
            }
        }
        possibleRound = 1 + possibleRound / 2;
        for (Integer key : removeCard) {
            containCard.remove(key);
            isDiscard[key] = true;
        }

        while (round <= possibleRound && coin > 0) {
            if (n/3 + possibleRound * 2 > n)
                break;
            boolean draw = false;
            // 소유하고 있는 카드 중 짝을 뽑을 수 있는 경우
            if (coin >= 1) {
                removeCard.clear();
                for (Integer key : containCard.keySet()) {
                    if (pairRound[key] <= possibleRound) {
                        removeCard.add(key);
                        coin--;
                        possibleRound++;
                        draw = true;
                        isDiscard[key] = true;
                        isDiscard[(n+1) - key] = true;
                        break;
                    }
                }
            }
            for (Integer key : removeCard) {
                containCard.remove(key);
            }
            // 2개를 뽑아야 하는 경우 = 갖고있는 카드로 뽑을 수 있는게 없다.
            // 2개 뽑는 것과 1개 뽑는 것중 1개 뽑는게 우선이어야 한다.
            if (!draw && coin >= 2) {
                for (int i = n/3; i < n/3 + (possibleRound) * 2; i++) {
                    if (isDiscard[cards[i]])
                        continue;
                    if (pairRound[cards[i]] <= possibleRound && !isDiscard[(n+1) - cards[i]]) {
                        coin -= 2;
                        isDiscard[cards[i]] = true;
                        isDiscard[(n+1) - cards[i]] = true;
                        possibleRound++;
                        break;
                    }
                }
            }
            round++;
        }
        return possibleRound;
    }
}