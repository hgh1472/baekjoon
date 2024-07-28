import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Main {
    static int[][] info = new int [102][102];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[1]);
            int y = Integer.parseInt(s[0]);
            int d = Integer.parseInt(s[2]);
            int g = Integer.parseInt(s[3]);
            List<Node> list = new ArrayList<>();
            list.add(new Node(x, y));
            switch (d) {
                case 0 :
                    list.add(new Node(x, y+1));
                    break;
                case 1 :
                    list.add(new Node(x-1, y));
                    break;
                case 2 : 
                    list.add(new Node(x, y-1));
                    break;
                case 3 : 
                    list.add(new Node(x+1, y));
                    break;
                default :
                    break;
            }
            curve(list, 0, g);
        }

        int count = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (info[i][j] == 1&& isSurround(i,j)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static boolean isSurround(int x, int y) {
        if (info[x+1][y] == 1 && info [x][y+1] == 1 && info[x+1][y+1] == 1)
            return true;
        return false;
    }

    public static void curve(List<Node> nodeList, int g, int d) {
        if (g == d) {
            for (Node node : nodeList) {
                info[node.x][node.y] = 1;
            }
            return;
        }
        Node std = nodeList.get(nodeList.size() - 1);
        for (int i = nodeList.size() - 2; i >= 0; i--) {
            Node node = nodeList.get(i);
            if (node == std)
                continue;
            int dx = node.x - std.x;
            int dy = node.y - std.y;
            nodeList.add(new Node(std.x +dy, std.y - dx));
        }
        curve(nodeList, g+1, d);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}