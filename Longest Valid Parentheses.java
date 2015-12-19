Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.



//第一种思路，dp每次遇到字符考虑...()和.....（.....)两种情况，记录最大长度
public class Solution {
    public int longestValidParentheses(String s) {
        if(s==null||s.length()==0) return 0;
        int len=s.length();
        int[] longest=new int[len];
        int res=0;
        for(int i=1;i<len;i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='(') longest[i]=(i-2)<0?2:(2+longest[i-2]);
                else{
                    if(i-1-longest[i-1]>=0&&s.charAt(i-1-longest[i-1])=='(') 
                        longest[i]=longest[i-1]+2+((i-1-longest[i-1]-1<0)?0:longest[i-1-longest[i-1]-1]);
                }
            }
            res=Math.max(res,longest[i]);
        }
        return res;
    }
}


//第二种思路，利用stack，每次将valid的pop出去，剩下的非valid得，之间最大的距离就是我们求的
class Solution {
public:
    int longestValidParentheses(string s) {
        int n = s.length(), longest = 0;
        stack<int> st;
        for (int i = 0; i < n; i++) {
            if (s[i] == '(') st.push(i);
            else {
                if (!st.empty()) {
                    if (s[st.top()] == '(') st.pop();
                    else st.push(i);
                }
                else st.push(i);
            }
        }
        if (st.empty()) longest = n;
        else {
            int a = n, b = 0;
            while (!st.empty()) {
                b = st.top(); st.pop();
                longest = max(longest, a-b-1);
                a = b;
            }
            longest = max(longest, a);
        }
        return longest;
    }
};