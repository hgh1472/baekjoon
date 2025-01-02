import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        List<Integer> crains = new ArrayList<>();
        for (String s : input) {
            crains.add(Integer.parseInt(s));
        }

        int m = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        List<Integer> boxes = new ArrayList<>();
        for (String s : input) {
            boxes.add(Integer.parseInt(s));
        }

        Collections.sort(crains);
        Collections.sort(boxes);

        if (boxes.get(m-1) > crains.get(n-1)) {
            System.out.println(-1);
            return;
        }

        crains.removeIf(crain -> boxes.get(0) > crain);

        int time = 0;
        while (!boxes.isEmpty()) {
            time++;
            for (int i = 0; i < crains.size(); i++) {
                if (boxes.isEmpty())
                    break;
                int p = binary(crains.get(i), boxes);
                if (p == -1)
                    continue;
                boxes.remove(p);
            }
        }
        System.out.println(time);
    }

    static int binary(int e, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            Integer midValue = list.get(mid);
            if (midValue == e) {
                return mid;
            } else if (midValue < e) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
