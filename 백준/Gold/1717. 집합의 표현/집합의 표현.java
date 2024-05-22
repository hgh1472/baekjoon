import java.io.*;
import java.util.*;

public class Main {
    static int[] set, rank;
    static int n, m;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        set = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            set[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 0) {
                union(b, c);
            } else {
                check(b, c);
            }
        }
    }

    public static void union(int b, int c) {
        int rootB = find(b);
        int rootC = find(c);
        if (rootB == rootC) {
            return;
        }
        if (rank[rootB] < rank[rootC]) {
            set[rootB] = rootC;
        } else {
            set[rootC] = rootB;
            if (rank[rootB] == rank[rootC]) {
                rank[rootB]++;
            }
        }
    }
    public static int find(int a) {
        if (set[a] != a) {
            set[a] = find(set[a]);
            return set[a];
        }
        return a;
    }

    public static void check(int b, int c) {
        int rootB = find(b);
        int rootC = find(c);
        if (rootB == rootC) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}