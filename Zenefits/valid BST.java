valid BST

public class Solution {
    public boolean isValidBST(TreeNode root) {
       return helper(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }
    
    public boolean helper(TreeNode root, long max, long min){//注意使用long以免integermax做node时候无法判断
        if(root==null) return true;
        if(root.val>=max||root.val<=min) return false;
        return helper(root.left,root.val,min)&&helper(root.right,max,root.val);
    }
    
}