import java.util.*;
import java.io.*;


public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
       String a="ajdieurrnelkgnhbofaa dfoibbqwerfdccccsxcfghio aafgbnmklbbwsdfcccc";
       String b="a+b+c-";
       System.out.println(sl(a,b));
    }

    public int sl(String s, String e){
       List<String> list=new ArrayList<String>();
       for(int i=0;i<e.length();i+=2){
            if(e.charAt(i+1)=='+'){
                String t=e.charAt(i)+""+e.charAt(i)+"";
                list.add(t);
            }else{
                String t=e.charAt(i)+""+e.charAt(i)+""+e.charAt(i)+""+e.charAt(i)+"";
                list.add(t);
            }
       }

       return helper(s,list,0);
    }

    public int helper(String s,List<String> list,int ind){
        if(ind>list.size()-1) return 0;
        String p=list.get(ind);
        int res=0;
        for(int i=0;i<s.length()-p.length()+1;i++){
            if(s.substring(i,i+p.length()).equals(p)){
                if(ind!=list.size()-1) res+=helper(s.substring(i+p.length()-1),list,ind+1);
                else res+=1;
                
            }
        }
        return res;
    }
 }

 // O(mn)
