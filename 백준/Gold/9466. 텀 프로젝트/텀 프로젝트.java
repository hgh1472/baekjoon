import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] numbers;
    static boolean[] isTeam;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            isTeam = new boolean[n + 1];
            isVisited = new boolean[n + 1];
            numbers = new int[n + 1];
            String[] split = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                numbers[j] = Integer.parseInt(split[j - 1]);
            }
            System.out.println(solution());
        }
    }

    static int solution() {
        int answer = 0;
        for (int i = 1; i < numbers.length; i++) {
            if (isVisited[i]) {
                continue;
            }
            if (numbers[i] == i) {
                isTeam[i] = true;
                isVisited[i] = true;
                continue;
            }
             answer += checkCycle(i);
        }
        return answer;
    }

    static int checkCycle(int num) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        while (true) {
            queue.add(num);
            isVisited[num] = true;
            num = numbers[num];
            if (isVisited[num] || numbers[num] == num) {
                isVisited[num] = true;
                break;
            }
        }
        if (queue.contains(num)) {
            boolean isCycle = false;
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                if (poll == num)
                    isCycle = true;
                isTeam[poll] = isCycle;
                if (!isCycle)
                    answer++;
            }
        }
        else {
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                isTeam[poll] = false;
                answer++;
            }
        }
        return answer;
    }
}