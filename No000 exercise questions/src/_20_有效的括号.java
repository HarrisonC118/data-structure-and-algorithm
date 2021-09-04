import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/submissions/
 * @author: HatcherCheung
 * Date:  2021/9/3
 */
public class _20_有效的括号 {
    private static HashMap<Character,Character> hashMap= new HashMap<>();
    static {
        hashMap.put('(', ')');
        hashMap.put('[', ']');
        hashMap.put('{', '}');
    }
    public boolean isValid(String s) {
        // 使用栈这个数据结构，后入先出
        Stack<Character> stack = new Stack();
        /**
         * len: 输入字符串的长度
         */
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // 从左到右依次筛选字符是不是左括号
            char c = s.charAt(i);
            if (hashMap.containsKey(c)) {
                // 按照从左到右的顺序依次把左括号入栈
                stack.push(c);
            } else {
                // 如果是右括号，就开始检查有没有左括号匹配
                // 有右括号但是栈是空的，就证不匹配
                if (stack.isEmpty()) {
                    return false;
                }
                // 如果从栈弹出的左括号对应的右括号与 c相等，就继续，否则直接返回false
                if (hashMap.get(stack.pop()) != c) {
                    return false;
                }
            }
        }
        // 如果把字符串遍历完，栈是空的(左括号已经全部被匹配上了)，就证明所有的括号都是有效的
        return stack.isEmpty();
    }
}
