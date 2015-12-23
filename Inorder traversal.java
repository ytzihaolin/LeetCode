Inorder traversal

publis List<TreeNode> inorder(TreeNode root){
	Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;
    List<TreeNode> res=new LinkedList<TreeNode>();

    while(cur!=null || !stack.empty()){
        while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        res.add(cur.val);
        cur = cur.right;
    }
}