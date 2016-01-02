import java.util.*;
import java.io.*;
public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
       String t="linzihao";
       MutableString a=new MutableString();
       a.count=t.length();
       a.offset=0;
       a.value=t.toCharArray();
       MutableString b=a.substring(1,4);
       System.out.println(a.charAt(2));
       System.out.println(b.charAt(2));

    }

 class MutableString {
    int count;
    char[] value;
    int offset;
    MutableString() {
        this.offset = 0;
        this.count = 0;
        this.value = new char[0];
    }
    MutableString (int begin, int count, char[] origin) {
        if (begin < 0 || offset+count > count ) {
            throw new StringIndexOutOfBoundsException();
        }
        this.offset = begin;
        this.count = count;
        this.value = origin;
    }



    //Time Complexity O(1)
    //Space Complexity O(1)
    public char charAt(int index) {
        if (index < 0 || index > count) {
            throw new StringIndexOutOfBoundsException();
        }
        return value[index + offset];
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
        return new MutableString(offset + begin, end - begin, value);
    }
    
    //Time Complexity O(1)
    //Space Complexity O(1)
    public void setcharAt(int index, char c) {
        if (index < 0 || index > count) {
            throw new StringIndexOutOfBoundsException();
        }
        value[offset + index] = c;
    }

    public String toString(){
        return new String(Arrays.copyOfRange(this.value, this.offset, this.offset+this.count));
    }
}
    
}
