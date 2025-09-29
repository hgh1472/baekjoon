import java.util.*;

class Solution {
    Cell[][] cells;
    public String[] solution(String[] commands) {
        cells = new Cell[51][51];
        for (int i = 1; i < 51; i++) {
            for (int j = 1 ; j< 51; j++) {
                cells[i][j] = new Cell(i,j);
            }
        }
        
        List<String> result = new ArrayList<>();
        for (String command : commands) {
            String[] info = command.split(" ");
            if (info[0].equals("UPDATE")) {
                if (info.length == 4) {
                    update(Integer.parseInt(info[1]), Integer.parseInt(info[2]), info[3]);
                }
                else {
                    update(info[1], info[2]);
                }
            }
            else if (info[0].equals("MERGE")) {
                merge(Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]));
            }
            else if (info[0].equals("UNMERGE")) {
                unmerge(Integer.parseInt(info[1]), Integer.parseInt(info[2]));
            }
            else {
                Cell cell = find(Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                String print = (cell.value.equals("") ? "EMPTY" : cell.value);
                result.add(print);
            }
        }
        
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    void update(int r, int c, String value) {
        Cell cell = find(r, c);
        cell.value = value;
    }
    
    void update(String value1, String value2) {
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (cells[i][j].value.equals(value1)) {
                    cells[i][j].value = value2;
                }
            }
        }
    }
    
    void merge(int r1, int c1, int r2, int c2) {
        Cell cell1 = find(r1,c1);
        Cell cell2 = find(r2,c2);
        
        if (cell1.value.length() >= 1) {
            union(cell1, cell2);
        }
        else if (cell2.value.length() == 0) {
            union(cell1, cell2);
        }
        else {
            union(cell2, cell1);
        }
    }
    
    void unmerge(int r, int c) {
        Cell root = find(r, c);
        String value = root.value;
        List<Cell> list = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                Cell root1 = find(i, j);
                if (root1.r == root.r && root1.c == root.c) {
                    list.add(cells[i][j]);
                }
            }
        }
        for (Cell cell : list) {
            cell.rootR = cell.r;
            cell.rootC = cell.c;
            cell.value = "";
        }
        cells[r][c].value = value;
    }
    
    Cell find(int r, int c) {
        Cell cell = cells[r][c];
        if (cell.rootR != r || cell.rootC != c) {
            Cell root = find(cell.rootR, cell.rootC);
            cell.rootR = root.r;
            cell.rootC = root.c;
        }
        return cells[cell.rootR][cell.rootC];
    }
    
    void union(Cell cell1, Cell cell2) {
        Cell root = find(cell2.r, cell2.c);
        root.rootR = cell1.r;
        root.rootC = cell1.c;
    }
    
    class Cell {
        int r, c;
        int rootR, rootC;
        String value;
        
        Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.rootR = r;
            this.rootC = c;
            value = "";
        }
    }
}