public class Solution {
    int start,maxlen;
    public String longestPalindrome(String s) {
        if(s=="") return "";
        start=0;
        maxlen=1;
        for(int i=0;i<s.length()-1;i++){
            grow(s,i,i);
            grow(s,i,i+1);
        }
        return s.substring(start,start+maxlen);
    }
    
    private void grow(String s,int a,int b){
        char[] ss=s.toCharArray();
        while(a>=0&&b<s.length()&&ss[a]==ss[b]){
              if(b-a+1>maxlen){
                maxlen=b-a+1;
                start=a;
               }
            a--;
            b++;
        }
    }
}