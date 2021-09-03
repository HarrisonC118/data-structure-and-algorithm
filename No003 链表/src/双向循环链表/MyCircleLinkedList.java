package 双向循环链表;

import 单向链表.AbstractList;

/**
 * @author: HatcherCheung
 * Date:  2021/9/2
 */
public class MyCircleLinkedList<E> extends AbstractList<E> {
    /**
     * first是头结点
     * last是尾结点
     */
    private Node<E> first;
    private Node<E> last;
    /**
     * 节点类
     * @param <E>
     */
    private static class Node<E> {
        private E element; // 当前节点的数值
        private Node<E> prev; // 存放上一个节点的地址
        private Node<E> next; // 存放了下一个节点的地址
        public Node(Node<E> prev, E element,Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * 获取对应节点的信息
     * @param index 节点的索引
     * @return 对应索引的值
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 替换节点的信息
     * @param index 想要替换的索引值
     * @param element 要替换成什么
     * @return 替换前这个节点存放的信息
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        // 获取当前位置的节点
        Node<E> node = node(index);
        E oldNodeElement = node.element;
        node.element = element;
        return oldNodeElement;
    }

    /**
     * 添加一个节点
     * @param index 这个节点要存放的位置
     * @param element 这个节点要存放的内容
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            // 往最后添加元素
            // 特殊情况：当链表是空的时候，first和last都是null
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, null);
            if (oldLast == null) {
                // 链表添加第一个元素
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<E>(prev, element, first);
            next.prev = node;
            prev.next = node;
            if (next == first) {
                first = node;
            }
        }
        size ++;
    }

    /**
     *
     * @param index 下标
     * @return 被删除节点的信息
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (node == first) { // index == 0
                first = next;
            }

            if (node == last) { // index == size - 1
                last = prev;
            }
        }

        size--;
        return node.element;
    }

    /**
     * 返回指定元素内容的下标
     * @param element 要查找下标的内容
     * @return 下标
     */
    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if(node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (node.element == element) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取指定索引的节点
     * @param index 节点索引
     * @return 索引对应的节点
     */
    private Node<E> node(int index) {
        // 查看索引是否有效
        rangeCheck(index);
        Node<E> node = first;
        if (index < (size >> 1)) {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    /**
     * 打印全部节点内容
     * @return
     */
    @Override
    public String toString() {
        Node<E> node = first;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("单项链表.MyArrayList{").append("size=").append(size).append(", elements=[");
        for (int i = 0; i < size; i++) {
            if(i != 0){
                stringBuilder.append(", ");
            }
            stringBuilder.append(node.element);
            node = node.next;
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}
