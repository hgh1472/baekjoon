import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] a;
    static int[] b;
    static int[] c;
    static int[] d;
    static int[] sumA;
    static int[] sumB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];
        sumA = new int[n * n];
        sumB = new int[n * n];
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            a[i] = input[0];
            b[i] = input[1];
            c[i] = input[2];
            d[i] = input[3];
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumA[count] = a[i] + b[j];
                sumB[count] = c[i] + d[j];
                count++;
            }
        }

        Arrays.sort(sumA);
        Arrays.sort(sumB);

        int left = 0;
        int right = n * n - 1;
        long answer = 0;
        while (left < n * n && 0 <= right) {
            int sum = sumA[left] + sumB[right];
            if (sum < 0) {
                left++;
            } else if (0 < sum) {
                right--;
            }
            else {
                long leftCount = 1;
                long rightCount = 1;
                while (left + 1 < n * n && sumA[left + 1] == sumA[left]) {
                    left++;
                    leftCount++;
                }
                while (0 <= right - 1 && sumB[right - 1] == sumB[right]) {
                    right--;
                    rightCount++;
                }
                answer += leftCount * rightCount;
                left++;
            }
        }
        System.out.println(answer);
    }

    static int binarySearch(long[] numbers, long key) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (numbers[mid] == key) {
                return mid;
            } else if (numbers[mid] < key) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
