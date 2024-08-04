import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<Integer> smaller = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Queue<Integer> bigger = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        int mid = Integer.parseInt(br.readLine());
        System.out.println(mid);
        for (int i = 1; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            // 숫자가 홀수개로 존재하는 경우
            if (i % 2 == 0) {
                if (mid < number) {
                    bigger.add(number);
                    smaller.add(mid);
                    mid = bigger.poll();
                } else {
                    smaller.add(number);
                }
            }
            // 숫자가 짝수개 => bigger가 smaller보다 1개 더 많아야 함
            else {
                if (mid < number) {
                    bigger.add(number);
                } else {
                    smaller.add(number);
                    bigger.add(mid);
                    mid = smaller.poll();
                }
            }
            System.out.println(mid);
        }
    }
}