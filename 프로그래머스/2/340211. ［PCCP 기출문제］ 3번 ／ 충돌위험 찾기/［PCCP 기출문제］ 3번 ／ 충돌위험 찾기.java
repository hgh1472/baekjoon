import java.util.*;

class Solution {
    int[][] map = new int[101][101];
    int answer;
    int end;
    public int solution(int[][] points, int[][] routes) {
        Point[] pointArr = new Point[points.length+1];
        for (int i = 0; i < points.length; i++) {
            pointArr[i+1] = new Point(points[i][0], points[i][1]);
        }
        
        answer = 0;
        Robot[] robot = new Robot[routes.length];
        for (int i = 0; i < routes.length; i++) {
            Point start = pointArr[routes[i][0]];
            map[start.x][start.y]++;
            if (map[start.x][start.y] == 2) {
                answer++;
            } 
            robot[i] = new Robot(start.x, start.y);
            for (int j = 1; j < routes[i].length; j++) {
                robot[i].add(pointArr[routes[i][j]]);
            }
        }
        
        for (int i = 0; i < robot.length; i++) {
            robot[i].clear();
        }
        
        end = 0;
        while (end != robot.length) {
            for (int i = 0; i < robot.length; i++) {
                if (!robot[i].isEnd()) {
                    robot[i].move();
                }
            }
            for (int i = 0; i < robot.length; i++) {
                robot[i].clear();
            }
        }
        
        return answer;
    }
    
    class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class Robot {
        int x, y;
        Queue<Point> points = new ArrayDeque<>();
        
        Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        void add(Point point) {
            points.add(point);
        }
        
        boolean isEnd() {
            return points.isEmpty();
        }
                
        void move() {
            Point p = points.peek();
            if (this.x != p.x) {
                if (this.x < p.x) {
                    this.x++;
                }
                else {
                    this.x--;
                }
            }
            else if (this.y != p.y) {
                if (this.y < p.y) {
                    this.y++;
                }
                else {
                    this.y--;
                }
            }
            
            if (this.x == p.x && this.y == p.y) {
                points.poll();
                if (points.isEmpty()) {
                    end++;
                }
            }
            map[this.x][this.y]++;
            if (map[this.x][this.y] == 2) {
                answer++;
            }
        }
        
        void clear() {
            map[this.x][this.y] = 0;
        }
    }
}