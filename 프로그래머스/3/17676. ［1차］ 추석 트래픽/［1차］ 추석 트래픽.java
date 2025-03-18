import java.util.*;

class Solution {
    public int solution(String[] lines) {
        Log[] logs = new Log[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] input = lines[i].split(" ");
            String[] time = input[1].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int sec = Integer.parseInt(time[2].split("\\.")[0]);
            int milisec = Integer.parseInt(time[2].split("\\.")[1]);

            Time endTime = new Time(hour, minute, sec, milisec);

            int processSec = Integer.parseInt(input[2].substring(0, 1));
            int processMilisec = 0;
            if (input[2].contains(".")) {
                processMilisec = Integer.parseInt(input[2].substring(2, input[2].length()-1));
            }

            Log log = new Log(endTime.minus(processSec, processMilisec), endTime);
            logs[i] = log;
        }

        Arrays.sort(logs);
        int answer = 1;
        for (int i = 0; i < logs.length; i++) {
            Time endTime = logs[i].endTime.addSecond();
            
            int count = 1;
            for (int j = i + 1; j < logs.length; j++) {
                if (logs[j].endTime.isBeforeOrEqual(endTime)) {
                    count++;
                    continue;
                }
                if (logs[j].startTime.isBeforeOrEqual(endTime)) {
                    count++;
                    continue;
                }
                // break;
            }
            answer = Math.max(answer, count);
        }

        return answer;
    }

    class Log implements Comparable<Log> {
        Time startTime;
        Time endTime;

        Log(Time startTime, Time endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Log o) {
            return this.endTime.compareTo(o.endTime);
        }
    }

    class Time implements Comparable<Time> {
        int hour;
        int minute;
        int sec;
        int milisec;

        public Time(int hour, int minute, int sec, int milisec) {
            this.hour = hour;
            this.minute = minute;
            this.sec = sec;
            this.milisec = milisec;
        }

        Time minus(int sec, int milisec) {
            int newMilisec = this.milisec - milisec + 1;
            int newSec = this.sec - sec;
            if (newMilisec < 0) {
                newMilisec += 1000;
                newSec -= 1;
            }
            int newMinute = this.minute;
            if (newSec < 0) {
                newSec += 60;
                newMinute -= 1;
            }
            int newHour = this.hour;
            if (newMinute < 0) {
                newHour -= 1;
                newMinute += 60;
            }
            return new Time(newHour, newMinute, newSec, newMilisec);
        }
        
        Time addSecond() {
            int newMilisec = this.milisec + 999;
            int newSec = this.sec;
            int newMinute = this.minute;
            int newHour = this.hour;
            if (newMilisec >= 1000) {
                newMilisec -= 1000;
                newSec += 1;
            }
            if (newSec >= 60) {
                newSec -= 60;
                newMinute += 1;
            }
            if (newMinute >= 60) {
                newMinute -= 60;
                newHour += 1;
            }
            return new Time(newHour, newMinute, newSec, newMilisec);
        }
        
        boolean isBeforeOrEqual(Time time) {
            int value1 = this.hour*10000000 + this.minute*100000+ this.sec*1000 + this.milisec;
            int value2 = time.hour*10000000 + time.minute*100000+ time.sec*1000 + time.milisec;
            if (value1 <= value2) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Time o) {
            int value1 = this.hour*10000000 + this.minute*100000+ this.sec*1000 + this.milisec;
            int value2 = o.hour*10000000 + o.minute*100000+ o.sec*1000 + o.milisec;
            if (value1 < value2) {
                return -1;
            }
            if (value1 == value2) {
                return 0;
            }
            return 1;
        }
    }
}