import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);
        int[] numbers = new int[n];
        split = br.readLine().split(" ");
        for (int i = 0; i < split.length; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }

        int front = 0;
        int rear = 0;
        int sum = 0;
        int length = 0;
        int minLength = n + 1;
        while (true) {
            if (sum < s) {
                if (rear == n)
                    break;
                sum += numbers[rear];
                rear++;
                length++;
            }
            if (sum >= s) {
                if (length < minLength) {
                    minLength = length;
                }
                if (front == n - 1)
                    break;
                sum -= numbers[front];
                front++;
                length--;
            }
        }
        if (minLength == n + 1) {
            System.out.println(0);
        } else {
            System.out.println(minLength);
        }
    }
}