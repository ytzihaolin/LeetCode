OA1 My

coding：
因为是Java1.7 所以必须给priorityqueue一个初始size，例如 new priorityqueue(k, comparator)。 另外记得import Java.util.*。就这两点要注意吧。


Window Sum
    public static List<Integer> windowSum (ArrayList<Integer> list, int windowSize){
        // Assumption: windowSize <= list.size()
        if (windowSize == 1) return list;
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (list == null || list.size() == 0) return res;
        int sum = 0;
        for (int i = 0; i < windowSize; i++){
            sum += list.get(i);
        }
        res.add(sum);
        for (int i = windowSize; i < list.size(); i++){
            sum = sum + list.get(i) - list.get(i - windowSize);
            res.add(sum);
        }
        return res;
    }
    

Rectangle Area
bool doOverlap(Point l1, Point r1, Point l2, Point r2)
{
    // If one rectangle is on left side of other
    if (l1.x > r2.x || l2.x > r1.x)
        return false;
 
    // If one rectangle is above other
    if (l1.y < r2.y || l2.y < r1.y)
        return false;
 
    return true;
}








// Time complexity: O(nlog(k)), Space Complexity: O(k)
public static point[] kClosestPoints(point[] array, point origin, int k){
        point[] res = new point[k];
        if (array == null || array.length == 0 || k == 0) return res;
        PriorityQueue<point> maxHeap;
        maxHeap = new PriorityQueue<point>(k, new Comparator<point>(){
            
            @Override
// static???????
            public int compare(point o1, point o2) {
                int o1distance = (o1.x - origin.x) * (o1.x - origin.x) + (o1.y - origin.y) * (o1.y - origin.y);
                int o2distance = (o2.x - origin.x) * (o2.x - origin.x) + (o2.y - origin.y) * (o2.y - origin.y);
                if (o1distance == o2distance ){
                    return 0;
                }
                return o1distance > o2distance ? -1 : 1;
            }  
        });
        for (int i = 0; i < array.length; i++){
            maxHeap.offer(array[i]);
            if (maxHeap.size() > k){
                maxHeap.poll(); 
            }
        }
        for (int i = k - 1; i >= 0; i--){
            res[i] = maxHeap.poll();
        }
        return res;
    }




leetcode原题Search a 2D Matrix II

public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        // Search from the up-right corner of the matrix, if it is smaller than the target,

        // then go down a step, if it is larger then go left.

        // Time Complexity: O(m+ n), Space Complexity: O(1)

        if (matrix == null || rowNum == 0 || colNum == 0){

            return (-1, -1);

        }
        int i = 0;

        int j = colNum - 1;

        while (i < rowNum && j >= 0){

            if (matrix[i][j] == target){

                return new Point(i,j);

            }else if (matrix[i][j] < target){

                i++;

            }else{

                j--;

            }

        }

        return (-1, -1);

    }

}

Min Number In Size K Sliding Windows
    public static ArrayList<Integer> windowMin(ArrayList<Integer> input, int size){
        // Maintain a deque to store current minimum element of the sliding window
        // Time Complexity: O(N), Space Complexity: O(n)
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (input == null || input.size() == 0) return result;
        if (size == 1) return input;
        if (input.size() < size) size = input.size();
        int min = Integer.MAX_VALUE;
        // Initialize a deque that stores the current maximum element.
        // For loop to initialize the deque and find the first element in the result arraylist.
        Deque<Integer> minStack = new LinkedList<Integer>();
        for (int i = size - 1; i >= 0; i--){
            if (input.get(i) <= min){
                min = input.get(i);
                minStack.offerFirst(min);
            }
        }
        result.add(min);
        // For loop to go through the whole arraylist
        // i stand for the left-side bound of the sliding window, j is the right bound.
        // Maintain the maxDeque, to keep the element at the top is the smallest one, and the second is  larger and the third is larger than the second one...
        int i = 0;
        int j = size;
        for (j = size; j < input.size(); j++){
            // If the ith element (the one that is going to leave the window) is the current min
            // then remove it from the deque.
            if (input.get(i++) == minStack.peekFirst()){
                minStack.removeFirst();
            }
            // remove all the elements that are larger the the new element from the tail of the deque.
            while (!minStack.isEmpty() && input.get(j) < minStack.peekLast()){
                minStack.removeLast();
            }
            // add the new element to the deque which is the largest one in it
            minStack.offerLast(input.get(j));
            // Add the current minimum to the result arraylist
            result.add(minStack.peekFirst());
        }
        return result;
    }

Max Number In Size K Sliding Windows
public class MaxValuesSlidingWindows {
  public List<Integer> maxWindows(int[] array, int k) {
    // array is not null not empty, k >= 1 and <= a.length
    List<Integer> max = new ArrayList<Integer>();
    Deque<Integer> deque = new LinkedList<Integer>();
    for (int i = 0; i < array.length; i++) {
      while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
        deque.pollLast();
      }
      while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
        deque.pollFirst();
      }
      deque.offerLast(i);
      if (i >= k - 1) {
        max.add(array[deque.peekFirst()]);
      }
    }
    return max;
  }
}








Maximum Minimum Path

