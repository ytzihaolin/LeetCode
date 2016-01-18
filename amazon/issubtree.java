issubtree


subtree

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
