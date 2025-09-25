import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int v = Integer.parseInt(input[0]);
        int e = Integer.parseInt(input[1]);
        int[][] edges = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                edges[i][j] = Integer.MAX_VALUE;
            }
            edges[i][i] = 0;
        }

        for (int i = 0; i < e; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            edges[a][b] = c;
        }

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                for (int k = 1; k <= v; k++) {
                    if (edges[i][k] == Integer.MAX_VALUE || edges[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) {
                    continue;
                }
                if (edges[i][j] == Integer.MAX_VALUE || edges[j][i] == Integer.MAX_VALUE) {
                    continue;
                }
                answer = Math.min(answer, edges[i][j] + edges[j][i]);
            }
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

}
