import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n;
    static boolean[] visited;
    static int[] in, post;
    static Map<Integer, Integer> inPosition, postPosition;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inPosition = new HashMap<>();
        postPosition = new HashMap<>();
        visited = new boolean[n+1];
        in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < n; i++) {
            inPosition.put(in[i], i);
        }
        post = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < n; i++) {
            postPosition.put(post[i], i);
        }
        int root = post[n - 1];
        pre(0, n-1, 0, n-1);
        System.out.println(sb);
    }

    static void pre(int is, int ie, int ps, int pe) {
        if (ie < is || pe < ps) {
            return;
        }
        int root = post[pe];
        sb.append(root).append(" ");

        int leftSubLength = inPosition.get(root) - is;
        pre(is, inPosition.get(root) - 1, ps, ps + leftSubLength - 1);
        pre(inPosition.get(root) + 1, ie, ps + leftSubLength, pe - 1);
    }
}
