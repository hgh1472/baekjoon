import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> temp = new ArrayList<>();
        
        String[] a = today.split("\\.");
        int[] todayInfo = new int[3];
        for (int i = 0; i < 3; i++)
            todayInfo[i] = Integer.parseInt(a[i]);
        for (int i = 0; i < privacies.length; i++) {
            String[] dayAndTerm = privacies[i].split(" ");
            if (isExpired(todayInfo, dayAndTerm[0], calculateTerm(terms, dayAndTerm[1])))
                temp.add(i + 1);
        }
        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++)
            answer[i] = temp.get(i);
        return answer;
    }
    
    public boolean isExpired(int[] todayInfo, String contractDay, int term) {
        String[] b = contractDay.split("\\.");
        int[] contractDayInfo = new int[3];
        for (int i = 0; i < 3; i++)
            contractDayInfo[i] = Integer.parseInt(b[i]);
        
        contractDayInfo[1] += term;
        if (contractDayInfo[1] >= 13) {
            while (contractDayInfo[1] >= 13) {
                contractDayInfo[1] -= 12;
                contractDayInfo[0] += 1;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (todayInfo[i] < contractDayInfo[i])
                return false;
            else if (todayInfo[i] > contractDayInfo[i])
                return true;
        }
        return true;
    }
    
    public int calculateTerm(String[] terms, String contract) {
        for (String s : terms) {
            String[] info = s.split(" ");
            if (info[0].equals(contract)) {
                return Integer.parseInt(info[1]);
            }
        }
        return 0;
    }
}