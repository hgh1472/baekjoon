import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Time> people = new PriorityQueue<>();
        for (String time : timetable) {
            String[] input = time.split(":");
            people.add(new Time(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
        
        Time start = new Time(9, 0);
        for (int i = 0; i < n - 1; i++) {
            Time now = start.add(i * t);
            int count = 0;
            while ((!people.isEmpty() && people.peek().isBeforeOrEqual(now)) && count < m) {
                people.poll();
                count++;
            }
        }
        
        int count = 0;
        Time now = start.add((n-1) * t);
        Time last = now;
        while ((!people.isEmpty() && people.peek().isBeforeOrEqual(now)) && count < m) {
            last = people.poll();
            count++;
        }
        if (count == m) {
            Time result = last.minus(1);
            return result.toString();
        }
        return now.toString();
    }
    
    class Time implements Comparable<Time> {
        int hour;
        int minute;
        
        Time(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }
        
        public Time add(int minute) {
            int newMinute = this.minute + minute;
            int newHour = this.hour;
            while (60 <= newMinute) {
                newHour++;
                newMinute -= 60;
            }
            return new Time(newHour, newMinute);
        }
        
        public Time minus(int minute) {
            int newMinute = this.minute - minute;
            int newHour = this.hour;
            if (newMinute < 0) {
                newHour--;
                newMinute = 60 + newMinute;
            }
            return new Time(newHour, newMinute);
        }
        
        public boolean isBeforeOrEqual(Time time) {
            int value1 = this.hour * 100 + this.minute;
            int value2 = time.hour * 100 + time.minute;
            
            if (value1 <= value2) {
                return true;
            }
            return false;
        }
        
        public String toString() {
            return String.format("%02d", this.hour) + ":" + String.format("%02d", this.minute);
        }
        
        @Override
        public int compareTo(Time o) {
            return (this.hour*100 + this.minute) - (o.hour*100 + o.minute);
        }
    }
}