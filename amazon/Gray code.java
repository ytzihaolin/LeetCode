Gray code


public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
        return result;
    }
}


public static int grayCheck(byte term1, byte term2){

	byte x=(byte)(term1^term2);
	int count=0;
	while(x!=0){
		x=(byte)(x&(x-1));
		count++;
	}
	return count==1?1:0;
}