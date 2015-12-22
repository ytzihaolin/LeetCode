 Shortest Palindrome 
 
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

The key point is to find the longest palindrome starting from the first character, and then reverse the remaining part as the prefix to s.

Naive way O(n^2)
	public String shortestPalindrome(String s) {
        if(s==null||s.length()==0) return s;
        int len=s.length();
        int index=0;
        for(int i=len;i>0;i--){
            if(isp(s.substring(0,i))){
                index=i;
                break;
            }
        }
        if(index==len) return s;
        String temp=new StringBuilder(s.substring(index)).reverse().toString();
        return temp+s;
    }
    
    public boolean isp(String a){//this method use reverse that take O(n)
        return a.equals(new StringBuilder(a).reverse().toString());
    }


    This method will cause TLE on big test case, KMP or Manacher Algorithm is needed for optimal solution



