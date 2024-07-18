import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    static int n;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < s.length; i++) {
            numbers[i] = Integer.parseInt(s[i]);
        }
        int[] lis = new int[n];
        int lisLength = 1;
        lis[0] = numbers[0];
        for (int i = 1; i < n; i++) {
            if (lis[lisLength - 1] < numbers[i]) {
                lisLength++;
                lis[lisLength - 1] = numbers[i];
            }
            else {
                int pos = Arrays.binarySearch(lis, 0, lisLength, numbers[i]);
                if (pos < 0) {
                    lis[-pos - 1] = numbers[i];
                }
            }
        }
        System.out.println(lisLength);
    }
}