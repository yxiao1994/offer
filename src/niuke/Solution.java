package niuke;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static long crazynumber(long n){
		long res=(long) ((1+Math.sqrt(8*n-7))/2);
		return res;
	}
	public static void MagicCoin(){
		Scanner in=new Scanner(System.in);
		int num=in.nextInt();
		StringBuilder sb=new StringBuilder();
		while(num!=0){
			if((num&1)==0){
				sb.insert(0, '2');
				num=(num-2)>>1;
			}
			else {
				sb.insert(0, '1');
				num=(num-1)>>1;
			}	
		}
		System.out.println(sb.toString());
	}
	 public static void reversenumber(){
	        Scanner in=new Scanner(System.in);
	        int num=in.nextInt();
	        int temp=num;
	        int reversenum=0;
	        while(temp!=0){
	            int left=temp%10;
	            reversenum=reversenum*10+left;
	            temp=temp/10;
	        }
	        int sum=num+reversenum;
	        System.out.println(sum);
	    }
	 public static void averageLen(){
	        Scanner in=new Scanner(System.in);
	        String str=in.nextLine();
	        int n=str.length();
	        int[] dp=new int[n];
	        dp[0]=1;
	        for(int i=1;i<n;i++){
	            if(str.charAt(i)==str.charAt(i-1))
	              dp[i]=dp[i-1];
	            else dp[i]=dp[i-1]+1;
	        }
	         double res=(double)n/dp[n-1];
	        System.out.println(String.format("%.2f", res));
	    }
	 public static int numberofLongest(){
	        Scanner in=new Scanner(System.in);
	        String s=in.nextLine();
	        int n=s.length();
	        List<String> allmatch=GetAllSeries(n/2);
	        int matchnumber=allmatch.size();
	        int[] res=new int[matchnumber];
	        int maxLen=0;
	        for(int i=0;i<allmatch.size();i++){
	            int matchlen=LCS(s,allmatch.get(i));
	            res[i]=matchlen;
	            if(matchlen!=n)
	            	maxLen=Math.max(maxLen,matchlen);
	        }
	        int count=0;
	        for(int a:res)
	            if(a==maxLen)
	                count++;
	        return count;
	    }
	 public static List<String> GetAllSeries(int n){
	        List<String> res=new ArrayList<>();
	        String cur="";
	        helpGetAllSeries(res,cur,n,n);
	        return res;
	    } 
	 public static void helpGetAllSeries(List<String> res,String s,int left,int right){
		 if(left==0&&right==0){
			 res.add(s);
		 }
		 if(left>0)
			 helpGetAllSeries(res, s+"(", left-1, right);
		 if(right>left)
			 helpGetAllSeries(res, s+")", left, right-1);
	 }
	 public static int LCS(String s1,String s2){
	        int m=s1.length();
	        int n=s2.length();
	        int[][] dp=new int[m+1][n+1];
	        for(int i=0;i<=m;i++){
	            for(int j=0;j<=n;j++){
	                if(i==0||j==0)
	                    dp[i][j]=0;
	                else if(s1.charAt(i-1)==s2.charAt(j-1))
	                    dp[i][j]=dp[i-1][j-1]+1;
	                else dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
	            }
	        }
	        return dp[m][n];
	    }
	 public static void repermute(){
	        Scanner in=new Scanner(System.in);
	        int seriesnum=in.nextInt();
	        for(int i=0;i<seriesnum;i++){
	            int n=in.nextInt();
	            int[] num=new int[n];
	            for(int j=0;j<n;j++)
	              num[j]=in.nextInt(); 
	            if(canmod4(num))
	                System.out.println("Yes");
	            else System.out.println("No");
	        }
	    }
	    public static boolean canmod4(int[] num){
	        int countmod4=0,countmod2=0;
	        for(int i=0;i<num.length;i++){
	            if(num[i]%4==0)
	                countmod4++;
	            else if(num[i]%2==0)
	                countmod2++;
	        }
	        int other=num.length-countmod4-countmod2;
	        if(countmod2==0)
	            return countmod4>=other-1;
	         return countmod4>=other;
	    }
	    public static int colorbrick(){
	    	Scanner in=new Scanner(System.in);
	    	String s=in.nextLine();
	    	HashSet<Character> set=new HashSet<>();
	    	for(int i=0;i<s.length();i++)
	    		set.add(s.charAt(i));
	    	if(set.size()>=3)
	    		return 0;
	    	else if(set.size()==2)
	    		return 2;
	    	else return 1;
	    }
	    public static int Longest01(){
	        Scanner in=new Scanner(System.in);
	        String s=in.nextLine();
	        int n=s.length();
	        int[] dp=new int[n];
	        dp[0]=0;
	        for(int i=1;i<n;i++){
	            if(s.charAt(i)!=s.charAt(i-1))
	                dp[i]=dp[i-1]+1;
	            else dp[i]=0;
	        }
	        int maxLen=0;
	        for(int i=1;i<n;i++)
	            maxLen=Math.max(maxLen,dp[i]);
	        return maxLen+1;
	    }
	    public static int maxabsdiff(){
	    	Scanner in=new Scanner(System.in);
	    	int n=in.nextInt();
	    	int[] nums=new int[n];
	    	for(int i=0;i<n;i++)
	    		nums[i]=in.nextInt();
	    	Arrays.sort(nums);
	    	int min=nums[0],max=nums[n-1];
	    	int diff=max-min;
	    	int minIndex=1,maxIndex=n-2;
	    	while(minIndex<maxIndex){
	    		diff+=max-nums[minIndex];
	    		diff+=nums[maxIndex]-min;
	    		max=nums[maxIndex];
	    		min=nums[minIndex];
	    		minIndex++;
	    		maxIndex--; 		
	    	}
	    	if((n&1)==1){
	    		diff+=Math.max(max-nums[minIndex], nums[minIndex]-min);
	    	}
	    	return diff;	
	    }
	    static final int mod = 1000000007;
	    public static int numofArray(){
	        Scanner in=new Scanner(System.in);
	        int n=in.nextInt();
	        int k=in.nextInt();
	        int[][] dp=new int[n+1][k+1];
	        for(int i=1;i<=k;i++)
	            dp[1][i]=1;
	        for(int i=2;i<=n;i++){
	            int sumnow=0;
	            for(int j=1;j<=k;j++)
	            	sumnow=(sumnow+dp[i-1][j])%mod;
	            for(int j=1;j<=k;j++){
	            	int invalid=0,p=2;
	            	while(p*j<=k){
	            		invalid=(invalid+dp[i-1][p*j])%mod;
	            		p++;
	            	}
	            	dp[i][j]=(sumnow-invalid)%mod;
	            }
	        }
	        int sum=0;
	        for(int i=1;i<=k;i++)
	            sum=(sum+dp[n][i])%mod;
	        return sum;
	    }
	    public static void main(String[] args){
	    	System.out.println(numofArray());
	    }
    
}
