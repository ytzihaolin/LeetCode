import java.util.*;
import java.io.*;


public class windwosum{
    
  
    public static void main(String[] args) {
        windwosum sol=new windwosum();
        
        sol.test();
    }

    public void test(){
       ArrayList<Integer> list=new ArrayList<Integer>();
       for(int i=1;i<=5;i++) list.add(i);
       sl(list,2);
    }

    public void sl(ArrayList<Integer> list, int windowSize){
        int len=list.size();
        int sum=0;
        ArrayList<Integer> res=new ArrayList<Integer>();
        for(int i=0;i<len;i++){
          sum+=list.get(i);
          if(i>=windowSize-1) {
            sum-=i-windowSize<0?0:list.get(i-windowSize); 
            res.add(sum);
          }
         
        }

        for(int i:res) System.out.println(i);
    }
}


