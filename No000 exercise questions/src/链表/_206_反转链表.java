package 链表;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author: HatcherCheung
 * Date:  2021/8/29
 */
public class _206_反转链表 {
    public static void main(String[] args) {

    }
    public ListNode reverseList2(ListNode head) {
        // 递归
        if(head == null || head.next == null) {
            // head.next == null 就表示已经分解成了最小子问题，因为只有一个节点了
            return head;
        }
        // 递
        ListNode newHead = reverseList2(head.next);
        // 归
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList1(ListNode head) {
        // 非递归
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            // 当curr不是null的情况下才能有next，不然会空指针异常
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // 因为curr已经是null了，所以返回prev
        return prev;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
