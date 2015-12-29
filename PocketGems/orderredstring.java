import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }


class DirectedGraph{
    HashMap<Character,HashSet<Character>> adj;
    DirectedGraph(){
        this.adj=new HashMap<Character,HashSet<Character>>();
    }

    public void addEdge(char a,char b){
        if(!this.adj.containsKey(b)) this.adj.put(b,new HashSet<Character>());
        if(this.adj.containsKey(a)) this.adj.get(a).add(b);
        else{
            this.adj.put(a,new HashSet<Character>());
            this.adj.get(a).add(b);
        }
    }
}

public String getOrderedString(String[] strs) { 
    DirectedGraph g = new DirectedGraph(); 
    HashMap<Character,Integer> indegree=new HashMap<Character,Integer>(); 
    for(String s:strs) {  
        if(s.isEmpty()) continue;  
        for(int i=1; i<s.length(); i++) {  
            indegree.put(s.charAt(i),indegree.containsKey(s.charAt(i))?indegree.get(s.charAt(i))+1:1);
            if(!indegree.containsKey(s.charAt(i-1))) indegree.put(s.charAt(i-1),0);
            g.addEdge(s.charAt(i-1), s.charAt(i));  
        }  
    }  
   
   Queue<Character> q=new LinkedList<Character>();
   for(char temp:indegree.keySet()){
        if(indegree.get(temp)==0) q.offer(temp);
   }

   StringBuilder sb=new StringBuilder();
   while(!q.isEmpty()){
        char temp=q.poll();
        System.out.println(temp);
        sb.append(temp);
        for(char cc:g.adj.get(temp)){
            int indeg=indegree.get(cc)-1;
            indegree.put(cc,indeg);
            if(indeg==0) q.offer(cc);
        }
   }
   return sb.toString();

} 




    public void test(){
       System.out.println(getOrderedString(new String[]{"ab","bc","bz"}));
    }

    
}
     