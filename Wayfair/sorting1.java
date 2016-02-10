public class test{
	public static void main(String[] argc){
		test sol=new test();
		sol.sol();
	}
    public static void sol() {
    	int[] a=new int[]{8,4,-3,-4,1,-5};
    	int[] b=new int[a.length];
    	int index=0;
    	for(int n:a){
    		if(n<0) b[index++]=n;
    	}
    	for(int n:a){
    		if(n>0) b[index++]=n;
    	}
    	for(int n:b) System.out.print(n+" ");
    }
}