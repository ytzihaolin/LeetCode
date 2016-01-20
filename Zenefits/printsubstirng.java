import java.util.*;
import java.io.*;


public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
      String a="linzihao";
      sl(a,2);
    }


  

    public void sl(String a,int n) {
       ArrayList<String> res=new ArrayList<String>();
       helper(a.toCharArray(),n,res,"",0);
       for(String r:res) System.out.println(r);
    
    }

    public void helper(char[] a, int n, ArrayList<String> res, String now,int start){
        
        if(n==0){
          res.add(now);
          return;
        }
        if(start>=a.length) return;
        for(int i=start;i<a.length;i++){
          helper(a, n-1, res, now+(a[i]+""), i+1);
        }
        return; 
    }
}

