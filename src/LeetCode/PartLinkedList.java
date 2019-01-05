package LeetCode;

import java.util.HashMap;
import java.util.List;

import LeetCode.PartTree.TreeNode;

public class PartLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 用一个数组创建链表
     *
     * @param arr 输入的数组
     * @return 返回的链表
     */
    public static ListNode CreateList(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        head.next = null;
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++, curr = curr.next)
            curr.next = new ListNode(arr[i]);
        return head;
    }

    /**
     * 遍历链表
     *
     * @param head 链表的头结点
     */
    public static void printlist(ListNode head) {
        if (head == null)
            System.out.println("Empty list!");
        for (; head != null; head = head.next)
            System.out.print(head.val + "\t");
        System.out.println();
        //System.out.println("List Traverse Completed!");
    }

    /**
     * 返回链表中倒数第k个节点
     *
     * @param k 参数k
     * @return 倒数第k个节点
     */
    public static ListNode FindkthToTail(ListNode head, int k) {
        if (k <= 0 || head == null)
            return null;//注意此处一定要判断
        ListNode fast = head;//快指针
        ListNode slow = head;//慢指针
        for (int i = 0; i < k - 1; i++) {
            if (fast.next == null)
                return null;//此处判断链表节点个数是否小于k
            else fast = fast.next;
        }
        while (fast.next != null) {//此处不能写成first！=null
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归版本反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseListRecurvely(ListNode head) {
        if (head == null || head.next == null)
            return head;
        else {
            ListNode newNode = reverseListRecurvely(head.next);
            head.next.next = head;
            head.next = null;
            return newNode;
        }
    }

    /**
     * Reverse a linked list from position m to n
     *
     * @param head
     * @param m    start position
     * @param n    end position
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m >= n)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        for (int i = 1; i < m; i++) {
            if (first.next == null)
                return head;
            else first = first.next;
        }
        ListNode curr = first.next;
        ListNode prev = null;
        for (int i = m; i <= n; i++) {
            if (curr != null) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            } else break;
        }
        first.next.next = curr;
        first.next = prev;
        return dummy.next;
    }

    /**
     * swap every two adjacent nodes and return its head.
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) {
            ListNode node1 = head.next;
            ListNode node2 = node1.next;
            head.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            head = node1;
        }
        return dummy.next;
    }

    /**
     * group all odd nodes together followed by the even nodes.
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        ListNode old = head, even = head.next, evenhead = even;
        while (even != null && even.next != null) {
            old.next = even.next;
            old = old.next;
            even.next = old.next;
            even = even.next;
        }
        old.next = evenhead;
        return head;
    }

    /**
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    public static boolean isCycled(ListNode head) {
        if (head == null)
            return false;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    /**
     * 找到环入口
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }
        if (fast.next == null || fast.next.next == null)
            return null;
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 对链表归并排序
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode medium = findmedium(head);
        ListNode midnext = medium.next;
        medium.next = null;//从中间将链表切成两半
        ListNode L1 = sortList(head);
        ListNode L2 = sortList(midnext);
        ListNode res = mergeTwoLists(L1, L2);
        return res;
    }

    /**
     * 将两个排序链表合并
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode res;
        if (l1.val < l2.val) {
            res = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            res = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode curr = res;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null)
            curr.next = l1;
        if (l2 != null)
            curr.next = l2;
        return res;
    }

    /**
     * 找到链表的中间节点
     *
     * @param head
     * @return
     */
    public static ListNode findmedium(ListNode head) {
        if (head == null)
            return null;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     * For example:Given 1->2->3->4->5->NULL and k = 2,return 4->5->1->2->3->NULL.
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (k < 0 || head == null)
            return null;
        k %= getlength(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode tail = dummy;
        for (int i = 0; i < k; i++)
            head = head.next;
        while (head.next != null) {
            head = head.next;
            tail = tail.next;
        }
        head.next = dummy.next;
        dummy.next = tail.next;
        tail.next = null;
        return dummy.next;
    }

    private static int getlength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * Given a sorted linked list, delete all nodes that have duplicate numbers,
     * leaving only distinct numbers from the original list.
     * For example,Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int value = head.next.val;
                while (head.next != null && head.next.val == value)
                    head.next = head.next.next;
            } else head = head.next;
        }
        return dummy.next;
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null)
            return null;
        ListNode leftdummy = new ListNode(0);
        ListNode rightdummy = new ListNode(0);
        ListNode left = leftdummy, right = rightdummy;
        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = left.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        right.next = null;
        left.next = rightdummy.next;
        return leftdummy.next;
    }

    /**
     * Convert Sorted List to Binary Search Tree
     *
     * @param head
     * @return
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        ListNode last = head, slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            last = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        fast = slow.next;
        last.next = null;
        if (head != slow)
            root.left = sortedListToBST(head);
        root.right = sortedListToBST(fast);
        return root;
    }

    /**
     * For example,Given {1,2,3,4,5,6}, reorder it to {1,6,2,5,3,4}.
     *
     * @param head
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode slow = findmiddle(head);
        ListNode fast = reverse(slow.next);
        printlist(fast);
        slow.next = null;
        slow = head;
        merge(slow, fast);
    }

    public static ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next;
    }

    public static ListNode findmiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        int i = 0;
        while (head1 != null && head2 != null) {
            if ((i & 1) == 0) {
                dummy.next = head1;
                head1 = head1.next;
            } else {
                dummy.next = head2;
                head2 = head2.next;
            }
            dummy = dummy.next;
            i++;
        }
        if (head1 != null)
            dummy.next = head1;
        if (head2 != null)
            dummy.next = head2;
    }

    /**
     * 链表插入排序
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = head;
        while (curr != null) {
            ListNode now = dummy;
            while (now.next != null && now.next.val < curr.val)
                now = now.next;
            ListNode pnext = curr.next;
            curr.next = now.next;
            now.next = curr;
            curr = pnext;
        }
        return dummy.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode curr = headA;
        while (curr.next != null)
            curr = curr.next;
        curr.next = headB;
        ListNode res = detectCycle(headA);
        curr.next = null;
        return res;
    }

    /**
     * Remove all elements from a linked list of integers that have value val.
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null) {
            if (curr.next.val == val)
                curr.next = curr.next.next;
            else curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * 判断链表是否是回文串
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode mid = findmid(head);
        ListNode right = reverseList(mid.next);
        mid.next = null;
        while (head != null && right != null) {
            if (head.val != right.val)
                return false;
            head = head.next;
            right = right.next;
        }
        return true;
    }

    public static ListNode findmid(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 验证s和t是否为变位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] cs = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cs[c - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            cs[c - 'a']--;
            if (cs[c - 'a'] < 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] a = {0, 1, 1, 0, 2};
        ListNode head = CreateList(a);
        System.out.println(isPalindrome(head));
    }

}
