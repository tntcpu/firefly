package com.tntcpu.leetcode.easy;

import com.tntcpu.leetcode.utils.ListNode;

public class AddTwoNumbers_2_Best {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        ListNode listNode1 = addTwoNumbers(l1, l2);

        l1.next = new ListNode(4);
        l2.next = new ListNode(6);
        ListNode listNode2 = addTwoNumbers(l1, l2);

        l1.next = new ListNode(3);
        l2.next = new ListNode(4);
        ListNode listNode3 = addTwoNumbers(l1, l2);


        System.out.println(listNode1.val);
        System.out.println(listNode2.val);
        System.out.println(listNode3.val);
//        int val = listNode.val;
//        int
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
