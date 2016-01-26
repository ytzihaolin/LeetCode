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
可以自己写删除操作，比较方便。

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