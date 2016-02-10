import java.io.*;
import java.util.*;



public class test{
	public static void main(String[] argc){
		test sol=new test();
		sol.sol();
	}
    public static void sol() {
    	int[] a=new int[]{8,4,-3,-4,1,-5};
    	for(int i=0;i<a.length;i++){
    		if(a[i]<0){
    			for(int j=i-1;j>=0;j--){
    				if(a[j]>0){
    					int temp=a[j];
    					a[j]=a[j+1];
    					a[j+1]=temp;
    				}
    			}
    		}
    	}

    	for(int n:a) System.out.print(" "+n);
    }
}