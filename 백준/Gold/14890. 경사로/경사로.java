import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int n, l;
    static int[][] map;
    static boolean[][] bridge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        bridge = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                bridge[i][j] = false;
            }
        }
        int before;
        int length;
        int count;
        int flag = 0;
        int way = 0;
        for (int i = 0; i < n; i++) {
            length = 0;
            count = 1;
            flag = 0;
            for (int j = 1; j < n; j++) {
                before = map[i][j - 1];
                if (before == map[i][j]) {
                    if (flag == 1) {
                        length++;
                        if (length == l) {
                            bridge[i][j] = true;
                            count += length;
                            length = 0;
                            flag = 0;
                        }
                    } else {
                        count++;
                    }
                }
                if (before - map[i][j] == 1) {
                    if (flag == 1) {
                        break;
                    }
                    length++;
                    flag = 1;
                    if (l == 1) {
                        count++;
                        length = 1;
                        flag = 0;
                        bridge[i][j] = true;
                    }
                }
                if (before - map[i][j] == -1) {
                    for (int k = j - 1; k >= j - l; k--) {
                        if (k < 0 || before != map[i][k] || bridge[i][k] == true) {
                            count--;
                            break;
                        }
                    }
                    count++;
                }
            }
            if (count == n) {
                way++;
            } else {

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bridge[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            length = 0;
            count = 1;
            flag = 0;
            for (int j = 1; j < n; j++) {
                before = map[j - 1][i];
                if (before == map[j][i]) {
                    if (flag == 1) {
                        length++;
                        if (length == l) {
                            bridge[j][i] = true;
                            count += length;
                            length = 0;
                            flag = 0;
                        }
                    } else {
                        count++;
                    }
                }
                if (before - map[j][i] == 1) {
                    if (flag == 1) {
                        break;
                    }
                    flag = 1;
                    length++;
                    if (l == 1) {
                        count++;
                        length = 1;
                        flag = 0;
                        bridge[j][i] = true;
                    }
                }
                if (before - map[j][i] == -1) {
                    for (int k = j - 1; k >= j - l; k--) {
                        if (k < 0 || before != map[k][i] || bridge[k][i] == true) {
                            count--;
                            break;
                        }
                    }
                    count++;
                }
            }
            if (count == n) {
                way++;
            } else {
            }
        }

        System.out.println(way);
    }
}