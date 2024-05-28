import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] dst;
    public static boolean[] visited;
    public static int[][] info;
    public static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        info = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
            info[i][i] = 1;
        }
        st = new StringTokenizer(br.readLine());
        dst = new int[m];
        for (int i = 0; i < m; i++) {
            dst[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            for (int j = 0; j < n; j++) {
                visited[j] = false;
            }
            dfs(i, i);
        }


        for (int i = 1; i < m; i++) {
            int startNation = dst[i - 1] - 1;
            int dstNation = dst[i] - 1;
            if (info[startNation][dstNation] == 0) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(info[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public static void dfs(int startNation, int dstNation) {
        visited[dstNation] = true;
        for (int i = 0; i < n; i++) {
            if (info[dstNation][i] == 1 && !visited[i]) {
                info[startNation][i] = 1;
                dfs(startNation, i);
            }
        }
    }
}