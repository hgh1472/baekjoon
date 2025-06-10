import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Node> history = new HashMap<>();
        TreeMap<String, Integer> times = new TreeMap<>();
        
        int defaultMinute = fees[0];
        int defaultCharge = fees[1];
        int unitMinute = fees[2];
        int unitCharge = fees[3];
        
        for (String record : records) {
            String[] info = record.split(" ");
            if (info[2].equals("IN")) {
                history.put(info[1], new Node(info[1], info[0]));
            }
            else {
                Node node = history.get(info[1]);

                int minute = node.calculateTime(info[0]);
                times.put(info[1], times.getOrDefault(info[1], 0) + minute);
                
                history.remove(info[1]);
            }
        }
        
        for (String number : history.keySet()) {
            Node node = history.get(number);
            int minute = node.calculateTime("23:59");
            times.put(number, times.getOrDefault(number, 0) + minute);
        }
        
        int[] answer = new int[times.size()];
        int idx = 0;
        for (String carNumber : times.keySet()) {
            answer[idx++] = calculateCharge(times.get(carNumber), defaultCharge, defaultMinute, unitCharge, unitMinute);
        }
        return answer;
        
    }
    
    int calculateCharge(int minute, int defaultCharge, int defaultMinute, int unitCharge, int unitMinute) {
        int charge = defaultCharge;       
        int plusTime = minute - defaultMinute;
        if (plusTime > 0) {
            int add = unitCharge * (plusTime/unitMinute);
            if (plusTime % unitMinute != 0) {
                add += unitCharge;
            }
            charge += add;
        }
        return charge;
    }
    
    class Node {
        String carNumber;
        Time time;
        
        Node(String carNumber, String time) {
            this.carNumber = carNumber;
            this.time = new Time(time);
        }
        
        public int calculateTime(String time) {
            int afterHour = Integer.parseInt(time.substring(0,2));
            int afterMinute = Integer.parseInt(time.substring(3,5));
            
            int diff = 0;
            diff += 60 * (afterHour - this.time.hour);
            diff += afterMinute - this.time.minute;
            return diff;
        }
    }
    
    class Time {
        int hour;
        int minute;
        
        Time(String time) {
            this.hour = Integer.parseInt(time.substring(0,2));
            this.minute = Integer.parseInt(time.substring(3, 5));
        }
    }
}