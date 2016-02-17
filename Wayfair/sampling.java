import java.io.*;
import java.util.*;



public class test{
	public static void main(String[] argc){
		test sol=new test();
		sol.sol();
	}
    public static void sol() {
    	int[] a=new int[]{1,2,3,4,5,6};
    	Random r=new Random();
    	int len=a.length;
    	int[] res=new int[3];
    	for(int i=0;i<3;i++){
    		int index=r.nextInt(len-i);
    		res[i]=a[index];
    		int temp=a[index];
    		a[index]=a[len-1-i];
    		a[len-1-i]=temp;
    	}

    	for(int i:res) System.out.println(i);
    }
}