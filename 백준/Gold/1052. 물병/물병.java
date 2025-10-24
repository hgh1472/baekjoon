import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 지민이는 N개의 물병을 가지고 있다. 각 물병에는 물을 무한대로 부을 수 있다. 처음에 모든 물병에는 물이 1리터씩 들어있다. 지민이는 이 물병을 또 다른 장소로 옮기려고 한다. 지민이는 한 번에 K개의 물병을
 * 옮길 수 있다. 하지만, 지민이는 물을 낭비하기는 싫고, 이동을 한 번보다 많이 하기는 싫다. 따라서, 지민이는 물병의 물을 적절히 재분배해서, K개를 넘지 않는 비어있지 않은 물병을 만들려고 한다.
 * <p>
 * 물은 다음과 같이 재분배 한다. 먼저 같은 양의 물이 들어있는 물병 두 개를 고른다. 그 다음에 한 개의 물병에 다른 한 쪽에 있는 물을 모두 붓는다. 이 방법을 필요한 만큼 계속 한다.
 * <p>
 * 이런 제약 때문에, N개로 K개를 넘지않는 비어있지 않은 물병을 만드는 것이 불가능할 수도 있다. 다행히도, 새로운 물병을 살 수 있다. 상점에서 사는 물병은 물이 1리터 들어있다. 예를 들어, N=3이고,
 * K=1일 때를 보면, 물병 3개로 1개를 만드는 것이 불가능하다. 한 병을 또다른 병에 부으면, 2리터가 들어있는 물병 하나와, 1리터가 들어있는 물병 하나가 남는다. 만약 상점에서 한 개의 물병을 산다면,
 * 2리터가 들어있는 물병 두 개를 만들 수 있고, 마지막으로 4리터가 들어있는 물병 한 개를 만들 수 있다. 1_000_000 5 -> 15808
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int answer = solve(n, k);
        System.out.println(answer);
    }

    static int solve(int n, int k) {
        int last = 0;
        for (int i = 0; i < k; i++) {
            if (n == 0) {
                break;
            }
            int cnt = find(n);
            n -= cnt;
            last = cnt;
        }
        if (n == 0) {
            return 0;
        }
        return last - n;
    }

    static int find(int n) {
        int answer = 1;
        while (answer <= n) {
            answer *= 2;
        }
        return answer / 2;
    }
}
