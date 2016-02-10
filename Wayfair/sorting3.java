import java.io.*;
import java.util.*;



public class test{
	public static void main(String[] argc){
		test sol=new test();
		sol.sol();
	}
    public static void sol() {
    	int[] a=new int[]{8,4,-3,-4,1,-5};

    	//pre process
        int min=a[0];
        for(int n:a) if(n<min) min=n;
        int max=a[0]-min;
        for(int i=0;i<a.length;i++){
            a[i]-=min;
            max=Math.max(max,a[i]);
        }

        max++;//make sure we have a good modifier 
      	int index=0;
      	//put in all negative;
        for(int n:a){
            n=n%max;
            if(n<-min){
                a[index]+=n*max;
                index++;
            }
        }

        //put in all positive;
        for(int n:a){
            n=n%max;
            if(n>=-min){
                a[index]+=n*max;
                index++;
            }
        } 

        //restore value;
        for(int i=0;i<a.length;i++){
        	a[i]/=max;
        	a[i]+=min;
        }

        for(int n:a) System.out.println(n);
    }
}