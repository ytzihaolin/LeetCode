rotate string

public int rightRotate(String word1, String word2){
	
	if(word1==null||word2==null||word1.length()==0||word2.length()==0||word1.length()!=word2.legnth())
		return -1;
	String str=word1+word1;
	return str.indexOf(word2)!=-1?1:-1;
}


gray code

public static int grayCheck(byte term1, byte term2){

	byte x=(byte)(term1^term2);
	int count=0;
	while(x!=0){
		x=(byte)(x&(x-1));
		count++;
	}
	return count==1?1:0;
}


remove vowels

StringBuilder sb;
s.toLowerCase();
v.toLowerCase();
for(1:s1.length()) if(v1.indexOf(s1.charAt(i))>-1) continue;
else sb.append(charat(i));

return sb.toString();