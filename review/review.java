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

利用arraylist, 从后面向前面扫，维持一个扫到过的数据排序数组，查找当前元素在其中的index，该index即为所求;
注意点是findindex函数需要细分情况

private int findIndex(List<Integer> sorted, int target) {
    if (sorted.size() == 0) return 0;
    int start = 0;
    int end = sorted.size() - 1;
    if (sorted.get(end) < target) return end + 1;
    if (sorted.get(start) >= target) return 0;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (sorted.get(mid) < target) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }
    if (sorted.get(start) >= target) return start;
    return end;
}



Valid Number 
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
主要注意各种corner case，设置各种flag。
If we see [0-9] we reset the number flags.
We can only see . if we didn't see e or ..
We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
We can only see + and - in the beginning and after an e
any other character break the validation.




Max Points on a Line 
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

double loop，对每一个点向后检查，同一条线条件为(x-x0)/(y-y0)==const（或者说x-x0,y-y0可以约为同一组互质的数)，注意overlap x==x0,y==y0的计数




LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

最简单的LRU，如果不包含key，直接用arraylist解决，每次调用先删改元素，再插入该元素，保证调用到的在队尾，超过size
从队头删除。
此处的思路完全相同，稍微复杂一点，可以使用doublelinkedlist，每次查找或set某个key时，先删除，后插入，保证插入位置在队尾。
超过size从队头删除。
注意，删除的时候需要知道index，可以使用hashmap保存，也可遍历查找（arraylist.indexOf)，使用dlinkedlist的好处是
可以自己写删除操作，比较方便。注意删除的时候同时需要在hashmap出也删除，此处可以多维持一个hashmap用来存放node->key，也可在设计dlist的时候直接将key存在dlist中

还有一种方法，使用已有data structure linkedhashmap， 其中有自动的remove oldest方法，我们不需要自己写
import java.util.LinkedHashMap;

public class LRUCache {

    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void set(int key, int value) {
        map.put(key,value);
    }
}




44. Wildcard Matching 
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

双指针方法，一个指s， 一个指p， 遇到"?"或相同字符同时向前，遇到"*"时，记录下当前位置，pattern指针先向前（也可认为是s->0, p->1)，意味着
"*"覆盖空字符，然后继续向前比较，直到遇到队尾还没匹配的情况（注意，如果出现多个"*",只记录最后一次出现的地方，因为前面的已经
部分匹配了）此时回到记录"*"，出现的地方，s,p同时向前（即s被"*"覆盖的字符增加，然后继续比较s->1,p->1......s->n,p->1）;


212. Word Search II 
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

先利用dict建trie，然后bfs或者dfs搜索board（后一步就是word search |的问题），注意使用dfs的时候，在调用
dfs时不用加条件，进入dfs的第一行再见判断条件，已节省coding



140. Word Break II 
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

wordbreak|判断是否breakable，使用dp，dp[i]=dict.contains(substring(j,i))&&dp[j]; 
或者对dict中的词语遍历，向后构建dp
wordbreak||, 最简单方法使用递归，注意两点，先确认从后向前的某一个后缀存在dict中，以免递归半天发现最后无法break，
其二是别忘了最后return list的时候判断整体字符是否存在list中。



127. Word Ladder 
Given two words (beginWord and endWord), and a dictionarys word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

bfs搜索，每次替换一以位置的字符（26个方式），返回匹配时的层数。

ladder||, 返回最短匹配的路径。思路和前面基本一样，但要注意，通过每一层结束后remove visited from
dict来控制所建graph是最短路径，这样visited只用于控制是否插入queue，不控制是否插入图，否则结果会变少。
dict.contains是最大条件，然后！visited可以插入queue， 但是不管是否visited都要建图（即同层元素指向统一图节点）
每层循环结束remove visited from dict，以防止非最短路径加入。




174. Dungeon Game
注意每个节点最小是1即可，先将最后一行一列初始化，然后dp到第一个格子



321. Create Maximum Number 
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

1. 先决定两个数组各贡献几个元素（0-k)（k-0)，将可能结果都列出来
2. 对单独一个数组，固定长度中返回最大的组合。
3. 将两个数组分别返回的数merge到一起。(其中有一步比较复杂，大体思路和merge list差不多，每次选大的append，但是由于是非排序的，当两个元素相等的时候
	双指针同时向后移动，
	1)如果一个指针到底，另一个没有，那么选没有到达队尾指针的元素。
		exp: a: 5,5,5,5.
			 b: 5,5,5,5,a,b,c......
	此时可以肯定的是，在append4个5以后，如果优先append a，那么第5个必然是5， 如果优先append b,第5个是Max（a,5);

	2）都没到达队尾，选最后不等位置最大的指针）
