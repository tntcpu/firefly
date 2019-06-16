package com.tntcpu.leetcode.medium;

import com.tntcpu.leetcode.utils.ListNode;

public class AddTwoNumbers_2_Self_Imitate {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);

    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode q = l1, p = l2, curr = result;
        int carray = 0;
        while (q != null || p != null) {
            int a = q == null ? 0 : q.val;
            int b = p == null ? 0 : p.val;
            int sum = a + b + carray;
            carray = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (q != null){
                q = q.next;
            }
            if (p != null){
                p = p.next;
            }
        }

        if (carray > 0) {
            curr.next = new ListNode(carray);
        }

        return result.next;
    }
}
