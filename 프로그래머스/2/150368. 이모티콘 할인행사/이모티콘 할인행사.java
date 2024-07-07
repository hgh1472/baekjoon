class Solution {
    static int emoticonCount;
    static int maxSign = 0;
    static int maxIncome = 0;
    static int[][] usersInfo;
    static int[] emoticonsInfo;
    public int[] solution(int[][] users, int[] emoticons) {
        emoticonCount = emoticons.length;
        usersInfo = users;
        emoticonsInfo = emoticons;
        int[] rates = new int[emoticonCount];
        calculate(0, rates);
        return new int[]{maxSign, maxIncome};
    }
    
    public void calculate(int stack, int[] rates) {
        if (stack == emoticonCount) {
            int[] countAndMoney = calculatePerson(rates);
            int count = countAndMoney[0];
            int money = countAndMoney[1];
            if (count > maxSign || (count == maxSign && money > maxIncome)) {
                maxSign = count;
                maxIncome = money;
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            rates[stack] = 10 * i;
            calculate(stack + 1, rates);
        }
    }
    
    public int[] calculatePerson(int[] rates) {
        int count = 0;
        int income = 0;
        for (int i = 0; i < usersInfo.length; i++) {
            int money = 0;
            for (int j = 0; j < emoticonCount; j++) {
                if (rates[j] >= usersInfo[i][0])
                    money += emoticonsInfo[j] - (emoticonsInfo[j] * rates[j] / 100);
            }
            if (money >= usersInfo[i][1])
                count++;
            else {
                income += money;
            }
        }
        return new int[]{count, income};
    }
}

// 9300
// 40% => 930 * 4 = 3720 9300 - 3720 = 5280
// 32% => 6324
// 11% => 
// 40,2900 / 11,5200 / 5,5900 / 40, 3100
// 40% 3개 => 4620
// 20% 1600 => 1280
// 1. 할인했을 때 가능한것 뽑기