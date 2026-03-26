import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<Long> li;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        li = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            build(i, 0, 10, 0);
        }
        Collections.sort(li);

        if (n >= li.size()) {
            System.out.println(-1);
            return;
        }
        System.out.println(li.get(n));
    }

    static void build(int length, int idx, int last, long num) {
        if (length == idx) {
            li.add(num);
            return;
        }
        for (int i = 0; i < last; i++) {
            build(length, idx + 1, i, num * 10 + i);
        }
    }
}