public static int solution(int[][] matrix){
        // Use Depth-First search algorithm, pass the minimum as a parameter and return the maximum of them.
        // Time Complexity: O(m*n), Space Complexity: O(1)
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        return dfsMaxMin(matrix, 0 ,0, matrix[0][0]);
    }
    public static int dfsMaxMin(int[][] matrix, int i ,int j, int min){
        min = Math.min(min, matrix[i][j]);
        int cols = matrix[0].length;
        int rows = matrix.length;
        if (i == rows - 1 && j == cols - 1){
            return min;
        }else if (i == rows - 1){
            return dfsMaxMin(matrix, i ,j+1, min);
        }else if (j == cols - 1){
            return dfsMaxMin(matrix, i+1 ,j, min);
        }else{
            int right = dfsMaxMin(matrix, i+1 ,j, min);
            int down = dfsMaxMin(matrix, i ,j+1, min);
            return Math.max(right, down);
        }
    }













    public static Container findOptimalWeights(double capacity, double[] weights, int numOfContainers){
        // Sort the array first and use two index variables l and r to traverse from left and right ends respectively. 
        // Compare the sum with the capacity, if it is smaller left++, else right--
        // Time Complexity: O(nlogn), Space Complexity: O(1)
        Container res = new Container(); 
        if(weights == null || weights.length < 2) return res;
        Arrays.sort(weights);
        int left = 0;
        int right = numOfContainers - 1;
        double minDiff = Double.MAX_VALUE;
        res.first = -1;
        res.second = -1;
        while (left < right){
            double diff = capacity - weights[left] - weights[right] ;
            if (diff >= 0 && diff < minDiff){
                minDiff = diff;
                res.first = weights[left];
                res.second = weights[right];
            }
            if (diff == 0){
                return res;
            }else if (diff < 0){
                right--;
            }else{
                left++;
            }
        }
        return res;
    }









    public static int TwoSumCount(int[] array, int target){
        if (array == null || array.length == 0) return 0;
        int count = 0;
        HashSet<Integer> hash = new HashSet<Integer>();
        for (int i = 0; i < array.length; i++){
            int diff = target - array[i];
            if (hash.contains(diff)){
                count++;
            }
            hash.add(array[i]);
        }
        return count;
    }


(2)balanced parenthese
public static boolean isBalanced(String s){
        if (s == null || s.length() == 0) return false;
        if (s.length() % 2 != 0) return false;
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.offerFirst(c);
            }else if(stack.isEmpty() || !areMatch(stack.pollFirst(), c)){
                return false;
            }
        }
        return true;
    }
    public static boolean areMatch(char c1, char c2){
        System.out.println(c1);
        System.out.println(c2);
        if (c1 == '(' && c2 == ')') return true;
        if (c1 == '[' && c2 == ']') return true;
        if (c1 == '{' && c2 == '}') return true;
        return false;
    }


(1)subtree

    public static boolean isSub(TreeNode root1, TreeNode root2){
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (isIdentical(root1, root2)) return true;
        return isSub(root1.left, root2) || isSub(root1.right, root2);
    }
    public static boolean isIdentical(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }


    public static ListNode reHalfList(ListNode head){
        if (head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null;
        ListNode cur = slow.next;
        ListNode next;
        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;      
        }
        slow.next = prev;
        return head;
    }



(5)longest palindromic substring

public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        int max = 1;
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++){
            int shiftCenter = shiftPalindromeCenter(s, i);
            int shiftLeft = shiftPalindromeLeftCenter(s, i);
            if (2 * shiftCenter + 1 > max){
                max = 2 * shiftCenter + 1;
                start = i - shiftCenter;
                end = i + shiftCenter;
            }
            if (2 * shiftLeft + 2> max){
                max = 2 * shiftLeft + 2;
                start = i - shiftLeft;
                end = i + shiftLeft + 1;
            }
        }
        return s.substring(start, end + 1);
    }
    public int shiftPalindromeCenter(String s, int center){
        int shift = 1;
        int len = s.length();
        while(center - shift >= 0 && center + shift < len){
            if (s.charAt(center - shift) != s.charAt(center + shift)){
                break;
            }else{
                shift++;
            }
        }
        return shift-1;
    }
    public int shiftPalindromeLeftCenter(String s, int center){
        int shift = 0;
        int len = s.length();
        while(center - shift >= 0 && center + shift + 1 < len){
            if (s.charAt(center - shift) != s.charAt(center + shift + 1)){
                break;
            }else{
shift++;
            }
        }
        return shift-1;
    }
}

(6) merge two sorted linked list. 


public class Solution {
  public ListNode merge(ListNode one, ListNode two) {
    // write your solution here
    if (two == null) return one;
    if (one == null) return two;
    ListNode newHead = one.value < two.value ? one : two;
    ListNode current = newHead == one ? two : one;
    ListNode prev = newHead;
    while (prev.next != null && current != null){
      if (current.value >= prev.next.value){
        prev = prev.next;
      }else{
        ListNode temp = prev.next;
        prev.next = current;
        current = temp;
        prev = prev.next;
      }
    }
    if (prev.next == null) prev.next = current;
    return newHead;
  }
}
