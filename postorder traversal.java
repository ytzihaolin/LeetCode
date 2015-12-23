postorder traversal

modify the preorder solution(push left first) and reverse the result

public List<Integer> postorderIt(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if(root==null) return pre;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        stack.push(cur);
        while(!stack.empty()) {
            cur = stack.pop();
            res.addFirst(cur.val);//reverse the preorder list

            if(cur.left!=null) stack.push(cur.left);//reverse here

            if(cur.right!=null) stack.push(cur.right);
           
        }
        return res;
    }