import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] time;
    static boolean[] visited;
    static List<Structure> structures;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        time = new int[n + 1];
        visited = new boolean[n + 1];

        structures = new ArrayList<>();
        PriorityQueue<Structure> q = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cost = input[0];
            Set<Integer> preceding = new HashSet<>();
            for (int j = 1; j < input.length - 1; j++) {
                preceding.add(input[j]);
            }
            structures.add(new Structure(i, cost, preceding));
        }

        for (Structure structure : structures) {
            int before = 0;
            for (Integer s : structure.preceding) {
                before = Math.max(before, find(s));
            }
            time[structure.number] = before + structure.cost;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(time[i]);
        }
    }

    static int find(int x) {
        if (visited[x]) {
            return time[x];
        }
        visited[x] = true;
        Structure structure = structures.get(x - 1);
        int before = 0;
        for (Integer s : structure.preceding) {
            if (visited[s]) {
                before = Math.max(before, time[s]);
            } else {
                before = Math.max(before, find(s));
            }
        }
        time[x] = before + structure.cost;
        return time[x];
    }

    static class Structure implements Comparable<Structure> {
        int number;
        int cost;
        Set<Integer> preceding;

        public Structure(int number, int cost, Set<Integer> preceding) {
            this.number = number;
            this.cost = cost;
            this.preceding = preceding;
        }

        public void add(int number) {
            preceding.add(number);
        }

        @Override
        public int compareTo(Structure o) {
            return preceding.size() - o.preceding.size();
        }
    }
}
