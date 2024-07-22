import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long[] arr;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int k = Integer.parseInt(s[2]);
        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        tree = new long[4 * n];
        init(1, n, 1);
        for (int i = 0; i < m + k; i++) {
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            if (a == 1) {
                long c = Long.parseLong(s[2]);
                update(1, n, 1, b, c - arr[b]);
                arr[b] = c;
            }
            else {
                int c = Integer.parseInt(s[2]);
                System.out.println(getSum(1, n, b, c, 1));
            }
        }
    }

    public static long init(int start, int end, int index) {
        if (start == end) {
            tree[index] = arr[start];
            return tree[index];
        }
        int mid = (start + end) / 2;
        tree[index] = init(start, mid, index * 2) + init(mid+1, end, index * 2 + 1);
        return tree[index];
    }

    public static long getSum(int start, int end, int left, int right, int index) {
        if (end < left || right < start)
            return 0;
        if (left <= start && end <= right)
            return tree[index];
        int mid = (start + end) / 2;
        return getSum(start, mid, left, right, index * 2) + getSum(mid + 1, end, left, right, index*2+1);
    }

    public static void update(int start, int end, int index, int what, Long value) {
        if (what < start || what > end)
            return;
        tree[index] += value;
        if (start == end)
            return;
        int mid = (start + end) / 2;
        update(start, mid, index * 2, what, value);
        update(mid+1, end, index*2+1, what, value);
    }
}