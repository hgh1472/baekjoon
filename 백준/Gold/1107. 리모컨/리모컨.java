import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static boolean[] disable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer channel = Integer.parseInt(br.readLine());
        int disableCount = Integer.parseInt(br.readLine());
        int[] number;
        disable = new boolean[10];
        if (disableCount > 0) {
            number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i : number) {
                disable[i] = true;
            }
        }
        int d = 0;
        while (true) {
            // 시작채널에서 움직이는게 더 빠른 경우
            int plusLength = ((Integer) (channel + d)).toString().length();
            int minusLength = ((Integer) (channel - d)).toString().length();
            if (minusLength + d > Math.abs(channel - 100)) {
                System.out.println(Math.abs(channel - 100));
                return;
            }
            // 채널번호 누르고 움직이는 경우
            if (isPossibleChannel(channel - d)) {
                System.out.println(minusLength + d);
                return;
            }
            if (isPossibleChannel(channel + d)) {
                System.out.println(plusLength + d);
                return;
            }
            d++;
        }
    }

    private static boolean isPossibleChannel(int channel) {
        if (channel < 0) {
            return false;
        }
        if (channel == 0) {
            return !disable[0];
        }
        while (channel != 0) {
            if (disable[channel % 10]) {
                return false;
            }
            channel /= 10;
        }
        return true;
    }
}