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
            Trie init = new Trie('s');
            List<String> numbers = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                numbers.add(br.readLine());
            }
            boolean isDuplicated = false;
            for (int j = 0; j < n; j++) {
                String number = numbers.get(j);
                Trie curr = init;
                for (int k = 0; k < number.length(); k++) {
                    char num = number.charAt(k);
                    if (curr.tries[num - '0'] == null) {
                        curr.tries[num - '0'] = new Trie(num);
                    }
                    else if (curr.tries[num - '0'] != null && k == number.length() - 1) {
                        // 이미 존재하는 Trie이고 내가 마지막이면, 일관성 X
                        isDuplicated = true;
                        break;
                    }
                    curr = curr.tries[num - '0'];
                    
                    // 지금까지 숫자가 이미 존재하는 번호면 일관성 X
                    if (curr.isLast == true) {
                        isDuplicated = true;
                        break;
                    }
                    
                    // 마지막 숫자면 하나의 번호로 반영
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