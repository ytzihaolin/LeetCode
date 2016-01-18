import java.util.*;
import java.io.*;


public class turningpoint{
    
  
    public static void main(String[] args) {
        turningpoint sol=new turningpoint();
        
        sol.test();
    }

    public void test(){
        int[] a=new int[]{1, 4, 9, 11, 22, 33};
       System.out.println(sl(a));
    }

    public int sl(int[] a){
        if(a.length<=2) return -1;
        if(a[1]>a[0]) return helper1(a);
        else return helper2(a);
    }

    public int helper1(int[] a){
        int l=0,r=a.length;
        int mid=0;
        while(l<=r){
          mid=l+(r-l)/2;
          if(mid==0||mid==a.length-1) return -1;
          if(a[mid]>a[mid-1]&&a[mid]>a[mid+1]) return mid;
          else if(a[mid]>a[mid-1]&&a[mid+1]>a[mid]) l=mid+1;
          else r=mid-1;
        }
        return -1;
    }

    public int helper2(int[] a){
        int l=0,r=a.length;
        int mid=0;
        while(l<=r){
          mid=l+(r-l)/2;
          if(mid==0||mid==a.length-1) return -1;
          if(a[mid]<a[mid-1]&&a[mid]<a[mid+1]) return mid;
          else if(a[mid]>a[mid-1]&&a[mid+1]>a[mid]) r=mid-1;
          else l=mid+1;
        }
        return -1;
    }
 }

 // O(mn)
