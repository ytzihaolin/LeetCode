Clone Graph


//for clone problem, it is usually relating to maintain a hashmap to get relating clone point

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        HashMap<UndirectedGraphNode,UndirectedGraphNode> hash=new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        q.offer(node);
        hash.put(node, newNode);
        while(!q.isEmpty()){
            UndirectedGraphNode temp=q.poll();
            for(UndirectedGraphNode tm:temp.neighbors){
                if(!hash.containsKey(tm)){
                    hash.put(tm,new UndirectedGraphNode(tm.label));
                    hash.get(temp).neighbors.add(hash.get(tm));
                    q.offer(tm);
                }else{
                    hash.get(temp).neighbors.add(hash.get(tm));
                }
            }
        }
        return newNode;
    }
}