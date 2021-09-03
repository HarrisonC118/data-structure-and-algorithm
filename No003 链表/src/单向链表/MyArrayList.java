package 单向链表;

public class MyArrayList<E> extends AbstractList<E> {
    /**
     * size: 元素的数量
     * elements：要存放数据的数组
     */
    private E[] elements;
    /**
     * 定义默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 构造函数 根据传入的参数初始化数组大小
     * @param capacity 需要初始化的容量
     */
    public MyArrayList(int capacity) {
        // 判断传入的值是否合法，如果小于默认初始化容量的大小，则以默认容量为主
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 抛出一个越界异常
     * @param index
     */
    private void outOfBoundsException(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
    }

    /**
     * 删除对应下标的数据
     * @param index 要删除的下表
     * @return 被删除元素的值
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements [i];
        }
        // 把最后一个值清空
        elements[--size] = null;
        trim();
        return old;
    }
    /**
     * 缩容
     */
    public void trim() {
        int capacity = elements.length;
        // 剩余空间小于一半就不缩容
        if (size >= (capacity >> 1) || capacity <= DEFAULT_CAPACITY) {
            return;
        }
        // 缩容
        int newCapacity = capacity >> 1;
        E[] newElements = (E[]) new Object[newCapacity];
        if (size >= 0) {
            System.arraycopy(elements, 0, newElements, 0, size);
        }
        elements = newElements;
    }


    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
    /**
     * 清空只需要把size设置成0就可以了，因为get和add都会判断是否大于size,所以使用者没法获得比size大的元素
     * 当使用泛型来确定存入的类型时，用户可能会在数组中存对象，但是对象在数组中存的是对应的地址值，所以清除元素时应该是把对应的数组值设置为Null
     * jvm会把没有引用的对象当做垃圾清除掉
     */
        for (int i = 0; i < size; i++) {
          elements[i] = null;
        }
       size = 0;
    }

    /**
     * 往指定的下表位置放元素
     * @param index 指定的下标
     * @param element 要放的元素
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 扩容
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
          elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 保证数组至少有capacity个数量
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity) {
            return;
        }
        // >>是位运算，效率高。左移1是乘2，右移1是除2
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        if (size >= 0) {
            System.arraycopy(elements, 0, newElements, 0, size);
        }
        elements = newElements;
    }

    /**
     * 根据下表返回对应的元素
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 把对应下标的元素替换成别的元素，返回被替换的元素
     * @param index 下标
     * @param element 要替换成的元素
     * @return
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 查看元素对应的位置
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        if(element == null ) {
          for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
              return i;
            }
          }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("单项链表.MyArrayList{").append("size=").append(size).append(", elements=[");
        for (int i = 0; i < size; i++) {
            if(i != 0){
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[i]);
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}
