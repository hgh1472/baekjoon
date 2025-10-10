class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int maxTime = convert(play_time);
        long[] arr = new long[maxTime+2];
        for (String log : logs) {
            String[] info = log.split("-");
            int start = convert(info[0]);
            int end = convert(info[1]);
            arr[start]++;
            arr[end]--;
        }
        
        long sum = 0;
        for (int i = 0; i <= maxTime; i++) {
            sum += arr[i];
            arr[i] = sum;
        }
        for (int i = 1; i <= maxTime; i++) {
            arr[i] += arr[i-1];
        }
        int duration = convert(adv_time);
        long max = 0;
        int answer = 0;
        for (int start = 0; start <= maxTime-duration; start++) {
            long minus = (start == 0) ? 0 : arr[start-1];
            long viewer = arr[start+duration-1] - minus;
            if (max < viewer) {
                max = viewer;
                answer = start;
            }
        }
        return convert(answer);
    }
    
    int convert(String time) {
        String[] info = time.split(":");
        int second = Integer.parseInt(info[2]);
        second += Integer.parseInt(info[1]) * 60;
        second += Integer.parseInt(info[0]) * 60 * 60;
        return second;
    }
    
    String convert(int second) {
        StringBuilder sb = new StringBuilder();
        int hour = second / 3600;
        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour).append(":");
        int minute = (second % 3600) / 60;
        if (minute < 10) {
            sb.append("0");
        }
        sb.append(minute).append(":");
        if (second % 60 < 10) {
            sb.append("0");
        }
        sb.append(second % 60);
        return sb.toString();
    }
}