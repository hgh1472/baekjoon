import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];

        Person[] people = new Person[n];
        for (int i = 0; i < n; i++) {
            people[i] = new Person(i);
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            people[input[0]].friends.add(people[input[1]]);
            people[input[1]].friends.add(people[input[0]]);
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            if (dfs(people[i], visited, 1)) {
                System.out.println(1);
                return;
            }
            visited[i] = false;
        }
        System.out.println(0);
    }

    static boolean dfs(Person person, boolean[] visited, int depth) {
        if (depth == 5) {
            return true;
        }
        for (Person friend : person.friends) {
            if (visited[friend.num]) {
                continue;
            }
            visited[friend.num] = true;
            if (dfs(friend, visited, depth + 1)) {
                return true;
            }
            visited[friend.num] = false;
        }
        return false;
    }

    static class Person {
        int num;
        List<Person> friends = new ArrayList<>();

        public Person(int num) {
            this.num = num;
        }
    }
}
