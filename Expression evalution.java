
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []




public class Solution {//very standard backtracking solution
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        if(num == null || num.length() == 0) return res;
        helper(num,res,"",target,0,0,0);
        return res;
    }
    
    public void helper(String num,List<String> res, String path,int target, int pos,long cur,long mulval){
        if(pos==num.length()){
            if(cur==target) res.add(path);
            return;
        }
        for(int i=pos;i<num.length();i++){
            if(i!=pos&&num.charAt(pos)=='0') break;
            long val = Long.parseLong(num.substring(pos, i + 1));
            if(pos==0){
                helper(num,res,String.valueOf(val),target,i+1,val,val);
            }else{
                helper(num,res,path+"+"+String.valueOf(val),target,i+1,cur+val,val);
                helper(num,res,path+"-"+String.valueOf(val),target,i+1,cur-val,-val);
                helper(num,res,path+"*"+String.valueOf(val),target,i+1,cur-mulval+mulval*val,mulval*val);
            }
        }
    }
} }
}

