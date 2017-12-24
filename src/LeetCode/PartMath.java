package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class PartMath {
	  static class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
		  }
	public static List<List<Integer>> combine(int n, int k) {  
        List<List<Integer>> combSet = new ArrayList<List<Integer>>(); 
        List<Integer> comb = new ArrayList<Integer>();  
          
        if(n<k) return combSet;  
        helper(n, k, combSet, comb, 1);  
        return combSet;  
    }  
      
    public static void helper(int n, int k, List<List<Integer>> combSet, List<Integer> comb, int start){  
        // one possible combinition constructured  
        if(comb.size() == k){  
            combSet.add(new ArrayList<>(comb));  
            return;  
        }  
          
        else{  
            for(int i=start; i<=n; i++){// try each possibility number in current position  
                comb.add(i);
                //System.out.println(comb);
                helper(n, k, combSet, comb, i+1); // after selecting number for current position, process next position  
                comb.remove(comb.size()-1); // clear the current position to try next possible number  
                //System.out.println(comb);
            }  
        }  
    }  
    public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> permuteSet = new ArrayList<List<Integer>>(); 
        List<Integer> permute = new ArrayList<Integer>();    
        if(nums==null||nums.length==0) return permuteSet;  
        boolean[] visited=new boolean[nums.length];
        Arrays.fill(visited, false);
        Arrays.sort(nums);
        dfs(permuteSet, permute, nums,visited);  
        return permuteSet;  
    }
    public static void dfs(List<List<Integer>> permuteSet, List<Integer> permute,int[] nums,boolean[] visited){
    	if(permute.size()==nums.length){
    		permuteSet.add(new ArrayList<>(permute));
    		return;
    	}
    	else{
    		for(int i=0;i<nums.length;i++){
    			if(i>0&&!visited[i-1]&&nums[i]==nums[i-1])
    				continue;
    			if(!visited[i]){
    				visited[i]=true;
    				permute.add(nums[i]);
    				dfs(permuteSet, permute, nums, visited);
    				permute.remove(permute.size()-1);
    				visited[i]=false;
    			}
    		}
    	}
    	
    }
    /**
     * 求数组的下一个排列
     * @param nums
     */
   public static void nextPermutation(int[] nums) {
        int i=nums.length-2;
        while(i>=0&&nums[i]>=nums[i+1])
        	i--;
        if(i>=0){
	        int j=nums.length-1;
	        while(nums[j]<=nums[i])
	        	j--;
	        swap(nums, i, j);
        }
        reverse(nums, i+1);
        
    }
   public static void reverse(int[] nums,int start){
	   int i=start;
	   int j=nums.length-1;
	   for(;i<j;i++,j--){
		   swap(nums, i, j);
	   }
   }
   /**
    * 将给定整数反转
    * @param x
    * @return
    */
   public static int reverse(int x) {
       long res=0;
       int sign=1;
       if(x<0){
    	   sign=-1;
    	   x*=-1;
       }
       while(x!=0){
    	   if(res>Integer.MAX_VALUE)
    		   break;
    	   res=10*res+x%10;
    	   x/=10;
       }
       res*=sign;
       if(res>Integer.MAX_VALUE||res<Integer.MIN_VALUE)
    	   return 0;
       return (int)res;
   }
   public static void swap(int[] a,int i,int j){
	   int temp=a[i];
	   a[i]=a[j];
	   a[j]=temp;
   }
   public static List<List<Integer>> combinationSum(int[] candidates, int target) {
	   List<List<Integer>> res = new ArrayList<List<Integer>>(); 
       if(candidates==null||candidates.length==0||target<0)
    	   return res;
       List<Integer> comb=new ArrayList<>();
       Arrays.sort(candidates);
       helpSum(res, comb, candidates, target, 0);
       return res;
   }
   public static void helpSum(List<List<Integer>> combSet, List<Integer> comb,int[] candidates,int target, int start){
	   if(target<0)
		   return;
	   else if(target==0){
		   combSet.add(new ArrayList<>(comb));
		   return;
	   }
	   else for(int i=start;i<candidates.length;i++){
		   if(i>start&&candidates[i]==candidates[i-1])
			   continue;
		   comb.add(candidates[i]);
		   helpSum(combSet, comb, candidates, target-candidates[i], i+1);
		   comb.remove(comb.size()-1);
	   }
   }
   public static List<String> letterCombinations(String digits) {
       List<String> res=new ArrayList<>();
       if(digits==null||digits.length()==0)
    	   return res;
       String dict[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
       StringBuilder sb=new StringBuilder();
       letterhelp(digits, dict, 0, sb, res);
       return res;
   }
   public static void letterhelp(String digits,String[] dict,int level,StringBuilder sb,List<String> res){
	   if(sb.length()==digits.length()){
		   res.add(sb.toString());
		   return;
	   }
	   else{
		   String str=dict[digits.charAt(level)-'2'];
		   for(int i=0;i<str.length();i++){
			   sb.append(str.charAt(i));
			   letterhelp(digits, dict, level+1, sb, res);
			   sb.deleteCharAt(sb.length()-1);
		   }
	   }
   }
   /**
    * 求数组中的两个数使得其和等于target
    * @param nums
    * @param target
    * @return
    */
   public static int[] twoSum(int[] nums, int target) {
	   int[] res=new int[2];
       HashMap<Integer, Integer> map=new HashMap<>();
       for(int i=0;i<nums.length;i++){
    	   map.put(nums[i], i);
       }
       for(int i=0;i<nums.length;i++){
    	   int key=target-nums[i];
    	   if(map.containsKey(key)&&i<map.get(key)){
    		   res[0]=i;
    	       res[1]=map.get(key);
    	   }
       }
       return res;
   }
   /**
    * 求数组中的三个数使得其和等于0
    * @param nums
    * @return
    */
   public static List<List<Integer>> threeSum(int[] nums) {
       List<List<Integer>> res=new ArrayList<>();
       if(nums==null||nums.length<3)
    	   return res;
       Arrays.sort(nums);
       for(int i=0;i<nums.length-2;i++){
    	   if(i>0&&nums[i]==nums[i-1])
    		   continue;
    	   int target=-nums[i];
    	   twosum(res,nums,i+1,nums.length-1,target);
       }
       return res;
   }
   public static void twosum(List<List<Integer>> res,int[] nums,int left,int right,int target){
	   while(left<right){
		   if(nums[left]+nums[right]==target){
			   List<Integer> now=new ArrayList<>();
			   now.add(nums[left]);
			   now.add(nums[right]);
			   now.add(-target);
			   res.add(now);
			   left++;
			   right--;
			   while(left<right&&nums[left]==nums[left-1])
				   left++;
			   while(left<right&&nums[right]==nums[right+1])
				   right--;
		   }
		   else if(nums[left]+nums[right]<target)
			   left++;
		   else right--;
	   }
   }
   /**
    * find three integers in nums such that the sum is closest to a given number target. 
    * @param nums
    * @param target
    * @return
    */
   public static int threeSumClosest(int[] nums, int target) {
	   Arrays.sort(nums);
	   int sum=nums[0]+nums[1]+nums[2],bestsum=sum;//初始化不能随意
       for(int i=0;i<nums.length-2;i++){
    	   int left=i+1,right=nums.length-1;
    	   while(left<right){
    		   sum=nums[i]+nums[left]+nums[right];
    		   int diff=sum-target;
    		   if(Math.abs(diff)<Math.abs(bestsum-target)){
    			   bestsum=sum;
    		   }
    		   if(diff==0)
    			   return target;
    		   else if(diff<0)
    			   left++;
    		   else right--;
    	   }
       }
       return bestsum;
   }
   /**
    * 求数组中的4个数使得其和等于target
    * @param nums
    * @param target
    * @return
    */
   public static List<List<Integer>> fourSum(int[] nums, int target) {
	   List<List<Integer>> res=new ArrayList<>();
       if(nums==null||nums.length<4)
    	   return res;
       Arrays.sort(nums);
       for(int i=0;i<nums.length-3;i++){
    	   if(i>0&&nums[i]==nums[i-1])
    		   continue;
    	   for(int j=i+1;j<nums.length-2;j++){
    		   if(j!=i+1&&nums[j]==nums[j-1])
    			   continue;
    		   int targetnow=target-nums[i]-nums[j];
    		   int left=j+1,right=nums.length-1;
    		   while(left<right){
    			   if(nums[left]+nums[right]==targetnow){
    				   List<Integer> now=new ArrayList<>();
    				   now.add(nums[i]);
    				   now.add(nums[j]);
    				   now.add(nums[left]);
    				   now.add(nums[right]);
    				   res.add(now);
    				   left++;
    				   right--;
    				   while(left<right&&nums[left]==nums[left-1])
    					   left++;
    				   while(left<right&&nums[right]==nums[right+1])
    					   right--;
    			   }
    			   else if(nums[left]+nums[right]<targetnow)
    				   left++;
    			   else right--;
    		   }
    	   }
       }
       return res;
   }
   /**
    * 判断给定的数是否是回文数
    * @param x
    * @return
    */
   public static boolean isPalindrome(int x) {
		 if(x<0)
			 return false;
		 int numberOfdigits=0,small=1;
		 int temp=x;
		 int big=1;
		 while(temp>0){
			 numberOfdigits++;
			 temp/=10;
			 if(temp!=0)
				 big*=10;
		 }
		 for(int i=0;i<numberOfdigits/2;i++){
			 if((x/big)%10!=(x/small)%10)
				 return false;
			 big/=10;
			 small*=10;
		 }
		 return true;
	 }
   /**
    * Divide two integers without using multiplication, division and mod operator.
    * If it is overflow, return MAX_INT.
    * @param dividend
    * @param divisor
    * @return
    */
   public static int divide(int dividend, int divisor) {
       long a=dividend>0?dividend:-(long)dividend;
       long b=divisor>0?divisor:-(long)divisor;
       int shift=31;
       long res=0;
       while(shift>=0){
    	   if(a>=(b<<shift)){
    		   res+=(1L<<shift);//这里一定注意是1L
    		   a-=(b<<shift);
    	   }
    	   shift--;
       }
       if((dividend>0&&divisor<0)||(dividend<0&&divisor>0))
    	   res=-res;
       if(res>Integer.MAX_VALUE||res<Integer.MIN_VALUE)
    	   return Integer.MAX_VALUE;
       return (int)res;
   }
   /**
    * Given an array of strings, group anagrams together.
    * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"]
    * Return:[ ["ate", "eat","tea"], ["nat","tan"], ["bat"]] 
    * @param strs
    * @return
    */
   public static List<List<String>> groupAnagrams(String[] strs) {
       List<List<String>> res=new ArrayList<>();
       if(strs==null||strs.length==0)
    	   return res;
       HashMap<String, List<String>> map=new HashMap<>();
       for(int i=0;i<strs.length;i++){
    	   char[] c=strs[i].toCharArray();
    	   Arrays.sort(c);
    	   String sorted=String.valueOf(c);
    	   if(!map.containsKey(sorted)){
    		   List<String> list=new ArrayList<>();
    		   map.put(sorted, list);
    	   }
    	   map.get(sorted).add(strs[i]);
       }
       for(String s:map.keySet()){
    	   res.add(map.get(s));
       }
       return res;
   }
   /**
    * Maximum Subarray 最大和子数组
    * @param nums
    * @return
    */
   public static int maxSubArray(int[] nums) {
       int maxsum=nums[0],localsum=nums[0];
       for(int i=1;i<nums.length;i++){
    	   localsum=Math.max(localsum+nums[i], nums[i]);
    	   maxsum=Math.max(maxsum, localsum);
       }
       return maxsum;
   }
   /**
    * Maximum Product Subarray 最大乘积子数组
    * @param nums
    * @return
    */
   public static int maxProduct(int[] nums){
	   int localmin=nums[0],localmax=nums[0],maxproduct=nums[0];
	   for(int i=1;i<nums.length;i++){
		   int temp=localmax;
		   localmax=Math.max(Math.max(nums[i], localmin*nums[i]),localmax*nums[i]);
		   localmin=Math.min(Math.min(nums[i], localmin*nums[i]),temp*nums[i]);
		   maxproduct=Math.max(maxproduct, localmax);
	   }
	   return maxproduct;
   }
   /**
    *阿里笔试题，求商品编号
    * @return
    */
   public static int NumberofProduct(int n){
	   int low=0,high=10000;
	   while(low<=high){
		   int mid=(low+high)/2;
		   int midnumber=mid*(mid-1)/2;
		   if(n==midnumber)
			   return mid-1;
		   else if(n>midnumber)
			   low=mid+1;
		   else high=mid-1;
	   }
	   return n-(low-1)*(low-2)/2;
   }
   /**
    * 合并区间
    * @param intervals
    * @return
    */
   public List<Interval> merge(List<Interval> intervals) {
	   List<Interval> res=new ArrayList<>();
	   if (intervals==null||intervals.size()==0) {
		   return res;
	   }
	   intervals.sort(new IntervalComp());
	   Interval last=intervals.get(0);
	   for(int i=1;i<intervals.size();i++){
		   Interval now=intervals.get(i);
		   if(now.start<=last.end)
			   last.end=Math.max(now.end,last.end);
		   else{
			   res.add(last);
			   last=now;
		   }
	   }
	   res.add(last);
	   return res;
   }
   private class IntervalComp implements Comparator<Interval>{
	   public int compare(Interval o1, Interval o2) {
		   return o1.start-o2.start;
	}   
   }
  
   public static void exchange(int[] a,int index1,int index2){
	   	int temp=a[index1];
	   	a[index1]=a[index2];
	   	a[index2]=temp;
   }
   /**
    * Permutation Sequence
    * @param n
    * @param k
    * @return
    */
   public static String getPermutation(int n, int k) {
	   if(k<=0||k>factorial(n))
		   return "";
	   String s="123456789".substring(0, n);
	   char[] c=new char[n];
	   for(int i=0;i<n;i++){
		   int factorialnow=factorial(s.length()-1);
		   int digit=(k-1)/factorialnow;
		   char res=s.charAt(digit);
		   s=s.replace(String.valueOf(res), "");
		   k-=(digit*factorialnow);
		   c[i]=res;
	   }
	   return String.valueOf(c);
   }
   public static int factorial(int n){
	   int res=1;
	   for(int i=2;i<=n;i++)
		   res*=i;
	   return res;
   }
   /**
    * plus one to the array
    * @param digits
    * @return
    */
   public static int[] plusOne(int[] digits) {
       int n=digits.length;
       int carry=1;
       for(int i=n-1;i>=0&&carry>0;i--){
    	   int sum=carry+digits[i];
    	   digits[i]=sum%10;
    	   carry=sum/10;
       }
       if(carry==0)
    	   return digits;
       int[] res=new int[n+1];
       res[0]=1;
       for(int i=0;i<n;i++)
    	   res[i+1]=digits[i];
       return res;
   }
   /**
    * Compute and return the square root of x.
    * @param x
    * @return
    */
   public static int mySqrt(int x) {
       long low=0,high=(x>>1)+1;//一定要用long,防止溢出
       while(low<=high){
    	   long mid=(low+high)>>1;
    	   long square=mid*mid;
    	   if(square==x)
    		   return (int)mid;
    	   else if(square<x)
    		   low=mid+1;
    	   else high=mid-1;
       }
       return (int)high;
   }
   public static int climbStairs(int n) {
	   if(n==1)
		   return 1;
	   int[] dp=new int[n];
	   dp[0]=1;
	   dp[1]=2;
	   for(int i=2;i<n;i++)
		   dp[i]=dp[i-1]+dp[i-2];
	   return dp[n-1];
   }
   public int largestRectangleArea(int[] heights) {
       Stack<Integer> stack=new Stack<>();
       int len=heights.length;
       int i=0,maxArea=0;
       while(i<=heights.length){
    	   int height=(i==len)?0:heights[i];
    	   if(stack.isEmpty()||height>=heights[stack.peek()]){
    		   stack.push(i);
    		   i++;
    	   }
    	   else{
    		  int barnow=stack.pop();
    		  int heightnow=heights[barnow];
    		  int left=stack.isEmpty()?-1:stack.peek();
    		  maxArea=Math.max(maxArea, (i-left-1)*heightnow);
    	   }
       }
       return maxArea;
   }
   /**
    * Given a set of distinct integers, nums, return all possible subsets.
    * @param nums
    * @return
    */
   public static List<List<Integer>> subsets(int[] nums) {
	   List<List<Integer>> res=new ArrayList<>();
       if(nums==null||nums.length==0)
    	   return res;
       List<Integer> curr=new ArrayList<>();
       res.add(curr);
       helpsubsets(nums, 0, curr, res);
       return res;
   }
   public static void helpsubsets(int[] nums,int start,List<Integer> curr,List<List<Integer>> res){
	   for(int i=start;i<nums.length;i++){
		   curr.add(nums[i]);
		   res.add(new ArrayList<>(curr));
		   helpsubsets(nums, i+1, curr, res);
		   curr.remove(curr.size()-1);
	   }
   }
   /**
    * 包含重复元素的子集
    * @param nums
    * @return
    */
   public List<List<Integer>> subsetsWithDup(int[] nums) {
	   List<List<Integer>> res=new ArrayList<>();
       if(nums==null||nums.length==0)
    	   return res;
       Arrays.sort(nums);
       List<Integer> curr=new ArrayList<>();
       res.add(curr);
       helpsubsetsWithdup(nums, 0, curr, res);
       return res;
   }
   public static void helpsubsetsWithdup(int[] nums,int start,List<Integer> curr,List<List<Integer>> res){
	   for(int i=start;i<nums.length;i++){
		   if(i!=start&&nums[i]==nums[i-1])
				   continue;
		   curr.add(nums[i]);
		   res.add(new ArrayList<>(curr));
		   helpsubsetsWithdup(nums, i+1, curr, res);
		   curr.remove(curr.size()-1);
	   }
   }
   /**
    * Given an encoded message containing digits, 
    * determine the total number of ways to decode it.
    * @param s
    * @return
    */
   public int numDecodings(String s) {
	   if(s.length()==0)
		   return 0;
       int n=s.length();
       int[] dp=new int[n+1];
       dp[0]=1;
       dp[1]=(s.charAt(0)!='0')?1:0;
       for(int i=2;i<=n;i++){
    	   if(s.charAt(i-1)!='0')
    		   dp[i]=dp[i-1]; 
    	   int digitnow=s.charAt(i-1)-'0';
    	   int digitpre=s.charAt(i-2)-'0';
    	   int twodigits=digitnow+digitpre*10;
    	   if(twodigits>=10&&twodigits<=26)
    		   dp[i]+=dp[i-2];
    	   }
       return dp[n];
   }
   /**
    * Gas Station
    * @param gas
    * @param cost
    * @return
    */
   public int canCompleteCircuit(int[] gas, int[] cost) {
       int res=0,sum=0,total=0;
       for(int i=0;i<gas.length;i++){
    	   sum+=gas[i]-cost[i];
    	   if(sum<0){
    		   res=i+1;
    		   sum=0;
    	   }
    	   total+=gas[i]-cost[i];
       }
       return total>=0?res:-1;
   }
   public int singleNumber(int[] nums) {
	   int res=0;
       for(int i=0;i<nums.length;i++)
    	   res^=nums[i];
       return res;
   }
   public static int singleNumberII(int[] nums) {
       int[] bitcount=new int[32];
       for(int i=0;i<32;i++)
    	   for(int j=0;j<nums.length;j++)
    		   bitcount[i]+=((nums[j]>>i)&1);
       int res=0;
       for(int i=0;i<32;i++)
    	   res+=((bitcount[i]%3)<<i);
       return res;
   }
   public static int[] singleNumberIII(int[] nums) {
       int[] res=new int[2];
       int xor=0;
       for(int num:nums)
    	   xor^=num;
       int onebit=xor-(xor&(xor-1));
       for(int num:nums){
    	   if((num&onebit)==0)
    		   res[0]^=num;
    	   else  res[1]^=num;   	   
       }
       return res;
   }
   /**
    * Write an algorithm to determine if a number is "happy".
    * @param n
    * @return
    */
   public static boolean isHappy(int n) {
       HashSet<Integer> set=new HashSet<>();
       while(!set.contains(n)){
           set.add(n);
           System.out.println(n);
           if(n==1)
               return true;
           n=getSquare(n);
       }
       return false;
   }
   public static int getSquare(int n){
       int sum=0;
       while(n!=0){
           int left=n%10;
           sum+=(left*left);
           n/=10;
       }
       return sum;
   }
   /**
    * 小于n的所有质数个数
    * @param n
    * @return
    */
   public static int countPrimes(int n) {
	   if(n<=1)
           return 0;
       int sqrn=(int) Math.sqrt(n);
       boolean[] flag=new boolean[n-1];
       Arrays.fill(flag,true);
       flag[0]=false;
       for(int i=2;i<=sqrn;i++){
    	   for(int j=i*i;j<n;j+=i)
    		   flag[j-1]=false;
       }
       int res=0;
       for(boolean f:flag)
    	   if(f==true)
    		   res++;
       return res;
   }
   /**
    * 判断num是不是丑数
    * Ugly numbers are positive numbers 
    * whose prime factors only include 2, 3, 5
    * @param num
    * @return
    */
   public boolean isUgly(int num) {
       while(num>=2){
           if(num%2==0)
               num/=2;
            else if(num%3==0)
               num/=3;
            else if(num%5==0)
               num/=5;
           else return false;
       }
       return num==1;
   }
   /**
    * 第n个丑数
    * @param n
    * @return
    */
   public static int nthUglyNumber(int n) {
	  List<Integer> list=new ArrayList<>();
	  list.add(1);
       int i2=0,i3=0,i5=0;
       int minnum=Integer.MAX_VALUE;
       while(list.size()<n){
    	   int num1=list.get(i2)*2;
    	   int num2=list.get(i3)*3;
    	   int num3=list.get(i5)*5;
    	   minnum=Math.min(num1, Math.min(num2, num3));
    	   if(minnum==num1)
    		   i2++;
    	   if(minnum==num2)
    		   i3++;
    	   if(minnum==num3)
    		   i5++;
    	   list.add(minnum);
       }
       return list.get(list.size()-1);
   }
   /**
    * 找到数组中第k小的元素
    * @param nums
    * @param k
    * @return
    */
   public static int findkthMin(int[] nums,int k){
	   if(k>nums.length||k<=0)
		   throw new RuntimeException();
	   return findkthMin(nums, k, 0, nums.length-1);   
   }
   public static int findkthMin(int[] nums,int k,int p,int r){
	   int q=partition(nums, p, r);
	   int realk=q-p+1;
	   if(k==realk)
		   return nums[q];
	   else if(k<realk)
		   return findkthMin(nums, k, p, q-1);
	   else return findkthMin(nums, k-realk, q+1, r);
   }
   public static int partition(int[] nums,int p,int r){
	     int x=nums[r];
	     int i=p-1;
	     for(int j=p;j<r;j++){
	    	 if(nums[j]<=x){
	    		 i++;
	    		 exchange(nums, i, j);
	    	 }
	     }
	     exchange(nums, i+1, r);
	     return i+1;
   }
   public static List<List<Integer>> combinationSum3(int k, int n) {
       List<List<Integer>> res=new ArrayList<>();
       if(n<=0||k<=0)
           return res;
       List<Integer> curr=new ArrayList<>();
       help(res,curr,n,k,1);
       return res;
   }
   public static void help( List<List<Integer>> res,List<Integer> curr,int target,int k,int start){
       if(curr.size()==k&&target==0){
           res.add(new ArrayList<>(curr));
           return;
       }
       if(target<0)
           return;
       for(int i=start;i<=9;i++){
           curr.add(i);
           help(res,curr,target-i,k,i+1);
           curr.remove(curr.size()-1);
       }
   }
   /**
    * 数组中出现次数超过1/3的元素
    * @param nums
    * @return
    */
   public static List<Integer> majorityElement(int[] nums) {
       int count1=0,count2=0;
       Integer can1=null,can2=null;
       for(int num:nums){
           if(can1!=null&&num==can1)
               count1++;
           else if(can2!=null&&num==can2)
               count2++;
           else if(count1==0){
        	   count1=1;
        	   can1=num;
           }
           else if(count2==0){
        	   count2=1;
        	   can2=num;
           }
           else{
        	   count1--;
        	   count2--;
           }
       }
       count1=0;
       count2=0;
       for(int num:nums){
    	   if(num==can1)
    		   count1++;
    	   else if(num==can2)
    		   count2++;
       }
       List<Integer> res=new ArrayList<>();
       if(count1>nums.length/3)
    	   res.add(can1);
       if(count2>nums.length/3)
    	   res.add(can2);
       return res;
   }
   /**
    * 判断一个数是否为2的指数幂
    * @param n
    * @return
    */
   public boolean isPowerOfTwo(int n) {
      return (n>0)&&((n&(n-1))==0);
   }
   static class Position{
	   int x;
	   int y;
	   public Position(int x,int y){
		   this.x=x;
		   this.y=y;
	   }
   }
   /**
    * Knight Probability in Chessboard
    * @param N
    * @param K
    * @param r
    * @param c
    * @return
    */
   public static double knightProbability(int N, int K, int r, int c) {
       if(r<0||r>=N||c<0||c>=N)
    	   return 0;
       double[][][] dp=new double[K+1][N][N];
       dp[0][r][c]=1;
       int[] dx={1,1,2,2,-1,-1,-2,-2};
       int[] dy={2,-2,1,-1,2,-2,1,-1};
       for(int k=1;k<=K;k++){
    	   for(int i=0;i<N;i++){
    		   for(int j=0;j<N;j++){
    			   for(int p=0;p<8;p++){
    				   int x=i+dx[p];
    				   int y=j+dy[p];
    				   if(x>=0&&x<N&&y>=0&&y<N)
    					   dp[k][i][j]+=dp[k-1][x][y]/8.0;
    			   }
    		   }
    	   }
       }
       double res=0;
       for(int i=0;i<N;i++)
    	   for(int j=0;j<N;j++)
    		   res+=dp[K][i][j];
       return res;
   }
   /**
    * Given an array nums containing n + 1 integers 
    * where each integer is between 1 and n (inclusive), 
    * prove that at least one duplicate number must exist.
    * Assume that there is only one duplicate number, find the duplicate one.
    * @param nums
    * @return
    */
   public int findDuplicate(int[] nums) {
       int low=1,high=nums.length;
       while(low<high){
           int mid=low+(high-low)/2;
           int count=0;
           for(int num:nums)
               if(num<=mid)
                   count++;
           if(count>mid)
               high=mid;
           else low=mid+1;
       }
       return low;
   }
   /**
    * buy and sell stock II
    * @param prices
    * @return
    */
   public static int maxProfitII(int[] prices) {
       int sum=0;
       for(int i=1;i<prices.length;i++){
    	   if(prices[i]>prices[i-1])
    		   sum+=(prices[i]-prices[i-1]);
       }
       return sum;
   }
   /**
    * buy and sell stock III
    * @param args
    */
   public static int maxProfitIII(int[] prices) {
       if(prices.length==0)
           return 0;
       int n=prices.length;
       int[] left=new int[n];
       int minprice=prices[0];
       for(int i=1;i<n;i++){
           left[i]=Math.max(left[i-1],prices[i]-minprice);
           minprice=Math.min(minprice,prices[i]);
       }
       int[] right=new int[n];
       int maxprice=prices[n-1];
       for(int i=n-2;i>=0;i--){
           right[i]=Math.max(right[i+1],maxprice-prices[i]);
           maxprice=Math.max(maxprice,prices[i]);
       }
       int maxprofit=0;
       for(int i=0;i<n;i++)
           maxprofit=Math.max(maxprofit,left[i]+right[i]);
       return maxprofit;
   }
   /**
    * Burst Balloons
    * @param nums
    * @return
    */  
   public int maxCoins(int[] nums) {
        int n=nums.length;
        int[] numbers=new int[n+2];
        System.arraycopy(nums,0,numbers,1,n);
        numbers[0]=numbers[n+1]=1;
        int[][] dp=new int[n+2][n+2];
        for(int i=1;i<=n;i++)
            dp[i][i]=numbers[i-1]*numbers[i]*numbers[i+1];
        for(int len=1;len<n;len++){
            for(int i=1;i<=n-len;i++){
                int j=i+len;
                for(int k=i;k<=j;k++)
                    dp[i][j]=Math.max(dp[i][j],dp[i][k-1]+dp[k+1][j]+numbers[i-1]*numbers[k]*numbers[j+1]);   
            }
        }
        return dp[1][n];
    }
   /**
    * Super Ugly Number
    * @param n
    * @param primes
    * @return
    */
   public static int nthSuperUglyNumber(int n, int[] primes) {
       int k=primes.length;
       int[] index=new int[k];
       List<Integer> l=new ArrayList<>();
       l.add(1);
       int[] nums=new int[k];
       while(l.size()<n){
    	   int minnum=Integer.MAX_VALUE;
           for(int i=0;i<k;i++){
               nums[i]=l.get(index[i])*primes[i];
               if(nums[i]<minnum){
                   minnum=nums[i];
               }
           }
           l.add(minnum);
           for(int i=0;i<k;i++)
        	   if(nums[i]==minnum)
        		   index[i]++;
       }
       return l.get(n-1);
   }
   class TreeNode{
	   TreeNode left;
	   TreeNode right;
	   int count=1;
	   int val;
	   public TreeNode(int val){
		   this.val=val;
	   }
   }
   /**
    * Count of Smaller Numbers After Self
    * 二叉搜索树版本
    * @param nums
    * @return
    */
   public List<Integer> countSmaller(int[] nums) {
       int n=nums.length;
       List<Integer> res=new ArrayList<>();
       if(nums.length==0)
    	   return res;
       TreeNode root=new TreeNode(nums[nums.length-1]);
       res.add(0);
       for(int i=nums.length-2;i>=0;i--)
    	   res.add(BSTinsert(root, nums[i]));
       Collections.reverse(res);
       return res;
   }
   private int BSTinsert(TreeNode root,int val){
	   int thisCount=0;
	   while(true){
		   if(val<=root.val){
			   root.count++;
			   if(root.left!=null)
				   root=root.left;
			   else{
				   root.left=new TreeNode(val);
				   break;
			   }
		   }
		   else{
			   thisCount+=root.count;
			   if(root.right!=null)
				   root=root.right;
			   else{
				   root.right=new TreeNode(val);
				   break;
			   }
		   }
	   }
	  return thisCount;
   }
   int starti,startj;
   /**
    * 不同岛屿的个数
    * @param grid
    * @return
    */
   public int numDistinctIslands(int[][] grid) {
      int m=grid.length;
      if(m==0)
          return 0;
      int n=grid[0].length;
      if(n==0)
          return 0;
       Set<String> set=new HashSet<>();
        for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
              if(grid[i][j]==1){
                  starti=i;
                  startj=j; 
                  StringBuilder sb=new StringBuilder();
                  dfs(grid,i,j,sb);
                  if(!set.contains(sb.toString()))
                      set.add(sb.toString());
              }
          }
        }
       return set.size();
   }
   public void dfs(int[][] grid,int i,int j,StringBuilder sb){
       if(i<0||i>=grid.length||j<0||j>=grid[0].length)
          return;
       if(grid[i][j]==1){
           grid[i][j]=0;
           sb.append("("+(i-starti)+","+(j-startj)+")");
           dfs(grid,i-1,j,sb);
           dfs(grid,i+1,j,sb);
           dfs(grid,i,j-1,sb);
           dfs(grid,i,j+1,sb);
       }
   }
   /**
    * You are given coins of different denominations 
    * and a total amount of money amount. 
    * Write a function to compute the fewest number of coins 
    * that you need to make up that amount. 
    * If that amount of money cannot be made up 
    * by any combination of the coins, return -1.
    * @param coins
    * @param amount
    * @return
    */
   public int coinChange(int[] coins, int amount) {
       int max=amount+1;
       int[] dp=new int[amount+1];
       Arrays.fill(dp,max);
       dp[0]=0;
       for(int i=1;i<=amount;i++){
           for(int j=0;j<=coins.length-1;j++){
               if(coins[j]<=i)
                   dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
           }
       }
       return dp[amount]>amount?-1:dp[amount];
   }
   public static boolean increasingTriplet(int[] nums) {
       int n=nums.length;
       if(n<3)
           return false;
       int small=nums[0],big=Integer.MAX_VALUE;
       for(int i=1;i<n;i++){
           if(nums[i]>big)
               return true;
           else if(nums[i]<=small){
               small=nums[i];
           }
           else{
               big=nums[i];
           }    
       }
       return false;
   }
   /**
    * power of four
    * @param num
    * @return
    */
   public static boolean isPowerOfFour(int num) {
       for(int i=0;i<32;i+=2){
           if((1<<i)==num)
               return true;
       }
       return false;
   }
   /**
    * Best Time to Buy and Sell Stock with Transaction Fee
    * @param prices
    * @param fee
    * @return
    */
   public int maxProfit(int[] prices, int fee) {
       int n=prices.length;
       int[] hold=new int[n+1];
       int[] unhold=new int[n+1];
       hold[0]=Integer.MIN_VALUE;
       for(int i=1;i<=n;i++){
           hold[i]=Math.max(hold[i-1],unhold[i-1]-prices[i-1]-fee);
           unhold[i]=Math.max(unhold[i-1],hold[i-1]+prices[i-1]);
       }
       return unhold[n];
   }
   public static int binary_search(int[] row,int R,int x){
	    int L = 0;
	    while (L < R){
	        int mid = (L + R) >> 1;
	        if(row[mid] <= x) L = mid + 1;
	        else R = mid;
	    }
	    return L;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   int[] nums={1,3,3,9};
	   System.out.println(binary_search(nums, 4, 2));
	}

}
