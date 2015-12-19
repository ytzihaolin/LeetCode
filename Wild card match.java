Wild card match

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



public class Solution {//keep index of the start on record, every time advance at least one pointer, if
	//not match happened and there's "*" exist before, re-point to the previous * and advance s pointer by 1
	//and check another way of parsing
    public boolean isMatch(String s, String p) {
        int sind=0,pind=0,starinds=0,starindp=-1;
        while(sind<s.length()){
            if(pind<p.length()&&(s.charAt(sind)==p.charAt(pind)||p.charAt(pind)=='?')){
                sind++;
                pind++;
                continue;
            }else if(pind<p.length()&&p.charAt(pind)=='*'){//first time encounter *, treat it as it replace
            	//white space in s, if this is not the case, go back to this * and let it replace increasing 
            	//length of characters in s
                starindp=pind;
                starinds=sind;
                pind++;
                continue;
            }else{
                if(starindp!=-1){//if multiple replace exist, let the last * replace most characters in s
                	//因为将对应的字符都对应到s中第一次出现的位置，后面的都用最后一个*来包含
                    sind=starinds+1;
                    pind=starindp+1;
                    starinds=sind;
                }else return false;
            }
        }
        while(pind<p.length()&&p.charAt(pind)=='*'){
            pind++;
        }
        return pind==p.length();
    }
}