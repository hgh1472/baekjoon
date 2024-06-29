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
        int minPH = 2000000000, tempPH;

        int firstSolution = 0, secondSolution = 0;
        for (int i = 0; i < n; i++) {
            int pos = Arrays.binarySearch(solutions, -solutions[i]);
            if (pos < 0) {
                if (pos == -1) {
                    if (i == 0) {
                        tempPH = Math.abs(solutions[i] + solutions[i + 1]);
                        if (minPH > tempPH) {
                            minPH = tempPH;
                            firstSolution = i;
                            secondSolution = i + 1;
                        }
                    }
                    else {
                        tempPH = Math.abs(solutions[i] + solutions[0]);
                        if (minPH > tempPH) {
                            minPH = tempPH;
                            firstSolution = i;
                            secondSolution = 0;
                        }
                    }
                }
                else if (pos == -(n + 1)) {
                    if (i == n - 1) {
                        tempPH = Math.abs(solutions[i] + solutions[i-1]);
                        if (tempPH < minPH) {
                            minPH = tempPH;
                            firstSolution = i;
                            secondSolution = i-1;
                        }
                    }
                    else {
                        tempPH = Math.abs(solutions[i] + solutions[n-1]);
                        if (tempPH < minPH) {
                            minPH = tempPH;
                            firstSolution = i;
                            secondSolution = n-1;
                        }
                    }
                }
                else {
                    if (Math.abs(solutions[i] + solutions[-pos -2]) < minPH && i != -pos - 2) {
                        minPH = Math.abs(solutions[i] + solutions[-pos -2]);
                        firstSolution = i;
                        secondSolution = -pos -2;
                    }
                    if (Math.abs(solutions[i] + solutions[-pos -1]) < minPH && i != -pos - 1) {
                        minPH = Math.abs(solutions[i] + solutions[-pos - 1]);
                        firstSolution = i;
                        secondSolution = -pos - 1;
                    }
                }
            }
            else {
                firstSolution = i;
                secondSolution = pos;
                break;
            }
        }
        System.out.println(solutions[firstSolution] + " " + solutions[secondSolution]);
    }
}