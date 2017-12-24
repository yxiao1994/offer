package niuke;

import java.util.Arrays;
import java.util.Scanner;

public class Geohash {
    public static int[] geohash(int n){
    	int[] res=new int[6];
    	int left=-90;
    	int right=90;
    	for(int i=0;i<6;i++){
    		int mid=(left+right)/2;
    		if(n>=mid){
    			left=mid;
    			res[i]=1;
    		}
    		else{
    			right=mid;
    			res[i]=0;
    		}
    	}
      return res;
    }
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int i=in.nextInt();
        int[] a=geohash(i);
        for(int j=0;j<6;j++)
        	System.out.print(a[j]);
	}

}
