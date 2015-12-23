preoder traversal

public List<Integer> preorderIt(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if(root==null) return pre;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        stack.push(cur);
        while(!stack.empty()) {
            cur = stack.pop();
            res.add(cur.val);
            if(cur.right!=null) stack.push(cur.right);
            if(cur.left!=null) stack.push(cur.left);
        }
        return res;
    }