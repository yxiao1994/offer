package LeetCode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Codec {
    private static final String sp = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helpserialize(root, sb);
        return sb.toString();
    }

    private void helpserialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NN).append(sp);
            return;
        }
        sb.append(String.valueOf(root.val)).append(sp);
        helpserialize(root.left, sb);
        helpserialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(sp)));
        return (bulidTree(queue));
    }

    public TreeNode bulidTree(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals(NN))
            return null;
        else {
            TreeNode root = new TreeNode(Integer.valueOf(s));
            root.left = bulidTree(queue);
            root.right = bulidTree(queue);
            return root;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

