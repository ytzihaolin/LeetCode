import java.util.*;
import java.io.*;
public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){

        System.out.println(sol(new int[]{1,2,3,7,9,10},10));

    }
    public int sol(int[] a,int t){
       HashSet<Integer> set=new HashSet<Integer>();
       helper(a,t,set);
       int count=0;
       for(int i=1;i<=t;i++){
            if(!set.contains(i)){
                count++;
                HashSet<Integer> tmp =new HashSet<Integer>();
                for(int aa:set){
                    if(aa+i<=t) tmp.add(aa+i);
                }
                for(int aa:tmp) set.add(aa);
                set.add(i);
            }
       }
       return count;
    }

    public void helper(int[] a,int t,HashSet<Integer> set){

       for(int i=0;i<a.length;i++){  
            HashSet<Integer> tmp =new HashSet<Integer>();       
            for(int aa:set){
                tmp.add(a[i]+aa);
            }
            for(int aa:tmp) set.add(aa);
            set.add(a[i]);
       }
       return;
    }

    
}
