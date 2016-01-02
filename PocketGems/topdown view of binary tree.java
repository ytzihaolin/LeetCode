topdown view of binary tree
// Java program to print top view of Binary tree
// Reference : http://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
import java.util.*;
public class TopDownViewofBinaryTree {
	public static class TreeNode {
		int key;
		TreeNode left, right;
		public TreeNode(int key) {
			this.key = key;
			left = right = null;
		}
	}

	// A class to represent a queue item. The queue is used to do Level
	// order traversal. Every Queue item contains node and horizontal
	// distance of node from root
	public static class QItem {
		TreeNode node;
		int hd;
		public QItem(TreeNode n, int h) {
			node = n;
			hd = h;
		}
	}

	// Class for a Binary Tree
	public static class Tree {
		TreeNode root;
		public Tree() {
			root = null;
		}
		public Tree(TreeNode n) {
			root = n;
		}

		public void printTopView() {
			// base case
			if (root == null) {
				return;
			}

			// Creates an empty hashset
			HashSet<Integer> set = new HashSet<>();

			// Create a queue and add root to it
			Queue<QItem> Q = new LinkedList<QItem>();
			Q.add(new QItem(root, 0)); // Horizontal distance of root is 0

			// Standard BFS or level order traversal loop
			while (!Q.isEmpty()) {
				// Remove the front item and get its details
				QItem qi = Q.remove();
				int hd = qi.hd;
				TreeNode n = qi.node;

				// If this is the first node at its horizontal distance,
				// then this node is in top view
				if (!set.contains(hd)) {
					set.add(hd);
					System.out.print(n.key + " ");
				}

				// Enqueue left and right children of current node
				if (n.left != null)
					Q.add(new QItem(n.left, hd - 1));
				if (n.right != null)
					Q.add(new QItem(n.right, hd + 1));
			}
		}
	}
	
	public static void main(String[] args){
        /* Create following Binary Tree
             1
           /  \
          2    3
           \
            4
             \
              5
               \
                6*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);
        Tree t = new Tree(root);
        System.out.println("Following are nodes in top view of Binary Tree");
        t.printTopView();
    }
}




bottom up view use treemap to store horizontal index and treenode;


public void bottomView()
    {
        if (root == null)
            return;
 
        // Initialize a variable 'hd' with 0 for the root element.
        int hd = 0;
 
        // TreeMap which stores key value pair sorted on key value
        Map<Integer, Integer> map = new TreeMap<>();
 
         // Queue to store tree nodes in level order traversal
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
 
        // Assign initialized horizontal distance value to root
        // node and add it to the queue.
        root.hd = hd;
        queue.add(root);
 
        // Loop until the queue is empty (standard level order loop)
        while (!queue.isEmpty())
        {
            TreeNode temp = queue.remove();
 
            // Extract the horizontal distance value from the
            // dequeued tree node.
            hd = temp.hd;
 
            // Put the dequeued tree node to TreeMap having key
            // as horizontal distance. Every time we find a node
            // having same horizontal distance we need to replace
            // the data in the map.
            map.put(hd, temp.data);
 
            // If the dequeued node has a left child add it to the
            // queue with a horizontal distance hd-1.
            if (temp.left != null)
            {
                temp.left.hd = hd-1;
                queue.add(temp.left);
            }
            // If the dequeued node has a left child add it to the
            // queue with a horizontal distance hd+1.
            if (temp.right != null)
            {
                temp.right.hd = hd+1;
                queue.add(temp.right);
            }
        }
 
        // Extract the entries of map into a set to traverse
        // an iterator over that.
        Set<Entry<Integer, Integer>> set = map.entrySet();
 
        // Make an iterator
        Iterator<Entry<Integer, Integer>> iterator = set.iterator();
 
        // Traverse the map elements using the iterator.
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> me = iterator.next();
            System.out.print(me.getValue()+" ");
        }
    }
}



side view of binary tree

level order BFS and also add a vd variable inside of the QItem class