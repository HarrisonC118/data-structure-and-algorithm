package 双向链表;

import 单项链表.AbstractList;

/**
 * @author: HatcherCheung
 * Date:  2021/8/28
 */
public class MyLinkedList<E> extends AbstractList<E> {
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
            Node<E> newNode;
            if (size == 0) {
                newNode = new Node<>(null, element, null);
                first = newNode;
            } else {
                // 如果原来的链表不是null，那就让newNode的next是null, newNode的prev指向以前的last，再更新last为newNode
                newNode = new Node<>(last, element, null);
                // 以前last节点的next指向新的newNode
                last.next = newNode;
            }
            last = newNode;

        }
        // 1. 先获取添加节点位置的前一个节点，把前一个节点next的值指向要添加的节点
        // 2. 把要添加的节点的next值设置为替换前这个节点的地址
        // 3. 如果是0，就把新节点的next的值换成first的值,然后把first这个头结点的值设置为这个新节点的地址
        // node是当前位置原来的节点
        Node<E> node = node(index);
        // 先把新节点的上一个节点和下一个节点定好
        Node<E> newNode = new Node<E>(node.prev, element, node);
        // 更改上一个节点的next和下一个节点的prev，都指向新的node
        node.prev = newNode;
        // 如果要在第一个节点处添加节点，那prev就是Null
        if( node.prev == null) {
            // 要把新节点作为第一个节点插进去，只需要让first指向newNode就可以了
            first = newNode;
        } else {
            node.prev.next = newNode;
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
        if(index == 0) {
            first = first.next;
        } else {
            Node<E> prevNode = node(index - 1);
            node = prevNode.next;
            prevNode.next = node.next;
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
