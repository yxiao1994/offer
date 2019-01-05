package LeetCode;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class PartArray {
    private int m = 0, n = 0;
    private Object item;

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void exchange(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }

        }
        return i + 1;
    }

    public static int removeDuplicatesII(int[] nums) {
        if (nums.length == 0)
            return 0;
        int count = 1;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (count == 0 && nums[i] == nums[j]) {
                continue;
            } else {
                if (nums[i] == nums[j])
                    count = 0;
                else
                    count = 1;
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static int majorityElement(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int v : nums) {
            if (!map.containsKey(v))
                map.put(v, 1);
            else
                map.put(v, map.get(v) + 1);
        }
        for (int key : map.keySet())
            if (map.get(key) > nums.length / 2)
                res = key;
        return res;
    }

    public static int majorityElement1(int[] nums) {
        int candidate = 0, ntimes = 0;
        for (int number : nums) {
            if (ntimes == 0) {
                candidate = number;
                ntimes = 1;
            } else if (candidate != number)
                ntimes--;
            else ntimes++;
        }
        return candidate;
    }

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0)
            return 0;
        n = grid[0].length;
        if (n == 0)
            return 0;
        int numofIslands = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1') {
                    numofIslands++;
                    dfs(grid, i, j);

                }
        return numofIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return;
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }

    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        int n = nums.length;
        if (n == 1)
            return nums[0];
        int[] res = new int[n];
        res[0] = nums[0];
        res[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            res[i] = Math.max(res[i - 2] + nums[i], res[i - 1]);
        }
        return res[n - 1];
    }

    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int maxArea = 0, i = 0, j = height.length - 1;
        while (i < j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j])
                i++;
            else j--;
        }
        return maxArea;
    }

    /**
     * Search target in Rotated Sorted Array
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < nums[high]) {
                if (target > nums[mid] && target <= nums[high])
                    low = mid + 1;
                else high = mid - 1;
            } else {
                if (target >= nums[low] && target < nums[mid])
                    high = mid - 1;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Search target in Rotated Sorted Array(consider duplicates)
     *
     * @param nums
     * @param target
     * @return
     */
    public static boolean searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] < nums[high]) {
                if (target > nums[mid] && target <= nums[high])
                    low = mid + 1;
                else high = mid - 1;
            } else if (nums[mid] == nums[high]) {
                for (int i = low; i <= high; i++) {
                    if (nums[i] == target)
                        return true;
                }
                return false;
            } else {
                if (target >= nums[low] && target < nums[mid])
                    high = mid - 1;
                else low = mid + 1;
            }
        }
        return false;
    }

    /**
     * Given an array of integers sorted in ascending order,
     * find the starting and ending position of a given target value.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        int[] res = new int[2];
        int low = 0, high = nums.length - 1;
        int left = -1, right = -1;
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target)//注意这里是小于，不包含等于
                low = mid;
            else
                high = mid;
        }
        if (nums[low] == target)//先判断低索引，再判断高索引
            left = low;
        else if (nums[high] == target)
            left = high;
        low = 0;
        high = nums.length - 1;
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            if (nums[mid] <= target)//注意这里是小于等于
                low = mid;
            else
                high = mid;
        }
        if (nums[high] == target)//先判断高索引，再判断低索引
            right = high;
        else if (nums[low] == target)
            right = low;
        res[0] = left;
        res[1] = right;
        return res;
    }

    /**
     * Search Insert Position
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    /**
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return;
        int n = matrix[0].length;
        if (n == 0)
            return;
        for (int i = 0; i < m; i++) {//先转置
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < m; i++) {//对每一行reverse
            for (int left = 0, right = n - 1; left < right; left++, right--) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
            }
        }
    }

    /**
     * jump game
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int maxstep = 0;
        for (int i = 0; i < nums.length && i <= maxstep; i++) {
            maxstep = Math.max(maxstep, i + nums[i]);
            if (maxstep >= nums.length - 1)
                return true;
        }
        return false;
    }

    /**
     * Unique Paths
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;
        for (int j = 0; j < n; j++)
            dp[0][j] = 1;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[m - 1][n - 1];
    }

    /**
     * Unique Paths II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = (obstacleGrid[0][0] == 1) ? 0 : 1;
        for (int i = 1; i < m; i++)
            dp[i][0] = (dp[i - 1][0] == 1 && obstacleGrid[i][0] == 0) ? 1 : 0;
        for (int j = 1; j < n; j++)
            dp[0][j] = (dp[0][j - 1] == 1 && obstacleGrid[0][j] == 0) ? 1 : 0;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else dp[i][j] = 0;
            }
        return dp[m - 1][n - 1];
    }

    public static int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++)
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
     * Do it in constant space.
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        boolean rowflag = false, colflag = false;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colflag = true;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                rowflag = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowflag == true) {
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }
        if (colflag == true) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }

    /**
     * Search a 2D Matrix
     * [[1,   3,  5,  7],[10, 11, 16, 20],[23, 30, 34, 50]]
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target)
                low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    /**
     * search a 2D Matrix II
     * 该矩阵从左到右，从上到下升序排列
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target)
                row++;
            else col--;
        }
        return false;
    }

    /**
     * Given an array with n objects colored red, white or blue,
     * sort them so that objects of the same color are adjacent,
     * with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent
     * the color red, white, and blue respectively.
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int left = 0, right = nums.length - 1, i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                i++;
                left++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            } else i++;
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     *
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0)
            return false;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                visited[i][j] = false;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (helpexist(board, word, i, j, 0, visited))
                    return true;
        return false;
    }

    public static boolean helpexist(char[][] board, String word, int i, int j, int pos, boolean[][] visited) {
        if (pos == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(pos) || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        boolean res = helpexist(board, word, i + 1, j, pos + 1, visited) ||
                helpexist(board, word, i - 1, j, pos + 1, visited) ||
                helpexist(board, word, i, j + 1, pos + 1, visited) ||
                helpexist(board, word, i, j - 1, pos + 1, visited);
        visited[i][j] = false;
        return res;
    }

    /**
     * Given a list of non-negative numbers and a target integer k,
     * write a function to check if the array has a continuous subarray of size at least 2
     * that sums up to the multiple of k,
     * that is, sums up to n*k where n is also an integer.
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum %= k;
            if (map.containsKey(sum)) {
                int prev = map.get(sum);
                if (i - prev > 1)
                    return true;
            } else map.put(sum, i);
        }
        return false;
    }

    public static int longsetSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int length = 0;
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > length)
                    length = i - prev;
            } else map.put(runningSum, i);
        }
        return length;
    }

    /**
     * Given an array of integers and an integer k,
     * find the total number of continuous subarrays whose sum equals to k.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * Find the median of the two sorted arrays.
     * The overall run time complexity should be O(log (m+n)).
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int sumlength = m + n;
        if ((sumlength & 1) == 1)
            return findkth(nums1, 0, nums2, 0, (sumlength + 1) / 2);
        else return (findkth(nums1, 0, nums2, 0, sumlength >> 1) +
                findkth(nums1, 0, nums2, 0, (sumlength >> 1) + 1)) / 2;
    }

    public static double findkth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (start1 >= nums1.length)
            return nums2[start2 + k - 1];
        if (start2 >= nums2.length)
            return nums1[start1 + k - 1];
        if (k == 1)
            return Math.min(nums1[start1], nums2[start2]);
        int key1 = (start1 + k / 2 - 1 < nums1.length) ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int key2 = (start2 + k / 2 - 1 < nums2.length) ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;
        if (key1 < key2)
            return findkth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
        else
            return findkth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
    }

    /**
     * Given an unsorted array of integers,
     * find the length of longest increasing subsequence.
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int[] dp = new int[len];
        int maxLength = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    /**
     * 动态规划加二分搜索，时间复杂度n*log n
     *
     * @param nums
     * @return
     */
    public static int lengthOfLISII(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    /**
     * Given two sorted integer arrays nums1 and nums2,
     * merge nums2 into nums1 as one sorted array.
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r = m + n - 1, p = m - 1, q = n - 1;
        while (p >= 0 && q >= 0) {
            if (nums1[p] > nums2[q]) {
                nums1[r] = nums1[p];
                p--;
            } else {
                nums1[r] = nums2[q];
                q--;
            }
            r--;
        }
        while (p >= 0)
            nums1[r--] = nums1[p--];
        while (q >= 0)
            nums1[r--] = nums2[q--];
    }

    /**
     * 产生杨辉三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows <= 0) return res;
        List<Integer> one = new ArrayList<Integer>();
        one.add(1);
        res.add(one);
        for (int i = 1; i < numRows; i++) {
            List<Integer> line = new ArrayList<Integer>();
            // 加入第一个1
            line.add(1);
            // 加入中间的数
            for (int j = 1; j < i; j++) {
                line.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            // 加入最后一个1
            line.add(1);
            res.add(line);
        }
        return res;
    }

    /**
     * Given a triangle, find the minimum path sum from top to bottom.
     * Each step you may move to adjacent numbers on the row below.
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1; j--) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
            }
            dp[0] = dp[0] + triangle.get(i).get(0);
        }
        int mintotal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            mintotal = Math.min(mintotal, dp[i]);
        return mintotal;
    }

    /**
     * Shortest Unsorted Continuous Subarray
     *
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE, n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return l > r ? 0 : r - l + 1;
    }

    /**
     * Best Time to Buy and Sell Stock
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    /**
     * Given a 2D board containing 'X' and 'O' (the letter O),
     * capture all regions surrounded by 'X'.
     *
     * @param board
     */
    public static void solve(char[][] board) {
        if (board.length < 3 || board[0].length < 3)
            return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            bfs(board, i, 0);
            bfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            bfs(board, 0, j);
            bfs(board, m - 1, j);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    public static void bfs(char[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O')
            return;
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(i, j));
        board[i][j] = '#';
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            int x = pos.x;
            int y = pos.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && board[nx][ny] == 'O') {
                    board[nx][ny] = '#';
                    queue.offer(new Position(nx, ny));
                }
            }
        }
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Position o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return (x == o.x) && (y == o.y);
        }
    }

    /**
     * 数组中最长的连续序列的长度
     * Given an unsorted array of integers,
     * find the length of the longest consecutive elements sequence
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        int res = 0;
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
                int prev = num - 1, next = num + 1;
                while (set.contains(next)) {
                    set.remove(next);
                    next++;
                }
                while (set.contains(prev)) {
                    set.remove(prev);
                    prev--;
                }
                res = Math.max(res, next - prev - 1);
            }
        }
        return res;
    }

    /**
     * 最长连续递增子序列
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length, maxLen = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1])
                dp[i] = dp[i - 1] + 1;
            else dp[i] = 1;
        }
        for (int i = 0; i < n; i++)
            maxLen = Math.max(maxLen, dp[i]);
        return maxLen;
    }

    public static int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();
        List<Gridvalue> gv = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                gv.add(new Gridvalue(i, j, forest.get(i).get(j)));
            }
        }
        Collections.sort(gv);
        List<Position> targetpos = new ArrayList<>();
        for (Gridvalue gridvalue : gv)
            if (gridvalue.val > 1)
                targetpos.add(new Position(gridvalue.x, gridvalue.y));
        Position startpos = new Position(0, 0);
        int step = 0;
        for (int i = 0; i < targetpos.size(); i++) {
            System.out.println(i);
            int stepnow = bfs(startpos, targetpos.get(i), forest);
            if (stepnow == -1)
                return -1;
            step += stepnow;
            startpos.x = targetpos.get(i).x;
            startpos.y = targetpos.get(i).y;
        }
        return step;
    }

    public static int bfs(Position source, Position target, List<List<Integer>> forest) {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int m = forest.size();
        int n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        visited[source.x][source.y] = true;
        Queue<Position> queue = new LinkedList<>();
        int res = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            boolean canTravel = false;
            for (int i = 0; i < qsize; i++) {
                Position pos = queue.poll();
                int x = pos.x;
                int y = pos.y;
                if (x == target.x && y == target.y)
                    return res;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n && forest.get(nx).get(ny) != 0 && !visited[nx][ny]) {
                        canTravel = true;
                        visited[nx][ny] = true;
                        queue.offer(new Position(nx, ny));
                    }
                }
            }
            if (canTravel == false)
                return -1;
            res++;
        }
        return res;
    }

    static class Gridvalue implements Comparable<Gridvalue> {
        int x;
        int y;
        int val;

        public Gridvalue(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public int compareTo(Gridvalue o) {
            // TODO Auto-generated method stub
            return this.val - o.val;
        }
    }

    /**
     * Suppose an array sorted in ascending order is rotated
     * at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * Find the minimum element.
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int low = 0, high = nums.length - 1, target = nums[nums.length - 1];
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            if (nums[mid] <= nums[nums.length - 1])
                high = mid;
            else low = mid;
        }
        return Math.min(nums[low], nums[high]);
    }

    /**
     * 可能包含重复元素
     *
     * @param nums
     * @return
     */
    public static int findMinII(int[] nums) {
        int low = 0, high = nums.length - 1, target = nums[nums.length - 1];
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[nums.length - 1])
                high = mid;
            else if (nums[mid] > nums[nums.length - 1])
                low = mid;
            else {
                int minnum = Integer.MAX_VALUE;
                for (int i = low; i <= high; i++)
                    minnum = Math.min(minnum, nums[i]);
                return minnum;
            }
        }
        return Math.min(nums[low], nums[high]);
    }

    /**
     * Given an array of n positive integers and a positive integer s,
     * find the minimal length of a contiguous subarray of which the sum ≥ s.
     * If there isn't one, return 0 instead.
     * For example, given the array [2,3,1,2,4,3] and s = 7,
     * the subarray [4,3] has the minimal length under the problem constraint.
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0, sum = 0, minLen = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= s) {
                while (sum >= s) {
                    minLen = Math.min(minLen, i - start + 1);
                    sum -= nums[start];
                    start++;
                }
            }
        }
        return minLen == nums.length + 1 ? 0 : minLen;
    }

    public static List<List<Integer>> getPermute(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helpgetPermute(res, cur, n);
        return res;
    }

    public static void helpgetPermute(List<List<Integer>> res, List<Integer> cur, int n) {
        if (cur.size() == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i <= 2; i++) {
            cur.add(i);
            helpgetPermute(res, cur, n);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * Course Schedule
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        int len = prerequisites.length;
        if (numCourses == 0 || len == 0)
            return true;
        int[] visited = new int[numCourses];
        for (int[] a : prerequisites) {
            int u = a[1], v = a[0];
            if (graph.containsKey(u))
                graph.get(u).add(v);
            else {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(v);
                graph.put(u, l);
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i))
                return false;
        }
        return true;
    }

    public static boolean dfs(HashMap<Integer, ArrayList<Integer>> graph, int[] visited, int u) {
        if (visited[u] == 1)//三种标志，0表示未访问，1表示在本次dfs已访问
            return false;//-1表示在之前的dfs里面已经访问过，且该节点不会出现环
        if (visited[u] == -1)//设置-1是为了避免对相同的节点重复遍历，否则会TLE
            return true;
        visited[u] = 1;
        if (graph.containsKey(u)) {
            for (int v : graph.get(u)) {
                if (!dfs(graph, visited, v))
                    return false;
            }
        }
        visited[u] = -1;
        return true;
    }

    /**
     * 有向图拓扑排序
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] countCourse = new int[numCourses];
        for (int[] a : prerequisites)
            countCourse[a[0]]++;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (countCourse[i] == 0)
                queue.add(i);
        int checked = queue.size(), i = 0;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            int c = queue.poll();
            res[i++] = c;
            for (int[] a : prerequisites) {
                if (a[1] == c) {
                    countCourse[a[0]]--;
                    if (countCourse[a[0]] == 0) {
                        queue.offer(a[0]);
                        checked++;
                    }
                }
            }
        }
        if (checked == numCourses)
            return res;
        else return new int[0];
    }

    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return findKthLargest(nums, n + 1 - k, 0, nums.length - 1);
    }

    public static int findKthLargest(int[] nums, int k, int p, int r) {
        int q = partition(nums, p, r);
        int orderq = q - p + 1;
        if (orderq == k)
            return nums[q];
        else if (k < orderq)
            return findKthLargest(nums, k, p, q - 1);
        else return findKthLargest(nums, k - orderq, q + 1, r);
    }

    public static int partition(int[] a, int p, int r) {
        int i = p - 1, x = a[r];
        for (int j = p; j < r; j++) {
            if (a[j] <= x) {
                i++;
                exchange(a, i, j);
            }
        }
        exchange(a, i + 1, r);
        return i + 1;
    }

    /**
     * Baseball Game
     *
     * @param ops
     * @return
     */
    public static int calPoints(String[] ops) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (String s : ops) {
            if (s.equals("D")) {
                int now = stack.peek() * 2;
                sum += now;
                stack.push(now);
            } else if (s.equals("C")) {
                int last = stack.pop();
                sum -= last;
            } else if (s.equals("+")) {
                int last = stack.pop();
                int now = last + stack.peek();
                stack.push(last);
                stack.push(now);
                sum += now;
            } else {
                int point = Integer.valueOf(s);
                stack.push(point);
                sum += point;
            }
        }
        return sum;
    }

    /**
     * Contains Duplicate III
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length < 2 || t < 0 || k < 1)
            return false;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            Integer f = set.floor(n);
            Integer c = set.ceiling(n);
            if ((f != null && f + t >= n) || (c != null && c <= n + t))
                return true;
            set.add(n);
            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }

    /**
     * Given a 2D binary matrix filled with 0's and 1's,
     * find the largest square containing only 1's and return its area.
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxArea = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    int area = dp[i][j] * dp[i][j];
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    /**
     * For example, given [1,2,3,4], return [24,12,8,6].
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int p = 1, n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = p;
            p *= nums[i];
        }
        p = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= p;
            p *= nums[i];
        }
        return res;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        if (m == 0)
            return 0;
        int n = grid[0].length;
        if (n == 0)
            return 0;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    List<Integer> l = new ArrayList<>();
                    l.add(0);
                    dfs(grid, i, j, l);
                    maxArea = Math.max(maxArea, l.get(0));
                }
            }
        }
        return maxArea;
    }

    private void dfs(int[][] grid, int i, int j, List<Integer> l) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return;
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            l.set(0, l.get(0) + 1);
            dfs(grid, i - 1, j, l);
            dfs(grid, i + 1, j, l);
            dfs(grid, i, j - 1, l);
            dfs(grid, i, j + 1, l);
        }
    }

    /**
     * Count of Smaller Numbers After Self
     * Binary Search 版本
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        List<Integer> sorted = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            res.add(index);
            sorted.add(index, nums[i]);
        }
        Collections.reverse(res);
        return (res);
    }

    public static int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0)
            return 0;
        int low = 0, high = sorted.size() - 1;
        if (target <= sorted.get(low))
            return 0;
        if (target > sorted.get(high))
            return high + 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (sorted.get(mid) >= target)
                high = mid;
            else low = mid;
        }
        if (sorted.get(low) >= target)
            return low;
        else return high;
    }

    /**
     * Longest Increasing Path in a Matrix
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int maxlen = 0;
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                maxlen = Math.max(maxlen, dfs(matrix, dp, i, j));
        }
        return maxlen;
    }

    private int dfs(int[][] matrix, int[][] dp, int x, int y) {
        if (dp[x][y] != 0)
            return dp[x][y];
        int path = 1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n || matrix[a][b] <= matrix[x][y])
                continue;
            path = Math.max(path, 1 + dfs(matrix, dp, a, b));
        }
        dp[x][y] = path;
        return path;
    }

    /**
     * Degree of an Array
     *
     * @param nums
     * @return
     */
    public static int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        int maxcount = 0;
        for (int num : nums)
            maxcount = Math.max(maxcount, map.get(num));
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (left.containsKey(nums[i]))
                continue;
            else if (map.get(nums[i]) == maxcount)
                left.put(nums[i], i);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (right.containsKey(nums[i]))
                continue;
            else if (map.get(nums[i]) == maxcount)
                right.put(nums[i], i);
        }
        int res = nums.length;
        for (int num : nums) {
            if (map.get(num) == maxcount)
                res = Math.min(res, right.get(num) - left.get(num) + 1);
        }
        return res;
    }

    /**
     * Give a string s, count the number of non-empty (contiguous) substrings
     * that have the same number of 0's and 1's,
     * and all the 0's and all the 1's in these substrings are grouped consecutively.
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        int curlen = 1, prelen = 0, res = 0;
        char curval = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == curval)
                curlen++;
            else {
                prelen = curlen;
                curlen = 1;
                curval = s.charAt(i);
            }
            if (prelen >= curlen)
                res++;
        }
        return res;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //System.out.println(res);
        //System.out.println(Arrays.toString(a));
        //System.out.println(removeDuplicates(a));
        //System.out.println(Arrays.toString(a));
        char[][] board = {
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        };
        int[][] matrix = {{0, 1},
                {1, 2},
                {2, 3},
                {4, 5},
                {5, 4}};
        int[] nums = {};
        System.out.println(findShortestSubArray(nums));

    }

}
