import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int[] team;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];
        team = new int[n];
        for (int i = 0; i < n; i++) {
            team[i] = i;
        }
        rank = new int[n];
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (union(input[0], input[1])) {
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println(0);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return true;
        }
        if (rank[rootA] > rank[rootB]) {
            team[rootB] = rootA;
        }
        else {
            team[rootA] = rootB;
            if (rank[rootA] == rank[rootB]) {
                rank[rootB]++;
            }
        }
        return false;
    }

    static int find(int x) {
        if (team[x] != x) {
            team[x] = find(team[x]);
        }
        return team[x];
    }
}
