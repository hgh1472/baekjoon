import java.util.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        Map<Integer, PriorityQueue<Integer>> mentors = new HashMap<>();
        int[] mentorCount = new int[k+1];
        for (int i = 1; i <= k; i++) {
            mentors.put(i, new PriorityQueue<>());
            mentorCount[i] = 1;
        }
        
        int waitingTime = calculateWaitingTime(reqs, mentorCount, mentors);
        for (int i = k; i < n; i++) {
            int plusMentor = 0;
            // 유형마다 상담사를 늘렸을 때 총 대기시간을 계산해서 가장 짧은 걸 선택
            for (int j = 1; j <= k; j++) {
                clearMentors(mentors, k);
                
                mentorCount[j]++;
                int estimatedWaitingTime = calculateWaitingTime(reqs, mentorCount, mentors);
                mentorCount[j]--;
                
                if (waitingTime > estimatedWaitingTime) {
                    waitingTime = estimatedWaitingTime;
                    plusMentor = j;
                }
            }
            mentorCount[plusMentor]++;
        }

        return waitingTime;
    }
    
    public int calculateWaitingTime(int[][] reqs, int[] mentorCount, Map<Integer, PriorityQueue<Integer>> mentors) {
        int estimatedWaitingTime = 0;
        for (int t = 0; t < reqs.length; t++) {
            int startTime = reqs[t][0];
            int counselTime = reqs[t][1];
            int type = reqs[t][2];

            PriorityQueue<Integer> mentor = mentors.get(type);
            // 상담시간이 끝난 상담을 종료한다.
            while (!mentor.isEmpty() && mentor.peek() <= startTime) {
                mentor.poll();
            }
            // 상담이 바로 가능한 경우
            if (mentor.size() < mentorCount[type]) {
                mentor.add(startTime + counselTime);
            } else { // 상담을 기다려야 하는 경우
                int turn = mentor.size() - mentorCount[type];
                List<Integer> temp = new ArrayList<>();
                for (int q = 0; q < turn; q++) {
                    temp.add(mentor.poll());
                }
                mentor.add(mentor.peek() + counselTime);
                estimatedWaitingTime += (mentor.peek() - startTime);
                mentor.addAll(temp);
            }
        }
        return estimatedWaitingTime;
    }
    
    public void clearMentors(Map<Integer, PriorityQueue<Integer>> mentors, int k) {
        for (int i = 1; i <= k; i++) {
            mentors.get(i).clear();
        }
    }
}
