package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author: HatcherCheung
 * Date:  2021/8/29
 */
public class _206_反转链表 {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        // 递归
//        if(head == null || head.next == null) {
//            return head;
//        }
//        ListNode newHead = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//        ListNode listNode = head;
        // 非递归
        ListNode newList = new ListNode();
        while(head != null ){
            // 临时变量，存储head的next指向的Node
            ListNode tmp = head.next;
            head.next = newList;
            newList = head;
            head = tmp;
        }
        return newList;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