4. 在所有结果中比较出最大的



30. Substring with Concatenation of All Words 
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].

使用hashmap<word,occrrance>，由于每个字符长度相等，就用brutalforce即可， 注意每次循环开始复制hashmap
找到一个word remove一个，最后一map.isEmpty()来判断。 而不是找到一个+1， 最后map.equals判断，否则会出问题



218. The Skyline Problem
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height

两个数据结构，
1）priorityqueue存储point(x,y)，可以用Hi,-Hi来区别一个楼的左上角和右上角。排序优先x小的, 其次y高的
2) treemap，记录每个Hi(即y)现在出现的次数，每次新遇到一个point的时候更新，可以getlastentry().getkey()取得当前最高Hi值，
   从priorityqueue中poll出的点存到结果当中的时候Hi使用当前最高的值（注意，只有poll出一个点且poll完更新height后当前maxheight改变的时候才记录结果）



76. Minimum Window Substring
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

典型substring problem， 套用双指针模板即可。

start=0, end=0;
for();//初始化判定标准，此处可以使用map来map字符出现次数
while(end<length){
	利用s.charAt(end++) 改变 map//利用s.charAt(end)改变判定标准
	if(符合了某个判定标准）{
		while(符合判定判定标准&&start<end){//在符合某一标准的情况下移动start直到再次打破标准
			利用s.charAt(start++) 改变 map;
			同时比较当前状态更新最后结果
		}
	}
}


132. Palindrome Partitioning II 
Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

dp问题，
for(i=1:len){
	for(j=i-1:0){//可在此处更新ispalindrome(j,i)
		if(substring(j,i) is palindrome) dp(i)=min{dp[j]+1,dp[i]}
	}
}//注意，判断substring是否palindrome也在dp循环中一点一点建立的




135. Candy 
There are N children standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

先从左向右保证rating高的比左边拿的多一个，再从右向左，保证rating高的比右边拿的多至少一个。最后求和





97. Interleaving String 
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

二维dp，dp[i][j]=true意味着s3.substring(0,i+j)是s1.substring(0，i)和s2.substring(0,j)以某种方式交叉的,注意因为存在s1,s2一个字符都没有的情况
所以dp[s1.length+1][s2.length+1]进行声明，dp[0][0]=true,表示两边都不贡献字符
状态方程dp[i][j]=(dp[i-1][j]&&s2.charAt(i-1)==s3.charAt(i+j-1))||(dp[i][j-1]&&s3.charAt(j-1)==s3.charAt(i+j-1))
基态即只对一个字符进行判断看是否由其组成




32. Longest Valid Parentheses 
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

dp, dp[i]表示终止于i位置的最长串长度，如果charAt(i)==')',则有两种情况
1）charAt(i-1)=='(', dp[i]=dp[i-2]+2;
2) ...........!='(' : (1). charAt(i-dp[i-1]-1)==')': dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2]//!....(.....)!
					  (2). charAt(i-dp[i-1]-1)!=')': 0;






282. Expression Add Operators 
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []


dfs backtracking问题，每个位置三个走路方向（+，-，*）， 注意传入dfs函数中的参数需要currentvalue，和multiplytemp。
multiplytemp=1.val 当当前数字位前为+号
			 2.-val 当当前数字位前为-号
			 3.multiplytemp*val，当当前位前为*号
currentvalue=1. currentvalue+val
		     2. currentvalue-val
		     3. currentvalue-multiplytemp+multiplytemp*val//此处为关键





124. Binary Tree Maximum Path Sum 
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

使用递归求单边path sum非常简单，此处只要加一个全局变量， 每次就sum=max{sum,maxleft+maxright+root.val}, 递归返回值为单边max即可


84. Largest Rectangle in Histogram 
Given n non-negative integers representing the histograms bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

O(n) stack based solution
遍历height array， stack中存递增高度，保证每一次插入的元素都比stack顶元素height要高，否则持续pop stack顶元素
意味着pop出的高度作为长方形左边找到了最右边的边（当前遍历指针指向的元素）， 高度按每次pop出的高度算，因为可以肯定在当前指针之前的高度均高于pop出的元素，否则不会存在在stack中
。计算这个长方形的面积并维持最大值。记住结束遍历后别忘了继续pop元素计算面积。



