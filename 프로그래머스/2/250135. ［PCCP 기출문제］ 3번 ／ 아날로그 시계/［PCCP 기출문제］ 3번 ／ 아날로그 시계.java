class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        double[] degree = new double[3];
        degree[0] = getHourPosition(h1, m1, s1);
        degree[1] = getMinutePosition(m1, s1);
        degree[2] = getSecondPosition(s1);
        
        int count = 0;
        
        int diff = (h2 - h1) * 60 * 60;
        diff += (m2 - m1) * 60;
        diff += (s2 - s1);
        
        Time time = new Time(h1, m1, s1);
        int answer = 0;
        if (degree[0] == degree[2]) {
            answer++;
        }
        else if (degree[1] == degree[2]) {
            answer++;
        }
        for (int i = 0; i < diff; i++) {
            time.addSecond();
            boolean isBeforeMinute = isBefore(degree[1], degree[2]);
            boolean isBeforeHour = isBefore(degree[0], degree[2]);
            double afterHour = getHourPosition(time.h, time.m, time.s);
            double afterMinute = getMinutePosition(time.m, time.s);
            double afterSecond = getSecondPosition(time.s);
            if (time.isAllSame()) {
                answer++;
            }
            else {
                if (isBeforeMinute && !isBefore(afterMinute, afterSecond)) {
                    answer++;
                }
                if (isBeforeHour && !isBefore(afterHour, afterSecond)) {
                    answer++;
                }
            }
            degree[0] = afterHour;
            degree[1] = afterMinute;
            degree[2] = afterSecond;
        }
        
        return answer;
    }
    
    boolean isBefore(double a, double b) {
        if (a <= 180) {
            return b < a || a+180 < b;
        }
        else {
            return (a - 180) < b && b < a;
        }
    }
    
    double getHourPosition(int h, int m, int s) {
        return (double)(h % 12) * 30 + m * 0.5 + ((double)s / 120);
    }
    
    double getMinutePosition(int m, int s) {
        return (double)m*6 + s*0.1;
    }
    
    double getSecondPosition(int s) {
        return (double)s*6;
    }
    
    class Time {
        int h, m, s;
        
        Time(int h, int m, int s) {
            this.h = h;
            this.m = m;
            this.s = s;
        }
        
        void addSecond() {
            this.s++;
            if (this.s == 60) {
                this.s = 0;
                this.m++;
            }
            if (this.m == 60) {
                this.m = 0;
                this.h++;
            }
        }
        
        boolean isAllSame() {
            if (this.h == 0 && this.m == 0 && this.s == 0) {
                return true;
            }
            if (this.h == 12 && this.m == 0 && this.s == 0) {
                return true;
            }
            return false;
        }
    }
}