import java.util.*;

class Solution {
    static long[] time = new long[360000];
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = switchSecond(play_time);
        int advTime = switchSecond(adv_time);
        long maxTime = 0;
        
        for (String log : logs) {
            String[] input = log.split("-");
            int start = switchSecond(input[0]);
            int end = switchSecond(input[1]);
            time[start] += 1;
            time[end] -= 1;
        }
        
        for (int i = 1; i <= playTime; i++) {
            time[i] = time[i] + time[i - 1];
        }
        
        for (int i = 1; i <= playTime; i++) {
            time[i] = time[i] + time[i - 1];
        }
        
        int startTime = 0;
        // i = 광고 시작 시간
        for (int i = 0; i <= playTime - advTime; i++) {
            long sum = 0;
            if (i == 0) {
                sum = time[i + advTime - 1];
            }
            else {
                sum = time[i + advTime - 1] - time[i-1];
            }
            if (maxTime < sum) {
                maxTime = sum;
                startTime = i;
            }
        }
        // return String.valueOf(time[3600]);
        return switchString(startTime);
    }
    
    public int switchSecond(String t) {
        String[] input = t.split(":");
        int second = Integer.parseInt(input[2]);
        int minute = Integer.parseInt(input[1]);
        int hour = Integer.parseInt(input[0]);
        int result = 0;
        result += 3600 * hour;
        result += 60 * minute;
        result += second;
        return result;
    }
    
    public String switchString(int second) {
        StringBuilder sb = new StringBuilder();
        int hour = second / 3600;
        if (hour / 10 == 0) {
            sb.append("0");
        }
        sb.append(hour).append(":");
        second %= 3600;
        
        int minute = second / 60;
        if (minute / 10 == 0) {
            sb.append("0");
        }
        sb.append(minute).append(":");
        
        second %= 60;
        if (second / 10 == 0) {
            sb.append("0");
        }
        sb.append(second);
        return sb.toString();
    }
}