85. Maximal Rectangle 
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.

紧接上面的问题，每次遍历每行更新height数组，然后用相同的方法求最大长方形面积，记录全局最大即可。


41. First Missing Positive 
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

利用原数组index来表征，若元素为正则移动到index=当前元素值得位置（注意如果移动位置的元素已经是num[i]=i，那就不要移动防止loop）。
最后遍历看哪一个元素不等于index即可。



56. Merge Intervals 
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

首先写comparator以interval start为标准排序，然后遍历排好序的list，注意维持一个start， end变量表示当前正在遍历的interval的起始，
如果新遍历到的元素start小于end那么更新start，end而非插入res，直到新元素start>当前记录的end则插入res。




57. Insert Interval 
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

1. 用和上一题一样的方法，先插入，后merge。
2. 将上一题中merge的逻辑单独提出来，写插入新interval的部分。
	while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
        newInterval = new Interval( // we could mutate newInterval here also
                Math.min(newInterval.start, intervals.get(i).start),
                Math.max(newInterval.end, intervals.get(i).end));
        i++;
    }



45. Jump Game II 
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

记录每一步能到达的最远curmaxindex，然后超过该curmaxindex后算新的一步，在到达当前步能到的最远curmaxindex前，计算当前位置可到达的最远index，该index的max为超过curmaxindex后的新curmaxindex；



123. Best Time to Buy and Sell Stock III
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

从左往右扫一遍作为第一次，从右往左扫一遍作为第二次，每个几点存以它为分界点时左右最大值(具体方法和|一样，可以通过记录min（或max对于从右向左）
	min=Math.min(prices[i],min);
    msofar=Math.max(msofar,prices[i]-min);
    dp[i]+=msofar;
），然后，找每个位置最大值

at most k transactions:

		for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        tmpMax means the maximum profit of just doing at most i-1 transactions, using at most first j-1 prices, and buying the stock at price[j] - this is used for the next loop.






99. Recover Binary Search Tree 
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

思路很简单，就是找inorder中后面元素小与前一元素的两个地方，然后互换，为了实现O(1)空间，使用morris travesal方法：
morris travesal方法： 即更改树结构来实现回溯
	每次遍历左子树前，先进入左子树的最右元素（即遍历最后会遇到的元素），然后将其right child由null改为当前root，然后开始遍历左子树
	遍历回root后改回null





87. Scramble String
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".	



递归判断是否anagram，是的话进行下一步判断，两种可能，
1）在i位置分割，s1,s2前i子串和后len-i子串互为scramble。
2）在i位置分隔，s1前i子串和s2后i子串，s1后len-i子串和s2前len-i子串互为scramble



138. Copy List with Random Pointer 
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.


思路很简单，利用hashmap建立对应新老节点的关系，然后建遍历旧list的同时取元素建立新的list。同样的思路也可用于copy graph问题
如果不适用hashmap结构，那么就把新node插入到对应老node后面，建完再改回来。实现hash功能






164. Maximum Gap
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.



O(n)的方法无外乎桶排序之类，此处因为求最大gap，所以只要把桶的长度定义小于最小可能gap长度，那么桶里面的元素gap
可以不用管，只求桶之间的元素gap即可。此时记录每个桶最大最小元素即可，桶的index=（num[i]-min)/bucketgap







315. Count of Smaller Numbers After Self 
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].


从后向前扫到的数据加到一个sorted array中，每次二分查找插入位置，插入位置前元素的数量就是所求。




72. Edit Distance 
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character


int[][] cost=new int[len1+1][len2+1];//注意，len+1的情况适用于一个元素也可以不要的时候。
cost[i][j]表示前i子串编程前j子串所用最少操作数。
cost[i+1][j+1]=cost[i][j] //当charAt(i)==charAt(j);
cost[i+1][j+1]=1+min{replace, delete, insert} //otherwise; all for word1
replace = cost[i][j];
delete = cost[i][j+1];
insert = cost[i+1][j];

base case是cost[i][0]=cost[0][i]=i;






25. Reverse Nodes in k-Group 
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.


数出k个来反转，返回head，k个以后的元素递归调用得到后面的head





115. Distinct Subsequences 
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.


dp题，真头大啊。
	for(int i=0; i<T.length(); i++) {
        for(int j=0; j<S.length(); j++) {
            if(T.charAt(i) == S.charAt(j)) {
                mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
            } else {
                mem[i+1][j+1] = mem[i+1][j];
            }
        }
    }
