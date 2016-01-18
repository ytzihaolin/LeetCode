import java.util.*;
import java.io.*;


public class ifendif{
    
  
    public static void main(String[] args) {
        ifendif sol=new ifendif();
        
        sol.test();
    }

    public void test(){
      
       sl(3);
    }

    public void sl(int n){
       ArrayList<List<String>> res=new ArrayList<List<String>>();
       bt(res,new ArrayList<String>(),n,0,0);
       for(List<String> a:res){
          int ifcount=0;
          int endcount=0;
          for(String b:a){
            if(b.equals("if")){
              for(int i=0;i<ifcount-endcount;i++) System.out.print("  ");
              System.out.println("if");
              ifcount++;
            }else{
               for(int i=0;i<ifcount-endcount-1;i++) System.out.print("  ");
               System.out.println("endif");
               endcount++;
            }
          }
          System.out.println();
       }
    }

    public void bt(ArrayList<List<String>> res, ArrayList<String> list, int n,int open,int close){
      if(open+close==2*n) {
          res.add(new ArrayList<String>(list));
          return;
      }
      if(open<n){
        list.add("if");
        bt(res,list,n,open+1,close);
        list.remove(list.size()-1);
      }
      if(open>close){
        
        list.add("endif");
        bt(res,list,n,open,close+1);
        list.remove(list.size()-1);
      }
    }
 }


