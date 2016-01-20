import java.util.*;
import java.io.*;


public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
      String a="linzihao";
      for(int i=0;i<=5;i++) sl(2,2,i);
      //sl(2,2,3);
    }


  

    public void sl(int x, int y, int k) {
       int[] fact=new int[x+y+1];
       fact[0]=1;
       for(int i=1;i<=x+y;i++) fact[i]=fact[i-1]*i;
        k++;//k is zero based, k means k+1 th permutation
       System.out.println(helper(x,y,k,fact));
    }

    public String helper(int x,int y, int k, int[] fact){
        if(x==0){
           String res="";
           for(int i=0;i<y;i++) res+="V";
           return res;
        }else if(y==0){
          String res="";
          for(int i=0;i<x;i++) res+="H";
          return res;
        }
        int comb=(fact[x-1+y]/fact[x-1]/fact[y]);
        if(k>comb) return "V"+helper(x,y-1,k-comb,fact);
        else return "H"+helper(x-1,y,k,fact);
    }

}

