import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] solutions;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        solutions = new int[n];
        String[] strings = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(strings[i]);
        }
        int minPH = 2000000000;
        int left = 0, right = n - 1;
        int firstSolution = 0, secondSolution = n - 1;
        while (left < right) {
            int tempPH = solutions[left] + solutions[right];
            if (Math.abs(tempPH) < minPH) {
                minPH = Math.abs(tempPH);
                firstSolution = left;
                secondSolution = right;
            }
            if (tempPH > 0) {
                right--;
            }
            else if (tempPH < 0) {
                left++;
            }
            else {
                break;
            }
        }
        System.out.println(solutions[firstSolution] + " " + solutions[secondSolution]);
    }
}