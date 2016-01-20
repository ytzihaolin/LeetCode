import java.util.*;
import java.io.*;


public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
      String a="Monster has called me again";
      sl(a);
      //sl(2,2,3);
    }


  

    public void sl(String s) {
       String[] ss=s.split(" ");
       Random r=new Random();
       for(int j=0;j<ss.length;j++){
          String sss=ss[j];
          if(sss.length()<=3) continue;
          char[] temp=sss.toCharArray();
          int len=temp.length;
          for(int i=0;i<len-2;i++){
              int ind=r.nextInt(len-2);
              char t=temp[i+1];
              temp[i+1]=temp[ind+1];
              temp[ind+1]=t;
          }
          ss[j]=new String(temp);
       }
       for(String res:ss) System.out.print(res+" ");
    }

   

}

