Palindrome Partitioning II My Submissions Question

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

public class Solution {
    public int minCut(String s) {
        if(s==null||s.length()==0) return 0;
        int len=s.length();
        boolean[][] isp=new boolean[len+1][len+1];
        int[] dp=new int[len+1];
        for(int i=0;i<=len;i++) dp[i]=i-1;
        for(int i=1;i<=len;i++){
            for(int j=i-1;j>=0;j--){
                if(s.charAt(j)==s.charAt(i-1)&&(i-1-j<2||isp[j+1][i-1])){
                    isp[j][i]=true;
                    dp[i]=Math.min(dp[i],dp[j]+1);
                }
            }
        }
        return dp[len];
    }
}

//isPalindr[i][j] = true means s[i:j) is a valid palindrome
//dp[i] means the minCut for s[0:i) to be partitioned 


//使用dp的时候注意inclusive和exclusive的问题，经常会使用len+1以处理0index问题