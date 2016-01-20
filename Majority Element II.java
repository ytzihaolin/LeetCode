Majority Element II 

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.




public class Solution {
    public List<Integer> majorityElement(int[] nums) {
       int c1=0,c2=0,can1=0,can2=0;
       for(int n:nums){
           if(n==can1) c1++;
           else if(n==can2) c2++;
           else if(c1==0){
               c1++;
               can1=n;
           }else if(c2==0){
               c2++;
               can2=n;
           }else{
               c1--;
               c2--;
           }
       }
       c1=0;
       c2=0;
       for(int n:nums){
           if(n==can1) c1++;
           if(n==can2) c2++;
       }
       List<Integer> res=new ArrayList<Integer>();
       if(c1>nums.length/3) res.add(can1);
       if(c2>nums.length/3&&can1!=can2) res.add(can2);//can1!=can2防止初始化可能造成的重复
       return res;
    }
}