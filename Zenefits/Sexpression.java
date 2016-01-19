import java.util.*;
import java.io.*;


public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
       // (A(B(D(E(G))))(C(F)))
                // char[][] pair = {{'B', 'D'}, {'D', 'E'}, {'A', 'B'}, {'C', 'F'}, {'E', 'G'}, {'A', 'C'}};
              // E3
                 char[][] pair = {{'A', 'B'}, {'A', 'C'}, {'B', 'D'}, {'D', 'C'}};
          // (A(B(D)(G))(C(E(F))(H)))
                // char[][] pair = {{'A', 'B'}, {'A', 'C'}, {'B', 'G'}, {'C', 'H'}, {'E', 'F'}, {'B', 'D'}, {'C', 'E'}};
                // E1
                // char[][] pair = {{'A', 'B'}, {'A', 'C'}, {'B', 'D'}, {'A', 'E'}};
                // E2
                 //char[][] pair = {{'A', 'B'}, {'A', 'C'}, {'B', 'D'}, {'A', 'C'}};
                // E4
         // char[][] pair = {{'A', 'B'}, {'A', 'C'}, {'B', 'G'}, {'C', 'H'}, {'E', 'F'}, {'B', 'D'}};
                // char[][] pair={{'A','B'},{'A','C'},{'B','G'},{'C','H'},{'E','F'},{'B','D'},{'C','E'}};
          System.out.println(sl(pair));
    }


  class TreeNode{
      char val;
      boolean hasp;
      HashSet<Character> child;
      TreeNode left;
      TreeNode right;
      TreeNode(char a){
        this.val=a;
        child=new HashSet<Character>();
      }
  }

  public String sl(char[][] pair) {
      HashMap<Character,TreeNode> map=new HashMap<Character,TreeNode>();
     for(char[] a:pair){
          char p=a[0];
          char c=a[1];
          if(!map.containsKey(p)) map.put(p,new TreeNode(p));
          if(!map.containsKey(c)) map.put(c,new TreeNode(c));
          if(!map.get(p).child.add(c)) return "E2";   
          if(map.get(p).child.size()>2) return "E1";
          map.get(c).hasp=true;
     }

     TreeNode root=null;
     for(char key:map.keySet()){
        TreeNode temp=map.get(key);
        if(!temp.hasp){
          if(root==null) root=temp;
          else return "E4";
        }
        makechild(temp,map);
     }

     if(ifE3(root)) return "E3";
     return printsexp(root);
  }

  public boolean ifE3(TreeNode node){
    HashSet<TreeNode> v=new HashSet<TreeNode>();
    Queue<TreeNode> q=new LinkedList<TreeNode>();
    q.offer(node);
    while(!q.isEmpty()){
      TreeNode temp=q.poll();
      if(!v.add(temp)) return true;
      if(temp.left!=null) q.offer(temp.left);
      if(temp.right!=null) q.offer(temp.right);
    }
    return false;
  }

  public void makechild(TreeNode node,HashMap<Character,TreeNode> map){
      char a='0',b='0';
      for(char t:node.child){
        if(a=='0') a=t;
        else{
          if(t<a){
            b=a;
            a=t;
          }else b=t;
        }
      }
      node.left=a=='0'?null:map.get(a);
      node.right=b=='0'?null:map.get(b);
  }

  public String printsexp(TreeNode node){
    if(node==null) return "";
    return "("+node.val+""+printsexp(node.left)+printsexp(node.right)+")";
  }

    
 }

 // O(mn)
