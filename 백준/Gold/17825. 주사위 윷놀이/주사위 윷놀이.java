import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] move;
    static int[] idx;
    static Node[] nodes;
    static Edge[] edges;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        move = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        idx = new int[4];
        answer = 0;

        edges = new Edge[50];
        for (int i = 0; i < 22; i++) {
            edges[i] = new Edge(i, i * 2);
            if (i > 0) {
                edges[i-1].next = edges[i];
            }
        }
        edges[21].number = 0;
        int id = 22;

        edges[id] = new Edge(id, 13);
        edges[5].special = edges[id++];
        Edge tmp = edges[5].special;
        edges[id] = new Edge(id, 16);
        tmp.next = edges[id++];
        tmp = tmp.next;
        edges[id] = new Edge(id, 19);
        tmp.next = edges[id++];
        tmp = tmp.next;
        edges[id] = new Edge(id, 25);
        Edge middle = edges[id++];
        tmp.next = middle;
        tmp = tmp.next;
        edges[id] = new Edge(id, 30);
        tmp.next = edges[id++];
        tmp = tmp.next;
        edges[id] = new Edge(id, 35);
        tmp.next = edges[id++];
        tmp = tmp.next;
        tmp.next = edges[20];

        edges[id] = new Edge(id, 22);
        edges[10].special = edges[id++];
        tmp = edges[10].special;
        edges[id] = new Edge(id, 24);
        tmp.next = edges[id++];
        tmp = tmp.next;
        tmp.next = middle;

        edges[id] = new Edge(id, 28);
        edges[15].special = edges[id++];
        tmp = edges[15].special;
        edges[id] = new Edge(id, 27);
        tmp.next = edges[id++];
        tmp = tmp.next;
        edges[id] = new Edge(id, 26);
        tmp.next = edges[id++];
        tmp = tmp.next;
        tmp.next = middle;

        nodes = new Node[4];
        for (int i = 0; i < 4; i++) {
            nodes[i] = new Node(edges[0]);
        }

        dfs(nodes[0], 0, 0, new int[10]);

        System.out.println(answer);
    }

    static void dfs(int idx, int score) {
        for (int i = 0; i < 4; i++) {
            Node node = nodes[i];
            node.next(move[idx]);
        }
    }

    static void dfs(Node node, int idx, int score, int[] record) {
        if (idx == 10) {
            answer = Math.max(answer, score);
            return;
        }
        Edge before = edges[node.edge.id];
        node.next(move[idx]);

        for (int i = 0; i < 4; i++) {
            if (nodes[i] == node) {
                continue;
            }
            if (nodes[i].edge == node.edge && node.edge != edges[21]) {
                node.edge = before;
                if (node.edge.special == null) {
                    node.isSpecial = false;
                }
                else {
                    node.isSpecial = true;
                }
                return;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (nodes[i].edge == edges[21]) {
                continue;
            }
            record[idx] = i;
            dfs(nodes[i], idx + 1, score + node.edge.number, record);
        }
        node.edge = before;
        if (node.edge.special == null) {
            node.isSpecial = false;
        }
        else {
            node.isSpecial = true;
        }
    }

    static class Node {
        Edge edge;
        boolean isSpecial;

        public Node(Edge edge) {
            this.edge = edge;
        }

        public void next(int count) {
            for (int i = 0; i < count; i++) {
                if (edge == edges[21]) {
                    break;
                }
                if (isSpecial) {
                    edge = edge.special;
                    isSpecial = false;
                }
                else {
                    edge = edge.next;
                }
            }
            if (edge.special != null) {
                isSpecial = true;
            }
        }
    }

    static class Edge {
        int id;
        int number;
        Edge next;
        Edge special;

        public Edge(int id, int number) {
            this.number = number;
            this.id = id;
        }
    }
}
