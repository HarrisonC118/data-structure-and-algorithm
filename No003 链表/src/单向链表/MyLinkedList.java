package 单向链表;

/**
 * @author: HatcherCheung
 * Date:  2021/8/28
 */
public class MyLinkedList<E> extends AbstractList<E> {
    private Node<E> first;

    @Override
    public void clear() {
        size = 0;
        first = null;
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
        // 1. 先获取添加节点位置的前一个节点，把前一个节点next的值指向要添加的节点
        // 2. 把要添加的节点的next值设置为替换前这个节点的地址
        // 3. 如果是0，就把新节点的next的值换成first的值,然后把first这个头结点的值设置为这个新节点的地址
        rangeCheckForAdd(index);
        if (index == 0){
            first = new Node<>(element, first);
        } else {
            Node<E> preNode = node(index - 1);
            preNode.next = new Node<>(element,preNode.next);
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
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 节点类
     * @param <E>
     */
    private static class Node<E> {
        private E element; // 当前节点的数值
        private Node<E> next; // 存放了下一个节点的地址
        public Node(E element,Node<E> next) {
            this.element = element;
            this.next = next;
        }
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
