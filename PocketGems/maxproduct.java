import java.util.*;
import java.io.*;
public class test {
    
  
    public static void main(String[] args) {
        test sol=new test();
        
        sol.test();
    }

    public void test(){
       System.out.println(max(new int[]{1,2,3,0,-1,-7,0,3,1,-9}));
    }

   public int max(int[] nums) {  
    int result = nums[0];  
    int maxHere = nums[0];  
    int minHere = nums[0];  
    for(int i=1; i<nums.length; i++) {  
        int a = nums[i] * maxHere;  
        int b = nums[i] * minHere;  
        maxHere = Math.max(nums[i], Math.max(a, b));  
        minHere = Math.min(nums[i], Math.min(a, b));  
        result = Math.max(result, maxHere);  
    }  
    return result;  
}
    
}
