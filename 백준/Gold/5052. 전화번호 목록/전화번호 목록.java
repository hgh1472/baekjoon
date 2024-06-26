import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Trie[] init = new Trie[10];
            List<String> numbers = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                numbers.add(br.readLine());
            }
            boolean isDuplicated = false;
            for (int j = 0; j < n; j++) {
                String number = numbers.get(j);
                char num = number.charAt(0);
                if (init[num - '0'] == null) {
                    init[num - '0'] = new Trie(num);
                }
                Trie curr = init[num - '0'];
                if (curr.isLast) {
                    isDuplicated = true;
                    break;
                }
                if (number.length() == 1)
                    curr.isLast = true;
                for (int k = 1; k < number.length(); k++) {
                    num = number.charAt(k);
                    if (curr.tries[num - '0'] == null) {
                        curr.tries[num - '0'] = new Trie(num);
                    }
                    else {
                        if (k == number.length() - 1) {
                            isDuplicated = true;
                            break;
                        }
                    }
                    curr = curr.tries[num - '0'];
                    if (curr.isLast == true) {
                        isDuplicated = true;
                        break;
                    }
                    if (k == number.length() - 1) {
                        curr.isLast = true;
                    }
                }
                if (isDuplicated) {
                    break;
                }
            }
            if (isDuplicated)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }

    private static class Trie {
        char num;
        Trie[] tries;
        boolean isLast;

        public Trie(char num) {
            this.num = num;
            this.tries = new Trie[10];
            this.isLast = false;
        }
    }
}