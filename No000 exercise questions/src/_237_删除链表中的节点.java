/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author: HatcherCheung
 * Date:  2021/8/28
 */
public class _237_删除链表中的节点 {

    // 思路：用当前节点的下一个节点的val替换掉当前节点的val,再把当前节点的next修改为下一个节点的next。
    // 等于是先覆盖值(val和next)，再删除当前节点的下一个节点

    public static void main(String[] args) {

    }
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
