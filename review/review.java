1. Two Sum 
Given an array of integers, find two numbers such that they add up to a specific target number.

利用hashset，遍历array，每次加到set中时先检查是否存在互补数已存在



3. Longest Substring Without Repeating Characters 
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.

 substring problem two pointer 模板，条件可用hashmap，int[26]等等，根据具体情况定
 left,right;
 while(right<length){
 	if(满足条件){
 		right++;
 		//modify counter here
 	}else{
 		while(left<right&&不满足条件){
 			移除left指向的元素
 		}
 	}
 }



 Majority Element II 
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

BM voting algorithm
利用pairout方法，统计candidate1,2,3...出现的次数c1,c2,c3....，如果ci=0，那么就换candidatei，第一轮统计完后再过一遍
确认最后的candidate次数符合n/3的要求（第一轮过完只是得到有潜在可能的candidate)




Count of Smaller Numbers After Self 
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

利用arraylist, 从后面向前面扫，维持一个扫到过的数据排序数组，查找当前元素在其中的index，该index即为所求

