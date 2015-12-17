Sliding Window Maximum


Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] r = new int[n-k+1];
        int i = 0;
        int ri=0;
        Deque<Integer> q=new ArrayDeque<Integer>();
        while(i<n){
            while(!q.isEmpty()&&q.peek()<i-k+1) q.poll();
            while(!q.isEmpty()&&nums[q.peekLast()]<=nums[i]) q.pollLast();
            q.offer(i++);
            if(i-1>=k-1) r[ri++]=nums[q.peek()];
        }
        return r;
    }
}