base case 是，所有s子串都包含1个空串即mem[0][i]=1;





301. Remove Invalid Parentheses 
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).
Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]


brutal force,使用BFS遍历所有可能，单独写一个isValid函数判断每次遍历到的是否成功，level control实现最短。






312. Burst Balloons My Submissions Question
Total Accepted: 4376 Total Submissions: 13205 Difficulty: Hard
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167



DP问题，dp[i][j]=max coin when burst i to j;
dp[i][j]=max{dp[i][j],dp[i][mid-1]+num[mid]*nums[i-1]*num[j+1]+dp[mid+1][j]};//mid表示i to j中最后扎破的气球
注意的一点是，上面的状态转换公式需要考虑边界条件，mid-1<0和mid+1>=len的情况。








117. Populating Next Right Pointers in Each Node II 
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL



 建立三个TreeNode变量：cur, head, pre;
 1. cur为pre cursor上一层的父节点层，因为该层next已经设置好，所以可以作为引导指针前进。通过两个while(cur!=null) 控制循环，外层为总遍历，内层为层遍历。
 2. head记录当前设置next的子节点层左边第一个节点。
 3. pre当前设置next的子节点层的cursor，用于建立next指针。
 当每层遍历结束后head，pre置null，cur置head，相当于cur下移一层。
 每次判断cur子节点的时候，如果head==null说明刚进入新层，此时更新head=子节点，pre=head；否则只管pre cursor在子节点层移动和cur
 cursor在父节点层移动。直到cur走到最右。







42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


首先确定左右两个边界，即递增序列的最后一个，因为从边界开始的递增数列无法储水。
然后从两个边界中找到较小的一个，向另一个边界扫描并计算储水，直到找到比其大的隔板，更新两个边界，然后循环上述过程直到两个边界相遇






128. Longest Consecutive Sequence 
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.


use hashmap to store number as key and the longest sequence ends or start at it as value.
scan through the array and update longest sequence by checking sequence value of the left element and right element 





145. Iterative Binary Tree Pre-In-Postorder Traversal
 
 Inorder: cur记录当前遍历到的节点，下一次循环先将当前所在节点所有左孩子push进去，然后pop出一个元素，记录，进入pop元素的右孩子

 	1. push所有左孩子
 	2. pop出节点并记录
 	3. 进入右孩子
 	stack.push(root);
 	cur=root;
 	while(cur!=null || !stack.empty()){
        while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        list.add(cur.val);
        cur = cur.right;
    }


 Preorder：类似递归，先push right，后push left。

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) satck.push(node.left);
        }


Postorder: 和preorder几乎一样，只是add(0,val)，先push left后push right







287. Find the Duplicate Number 
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.



fast slow pointer方法，和检查linkedlist cycle的问题相同，当快慢pointer相遇的时候,将一个pointer重新指向head，
此时两个pointer一次移动一步，相遇点即为entry point，也即duplicate number；

此题的head=0；
slow=head;
fast=head;
while(fast!=null&&fast.next!=null){//while 条件在保证有cycle的情况下可以不加
	fast=fast.next.next;
	slow=slow.next;
	if(fast==slow) break;
}//此时fast，slow在meeting point，但非entry point







330. Patching Array 
Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.

greedy:
第一想法是存hashmap，把能加到的数存起来，然后从小到大补漏，补得同时更新能加到的数。
更好的方法是greedy，类似max leap，扫数组并维持能到达的最大边界。如果数组元素小于该边界那么不添加元素，只更新新的最大边界（+currentnum)，如果数组元素大于该边界，
那么意味着要增加该边界+1这个数，同时边界*2.  最大边界达到所求之上的时候返回count即可







implement segment tree
307. Range Sum Query - Mutable
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.


关键： buildtree(int[] a, int start, int end){
	if(start>end) return null;
	if(start==end) return new stree(start,end);
	stree root=new stree(start, end);
	root.left=buildtree(a,start,mid);
	root.right=buildtree(a,mid+1,end);
	return root;
}






implement trie
208. Implement Trie (Prefix Tree) 
Implement a trie with insert, search, and startsWith methods.


可以使用trienode[]建child，也可以使用hashmap<Character,TrieNode>建立child。
推荐hashmap，方便快捷






73. Set Matrix Zeroes 
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

