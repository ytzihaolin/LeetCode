187. Repeated DNA Sequences 

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].


public class Solution {//进制转换，4进制可做hash function
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res=new LinkedList<String>();
        if(s==null||s.length()<10) return res;
        HashMap<Character,Integer> change=new HashMap<Character,Integer>();
        change.put('A',0);
        change.put('C',1);
        change.put('G',2);
        change.put('T',3);
        HashSet<Integer> hash=new HashSet<Integer>();
        HashSet<String> resd=new HashSet<>();
        int hv=0;
        for(int i=0;i<s.length();i++){
            if(i>9) hv-=Math.pow(4,9)*change.get(s.charAt(i-10));
            hv=hv*4+change.get(s.charAt(i));
            if(i>=9&&!hash.add(hv)) resd.add(s.substring(i-9,i+1));
        }
        return new ArrayList<String>(resd);
    }
}