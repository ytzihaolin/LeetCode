import java.util.*;
import java.io.*;


use arraylist of hashmap and version to control setcharat, every time we get char from the HashMap
using the maximum version that is smaller than this.version.


public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
       String t="linzihao";
       MutableString a=new MutableString(0);
       a.count=t.length();
       a.offset=0;
       char[] tt=t.toCharArray();
       for(int i=0;i<tt.length;i++){
            a.value.add(new HashMap<Integer,Character>());
            a.value.get(a.value.size()-1).put(a.version,tt[i]);
       }
       MutableString b=a.substring(1,4);
       System.out.println(a.charAt(2));
       System.out.println(b.charAt(2));
       b.setcharAt(2,'x');
       System.out.println(a.toString());
       System.out.println(b.toString());
    }

 class MutableString {
    int count;
    int version;
    ArrayList<HashMap<Integer,Character>> value;
    int offset;
    MutableString(int ver) {
        this.offset = 0;
        this.count = 0;
        this.value = new ArrayList<HashMap<Integer,Character>>();   
        this.version=ver;
    }
    MutableString (int begin, int count, ArrayList<HashMap<Integer,Character>> origin, int ver) {
        if (begin < 0 || offset+count > count ) {
            throw new StringIndexOutOfBoundsException();
        }
        this.offset = begin;
        this.count = count;
        this.value = origin;
        this.version=ver;
    }



    //Time Complexity O(1)
    //Space Complexity O(1)
    public char charAt(int index) {
        if (index < 0 || index > count) {
            throw new StringIndexOutOfBoundsException();
        }
        int t=0;
            while(this.value.get(index+offset).containsKey(t)&&t<=this.version){
                t++;
            }
        return value.get(index + offset).get(t-1);
    }
    
    //Time Complexity O(1)
    //Space Complexity O(1)
    public MutableString substring(int begin, int end) {
        if (begin < 0 || end > count || begin > end) {
            throw new StringIndexOutOfBoundsException();
        }
        if (begin == 0 && end == count) {
            return this;
        }
        return new MutableString(offset + begin, end - begin, value, this.version);
    }
    
    //Time Complexity O(1)
    //Space Complexity O(1)
    public void setcharAt(int index, char c) {
        this.version++;
        this.value.get(index+offset).put(version,c);
    }

    public String toString(){
        char[] res=new char[count];
        int index=0;
        for(int i=offset;i<offset+count;i++){
            int t=0;
            while(this.value.get(i).containsKey(t)&&t<=this.version){
                t++;
            }
            res[index++]=this.value.get(i).get(t-1);
        }
        return new String(res);
    }
}
    
}