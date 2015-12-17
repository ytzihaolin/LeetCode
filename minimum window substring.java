Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"




public class Solution {
    public String minWindow(String s, String t) {
        int[] map=new int[128];
        for(char c:t.toCharArray()) map[c-'A']++;
        int start=0,end=0,head=0,len=Integer.MAX_VALUE;
        int counter=t.length();
        while(end<s.length()){
            if(map[s.charAt(end++)-'A']-->0) counter--;
            while(counter==0){
                if(end-start<len){
                    head=start;
                    len=end-start;
                }
                if(map[s.charAt(start++)-'A']++==0) counter++;
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(head,head+len);
    }
}



substring template:

int findSubstring(string s){
vector<int>map(128,0);intcounter;//record the occurrence of chars in the substring.
int begin=0, end=0;//two pointers, one point to the tail and one point to the head;
int d;//the length of substring
for() {/* initialize the hash map here */}
while(end<s.size()){
     if(map[s[end++]]-->0){/* modify counter here */}
     while(/* counter condition */){//enter this loop when a substring is valid, increase begin to make it invalid/* update d here */
     if(map[s[begin++]]++==0){/*modify counter here*/}
     }
     }
     return d;
}