package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.xml.soap.Node;

public class PartString {
	/**
	 * 字符串编辑距离，动态规划求解
	 * @param s1
	 * @param s2
	 * @return
	 */
	 public static int distance(String s1,String s2){
	    	int len1=s1.length();
	    	int len2=s2.length();
	    	int[][] c=new int[len1+1][len2+1];
	    	for(int i=0;i<len1;i++)
	    		c[i][len2]=len1-i;
	    	for(int j=0;j<len2;j++)
	    		c[len1][j]=len2-j;
	    	c[len1][len2]=0;
	    	for(int i=len1-1;i>=0;i--){
	    		for(int j=len2-1;j>=0;j--){
	    			if(s1.charAt(i)==s2.charAt(j))
	    				c[i][j]=c[i+1][j+1];
	    			else
	    				c[i][j]=Math.min(Math.min(c[i+1][j], c[i][j+1]), c[i+1][j+1])+1;
	    		}
	    	}
	    	return c[0][0];
	    }

	/**
	 *  Longest Palindromic Substring dp解法
	 * @param s
	 * @return
	 */
	 public static String longestPalindromedp(String s) {
		 if(s == null || s.length() == 0)
			 return "";
		 int n = s.length();
		 int maxPalinLen = 0, left = 0, right = 0;
		 boolean dp[][] = new boolean[n][n];
		 for(int i=0; i < n; i++)
		 	dp[i][i] = true;
		 for(int i=0; i < n-1; i++) {
			 dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
			 if(dp[i][i+1]){
			 	left = i;
			 	right = i+1;
			 	maxPalinLen = 2;
			 }

		 }
		 for(int r=2; r<n; r++){
			for(int i=0; i < n-r; i++){
				dp[i][i+r] = dp[i+1][i+r-1] && (s.charAt(i) == s.charAt(i + r));
				if(dp[i][i+r] && r+1 > maxPalinLen){
					maxPalinLen = r+1;
					left = i;
					right = i+r;
				}

			}
		 }
		 return s.substring(left, right+1);
	 }
	 public static String moveStr(String str){
		 int n=str.length();
		 char[] c=new char[n];
		 int j=n-1;
		 for(int i=n-1;i>=0;i--){
			 if(str.charAt(i)!='#'){
				 c[j--]=str.charAt(i);
			 }
		 }
		 for(;j>=0;j--)
			 c[j]='#';
		 return String.valueOf(c);
	 }
     public static String reverseString(String s) {
    	 if(s==null)
    		 return null;
    	 char[] c=s.toCharArray();
         for(int i=0,j=s.length()-1;i<j;i++,j--){
        	 char temp=c[i];
        	 c[i]=c[j];
        	 c[j]=temp;
         }
         return new String(c);
      }
     public static String reverseVowels(String s) {
    	 if(s==null)
    		 return null;
         char[] c=s.toCharArray();
         int i=0,j=c.length-1;
         while(i<j){
        	 while(!isVoewl(c[i])&&i<s.length()-1)
        		 i++;
        	 while(!isVoewl(c[j])&&j>0)
        		 j--;
        	 if(i<j){
        		 char temp=c[i];
            	 c[i]=c[j];
            	 c[j]=temp;
            	 i++;
            	 j--;
        	 }
         }
         return new String(c);
     }
     public static boolean isVoewl(char c){
    	 return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
    			 c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
     }
     /**
      * 两个字符串表示的数字相乘
      * @param num1
      * @param num2
      * @return
      */
	 public static String multiply(String num1, String num2) {
		    if(num1.equals("0")||num2.equals("0"))
			 return "0";
	        StringBuilder s1=new StringBuilder(num1).reverse();
	        StringBuilder s2=new StringBuilder(num2).reverse();
	        int[] d=new int[s1.length()+s2.length()];
	        for(int i=0;i<s1.length();i++){
	        	for(int j=0;j<s2.length();j++)
	        		d[i+j]+=(s1.charAt(i)-'0')*(s2.charAt(j)-'0');
	        }
	        StringBuilder res=new StringBuilder();
	        for(int i=0;i<d.length;i++){
	        	res.insert(0, d[i]%10);
	        	int carry=d[i]/10;
	        	if(i<d.length-1)
	        	d[i+1]+=carry;
	        }
	        if(res.length()>0&&res.charAt(0)=='0')
	        	res.deleteCharAt(0);
	        return res.length()==0?"0":res.toString();
	    }
	 /**
	  * 最长回文子串
	  * @param s
	  * @return
	  */
	 public static String longestPalindrome(String s) {
		    if(s==null||s.length()==0)
		    	return null;
		    int n=s.length();
	        Boolean[][] c=new Boolean[n][n];
	        int start=0,end=0,maxLen=0;
	        for(int i=0;i<n;i++){
	        	c[i][i]=true;
	        }
	        for(int i=0;i<n-1;i++){
	        	c[i][i+1]=(s.charAt(i)==s.charAt(i+1));
	        	if(c[i][i+1]==true){
	        		start=i;
	        		end=i+1;
	        		maxLen=2;
	        	}
	        }
	       
	        for(int l=2;l<n;l++){
	        	for(int i=0;i<n-l;i++){
	        		c[i][i+l]=c[i+1][i+l-1]&&(s.charAt(i)==s.charAt(i+l));
	        		if(c[i][i+l]==true&&l+1>maxLen){
	        			start=i;
	        			end=i+l;
	        			maxLen=l+1;
	        		}
	        	}
	        }
	        return s.substring(start, end+1);
	    }
	 /**
	  * 最长回文子串
	  * @param s
	  * @return
	  */
	 public String longestPalindrome1(String s) {
		 int start=0,end=0;
		 for(int i=0;i<s.length();i++){
			 int len1=expandAroundCenter(s, i, i);
			 int len2=expandAroundCenter(s, i, i+1);
			 int len=Math.max(len1, len2);
			 if(len>end-start){
			 start=i-(len-1)/2;
			 end=i+len/2;
			 }
		 }
		 return s.substring(start, end+1);
	 }
	 public int expandAroundCenter(String s,int left,int right){
		 int L=left,R=right;
		 while(L>=0&&R<=s.length()-1&&s.charAt(L)==s.charAt(R)){
			 L--;
			 R++;
		 }
			return R-L-1; 
	 }
	 /**
	  * 判断括号序列是否合法
	  * @param s
	  * @return
	  */
	 public static boolean isValid(String s) {
		    if(s==null||s.isEmpty())
			   return true;
	        Stack<Character> stack=new Stack<>();
	        char[] c=s.toCharArray();
	        for(int i=0;i<c.length;i++){
	        	if(c[i]=='['||c[i]=='{'||c[i]=='(')
	        		stack.push(c[i]);
	        	else if(!stack.isEmpty()&&ismatch(stack.peek(),c[i]))
	        		stack.pop();
	        	else return false;
	        }
	        return stack.isEmpty();
	  }
	 public static boolean ismatch(char c1,char c2){
		 return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}')
		            || (c1 == '[' && c2 == ']');

	 }
	 /**
	  * 生成合法的括号方案
	  * @param n
	  * @return
	  */
	 public static List<String> generateParenthesis(int n) {
		 List<String> res=new ArrayList<>();
		 helpGenerate(n, n, res, "");
		 return res;
	  }
	 public static void helpGenerate(int left,int right,List<String> res,String str){
		 if(left==0&&right==0)
			 res.add(str);
		 if(left>0)
			 helpGenerate(left-1, right, res, str+"(");
		 if(right>0&&left<right)
			 helpGenerate(left, right-1, res, str+")");
	 }
	 /**
	  * Returns the index of the first occurrence of substr in str, 
	  * or -1 if substr is not part of str.
	  * @param str
	  * @param substr
	  * @return
	  */
	public static int strStr(String str, String substr) {
		   if(substr.isEmpty())
			   return 0;
	       if(str==null||str.isEmpty()||substr==null)
	    	   return -1;
	       for(int i=0;i<str.length()-substr.length()+1;i++){
	    	   if(str.charAt(i)!=substr.charAt(0))
                   continue;
	    	   int j=1;
	    	   for(;j<substr.length();j++){
	    		   if(substr.charAt(j)!=str.charAt(i+j))
	    			   break;
	    	   }
	    	   if(j==substr.length())
	    		   return i;
	       }
	       return -1;
	    }
	/**
	 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
	 * return the length of last word in the string.
	 * @param s
	 * @return
	 */
	 public static int lengthOfLastWord(String s) {
		   if(s==null||s.isEmpty())
			   return 0;
	       int end=s.length()-1;
	       while(end>=0&&s.charAt(end)==' ')
	    	   end--;
	       if(end==-1)
	    	   return 0;
	       int start=end;
	       while(start>=0&&s.charAt(start)!=' ')
	    	   start--;
	       return end-start;
	   }
	 /**
	  * Given two binary strings, return their sum (also a binary string).
	  * For example,a = "11" b = "1" Return "100".
	  * @param a
	  * @param b
	  * @return
	  */
	 public static String addBinary(String a, String b) {
		    String ans = "";
	        int carry = 0;
	        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
	            int sum = carry;
	            sum += (i >= 0) ? a.charAt(i) - '0' : 0;
	            sum += (j >= 0) ? b.charAt(j) - '0' : 0;
	            ans = (sum&1) + ans;
	            carry = sum >> 1;
	        }
	        if (carry != 0) {
	            ans = carry + ans;
	        }
	        return ans;
	    }
	 /**
	  * Given a string S and a string T, find the minimum window in S 
	  * which will contain all the characters in T in complexity O(n).
	  * For example,S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
	  * @param s
	  * @param t
	  * @return
	  */
	 public static String minWindow(String s, String t) {
	        int[] tarHash=new int[256];
	        for(int i=0;i<t.length();i++)
	        	tarHash[t.charAt(i)]++;
	        int begin=-1,end=s.length();
	        int start=0,found=0,minLength=Integer.MAX_VALUE;
	        int[] srcHash=new int[256];
	        for(int i=0;i<s.length();i++){
	        	srcHash[s.charAt(i)]++;
	        	if(srcHash[s.charAt(i)]<=tarHash[s.charAt(i)])
	        		found++;
	        	if(found==t.length()){
	        		while(start<i&&srcHash[s.charAt(start)]>tarHash[s.charAt(start)])
	        		{
	        			srcHash[s.charAt(start)]--;
	        			start++;
	        		}
	        		if(i-start<minLength){
	        			begin=start;
	        			end=i;
	        			minLength=i-start;
	        		}
	        		srcHash[s.charAt(start)]--;
	        		start++;
	        		found--;	
	        	}
	        }
	        return begin==-1?"":s.substring(begin, end+1);
	    }
	 /**
	  * 判断字符串是否回文，只考虑数字和字母
	  * @param s
	  * @return
	  */
    public static boolean isPalindrome(String s) {
	        if(s==null||s.isEmpty())
	        	return true;
	        int i=0,j=s.length()-1;
	        s=s.toLowerCase();
	        for(;i<j;i++,j--){
	        	while(i<j&&!isalphanumeric(s.charAt(i)))
	        		i++;
	        	while(i<j&&!isalphanumeric(s.charAt(j)))
	        		j--;
	        	if(s.charAt(i)!=s.charAt(j))
	        		return false;
	        }
	        return true;
	    }
    private static boolean isalphanumeric(char c){
    	 return (c>='0'&&c<='9')||(c>='a'&&c<='z');
    }
    /**
     * Word Ladder
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    	 Set<String> dict = new HashSet<>();
         for (String word : wordList) {//一定要转化成hashset，否则会超时
             dict.add(word);
         }        
         if (beginWord.equals(endWord)) {
             return 1;
         }
        int res=1;
        Queue<String> queue=new LinkedList<>();
        HashSet<String> set=new HashSet<>();
        queue.add(beginWord);
        set.add(beginWord);
        while(!queue.isEmpty()){
        	res++;
        	int qsize=queue.size();
        	for(int i=0;i<qsize;i++){
        		String word=queue.poll();
        		for(String nextword:getnextword(word, dict, set)){
        			if(nextword.equals(endWord))
        				return res;
        			set.add(nextword);
        			queue.add(nextword);
        		}
        	}
        }
        return 0;
    }
    public static ArrayList<String> getnextword(String word,Set<String> dict,HashSet<String> set){
    	 ArrayList<String> res=new ArrayList<>();
    	 int len=word.length();
    	 for(int i=0;i<len;i++){
    		 for(char c='a';c<='z';c++){
    			 if(c==word.charAt(i))
    				 continue;
    			 String curword=replace(word, i, c);
    			 if(dict.contains(curword)&&!set.contains(curword))
    				 res.add(curword);
    		 }
    	 }
    	 return res;
    }
    private static String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
    /**
     * Given a string s, partition s such that every 
     * substring of the partition is a palindrome.
     * For example, given s = "aab", Return[["aa","b"],["a","a","b"]]
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
    	List<List<String>> res=new ArrayList<>();
    	if(s==null||s.isEmpty())
    		return res;
    	List<String> curr=new ArrayList<>();
    	dfspartition(res, curr, 0, s);
    	return res;
    }
    public static void dfspartition(List<List<String>> res,List<String> curr,int start,String s){
    	if(start==s.length())
    		res.add(new ArrayList<>(curr));
    	for(int i=start;i<s.length();i++){
    		if(isPalindrome(s,start,i)){
    			curr.add(s.substring(start, i+1));
    			dfspartition(res, curr, i+1, s);
    			curr.remove(curr.size()-1);
    		}
    	}
    }
    public static boolean isPalindrome(String s,int start,int end){
    	int i=start,j=end;
    	for(;i<j;i++,j--){
    		if(s.charAt(i)!=s.charAt(j))
    			return false;
    	}
    	return true;
    }
    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * @param s
     * @return
     */
    public static int minCut(String s) {
		  if(s==null||s.length()==0)
			   return 0;
		   int n=s.length();
	       int[] cuts=new int[n+1];
	       for(int i=0;i<=n;i++)
	    	   cuts[i]=n-i-1;
	       boolean[][] dp=new boolean[n][n];	   
	       for(int i=n-1;i>=0;i--){
	    	   for(int j=i;j<n;j++){
	    		   if((j-i<2&&s.charAt(i)==s.charAt(j))||(dp[i+1][j-1]==true&&s.charAt(i)==s.charAt(j))){
	    			   dp[i][j]=true;
	    			   cuts[i]=Math.min(cuts[i], cuts[j+1]+1);
	    		   }
	    	   }
	       }
	       return cuts[0];
	    }
    /**
     * Given a string S and a string T, 
     * count the number of distinct subsequences of S which equals T.
     * Here is an example:S = "rabbbit", T = "rabbit"
     * Return 3.
     * @param s
     * @param t
     * @return
     */
  public int numDistinct(String s, String t) {
        int m=s.length(),n=t.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<m;i++)
        	dp[i][0]=1;
        for(int j=1;j<n;j++)
        	dp[0][j]=0;
        for(int i=1;i<=m;i++){
        	for(int j=1;j<=n;j++){
        		if(s.charAt(i-1)==t.charAt(j-1))
        			dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
        		else dp[i][j]=dp[i-1][j];
        	}
        }
        return dp[m][n];
    }
  /**
   * Given a non-empty string s and a dictionary wordDict 
   * containing a list of non-empty words, 
   * determine if s can be segmented into a space-separated sequence 
   * of one or more dictionary words.
   * For example, givens = "leetcode",dict = ["leet", "code"].
   * Return true because "leetcode" can be segmented as "leet code". 
   */
  public static boolean wordBreak(String s, List<String> wordDict) {
      int n=s.length();
      boolean[] dp=new boolean[n+1];
      dp[0]=true;
      for(int i=1;i<=n;i++){
    	  for(int j=0;j<i;j++){
    		  if(dp[j]&&wordDict.contains(s.substring(j,i)))
    		    dp[i]=true;
    	  }
      }
      return dp[n];
  }
  /**
   * Reverse Words in a String
   * @param s
   * @return
   */
  public static String reverseWords(String s) {
      s=s.trim();
      if(s.isEmpty())
    	  return " ";
      int n=s.length();
      int nextblack=n;
      StringBuilder sb=new StringBuilder();
      int j=n-1;
      while(j>=0){
    	  if(s.charAt(j)==' '){
    		  sb.append(s.substring(j+1, nextblack));
    		  sb.append(" ");
    		  while(s.charAt(j-1)==' ')
    			  j--;  
    		  nextblack=j;
    	  }
    	  j--;
      }
      sb.append(s.substring(0,nextblack));
      return sb.toString();
  }
  /**
   * Given a list of non negative integers, 
   * arrange them such that they form the largest number.
   * For example, given [3, 30, 34, 5, 9], 
   * the largest formed number is 9534330.
   * @param nums
   * @return
   */
  public static String largestNumber(int[] nums) {
	  int n=nums.length;
	  if(n==0)
		  return "";
      String[] strs=new String[n];
      for(int i=0;i<n;i++)
    	  strs[i]=String.valueOf(nums[i]);
      Arrays.sort(strs,new Comparator<String>() {
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return (o2+o1).compareTo(o1+o2);
		}
	});
      StringBuilder sb=new StringBuilder();
      for(String s:strs)
    	  sb.append(s);
      while(sb.charAt(0)=='0'&&sb.length()>1)
          sb.deleteCharAt(0);
      return sb.toString();
  }
  /**
   * Given a non-empty string s, 
   * you may delete at most one character. 
   * Judge whether you can make it a palindrome.
   * @param s
   * @return
   */
  public boolean validPalindrome(String s) {
      if(s.isEmpty())
          return true;
       int n=s.length();
      if(isPalindrome(s,0,n-1))
          return true;
      int i=0,j=n-1;
      while(i<j){
          if(s.charAt(i)!=s.charAt(j)){
              if(isPalindrome(s,i+1,j)||isPalindrome(s,i,j-1))
                  return true;
              else return false;
          }
          i++;
          j--;
  }
      return true;
}
 /**
  * Given a string containing only three types of characters: '(', ')' and '*',
  *  write a function to check whether this string is valid.
  * @param s
  * @return
  */
  public static boolean checkValidString(String s) {
      return check(s, 0, 0);
  }
  private static boolean check(String s, int start, int count) {
      if (count < 0) return false;  
      for (int i = start; i < s.length(); i++) {
          char c = s.charAt(i);
          if (c == '(') {
              count++;
          }
          else if (c == ')') {
                count--;
              if (count < 0) return false;     
          }
          else if (c == '*') {
              return check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count);
          }
      }      
      return count == 0;
  }
  /**
   * 判断两个字符串能否一对一映射
   * @param s
   * @param t
   * @return
   */
  public boolean isIsomorphic(String s, String t) {
      HashMap<Character,Character> map=new HashMap<>();
      Set<Character> set=new HashSet<>();
      int n=s.length();
      for(int i=0;i<n;i++){
          char c1=s.charAt(i);
          char c2=t.charAt(i);
          if(!map.containsKey(c1)){
              if(set.contains(c2))
                  return false;
              map.put(c1,c2);
              set.add(c2);
          }
          else{
              if(map.get(c1)!=c2)
                  return false;
          }
      }
      return true;
  }
  //定义字典树结构
  class TrieNode{
	  String word;
	  final int R=26;
	  TrieNode[] links;
	  boolean isEnd=false;
	  public TrieNode(){
		  links=new TrieNode[R];
	  }
	  public TrieNode get(char c){
		  return links[c-'a'];
	  }
	  public void put(char c){
		  links[c-'a']=new TrieNode();
	  }
	  public boolean containsKey(char c){
		  return links[c-'a']!=null;
	  }
	  public boolean isEnd(){
		  return isEnd;
	  }
	  public void setEnd(){
		  isEnd=true;
	  }
  }
  /**
   *字典树插入字符串
   * @param root
   * @param s
   */
  public static void insert(TrieNode root,String s){
	  TrieNode node=root;
	  for(int i=0;i<s.length();i++){
		  char c=s.charAt(i);
		  if(!node.containsKey(c))
			  node.put(c);
		  node=node.get(c);
	  }
	  node.setEnd();
	  node.word=s;
  }
  /**
   * word search II
   * Given a 2D board and a list of words from the dictionary, 
   * find all words in the board.
   * @param board
   * @param words
   * @return
   */
    public List<String> findWords(char[][] board, String[] words) {
	    List<String> res=new ArrayList<>();
	    if(board.length==0||board[0].length==0)
	    	return res;
	    TrieNode root=new TrieNode();
	    for(String word:words)
	    	insert(root, word);
	    int m=board.length,n=board[0].length;
	    boolean[][] visited=new boolean[m][n];
	    for(int i=0;i<m;i++)
	    	for(int j=0;j<n;j++)
	    		helpfind(board,res,root,i,j,visited);
	    return res;
	}
  public static void helpfind(char[][] board,List<String> res,TrieNode root, int i, int j,boolean[][] visited){
	  if(root.isEnd){
          if(!res.contains(root.word))
		     res.add(root.word);
      }
	  int m=board.length,n=board[0].length;
	  if(i<0||i>=m||j<0||j>=n||visited[i][j]||!root.containsKey(board[i][j]))
		  return;
      char c=board[i][j];
	  visited[i][j]=true;
	  TrieNode node=root.get(c);
	  helpfind(board, res, node, i-1, j, visited);
	  helpfind(board, res, node, i+1, j, visited);
	  helpfind(board, res, node, i, j-1, visited);
	  helpfind(board, res, node, i, j+1, visited);
	  visited[i][j]=false;
  }
  /**
   * Given "aacecaaa", return "aaacecaaa".
   * Given "abcd", return "dcbabcd".
   * @param s
   * @return
   */
  public static String shortestPalindrome(String s) {
      int n=s.length();
      if(n==0)
          return "";
      StringBuilder sb=new StringBuilder();
      int j=n-1;
      for(;j>0;j--){
          if(isPalindrome(s,j))
              break;
      }
      String toadd=s.substring(j+1,n);
      sb=sb.append(toadd).reverse();
      return sb.append(s).toString();
  }
  public static boolean isPalindrome(String s,int end){
      int i=0,j=end;
       while(i<j){
           if(s.charAt(i)!=s.charAt(j)){
               return false;
           }
           i++;
           j--;
       }
      return true;
  }
  String res;
  int minInterval=Integer.MAX_VALUE;
  /**
   * Given a time represented in the format "HH:MM", 
   * form the next closest time by reusing the current digits.
   * There is no limit on how many times a digit can be reused.
   * @param time
   * @return
   */
  public String nextClosestTime(String time) {
      Set<Character> set=new HashSet<>();
      for(int i=0;i<time.length();i++){
          char c=time.charAt(i);
          if(c!=':')
              set.add(c);
      }
      getNext(set,time,"");
      return res;
  }
  public void getNext(Set<Character> set,String time,String curr){
      if(curr.length()==5){
          if(isValidTime(curr)&&timeInterval(time,curr)<minInterval){
              minInterval=timeInterval(time,curr);
              res=curr;
          }
          return;
      }
      if(curr.length()==2)
          curr+=":";
      for(Character c:set)
          getNext(set,time,curr+c);
                 
  }
  public int timeInterval(String t1,String t2){
      int m1=getMinute(t1);
      int m2=getMinute(t2);
      if(m1<m2)
          return m2-m1;
      else return 1440+m2-m1;
  }
  public int getMinute(String time){
      String str[]=time.split(":");
      return 60*Integer.valueOf(str[0])+Integer.valueOf(str[1]);
  }
  public boolean isValidTime(String time){
      String str[]=time.split(":");
      return Integer.valueOf(str[0])<24&&Integer.valueOf(str[1])<60;
  }
  /**
   * Different Ways to Add Parentheses
   * @param input
   * @return
   */
  public static List<Integer> diffWaysToCompute(String input) {
      int n=input.length();
      List<Integer> res=new ArrayList<>();
      for(int i=0;i<n;i++){
          char c=input.charAt(i);
          if(c=='+'||c=='-'||c=='*'){
              List<Integer> left=diffWaysToCompute(input.substring(0,i));
              List<Integer> right=diffWaysToCompute(input.substring(i+1,n));
              for(int l:left){
                  for(int r:right){
                	  if(c=='+')
                		  res.add(l+r);
                	  else if(c=='-')
                		  res.add(l-r);
                	  else 
                		  res.add(l*r);
                  }
              }
          }
      }
      if(res.size()==0){
          res.add(Integer.valueOf(input));
      }
      return res;
  }
  /**
   * Given two strings A and B, 
   * find the minimum number of times A has to be repeated such that
   * B is a substring of it. If no such solution, return -1.
   * For example, with A = "abcd" and B = "cdabcdab".
   * Return 3, because by repeating A three times (“abcdabcdabcd”), 
   * B is a substring of it;
   * @param A
   * @param B
   * @return
   */
  public int repeatedStringMatch(String A, String B) {
      StringBuilder sb=new StringBuilder();
      int res=0;
      while(sb.length()<B.length()){
          sb=sb.append(A);
          res++;
      }
      if(sb.toString().indexOf(B)!=-1)
          return res;
      sb=sb.append(A);
      res++;
       if(sb.toString().indexOf(B)!=-1)
          return res;
      return -1;
  }
  /**
   * Given a pattern and a string str,
   * find if str follows the same pattern.
   * @param pattern
   * @param str
   * @return
   */
  public static boolean wordPattern(String pattern, String str) {
      HashMap<Character,String> map=new HashMap<>();
      String[] strs=str.split(" ");
      Set<String> set=new HashSet<>();
      if(pattern.length()!=strs.length)
    	  return false;
      for(int i=0;i<pattern.length();i++){
    	  char c=pattern.charAt(i);
    	  if(!map.containsKey(c)){
    		  if(set.contains(strs[i]))
    			  return false;
    		  map.put(c, strs[i]);
    		  set.add(strs[i]);
    	  }
    	  else if(!map.get(c).equals(strs[i]))
    		  return false;
      }
      return true;
  } 
  public static List<String> removeInvalidParentheses(String s) {
      List<String> res=new ArrayList<>();
      helpremove(s,0,0,res,new char[]{'(',')'});
      return res;
  }
  private static void helpremove(String s,int last_i,int last_j,List<String> res,char[] par){
      for(int i=last_i,count=0;i<s.length();i++){
          if(s.charAt(i)==par[0])
              count++;
          else if(s.charAt(i)==par[1])
              count--;
          if(count<0){
              for(int j=last_j;j<=i;j++){
                  if(s.charAt(j)==par[1])
                      helpremove(s.substring(0,j)+s.substring(j+1,s.length()),i,j,res,par);
              }
              return;
          }              
      }
     String reversed=new StringBuilder(s).reverse().toString();
     if(par[0]=='(')
          helpremove(reversed,0,0,res,new char[]{')','('});
     else {
         if(!res.contains(reversed))
            res.add(reversed);
     }
  }
  public static String removeDuplicateLetters(String s) {
	  int[] count=new int[256];
      int n=s.length();
      for(int i=0;i<n;i++)
          count[s.charAt(i)-'a']++;
      Stack<Character> stack=new Stack<>();
      for(int i=0;i<n;i++){
          char c=s.charAt(i);
          count[c-'a']--;
          if(stack.contains(c))
              continue;
          while(!stack.isEmpty()&&c<stack.peek()&&count[stack.peek()-'a']>0)
             stack.pop();
          stack.push(c);
      }
      StringBuilder sb=new StringBuilder();
      while(!stack.isEmpty())
          sb.append(stack.pop());
      return sb.reverse().toString();  
  }
  /**
   * Given a list of unique words, 
   * find all pairs of distinct indices (i, j) in the given list, 
   * so that the concatenation of the two words, 
   * i.e. words[i] + words[j] is a palindrome.
   * @param words
   * @return
   */
  public static List<List<Integer>> palindromePairs(String[] words) {
      List<List<Integer>> res=new ArrayList<>();
      HashMap<String,Integer> map=new HashMap<>();
      int n=words.length;
      for(int i=0;i<n;i++)
          map.put(words[i],i);
      for(int i=0;i<n;i++){
          for(int j=0;j<=words[i].length();j++){
              String s1=words[i].substring(0,j);
              String s2=words[i].substring(j);
              if(isPalindrome(s1)){
                  String reverse=new StringBuilder(s2).reverse().toString();
                  if(map.containsKey(reverse)&&map.get(reverse)!=i){
                      List<Integer> curr=new ArrayList<>();
                      curr.add(map.get(reverse));
                      curr.add(i);
                      res.add(curr);
                  }
              }
               if(isPalindrome(s2)){
                  String reverse=new StringBuilder(s1).reverse().toString();
                  if(map.containsKey(reverse)&&map.get(reverse)!=i&&s2.length()!=0){
                      List<Integer> curr=new ArrayList<>();
                      curr.add(i);
                      curr.add(map.get(reverse));
                      res.add(curr);
                  }
              }
          }
      }
      return res;
  }
  /**
   * Given two strings s1, s2, 
   * find the lowest ASCII sum of deleted characters to make two strings equal.
   * @param s1
   * @param s2
   * @return
   */
  public int minimumDeleteSum(String s1, String s2) {
      int m=s1.length(),n=s2.length();
      int[][] dp=new int[m+1][n+1];
      for(int j=1;j<=n;j++)
          dp[0][j]=dp[0][j-1]+s2.charAt(j-1);
      for(int i=1;i<=m;i++){
          dp[i][0]=dp[i-1][0]+s1.charAt(i-1);
      }
      for(int i=1;i<=m;i++){
          for(int j=1;j<=n;j++){
              if(s1.charAt(i-1)==s2.charAt(j-1))
                  dp[i][j]=dp[i-1][j-1];
              else{
                  dp[i][j]=Math.min(dp[i-1][j]+s1.charAt(i-1),dp[i][j-1]+s2.charAt(j-1));
              }
          }
      }
      return dp[m][n];
  }
	public static void main(String[] args) {
//		String[] words={"","aba"};
//        List<List<Integer>> res=palindromePairs(words);
//        for(List<Integer> l:res)
//        	System.out.println(l);
		String s = longestPalindromedp("abacab");
		System.out.println(s);
	}

}
