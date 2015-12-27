 Topdown view of Binary tree



class QItem
{
   TreeNode node;
   int hd;
   public QItem(TreeNode n, int h)
   {
        node = n;
        hd = h;//hd代表每个节点的横坐标，左右孩子各-1，+1， 通过hashset判断该横坐标上是否存在过节点，若是最新节点
        //意味着会存在到结果view中
   }
}


 public void printTopView()
    {
        // base case
        if (root == null) {  return;  }
 
        // Creates an empty hashset
        HashSet<Integer> set = new HashSet<>();
 
        // Create a queue and add root to it
        Queue<QItem> Q = new LinkedList<QItem>();
        Q.add(new QItem(root, 0)); // Horizontal distance of root is 0
 
        // Standard BFS or level order traversal loop
        while (!Q.isEmpty())
        {
            // Remove the front item and get its details
            QItem qi = Q.remove();
            int hd = qi.hd;
            TreeNode n = qi.node;
 
            // If this is the first node at its horizontal distance,
            // then this node is in top view
            if (!set.contains(hd))
            {
                set.add(hd);
                System.out.print(n.key + " ");
            }
 
            // Enqueue left and right children of current node
            if (n.left != null)
                Q.add(new QItem(n.left, hd-1));
            if (n.right != null)
                Q.add(new QItem(n.right, hd+1));
        }
    }