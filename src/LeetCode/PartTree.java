package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.management.RuntimeErrorException;

public class PartTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 二叉树中序遍历递归版本
     *
     * @param root
     */
    public static void InorderTraversal(TreeNode root) {
        if (root != null) {
            InorderTraversal(root.left);
            System.out.print(root.val + " ");
            InorderTraversal(root.right);
        }
    }

    /**
     * 二叉树先序遍历递归版本
     *
     * @param root
     */
    public static void PreorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            PreorderTraversal(root.left);
            PreorderTraversal(root.right);
        }
    }

    /**
     * 二叉树中序遍历迭代版本
     *
     * @param root
     */
    public static void InorderItreation(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
        System.out.println();
    }

    /**
     * 二叉树先序遍历迭代版本
     *
     * @param root
     */
    public static void PreorderItreation(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                System.out.print(root.val + " ");
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        System.out.println();
    }

    /**
     * 二叉树后序遍历迭代版本
     *
     * @param root
     */
    public static List<Integer> postOrderIteration(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, Boolean> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            if (temp.left != null && !map.containsKey(temp.left)) {
                temp = temp.left;
                while (temp != null && !map.containsKey(temp)) {
                    stack.push(temp);
                    temp = temp.left;
                }
                continue;
            }
            if (temp.right != null && !map.containsKey(temp.right)) {
                stack.push(temp.right);
                continue;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            map.put(node, true);
        }
        return res;
    }

    /**
     * 根据先序和中序结果重建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length < 1)
            return null;
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd)
            return null;
        int val = preorder[preStart];
        int index = inStart;
        while (index <= inEnd && inorder[index] != val)
            index++;
        if (index > inEnd)
            throw new RuntimeException("Invalid input");
        TreeNode root = new TreeNode(val);
        int leftLen = index - inStart;
        root.left = construct(preorder, preStart + 1, preStart + leftLen, inorder, inStart, index - 1);
        root.right = construct(preorder, preStart + leftLen + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 找出所有路径和等于sum的所有路径
     *
     * @param root
     * @param sum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        List<Integer> path = new ArrayList<>();
        helpPathSum(res, path, root, sum);
        return res;
    }

    public static void helpPathSum(List<List<Integer>> res, List<Integer> path, TreeNode root, int sum) {
        if (root == null)
            return;
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(path));
            //return;
        }
        helpPathSum(res, path, root.left, sum - root.val);
        helpPathSum(res, path, root.right, sum - root.val);
        path.remove(path.size() - 1);
    }

    /**
     * 二叉树层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> now = new ArrayList<>();
            int qsize = queue.size();//必须先获取队列大小
            for (int i = 0; i < qsize; i++) {
                TreeNode node = queue.poll();
                now.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(now);
        }
        return res;
    }

    /**
     * 判断二叉树是否平衡
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int depthdiff = depth(root.left) - depth(root.right);
        if (Math.abs(depthdiff) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 树的深度
     *
     * @param root
     * @return
     */
    public static int depth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    /**
     * 根节点到叶节点的最短路径
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 判断两颗二叉树是否一致
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 根据已排序数组建立二叉搜索树
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return buildBst(nums, 0, nums.length - 1);
    }

    public static TreeNode buildBst(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBst(nums, start, mid - 1);
        root.right = buildBst(nums, mid + 1, end);
        return root;
    }

    /**
     * 二叉树左右反转
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        return helpsumNumbers(root, 0);
    }

    public static int helpsumNumbers(TreeNode root, int prev) {
        if (root == null)
            return 0;
        int sum = 10 * prev + root.val;
        if (root.left == null && root.right == null)
            return sum;
        return helpsumNumbers(root.left, sum) + helpsumNumbers(root.right, sum);
    }

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return helpValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean helpValid(TreeNode root, long low, long high) {
        if (root == null)
            return true;
        if (root.val >= high || root.val <= low)
            return false;
        return helpValid(root.left, low, root.val) && helpValid(root.right, root.val, high);
    }

    /**
     * Given a binary tree, check whether it is a mirror of itself
     * (ie, symmetric around its center).
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return helpSymmetric(root.left, root.right);
    }

    public boolean helpSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return t1.val == t2.val && helpSymmetric(t1.left, t2.right) && helpSymmetric(t1.right, t2.left);
    }

    /**
     * 根据中序和后序重建二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public static TreeNode buildTreeII(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length || inorder.length == 0)
            return null;
        return helpbuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static TreeNode helpbuildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (is > ie)
            return null;
        int val = postorder[pe];
        int index = is;
        while (index <= ie && inorder[index] != val)
            index++;
        if (index > ie)
            throw new RuntimeException();
        TreeNode root = new TreeNode(val);
        int leftLen = index - is;
        root.left = helpbuildTree(inorder, is, index - 1, postorder, ps, ps + leftLen - 1);
        root.right = helpbuildTree(inorder, index + 1, ie, postorder, ps + leftLen, pe - 1);
        return root;
    }

    /**
     * Given a binary tree, flatten it to a linked list in-place.
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null)
            flatten(root.left);
        if (root.right != null)
            flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null)
            root = root.right;
        root.right = temp;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(Integer.MIN_VALUE);
        helper(root, res);
        return res.get(0);
    }

    private int helper(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return 0;
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        int cur = root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0);
        if (cur > res.get(0))
            res.set(0, cur);
        return root.val + Math.max(left, Math.max(right, 0));
    }

    /**
     * Binary Tree Right Side View
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                TreeNode node = queue.poll();
                if (i == qsize - 1)
                    res.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return res;
    }

    /**
     * 完全二叉树节点个数
     *
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int ld = 0, rd = 0;
        TreeNode ln = root, rn = root;
        while (ln != null) {
            ld++;
            ln = ln.left;
        }
        while (rn != null) {
            rd++;
            rn = rn.right;
        }
        if (ld == rd)
            return (1 << ld) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        return (left != null) ? left : right;
    }

    /**
     * 二叉搜索树最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        boolean f1 = (p.val < root.val);
        boolean f2 = (q.val < root.val);
        if ((f1 && !f2) || (f2 && !f1))
            return root;
        else if (f1 && f2)
            return lowestCommonAncestorBST(root.left, p, q);
        else return lowestCommonAncestorBST(root.right, p, q);
    }

    static int maxpath = 0;

    /**
     * Given a binary tree,
     * find the length of the longest path
     * where each node in the path has the same value.
     *
     * @param root
     * @return
     */
    public static int longestUnivaluePath(TreeNode root) {
        help(root);
        return maxpath;
    }

    public static int help(TreeNode root) {
        if (root == null)
            return 0;
        int leftmax = help(root.left);
        int rightmax = help(root.right);
        int res = 0, sum = 0;
        if (root.left != null && root.val == root.left.val) {
            res = Math.max(res, leftmax + 1);
            sum += leftmax + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            res = Math.max(res, rightmax + 1);
            sum += rightmax + 1;
        }
        maxpath = Math.max(maxpath, sum);
        return res;
    }

    /**
     * Given a binary tree, return all root-to-leaf paths.
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        helpdfs(root, String.valueOf(root.val), res);
        return res;
    }

    public void helpdfs(TreeNode root, String sb, List<String> res) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            res.add(sb);
            return;
        }
        if (root.left != null)
            helpdfs(root.left, sb + "->" + String.valueOf(root.left.val), res);
        if (root.right != null)
            helpdfs(root.right, sb + "->" + String.valueOf(root.right.val), res);
    }

    /**
     * Verify Preorder Serialization of a Binary Tree
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] strs = preorder.split(",");
        for (String str : strs) {
            stack.push(str);
            while (stack.size() > 2 && stack.get(stack.size() - 1).equals("#")
                    && stack.get(stack.size() - 2).equals("#") && !stack.get(stack.size() - 3).equals("#")) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }
        return stack.size() == 1 && stack.get(0).equals("#");
    }

    /**
     * House Robber III
     */
    HashMap<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        if (map.containsKey(root))
            return map.get(root);
        if (root == null)
            return 0;
        int leftmax = rob(root.left);
        int rightmax = rob(root.right);
        int rootmax = root.val;
        if (root.left != null)
            rootmax += (rob(root.left.left) + rob(root.left.right));
        if (root.right != null)
            rootmax += (rob(root.right.left) + rob(root.right.right));
        int maxmoney = Math.max(rootmax, leftmax + rightmax);
        map.put(root, maxmoney);
        return maxmoney;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] preorder = {1, 4, 4, 4, 5, 5};
        int[] inorder = {4, 4, 4, 1, 5, 5};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(longestUnivaluePath(root));
    }

}
