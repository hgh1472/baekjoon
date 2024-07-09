import java.util.*;
class Solution {
    static String[][] cells;
    static int[] merge;
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        cells = new String[51][51];
        merge = new int[2501];
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                cells[i][j] = "";
                int p = encode(i, j);
                merge[p] = p;
            }
        }
        for (String command : commands) {
            String[] split = command.split(" ");
            if (split[0].equals("UPDATE"))
                update(split);
            else if (split[0].equals("MERGE"))
                merge(split);
            else if (split[0].equals("UNMERGE"))
                unmerge(split);
            else if (split[0].equals("PRINT"))
                answer.add(print(split));
        }
        return answer.toArray(String[]::new);
    }
    
    public void update(String[] command) {
        if (command.length == 4) {
            // update r c value => 루트 위치의 값을 바꿔준다.
            int r = Integer.parseInt(command[1]);
            int c = Integer.parseInt(command[2]);
            int root = find(encode(r, c));
            int[] pos = decode(root);
            cells[pos[0]][pos[1]] = command[3];
            return;
        }
        // update value1 value2
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (cells[i][j].equals(command[1]))
                    cells[i][j] = command[2];
            }
        }
    }
    
    public void merge(String[] command) {
        int r1 = Integer.parseInt(command[1]);
        int c1 = Integer.parseInt(command[2]);
        int r2 = Integer.parseInt(command[3]);
        int c2 = Integer.parseInt(command[4]);
        if (r1 == r2 && c1 == c2)
            return;
        union(r1, c1, r2, c2);
    }
    
    public void union(int x1, int y1, int x2, int y2) {
        int rootX = find(encode(x1, y1));
        int rootY = find(encode(x2, y2));
        if (rootX == rootY)
            return;
        
        int[] x = decode(rootX);
        int[] y = decode(rootY);
        
        if (cells[x[0]][x[1]] != "" || (cells[x[0]][x[1]] == "" && cells[y[0]][y[1]] == "")) {
            merge[rootY] = rootX;
            cells[y[0]][y[1]] = cells[x[0]][x[1]];
        }
        else {
            merge[rootX] = rootY;
            cells[x[0]][x[1]] = cells[y[0]][y[1]];
        }
    }
    
    public int find(int pos) {
        if (merge[pos] != pos) {
            merge[pos] = find(merge[pos]);
        }
        return merge[pos];
    }
    
    public int encode(int x, int y) {
        return (x-1) * 50 + y;
    }
    
    public int[] decode(int n) {
        if (n % 50 == 0) {
            return new int[]{n/50, 50};
        }
        return new int[]{n/50 + 1, n % 50};
    }
    
    
    public void unmerge(String[] command) {
        int x = Integer.parseInt(command[1]);
        int y = Integer.parseInt(command[2]);
        int root = find(encode(x, y));
        int[] pos = decode(root);
        cells[x][y] = cells[pos[0]][pos[1]];
        List<Integer> changeList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (i == x && j == y)
                    continue;
                int p = encode(i, j);
                if (find(p) == root) {
                    cells[i][j] = "";
                    changeList.add(p);
                }
            }
        }
        for (int i = 0; i < changeList.size(); i++) {
            int p = changeList.get(i);
            merge[p] = p;
        }
        merge[encode(x, y)] = encode(x, y);
    }
    
    public String print(String[] command) {
        int r = Integer.parseInt(command[1]);
        int c = Integer.parseInt(command[2]);
        int root = find(encode(r, c));
        int[] pos = decode(root);
        
        if (cells[pos[0]][pos[1]] == "")
            return "EMPTY";
        return cells[pos[0]][pos[1]];
    }
}

