import java.util.*;

class Solution {
    static int[] q1 = new int[600001];
    static int[] q2 = new int[600001];
    static int sum1 = 0;
    static int sum2 = 0;
    static int front1 = 0;
    static int rear1 = 0;
    static int front2 = 0;
    static int rear2 = 0;
    public int solution(int[] queue1, int[] queue2) {
        CustomQueue q1 = new CustomQueue(queue1);
        CustomQueue q2 = new CustomQueue(queue2);
        
        for (int i = 0; i < 600005; i++) {
            if (q1.sum == q2.sum) {
                return i;
            }
            else if (q1.sum > q2.sum) {
                int e = q1.pop();
                q2.push(e);
            }
            else {
                int e = q2.pop();
                q1.push(e);
            }
        }
        return -1;
    }
    
    class CustomQueue {
        int front;
        int rear;
        long sum;
        int[] queue;
        
        public CustomQueue(int[] queue) {
            this.front = 0;
            this.rear = 0;
            this.sum = 0L;
            this.queue = new int[600001];
            for (int i = 0; i < queue.length; i++) {
                push(queue[i]);
            }
        }
        
        public int pop() {
            int e = queue[this.rear];
            this.sum -= e;
            this.rear = (this.rear + 1) % 600001;
            return e;
        }
        
        public void push(int e) {
            queue[this.front] = e;
            this.sum += e;
            this.front = (this.front + 1) % 600001;
        }
    }
}