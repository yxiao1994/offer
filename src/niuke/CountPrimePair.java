package niuke;

import java.util.ArrayList;
import java.util.Scanner;

public class CountPrimePair {
	public static int findPrimePair(int sum){
		if(sum<3)
			return 0;
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=2;i<1000;i++)
			if(isPrime(i))
				list.add(i);
		int count=0;
		for(int i=0,j=list.size()-1;i<=j;){
			if(list.get(i)+list.get(j)>sum)
				j--;
			else if(list.get(i)+list.get(j)==sum){
				count++;
				i++;
			}
			else
				i++;
		}
		return count;
	}
	public static int findPrimePair1(int sum){
		if(sum<=3)
			return 0;
		int count=0;
		for(int i=2;i<=sum/2;i++){
			if(isPrime(i)==true){
				if(isPrime(sum-i)==true)
					count++;
			}
		}
		return count;
	}
    public static Boolean isPrime(int num){
    	for(int i=2;i<=Math.sqrt(num);i++)
    		if(num%i==0)
    			return false;
    	return true;
    }
   public static void main(String[] args){
	   Scanner in=new Scanner(System.in);
	   int i=in.nextInt();
	   System.out.println(findPrimePair1(i));
   }
}
