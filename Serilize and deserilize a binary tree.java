Serilize and deserilize a binary tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        s(root,sb);
        return sb.toString();
    }
    
    public void s(TreeNode node,StringBuilder sb){
        if(node==null) sb.append("X").append(",");
        else{
            sb.append(Integer.toString(node.val)).append(",");
            s(node.left,sb);
            s(node.right,sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tr=data.split(",");
        Queue<String> q=new LinkedList<String>();
        for(String c:tr) q.offer(c);
        return b(q);
    }
    
    public TreeNode b(Queue<String> q){
        String val=q.poll();
        if(val.equals("X")) return null;
        TreeNode res=new TreeNode(Integer.parseInt(val));
        res.left=b(q);
        res.right=b(q);
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));