In-order Morris Traversal

http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
每次访问一个节点时都先遍历到其左子树的rightmost node也就是inorder中当前节点的前驱节点，然后将rightmost node的右节点
指回该节点，遍历后再回复原状

Morris Traversal 只需要O(1)空间，而且同样可以在O(n)时间内完成。

public void morrisTraversal(TreeNode root){
        TreeNode temp = null;
        while(root!=null){
            if(root.left!=null){//如果有左孩子那么就将左孩子的最右leaf连接到当前节点
                temp = root.left;
                while(temp.right!=null && temp.right != root)//找到最右leaf
                    temp = temp.right;
                
                if(temp.right!=null){//如果最右leaf已经连接过当前节点，意味着左子树遍历完毕，指针又回到原来的父节点，此时取消这个链接以保证
                	//树结构不被破坏
                    temp.right = null;
                    System.out.println(root.val);
                    root = root.right;
                }else{//如果最右节点没有连接当前结点那么就连接然后进入左子树
                    temp.right = root;
                    root = root.left;
                }
            }else{
                System.out.println(root.val);
                root = root.right;
            }
        }
    }