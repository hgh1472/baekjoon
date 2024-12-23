class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long distance = 0L;
        int carry = 0;
        int get = 0;
        for (int i = n; i > 0; i--) {
            carry += deliveries[i - 1];
            get += pickups[i - 1];
            while (carry > 0 || get > 0) {
                carry -= cap;
                get -= cap;
                distance += i * 2;
            }
        }
        return distance;
    }
}