class Solution {
    int maxCount;
    int maxSales;
    
    public int[] solution(int[][] users, int[] emoticons) {
        maxCount = 0;
        maxSales = 0;
        combination(0, new int[emoticons.length], users, emoticons);
        int[] answer = {maxCount, maxSales};
        return answer;
    }
    
    public void combination(int count, int[] discounts, int[][] users, int[] emoticions) {
        if (count == emoticions.length) {
            calculate(discounts, users, emoticions);
            return;
        }
        for (int i = 10; i <= 40; i += 10) {
            discounts[count] = i;
            combination(count+1, discounts, users, emoticions);
        }
    }
    
    public void calculate(int[] discounts, int[][] users, int[] emoticions) {
        int[] prices = new int[emoticions.length];
        for (int i = 0; i < emoticions.length; i++) {
            prices[i] = emoticions[i] * (100 - discounts[i]) / 100;
        }
        
        int count = 0;
        int sales = 0;
        for (int i = 0; i < users.length; i++) {
            int sum = 0;
            for (int j = 0; j < emoticions.length; j++) {
                if (users[i][0] <= discounts[j]) {
                    sum += prices[j];
                }
            }
            if (sum >= users[i][1]) {
                count++;
            }
            else {
                sales += sum;
            }
        }
        
        if (maxCount < count) {
            maxCount = count;
            maxSales = sales;
        }
        else if (maxCount == count && maxSales < sales) {
            maxSales = sales;
        }
    }
    
    /**
    1. 가입자가 최대 -> 각 이모티콘을 구매하려면 최소 비율 이상 
    (사용자 할인율, 금액) 할인율을 더 높이면 금액이 낮아짐 -> 안살 수도 있음
    할인율 40으로 시작 -> 40이면 사는데, 안사는 사람이 생길수도있음
    
    2. 판매액 최대
    */
}