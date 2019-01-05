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
     * 最长非重复子串的长度
     *
     * @param s 给定字符串
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int left = 0, right = 0;
        int maxLen = 0;
        HashSet<Character> set = new HashSet<>();
        int n = s.length();
        while (left < n && right < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                maxLen = Math.max(maxLen, right - left);
            } else {
                set.remove(s.charAt(left));
                left++;
            }

        }
        return maxLen;
    }

    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R <= s.length() - 1 && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 字符串转整数，注意溢出、开头的正负号、去除其他非数字字符等细节
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.isEmpty())
            return 0;
        String s = str.trim(); //去除首尾空格
        long res = 0; //定义为长整型防止溢出
        int i = 0, sign = 1;
        if (s.charAt(0) == '+')
            i++;
        else if (s.charAt(0) == '-') {
            sign = -1;
            i++;
        }
        while (i < s.length()) {
            if (res > Integer.MAX_VALUE)
                break;
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                res = 10 * res + (s.charAt(i) - '0');
            else break;
            i++;
        }
        res *= sign;
        if (res > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if (res < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int) res;
    }

    /**
     * 字符串数组的最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE; // 所有字符串的最短长度
        for (int i = 0; i < strs.length; i++)
            minLen = Math.min(minLen, strs[i].length());
        int i = 0;
        for (; i < minLen; i++) {
            char c = strs[0].charAt(i);
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    flag = false;
                    break;
                }
            }
            if (flag == false)
                break;
        }
        return strs[0].substring(0, i);
    }

    /**
     * 两个字符串表示的数字相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int n1 = num1.length(), n2 = num2.length();
        int n = n1 + n2;
        int[] nums = new int[n];
        StringBuilder sb1 = new StringBuilder(num1).reverse();
        StringBuilder sb2 = new StringBuilder(num2).reverse();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n1; i++)
            for (int j = 0; j < n2; j++)
                nums[i + j] += (sb1.charAt(i) - '0') * (sb2.charAt(j) - '0');
        for (int i = 0; i < n; i++) {
            res.append(nums[i] % 10);
            if (i < nums.length - 1)
                nums[i + 1] += nums[i] / 10;
        }
        if (nums[n - 1] == 0)
            res.deleteCharAt(res.length() - 1);
        return res.reverse().toString();
    }

    /**
     * 判断括号序列是否合法
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.push(s.charAt(i));
            else if (!stack.isEmpty() && ismatch(s.charAt(i), stack.peek()))
                stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }

    public boolean ismatch(char c1, char c2) {
        return ((c1 == ')' && c2 == '(') || (c1 == ']' && c2 == '[') || (c1 == '}' && c2 == '{'));
    }

    /**
     * Returns the index of the first occurrence of str2 in str1,
     * or -1 if str2 is not part of str1.
     *
     * @param str1
     * @param str2
     * @return
     */
    public int strStr(String str1, String str2) {
        if (str1 == null || str2 == null)
            return -1;
        if (str2.isEmpty())
            return 0;
        for (int i = 0; i <= str1.length() - str2.length(); i++) {
            if (str1.charAt(i) != str2.charAt(0))
                continue;
            else {
                int j = 1;
                for (; j < str2.length(); j++) {
                    if (str1.charAt(i + j) != str2.charAt(j))
                        break;
                }
                if (j == str2.length())
                    return i;
            }
        }
        return -1;
    }

    /**
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
     * return the length of last word in the string.
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int j = s.length() - 1;
        while (j >= 0 && s.charAt(j) == ' ')
            j--;
        int i = j;
        while (i >= 0 && s.charAt(i) != ' ')
            i--;
        return j - i;
    }

    /**
     * Given two binary strings, return their sum (also a binary string).
     * For example,a = "11" b = "1" Return "100".
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int sum = 0, i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int num1 = (i >= 0) ? a.charAt(i) - '0' : 0;
            int num2 = (j >= 0) ? b.charAt(j) - '0' : 0;
            sum += num1 + num2;
            sb.append((sum & 1));
            sum >>= 1;
            i--;
            j--;
        }
        if (sum == 1)
            sb.append(1);
        return sb.reverse().toString();
    }

    /**
     * Given a string S and a string T,
     * count the number of distinct subsequences of S which equals T.
     * Here is an example:S = "rabbbit", T = "rabbit"
     * Return 3.
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;
        for (int j = 1; j < n; j++)
            dp[0][j] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[m][n];
    }

    /**
     * 将字符串划分为尽可能多的区域
     * 每个字母至多出现在其中一个区域内
     *
     * @param str
     * @return
     */
    public List<Integer> partitionLabels(String str) {
        List<Integer> res = new ArrayList<>();
        if (str == null || str.length() == 0)
            return res;
        int n = str.length();
        int[] lastIndex = new int[26]; // 保存每个字母的最后出现的位置
        for (int i = 0; i < n; i++)
            lastIndex[str.charAt(i) - 'a'] = i;
        int left = -1, right = 0;
        for (int j = 0; j < n; j++) {
            right = Math.max(right, lastIndex[str.charAt(j) - 'a']);
            if (right == j) {
                res.add(right - left);
                left = right;
            }
        }
        return res;
    }

    /**
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str.length() == 0)
            return res;
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[str.length()];
        dfs(res, str, sb, visited);
        return res;
    }

    private void dfs(ArrayList<String> res, String str, StringBuilder sb, boolean[] visited) {
        if (sb.length() == str.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!visited[i]) {
                if (i > 0 && !visited[i - 1] && str.charAt(i) == str.charAt(i - 1))
                    continue;
                visited[i] = true;
                sb.append(str.charAt(i));
                dfs(res, str, sb, visited);
                visited[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PartString obj = new PartString();
        System.out.println(obj.Permutation("ab").size());
    }

}
