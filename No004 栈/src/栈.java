import java.util.ArrayList;

/**
 * @author: HatcherCheung
 * Date:  2021/9/3
 */
public class 栈<E> {
    private ArrayList<E> arrayList = new ArrayList<>();
    public int size() {
        return arrayList.size();
    }
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }
    public void push(E element) {
        arrayList.add(element);
    }
    public E pop() {
        return arrayList.remove( arrayList.size() - 1);
    }
    public E top() {
        return arrayList.get(arrayList.size() - 1);
    }
}
