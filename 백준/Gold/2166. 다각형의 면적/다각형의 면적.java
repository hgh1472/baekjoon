import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public void test() {
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            list.add(new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }
        Node start = list.get(0);
        long x = start.x;
        long y = start.y;
        double result = 0;
        for (int i = 1; i < list.size(); i++) {
            Node node = list.get(i);
            long nx = node.x;
            long ny = node.y;
            result += x*ny - y*nx;
            x = nx;
            y = ny;
        }
        result += x * start.y - y * start.x;
        result = Math.abs(result) / 2;
        System.out.printf("%.1f", result);
    }

    static class Node {
        long x;
        long y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
