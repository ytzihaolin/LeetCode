monkey grid

public class MonkeyProblem {  
    static class Point {  
        int x, y;  
        Point(int x, int y) {  
            this.x = x;  
            this.y = y;  
        }  
        @Override  
        public boolean equals(Object o) {  
            if (this == o) return true;  
            if (!(o instanceof Point)) return false;  
            Point pair = (Point) o;  
            return x == pair.x && y == pair.y;  
        }  
        @Override  
        public int hashCode() {  //重写hashCode
            return 31 * x + y;  
        }  
    }  
      
    public static int digitSum(int n) {  
        if(n < 0) n = -n;  
        int sum = 0;  
        while(n != 0) {  
            sum += n % 10;  
            n /= 10;  
        }  
        return sum;  
    }  
  
    private static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  
    public static int countSteps(int k) {  
        Set<Point> set = new HashSet<>(); 
        Queue<Point> queue = new LinkedList<>();  
        queue.offer(new Point(0, 0));  
        while(!queue.isEmpty()) {  
            Point p = queue.poll();  
            if(set.contains(p) || (digitSum(p.x) + digitSum(p.y)) > k) continue;  
            set.add(p);  
            for(int i=0; i<4; i++) {  
                queue.offer(new Point(p.x+dir[i][0], p.y+dir[i][1]));  
            }  
        }  
        return set.size();  
    }  
  
    public static void main(String[] args) {  
        System.out.println(countSteps(19));  
    }  
} 