import java.util.Stack;

/**
 * @author: HatcherCheung
 * Date:  2021/9/3
 */
public class MyMainForStack {
    public static void main(String[] args) {
        栈<Integer> stack = new 栈<Integer>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        stack.push(55);
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
