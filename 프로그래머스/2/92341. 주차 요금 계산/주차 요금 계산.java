import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> times = new TreeMap<>();
        Map<String, Time> parks = new TreeMap<>();
        
        for (String record : records) {
            String[] info = record.split(" ");
            if (info[2].equals("IN")) {
                parks.put(info[1], new Time(info[0]));
            }
            else {
                Time in = parks.get(info[1]);
                Time out = new Time(info[0]);
                times.put(info[1], times.getOrDefault(info[1], 0) + out.minus(in));
                parks.remove(info[1]);
            }
        }
        
        Time last = new Time("23:59");
        for (String car : parks.keySet()) {
            times.put(car, times.getOrDefault(car, 0) + last.minus(parks.get(car)));
        }
        
        int[] answer = new int[times.size()];
        int idx = 0;
        for (String car : times.keySet()) {
            answer[idx++] = times.get(car);
        }
        
        for (int i = 0; i < answer.length; i++) {
            int charge = fees[1];
            if (answer[i] > fees[0]) {
                int addTime = answer[i] - fees[0];
                int addCharge = (addTime / fees[2]) * fees[3];
                if (addTime % fees[2] != 0) {
                    addCharge += fees[3];
                }
                charge += addCharge;
            }
            answer[i] = charge;
        }
        return answer;
    }
    
    class Time {
        int hour, minute;
        
        Time(String time) {
            String[] info = time.split(":");
            this.hour = Integer.parseInt(info[0]);
            this.minute = Integer.parseInt(info[1]);
        }
        
        int minus(Time time) {
            int minute = (this.hour - time.hour) * 60;
            minute += (this.minute - time.minute);
            return minute;
        }
    }
}