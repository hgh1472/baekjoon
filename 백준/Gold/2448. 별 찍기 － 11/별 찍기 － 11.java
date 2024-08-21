import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class Main {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        int length = k / 3;
        map = new char[k][2 * k + 1];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= 2 * k; j++) {
                map[i][j] = ' ';
            }
        }

        drawTriangles(0, k-1, length);
        StringBuilder sb = new StringBuilder();
        for (char[] chars : map) {
            sb.append(chars);
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    public static void drawTriangles(int x, int y, int level) {
        if (level == 1) {
            drawTriangle(x, y);
            return;
        }
        int sub = level / 2;
        drawTriangles(x, y, sub);
        drawTriangles(x + 3 * sub, y - 3 * sub, sub);
        drawTriangles(x + 3 * sub, y + 3 * sub, sub);
    }

    public static void drawTriangle(int x, int y) {
        map[x][y] = '*';

        map[x+1][y-1] = '*';
        map[x+1][y+1] = '*';

        map[x+2][y-2] = '*';
        map[x+2][y-1] = '*';
        map[x+2][y] = '*';
        map[x+2][y+1] = '*';
        map[x+2][y+2] = '*';
    }
}
