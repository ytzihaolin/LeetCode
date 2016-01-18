import java.util.*;
import java.io.*;


public class combinationsum{
    
  
    public static void main(String[] args) {
        combinationsum sol=new combinationsum();
        
        sol.test();
    }

    public void test(){
      
       sl(4);
    }

    public void sl(int n){
       List<List<Integer>> res=new ArrayList<List<Integer>>();
       helper(res,new ArrayList<Integer>(),0,n);
       for(List<Integer> a:res){
        for(int b:a){
          System.out.print(b+" ");
        }
        System.out.println();
       }
    }

    public void helper(List<List<Integer>> res, ArrayList<Integer> list, int sum, int t){
      if(sum>t) return;
      if(sum==t){
        res.add(new ArrayList<Integer>(list));
        return;
      }
      for(int i=1;i<t;i++){
        list.add(i);
        helper(res,list,sum+i,t);
        list.remove(list.size()-1);
      }
    }
}


