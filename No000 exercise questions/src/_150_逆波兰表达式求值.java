import java.util.Objects;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * @author: HatcherCheung
 * Date:  2021/9/4
 */
public class _150_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int len = tokens.length;
        /**
         * prev和last用来保存先后出栈的数字，除法和减法会用到
         */
        int prev = 0;
        int last = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    prev = stack.pop();
                    last = stack.pop();
                    stack.push(last - prev);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    prev = stack.pop();
                    last = stack.pop();
                    stack.push(last / prev);
                    break;
                default:
                    // 如果不是符号，那就说明是数字。把数字入栈
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
