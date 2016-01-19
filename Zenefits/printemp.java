import java.util.*;
import java.io.*;


public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
       List<String[]> a=new ArrayList<String[]>();
       a.add(new String[]{"parker","parker"});
       a.add(new String[]{"laks","parker"});
       a.add(new String[]{"avinash","laks"});
       a.add(new String[]{"jonathan","avinash"});
       a.add(new String[]{"jason","laks"});
       a.add(new String[]{"david","parker"});
       a.add(new String[]{"arisa","david"});
       sl("parker",a);
    }


  class emp{
    String name;
    emp boss;
    HashSet<emp> sub;
    emp(String a){
      this.name=a;
      this.sub=new HashSet<emp>();
    }
  }
  public  void sl(String name, List<String[]> a) {
      HashMap<String,emp> map=new HashMap<String,emp>();
      for(String[] aa:a){
          String sub=aa[0];
          String boss=aa[1];
          if(!map.containsKey(sub)){
             map.put(sub,new emp(sub));
          }
          if(!map.containsKey(boss)){
            map.put(boss,new emp(boss));
          }
          map.get(sub).boss=map.get(boss);
          map.get(boss).sub.add(map.get(sub));
      }

      Queue<emp> q=new LinkedList<emp>();
      Set<emp> visited=new HashSet<emp>();
      q.offer(map.get(name));
      while(!q.isEmpty()){
        int size=q.size();
        for(int i=0;i<size;i++){
          emp temp=q.poll();
          if(!visited.add(temp)) continue;
          System.out.print(temp.name+" ");
          for(emp e:temp.sub) q.offer(e);
        }
        System.out.println();
      }
  }

    
 }

 // 可以不用boss一栏
