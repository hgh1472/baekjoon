import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;

    static int[][] s2d2;
    static int[][] nutrients;
    static PriorityQueue<Tree> springTrees = new PriorityQueue<>();
    static Queue<Tree> autumnTrees = new ArrayDeque<>();
    static Queue<Tree> deads = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        k = input[2];

        s2d2 = new int[n + 1][n + 1];
        nutrients = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nutrients[i][j] = 5;
            }
        }

        for (int i = 1; i <= n; i++) {
            input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= n; j++) {
                s2d2[i][j] = input[j - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
            springTrees.add(new Tree(input[0], input[1], input[2]));
        }

        for (int i = 0; i < k; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(springTrees.size());
    }

    static void spring() {
        while (!springTrees.isEmpty()) {
            Tree tree = springTrees.poll();
            if (nutrients[tree.x][tree.y] < tree.age) {
                deads.add(tree);
                continue;
            }
            nutrients[tree.x][tree.y] -= tree.age;
            tree.age += 1;
            autumnTrees.add(tree);
        }
    }

    static void summer() {
        while (!deads.isEmpty()) {
            Tree dead = deads.poll();
            nutrients[dead.x][dead.y] += dead.age / 2;
        }
    }

    static void autumn() {
        while (!autumnTrees.isEmpty()) {
            Tree tree = autumnTrees.poll();
            springTrees.add(tree);
            if (tree.age % 5 != 0) {
                continue;
            }
            int x = tree.x;
            int y = tree.y;
            if (1 < x && 1 < y) {
                springTrees.add(new Tree(x - 1, y - 1, 1));
            }
            if (1 < x) {
                springTrees.add(new Tree(x - 1, y, 1));
            }
            if (1 < y) {
                springTrees.add(new Tree(x, y - 1, 1));
            }
            if (x < n && y < n) {
                springTrees.add(new Tree(x + 1, y + 1, 1));
            }
            if (x < n) {
                springTrees.add(new Tree(x + 1, y, 1));
            }
            if (y < n) {
                springTrees.add(new Tree(x, y + 1, 1));
            }
            if (1 < x && y < n) {
                springTrees.add(new Tree(x - 1, y + 1, 1));
            }
            if (x < n && 1 < y) {
                springTrees.add(new Tree(x + 1, y - 1, 1));
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nutrients[i][j] += s2d2[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}
