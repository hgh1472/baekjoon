import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] pos = new int[n];

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int idx = find(list, arr[i]);
            if (idx == list.size()) {
                list.add(arr[i]);
            } else {
                Integer num = list.get(idx);
                list.set(idx, arr[i]);
            }
            pos[i] = idx;
        }

        Deque<Integer> q = new ArrayDeque<>();
        int len = list.size();
        for (int i = n - 1; i >= 0; i--) {
            if (pos[i] == len - 1) {
                q.addFirst(arr[i]);
                len--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            sb.append(q.poll()).append(" ");
        }
        System.out.println(list.size());
        System.out.println(sb);
    }

    // num보다 큰 수 중 가장 작은 수
    static int find(List<Integer> list, int num) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) == num) {
                return mid;
            } else if (list.get(mid) < num) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}
