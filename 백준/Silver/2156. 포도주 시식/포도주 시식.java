import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        arr[0][0] = 0;
        arr[0][1] = num[0];
        arr[0][2] = num[0];

        for (int i = 1; i < n; i++) {
            arr[i][0] = Math.max(Math.max(arr[i-1][0], arr[i-1][1]), arr[i-1][2]);
            arr[i][1] = arr[i-1][0] + num[i];
            arr[i][2] = Math.max(arr[i-1][0], arr[i-1][1]) + num[i];
        }

        System.out.println(Math.max(Math.max(arr[n-1][0], arr[n-1][1]), arr[n-1][2]));
    }
}