以第一列和第一行作为存储状态的单元。row=0：rlength，那么col=1:clength；
单独用col0flag记录第一列是否需要置零。
注意如果col从1开始，row从0开始，那么最后遍历置0的时候需要row从最后到0，不然0row先置0了其他全为0
public void setZeroes(int[][] matrix) {
        if(matrix==null||matrix.length==0) return;
        int col0=1;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0]==0) col0=0;
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }
        
        for(int i=matrix.length-1;i>=0;i--){//必须的
            for(int j=matrix[0].length-1;j>=1;j--){
                 if(matrix[i][0]==0||matrix[0][j]==0) matrix[i][j]=0;
            }
            if(col0==0) matrix[i][0]=0;
        }
    }




166. Fraction to Recurring Decimal My Submissions Question
Total Accepted: 25679 Total Submissions: 177983 Difficulty: Medium
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

先判断正负，取绝对值后计算，整数部分好计算，小数部分每次余数乘10再算。其余和整数相同，对于循环的处理，维持一个hashmap，记录
小数部分每次得到的余数和append结果的index对应关系，如果哪一步得到的余数存在过，那么就在记录的index位置插入'(',最后插入')'即可。





29. Divide Two Integers My Submissions Question
Total Accepted: 58449 Total Submissions: 379993 Difficulty: Medium
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

同样先判断正负问题，此处乘积二分法，sum=分母，sum持续乘2，记录sum的倍数，直到超过分子，然后递归求（分母-sum）即剩下的部分除以分子的结果。
base case为分子小于分母，则返回0；



151. Reverse Words in a String My Submissions Question
Total Accepted: 89425 Total Submissions: 575084 Difficulty: Medium
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

1. split分解，然后stringbuilder逆序append
2. 先反转整个s，然后按单个word反转






91. Decode Ways My Submissions Question
Total Accepted: 60129 Total Submissions: 352763 Difficulty: Medium
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.


dp问题，从前向后建立dp的话。dp[i]依赖于dp[i-1]和dp[i-2]。下面两部之前，如果当前位=='0'，那么dp[i-1]先置0；
1. substring(i-1,i+1)(即当前位与前一位组合成的数字)在26以内 dp[i]=dp[i-1]+dp[i-2]
2. 否则，dp[i]=dp[i-1];


		int[] dp=new int[len+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=len;i++){
           if(s.charAt(i-1)=='0') dp[i-1]=0;
           dp[i]=(Integer.parseInt(s.substring(i-2,i))<=26)?dp[i-2]+dp[i-1]:dp[i-1];
        }
        return dp[len];

从后向前扫就不用首先dp[i-1]判断置零了，当前位为零直接跳过即可。








4. Median of Two Sorted Arrays My Submissions Question
Total Accepted: 81804 Total Submissions: 453266 Difficulty: Hard
There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

建一个函数，find Kth in two sorted array， 那么这里k=(len1+len2)/2；
函数中，arrayA, arrayB分别找第k/2的元素（超出范围的话自动选没超出的那个去除），然后相比，小的那个数组就可以将前k/2个元素除掉（即下次递归改变start index），进而递归调用寻找k=k/2；
basecase是：
1. 有一个数组的start index超过上限，那么直接在第二个数组中找第k个就可以。
2. k==1,返回两个数组从各自start index首元素调最小的即可




331. Verify Preorder Serialization of a Binary Tree


1. using indegree and outdegree:
all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).

public boolean isValidSerialization(String preorder) {
    String[] nodes = preorder.split(",");
    int diff = 1;
    for (String node: nodes) {
        if (--diff < 0) return false;
        if (!node.equals("#")) diff += 2;
    }
    return diff == 0;
}

2. using stack:
This is very simple problem if you use stacks. The key here is, when you see two consecutive "#" characters on stack, pop both of them and replace the topmost element on the stack with "#". 


follow-up: 
validate preorder for BST:
1. Push to stack till you get higher element than the topmost element of the stack. [i.e. you keep pushing till you hit the leftmost leaf]
2. If you find current value which is greater than the TOP of Stack, POP till you hit higher element, and also mark the last poped value, which is your variable (Left_Tree_Highest). This variable is used to check whether the order is correct or not.
3. In all the steps, if you find current element lesser than Left_Tree_Highest, then your tree is not a Binary Search Tree and it should return “NO”.
4. If all the element traversed, and you have not hit “NO”, means given sequence follows the Binary Search Tree rule.




Read4 problem:

https://segmentfault.com/a/1190000003794420





130. Surrounded Regions My Submissions Question
Total Accepted: 47271 Total Submissions: 301078 Difficulty: Medium
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

