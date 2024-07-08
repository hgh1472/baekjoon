class Solution {
    static boolean[] isPossible;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        isPossible = new boolean[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            isPossible[i] = true;
        for (int i = 0; i < numbers.length; i++) {
            String binary = makeBinary(numbers[i]);
            validateBinary(binary, 0, binary.length() - 1, i);
            if (isPossible[i])
                answer[i] = 1;
        }
        return answer;
    }
    
    public void validateBinary(String s, int left, int right, int stringIndex) {
        if (left >= right)
            return;
        int mid = (left + right) / 2;
        int leftChild = (left + mid - 1) / 2;
        int rightChild = (mid + 1 + right) / 2;
        if (s.charAt(mid) == '0') {
            if (s.charAt(leftChild) == '1' || s.charAt(rightChild) == '1') {
                isPossible[stringIndex] = false;
                return;
            }
        }
        validateBinary(s, left, mid - 1, stringIndex);
        validateBinary(s, mid + 1, right, stringIndex);
    }
    
    public String makeBinary(long number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.insert(0, number % 2);
            number /= 2;
        }
        int n = 1;
        while (true) {
            if (sb.length() == Math.pow(2, n) - 1)
                break;
            else if (Math.pow(2, n) - 1 < sb.length() && sb.length() < Math.pow(2, n+1) - 1) {
                int d = (int) Math.pow(2, n+1) - 1 - sb.length();
                for (int i = 0; i < d; i++)
                    sb.insert(0, "0");
                break;
            }
            n++;
        }
        return sb.toString();
    }
}
// 64 + 
// 1011111 X
// 1101111 O
// 0111111 O

// 111 O
// 0101010 O
// 101 X
// 1111
// 0001111
// 2^n - 1개
