public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        PriorityQueue<Point> points=new PriorityQueue<Point>(new Comparator<Point>(){
            public int compare(Point a, Point b){
                if(a.x!=b.x)
                    return a.x-b.x;
                return a.y-b.y;//tie breaker
            }
        });
        TreeMap<Integer, Integer> heights=new TreeMap<Integer, Integer>();
        ArrayList<int[]> result=new ArrayList<>();
        for(int[] building: buildings){
            points.add(new Point(building[0], -building[2]));//to indicate this entry is the begin point of the building
            points.add(new Point(building[1], building[2]));
        }
        heights.put(0, 1);//counting number of edge point with this height;
        int maxHeight=0;
        while(!points.isEmpty()){
            Point point=points.poll();//every new point is from right to left 
            if(point.y<0){
                if(!heights.containsKey(-point.y))//with height[] we can always get the maximum height of the poped points
                    heights.put(-point.y, 0);
                heights.put(-point.y, heights.get(-point.y)+1);
            }
            else{
                heights.put(point.y, heights.get(point.y)-1);
                if(heights.get(point.y)==0) heights.remove(point.y);//when ending point reached, remove this height so new height is updated
            }
            int currentMax=heights.lastEntry().getKey();
            if(currentMax!=maxHeight){
                result.add(new int[]{point.x, currentMax});
                maxHeight=currentMax;
            }
        }
        return result;
    }
    private class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}