public class GCD {
	public static int Solution(int[] nums){
		if(nums == null || nums.length == 0 || nums[0] == 0) return 0;
		if(nums.length == 1) return nums[0];
		int divisor = nums[0];
		for(int i = 1; i < nums.length; ++i) {
          	if(nums[i] == 0) return 0;
			int divident = nums[i];
			while( divident % divisor != 0){
				int tmp = divisor;
				divisor = divident % divisor;
				divident = tmp;
			}
		}
		return divisor;
	}
}


 public int sol(int[] a){
      if(a==null||a.length==0||a[0]==0) return 0;
      if(a.length==1) return a[0];
      int res=a[0];
      for(int n:a) res=gcd(res,n);
      return res;
    }

    public int gcd(int a, int b){
      if(a<b) return gcd(b,a);
      return b==0?a:gcd(b,a%b);
    }