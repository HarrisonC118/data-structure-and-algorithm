import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.Arrays;

public class MyArrayList<E> {
    /**
     * size: 元素的数量
     * elements：要存放数据的数组
     */
    private int size;
    private E[] elements;
    /**
     * 定义默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    /**
     * 构造函数 根据传入的参数初始化数组大小
     * @param capacity 需要初始化的容量
     */
    public MyArrayList(int capacity) {
        // 判断传入的值是否合法，如果小于默认初始化容量的大小，则以默认容量为主
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
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
     * 检查输入的值是否越界
     * @param index
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size ) {
            outOfBoundsException(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size ) {
            outOfBoundsException(index);
        }
    }

    /**
     * 获得动态数组的大小
     * @return 数组的大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断动态数组是不是空的
     * @return 是空的或者不是空的
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 删除对应下标的数据
     * @param index 要删除的下表
     * @return 被删除元素的值
     */
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements [i];
        }
        // 把最后一个值清空
        elements[--size] = null;
        return old;
    }

    /**
     * 判断这个动态数组包不包含这个元素
     * @param element
     * @return
     */
    public boolean contains (E element) {
        return (indexOf(element) != ELEMENT_NOT_FOUND);
    }

    /**
     * 清除所有元素
     */
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
     * 往动态数组中添加元素
     * @param element
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 往指定的下表位置放元素
     * @param index 指定的下标
     * @param element 要放的元素
     */
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
        for (int i = 0; i < size; i++) {
          newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 根据下表返回对应的元素
     * @param index
     * @return
     */
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
        stringBuilder.append("MyArrayList{").append("size=").append(size).append(", elements=[");
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
