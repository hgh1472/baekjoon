import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] gates = new int[p];
        for (int i = 0; i < p; i++) {
            gates[i] = Integer.parseInt(br.readLine());
        }

        visited = new int[g + 1];
        for (int i = 1; i <= g; i++) {
            visited[i] = i;
        }
        int count = 0;
        for (int gate : gates) {
            int result = find(gate);
            if (result == 0) {
                break;
            }
            visited[result] = visited[result - 1];
            count++;
        }
        System.out.println(count);
    }

    static int find(int gate) {
        int answer = 0;
        if (visited[gate] != gate) {
            visited[gate] = find(visited[gate]);
        }
        return visited[gate];
    }
}
