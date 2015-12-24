Valid Number

Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true


corner cases too many:
some of them: 
1e+7; true
.1; true
1.e1; true



public class Solution {
    public boolean isNumber(String s) {
        if(s==null||s.trim().replace(" +","").length()==0) return false;
        boolean beforee=false,beforepoint=false,pree=false,prepoint=false,aftere=false,afterpoint=false;
        s=s.trim().replace(" +","");
        if(s.charAt(0)=='+'||s.charAt(0)=='-') s=s.substring(1);
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='e'){
                if(!beforee||pree||i==s.length()-1) return false;
                else{
                    pree=true;
                    if(s.charAt(i+1)=='+'||s.charAt(i+1)=='-') s=new StringBuilder(s).deleteCharAt(i+1).toString();//solve 1e+2,1e-2 case
                } 
            }
            
            else if(c=='.'){
                if(prepoint||pree) return false;
                else prepoint=true;
            }
            
            else if(!Character.isDigit(c)) return false;
            
            else{
                beforee=true;
                if(!prepoint) beforepoint=true;
                if(pree) aftere=true;
                if(prepoint) afterpoint=true;
            }
        }
        if((pree&&!aftere)||(prepoint&&!afterpoint&&!beforepoint)) return false;// two cases: "." and "10e"
        else return true;
    }
}