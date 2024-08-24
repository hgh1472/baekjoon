import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

//
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            HashMap<Integer, Integer> keyCount = new HashMap<>();
            for (int j = 0; j < k; j++) {
                String[] s = br.readLine().split(" ");
                int num = Integer.parseInt(s[1]);
                if (s[0].equals("I")) {
                    keyCount.put(num, keyCount.getOrDefault(num, 0) + 1);
                    minQueue.add(num);
                    maxQueue.add(num);
                } else {
                    if (s[1].equals("1")) {
                        while (!maxQueue.isEmpty()) {
                            Integer poll = maxQueue.poll();
                            if (keyCount.containsKey(poll)) {
                                Integer count = keyCount.get(poll);
                                if (count == 1) {
                                    keyCount.remove(poll);
                                } else {
                                    keyCount.put(poll, count - 1);
                                }
                                break;
                            }
                        }
                    } else {
                        while (!minQueue.isEmpty()) {
                            Integer poll = minQueue.poll();
                            if (keyCount.containsKey(poll)) {
                                Integer count = keyCount.get(poll);
                                if (count == 1) {
                                    keyCount.remove(poll);
                                } else {
                                    keyCount.put(poll, count - 1);
                                }
                                break;
                            }
                        }
                    }
                }
            }

            Set<Integer> set = keyCount.keySet();
            if (keyCount.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                Integer max = maxQueue.poll();
                while (!keyCount.containsKey(max)) {
                    max = maxQueue.poll();
                }
                Integer min = minQueue.poll();
                while (!keyCount.containsKey(min)) {
                    min = minQueue.poll();
                }
                System.out.printf("%d %d\n", max, min);
            }
            maxQueue.clear();
            minQueue.clear();
            keyCount.clear();
        }


    }
}
