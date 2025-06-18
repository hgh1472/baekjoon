import java.util.*;

class Solution {
    Map<String, Person> enrolls = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        PriorityQueue<Person> q = new PriorityQueue<>();
        
        for (int i = 0; i < enroll.length; i++) {
            int level = (referral[i].equals("-")) ? 0 : enrolls.get(referral[i]).level+1;
            Person p = new Person(enroll[i], referral[i], level);
            enrolls.put(enroll[i], p);
            q.add(p);
        }
        
        for (int i = 0; i < seller.length; i++) {
            enrolls.get(seller[i]).add(amount[i] * 100);
        }
        
        while (!q.isEmpty()) {
            Person poll = q.poll();
            for (int income : poll.income) {
                Person start = poll;
                int plus = income/10;
                start.sum -= plus;
                if (start.parent.equals("-")) {
                    continue;
                }
                enrolls.get(start.parent).plus += plus;
                start = enrolls.get(start.parent);
                plus /= 10;
                while (plus > 0) {
                    start.plus -= plus;
                    if (start.parent.equals("-")) {
                        break;
                    }
                    enrolls.get(start.parent).plus += plus;
                    start = enrolls.get(start.parent);
                    plus /= 10;
                }
            }
        }
        
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            Person p = enrolls.get(enroll[i]);
            answer[i] = p.sum + p.plus;
        }
        
        return answer;
    }
    
    class Person implements Comparable<Person> {
        String name;
        String parent;
        int level;
        List<Integer> income = new ArrayList<>();
        int plus;
        int sum;
        
        Person(String name, String parent, int level) {
            this.name = name;
            this.parent = parent;
            this.level = level;
            this.plus = 0;
            this.sum = 0;
        }
        
        public void add(int income) {
            this.income.add(income);
            this.sum += income;
        }
        
        @Override
        public int compareTo(Person p) {
            return p.level - this.level;
        }
    }
}
