package 链表;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author: HatcherCheung
 * Date:  2021/8/29
 */
public class _141_环形链表 {

    public boolean hasCycle(ListNode head) {
        // 如果传入的是Null或者只有一个节点，就返回false
        if(head == null || head.next == null ){
            return false;
        }
        // 到这里表示head和head.next就不可能是null了
        ListNode slowPoint = head;
        ListNode fastPoint = head.next;
        // 如果fastPoint为空或者fastPoint.next为空，就证明已经遍历完了
        while (fastPoint != null && fastPoint.next != null) {
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
            if(fastPoint == slowPoint) {
                return true;
            }
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