此类题直接用另一种字符标记无法被包围的'O',剩下的'O'就是可以被标记的，这样翻转后再改回来即可


273. Integer to English Words My Submissions Question
Total Accepted: 11859 Total Submissions: 66189 Difficulty: Medium
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"


public class Solution {
    private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return helper(num); 
    }

    private String helper(int num) {
        String result = new String();
        if (num < 10) result = belowTen[num];
        else if (num < 20) result = belowTwenty[num -10];
        else if (num < 100) result = belowHundred[num/10] + " " + helper(num % 10);
        else if (num < 1000) result = helper(num/100) + " Hundred " +  helper(num % 100);
        else if (num < 1000000) result = helper(num/1000) + " Thousand " +  helper(num % 1000);
        else if (num < 1000000000) result = helper(num/1000000) + " Million " +  helper(num % 1000000);
        else result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
        return result.trim();
    }
}//先/后%







179. Largest Number My Submissions Question
Total Accepted: 38970 Total Submissions: 215130 Difficulty: Medium
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

自己重写comparator，然后sort即可




224. Basic Calculator My Submissions Question
Total Accepted: 22319 Total Submissions: 107209 Difficulty: Medium
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

stack只push当前计算所得的结果，不push计算符号，记得也要保存正负号
对于（），记得保存两个符号，针对"-(-3+1)"这种情况，第一个符号可以将-1推入到stack中




54. Spiral Matrix My Submissions Question
Total Accepted: 51311 Total Submissions: 233992 Difficulty: Medium
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

This is a very simple and easy to understand solution. I traverse right and increment rowBegin, then traverse down and decrement colEnd, then I traverse left and decrement rowEnd, and finally I traverse up and increment colBegin.
通过上面四个变量控制每次行进的方向，注意后两个travesal的时候需要先检查





5. Longest Palindromic Substring My Submissions Question
Total Accepted: 92054 Total Submissions: 413111 Difficulty: Medium
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

分两种情况grow palindrome， 然后维持最大值



43. Multiply Strings My Submissions Question
Total Accepted: 53290 Total Submissions: 235571 Difficulty: Medium
Given two numbers represented as strings, return multiplication of the numbers as a string.

大数乘法，维持一个len1+len2长度的数组，将对位乘积之和放入，代表该位上的数，最后遍历一遍考虑进位和corner case即可



93. Restore IP Addresses My Submissions Question
Total Accepted: 50174 Total Submissions: 221465 Difficulty: Medium
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

穷举所有可能插入.的位置，然后函数判断是否合法。





316. Remove Duplicate Letters My Submissions Question
Total Accepted: 7727 Total Submissions: 32885 Difficulty: Medium
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

递归解决，每次处理一个字母，找到第一个该处字母是最后一次出现的位置。然后从这之前的substring中选一个字母作为此次循环确定的字母，输出并替换掉后面所有的该字母，
进入下一次递归








222. Count Complete Tree Nodes My Submissions Question
Total Accepted: 26598 Total Submissions: 110945 Difficulty: Medium
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.


The height of a tree can be found by just going left. Let a single node tree have height 0. Find the height h of the whole tree. If the whole tree is empty, i.e., has height -1, there are 0 nodes.

Otherwise check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height.

If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).
就是找最后一行结束的node在当前节点左子树中还是右子树中，以确定哪一个子树是full可以直接计算节点数了








60. Permutation Sequence My Submissions Question
Total Accepted: 48324 Total Submissions: 197975 Difficulty: Medium
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.


注意此处是permutation，不是组合combination，此类题先简历factorial数组，然后fact数组确定每一位上的数，第i位剩下i-1位数那么总排列数为
fact[i-1]
注意如果k从0开始的话，需要k--;






69. Sqrt(x) My Submissions Question
Total Accepted: 83043 Total Submissions: 336425 Difficulty: Medium
Implement int sqrt(int x).

Compute and return the square root of x.


二分法找，注意点是如果mid^2<=x的时候，不能直接left=mid+1,因为此时mid可能就是所求，需要进一步对比(mid+1)^2是否<=x







310. Minimum Height Trees My Submissions Question
Total Accepted: 6675 Total Submissions: 25926 Difficulty: Medium
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]


最多只有两个root，首先找到叶子节点（邻居只有一个的），然后向内遍历（将叶子的邻居去掉叶子，然后看去掉后的邻居数是否为1，是则为新的叶子），直到剩下少于3个及节点