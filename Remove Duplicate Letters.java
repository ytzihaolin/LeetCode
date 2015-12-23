Remove Duplicate Letters 

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"


Greedy is all about finding the choice method

in this problem, we have to find the smallest char in each step that after this char contains all other chars

public class Solution {
    public String removeDuplicateLetters(String s) {
        if(s==null) return s;
        int pos=0;
        int[] map=new int[26];
        for(int i=0;i<s.length();i++) map[s.charAt(i)-'a']++;
        for(int i=0;i<s.length();i++){
            if(s.charAt(pos)>s.charAt(i)) pos=i;
            if(--map[s.charAt(i)-'a']==0) break;
        }
        return s.length()==0?"":s.charAt(pos)+removeDuplicateLetters(s.substring(pos+1).replace(""+s.charAt(pos),""));//char to string ""+char
    }
}