public static int minSum(TreeNode root){
                if(root == null){
                        return 0;
                }
                if(root.left == null && root.right == null){
                        return root.val;
                }
                if(root.left != null && root.right == null){
                        return minSum(root.left) + root.val;
                }
                if(root.left == null && root.right != null){
                        return minSum(root.right) + root.val;
                }
                return Math.min(minSum(root.left), minSum(root.right)) + root.val;
        }